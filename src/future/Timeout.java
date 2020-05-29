package future;

import java.util.concurrent.*;

/**
 * 描述：演示get的超时方法，需要注意超时后处理，调用future.cancel()。
 * 演示cancel传入true和false的区别，代表是否中断正在执行的任务。
 * <p>
 * Create By ZhangBiao
 * 2020/5/29
 */
public class Timeout {

    private static final Ad DEFAULT_AD = new Ad("无网络时候的默认广告");

    private static final ExecutorService service = Executors.newFixedThreadPool(10);

    static class Ad {

        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad> {

        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("sleep期间被中断了");
                return new Ad("被中断时候的默认广告");
            }
            return new Ad("旅游订票哪家强？找某程");
        }

    }

    public void printAd() {
        Future<Ad> submit = service.submit(new FetchAdTask());
        Ad ad;
        try {
            ad = submit.get(4000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("被中断时候的默认广告");
        } catch (ExecutionException e) {
            ad = new Ad("异常时候的默认广告");
        } catch (TimeoutException e) {
            ad = new Ad("超时时候的默认广告");
            System.out.println("超时，未获取到广告");
            boolean cancel = submit.cancel(true);
            System.out.println("cancel的结果：" + cancel);
        }
        service.shutdown();
        System.out.println(ad);
    }

    public static void main(String[] args) {
        Timeout timeout = new Timeout();
        timeout.printAd();
    }

}
