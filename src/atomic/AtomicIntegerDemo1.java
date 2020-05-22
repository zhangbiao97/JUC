package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：演示AtomicInteger的基本用法，对比非原子类的线程安全难问题，使用了原子类
 * 之后，不需要加锁，也可以保证线程安全。
 * <p>
 * Create By ZhangBiao
 * 2020/5/22
 */
public class AtomicIntegerDemo1 implements Runnable {

    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    private static volatile int basicCount = 0;

    public void incrementAtomic() {
        atomicInteger.getAndIncrement();
    }

    public void incrementBasic() {
        basicCount++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 task = new AtomicIntegerDemo1();
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("原子类的结果：" + atomicInteger.get());
        System.out.println("普通变量的结果：" + basicCount);
    }

}
