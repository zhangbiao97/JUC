package future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 描述：演示Future的使用方法，lambda形式
 * <p>
 * Create By ZhangBiao
 * 2020/5/29
 */
public class OneFutureLambda {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Callable<Integer> callableTask = () -> {
            Thread.sleep(3000);
            return new Random().nextInt();
        };
        Future<Integer> submit = service.submit(callableTask);
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }

}
