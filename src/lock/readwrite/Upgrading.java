package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述：演示ReentrantReadWriteLock可以降级，不能升级
 * <p>
 * Create By ZhangBiao
 * 2020/5/21
 */
public class Upgrading {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private static void readUpgrading() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁，正在读取");
            Thread.sleep(1000);
            System.out.println("升级会带来阻塞");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到了写锁，升级成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void writeDowngrading() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁，正在写入");
            Thread.sleep(1000);
            readLock.lock();
            System.out.println("在不释放写锁的情况下，直接获取读锁，成功降级");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        /*System.out.println("先演示降级是可以的");
        Thread thread1 = new Thread(() -> writeDowngrading(), "Thread1");
        thread1.start();*/
        System.out.println("-----------------------------------");
        System.out.println("演示升级是不行的");
        Thread thread2 = new Thread(() -> readUpgrading(), "Thread2");
        thread2.start();
    }

}
