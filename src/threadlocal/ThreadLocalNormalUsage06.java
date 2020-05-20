package threadlocal;

/**
 * 描述：演示ThreadLocal用法2，避免传递参数的麻烦。
 * <p>
 * Create By ZhangBiao
 * 2020/5/20
 */
public class ThreadLocalNormalUsage06 {

    public static void main(String[] args) {
        new Service1().process("张三");
    }

}

class User {

    public String name;

    public User(String name) {
        this.name = name;
    }

}

class UserContextHolder {

    public static ThreadLocal<User> holder = new ThreadLocal<>();

}

class Service1 {

    public void process(String name) {
        User user = new User(name);
        UserContextHolder.holder.set(user);
        new Service2().process();
    }

}

class Service2 {

    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service2拿到用户名：" + user.name);
        new Service3().process();
    }

}

class Service3 {

    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service3拿到用户名：" + user.name);
        UserContextHolder.holder.remove();
    }

}
