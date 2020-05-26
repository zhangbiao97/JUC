package collections.copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 描述：对比两个迭代器。
 * <p>
 * Create By ZhangBiao
 * 2020/5/26
 */
public class CopyOnWriteArrayListDemo2 {

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3});

        System.out.println(list);

        Iterator<Integer> iterator1 = list.iterator();

        list.remove(2);
        Thread.sleep(1000);
        System.out.println(list);

        Iterator<Integer> iterator2 = list.iterator();

        iterator1.forEachRemaining(System.out::println);
        iterator2.forEachRemaining(System.out::println);
    }

}
