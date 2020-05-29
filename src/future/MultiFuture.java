package future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 描述：演示批量提交任务时，用List来批量接收结果
 * <p>
 * Create By ZhangBiao
 * 2020/5/29
 */
public class MultiFuture {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(20);
        ArrayList<Future> futures = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Future<Integer> future = service.submit(new CallableTask());
            futures.add(future);
        }
        Thread.sleep(5000);
        for (int i = 0; i < 20; i++) {
            Future<Integer> future = futures.get(i);
            try {
                Integer result = future.get();
                System.out.println(result);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    static class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }

    }

}
