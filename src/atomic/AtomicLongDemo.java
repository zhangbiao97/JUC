package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 描述：演示高并发场景下，LongAdder比AtomicLong性能好。
 * <p>
 * Create By ZhangBiao
 * 2020/5/22
 */
public class AtomicLongDemo {

    public static void main(String[] args) {
        AtomicLong counter = new AtomicLong(0);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(new Task(counter));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        long endTime = System.currentTimeMillis();
        System.out.println(counter.get());
        System.out.println("AtomicLong耗时：" + (endTime - startTime) / 1000 + "s");
    }

    private static class Task implements Runnable {

        private AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        }
    }

}
