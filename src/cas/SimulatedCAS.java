package cas;

/**
 * 描述：模拟CAS操作，等价代码
 * <p>
 * Create By ZhangBiao
 * 2020/5/23
 */
public class SimulatedCAS {

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

}
