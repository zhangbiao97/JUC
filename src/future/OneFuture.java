package future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 描述：演示Future的使用方法
 * <p>
 * Create By ZhangBiao
 * 2020/5/29
 */
public class OneFuture {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> submit = service.submit(new CallableTask());
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }

    static class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }

    }

}
