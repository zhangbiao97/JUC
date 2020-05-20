package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：两个线程打印日期
 * <p>
 * Create By ZhangBiao
 * 2020/5/20
 */
public class ThreadLocalNormalUsage00 {

    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(10);
                System.out.println(date);
            }

        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(1000);
                System.out.println(date);
            }

        }).start();
    }

    public String date(int seconds) {
        //参数的单位是毫秒，从1970-1-1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

}
