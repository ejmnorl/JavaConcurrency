package LockExample;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//TAKE AWAY: ReentrantLock == Synchronized
public class LockExample1 {
    private static final long SLEEP_TIME = 3000;
    private Lock reentrantLock = new ReentrantLock();

    public void foo() {
        try {
            reentrantLock.lock();
            System.out.println("This is foo...");
            bar();
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            reentrantLock.unlock(); //NOTE: must be put in finally block since there might be some exceptions during code execution
        }
    }

    public void bar() {
        try {
            reentrantLock.lock();
            System.out.println("This is bar...");
        } finally {
            reentrantLock.unlock(); //NOTE: must be put in finally block since there might be some exceptions during code execution
        }
    }

    public void foo_bar() {
        System.out.println("This is foo_bar...");
    }

    public static void main(String[] args) throws InterruptedException{
        LockExample1 ex1 = new LockExample1();

        Thread t1 = new Thread(() -> {
            ex1.foo();
        });
        Thread t2 = new Thread(() -> ex1.bar());
        Thread t3 = new Thread(() -> ex1.foo_bar());

        t1.start();
        t1.join();

        t2.start();
        t3.start();

        t2.join();
        t3.join();

    }

}
