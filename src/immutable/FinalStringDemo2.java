package immutable;

/**
 * Create By ZhangBiao
 * 2020/5/24
 */
public class FinalStringDemo2 {

    public static void main(String[] args) {
        String a = "wukong2";
        final String b = getDashixiong();
        String c = b + 2;
        System.out.println((a == c));
    }

    private static String getDashixiong() {
        return "wukong";
    }

}
