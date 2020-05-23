package cas;

/**
 * 描述：模拟CAS操作，等价代码
 * <p>
 * Create By ZhangBiao
 * 2020/5/23
 */
public class TwoThreadsCompetition implements Runnable {

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadsCompetition task = new TwoThreadsCompetition();
        task.value = 0;
        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(task.value);
    }

}
