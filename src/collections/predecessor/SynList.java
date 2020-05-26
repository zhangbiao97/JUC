package collections.predecessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 描述：演示Collections.synchronizedList(new ArrayList<E>());
 * <p>
 * Create By ZhangBiao
 * 2020/5/26
 */
public class SynList {

    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        list.add(5);
        System.out.println(list.get(0));
    }

}
