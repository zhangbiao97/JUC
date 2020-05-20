package threadlocal;

/**
 * Create By ZhangBiao
 * 2020/5/20
 */
public class ThreadLocalNPE {

    ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public void set() {
        threadLocal.set(Thread.currentThread().getId());
    }

    public long get() {
        return threadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        System.out.println(threadLocalNPE.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println(threadLocalNPE.get());
            }
        }).start();
    }

}
