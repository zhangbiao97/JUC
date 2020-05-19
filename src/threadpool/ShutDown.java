package threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：演示关闭线程池
 * <p>
 * Create By ZhangBiao
 * 2020/5/19
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ShutDownTask());
        }
        Thread.sleep(1500);
        executorService.shutdown();
        executorService.execute(new ShutDownTask());
        //List<Runnable> runnables = executorService.shutdownNow();
        //System.out.println(executorService.isTerminated());
       /* System.out.println(executorService.isShutdown());
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
        Thread.sleep(10000);
        System.out.println(executorService.isTerminated());*/
    }

}

class ShutDownTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
        }
    }

}
