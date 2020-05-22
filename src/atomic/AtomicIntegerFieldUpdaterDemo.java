package atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 描述：演示AtomicIntegerFieldUpdater的用法
 * <p>
 * Create By ZhangBiao
 * 2020/5/22
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable {

    static Candidate tom;

    static Candidate peter;

    public static AtomicIntegerFieldUpdater<Candidate> scoreUpdater =
            AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            peter.score++;
            scoreUpdater.getAndIncrement(tom);
        }
    }

    public static class Candidate {

        volatile int score;

    }

    public static void main(String[] args) {
        tom = new Candidate();
        peter = new Candidate();
        AtomicIntegerFieldUpdaterDemo task = new AtomicIntegerFieldUpdaterDemo();
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("普通变量：" + peter.score);
        System.out.println("升级后的变量：" + tom.score);
    }

}
