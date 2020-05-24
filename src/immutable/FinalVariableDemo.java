package immutable;

/**
 * 描述：演示final变量
 * <p>
 * Create By ZhangBiao
 * 2020/5/24
 */
public class FinalVariableDemo {

    private static final Person person = null;

    private final int a;

    public FinalVariableDemo(int a) {
        this.a = a;
    }

    void testFinal() {
        final int b = 7;
        int c = b;
    }

    /*static {
        person = null;
    }*/
}
