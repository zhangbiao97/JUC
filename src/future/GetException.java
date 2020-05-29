package future;

import java.util.concurrent.*;

/**
 * 描述：演示get方法过程中抛出异常，for循环为了演示抛出Exception的时机：
 * 并不是说一产生异常就抛出，知道我们get执行时，才会抛出。
 * <p>
 * Create By ZhangBiao
 * 2020/5/29
 */
public class GetException {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(20);
        Future<Integer> submit = service.submit(new CallableTask());
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            Thread.sleep(500);
        }
        System.out.println(submit.isDone());
        try {
            submit.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionException异常");
        }
    }

    static class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("Callable抛出异常");
        }

    }

}
