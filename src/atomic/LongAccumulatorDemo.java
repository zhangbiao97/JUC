package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * 描述：演示LongAccumulator的用法
 * <p>
 * Create By ZhangBiao
 * 2020/5/22
 */
public class LongAccumulatorDemo {

    public static void main(String[] args) {
        LongAccumulator accumulator = new LongAccumulator((x, y) -> x + y, 0);
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        IntStream.range(1, 10).forEach(i -> executorService.submit(() -> accumulator.accumulate(i)));
        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        System.out.println(accumulator.getThenReset());
    }

}
