package immutable;

/**
 * 描述：final的方法
 *
 * Create By ZhangBiao
 * 2020/5/24
 */
public class FinalMethodDemo {

    public void drink(){

    }

    public final void eat(){

    }

    public static void sleep(){

    }

}

class SubClass extends FinalMethodDemo {

    @Override
    public void drink() {
        super.drink();
    }


    public static void sleep(){

    }

}
