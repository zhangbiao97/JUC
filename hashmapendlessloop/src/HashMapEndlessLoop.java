import java.util.HashMap;

/**
 * 描述：HashMap在多线程的情况下造成死循环的情况。
 * <p>
 * Create By ZhangBiao
 * 2020/5/26
 */
public class HashMapEndlessLoop {

    public static void main(String[] args) {
        final HashMap<Integer, String> map = new HashMap<>(2, 1.5f);
        map.put(5, "C");
        map.put(7, "B");
        map.put(3, "A");
        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(15, "D");
                System.out.println(map);
            }
        }, "Thread 1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(1, "E");
                System.out.println(map);
            }
        }, "Thread 2").start();
    }

}
