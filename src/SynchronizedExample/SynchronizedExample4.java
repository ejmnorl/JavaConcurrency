package SynchronizedExample;

//Problem:  同一个类的所有instance，how to guarantee同一时间只有一个thread可以执行某一个方法
public class SynchronizedExample4 {
    //All the same as SynchronizedExample1
    private static final long SLEEP_TIME = 3000;

    public void foo() {
        synchronized (SynchronizedExample4.class) {
            System.out.println("This is foo4...");
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public  void bar() {
        synchronized (SynchronizedExample4.class) {
            System.out.println("This is bar4...");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        SynchronizedExample4 ex1 = new SynchronizedExample4();
        SynchronizedExample4 ex2 = new SynchronizedExample4();

        Thread t1 = new Thread(() -> {
            ex1.foo();
        });
        Thread t2 = new Thread(() -> {
            ex2.bar();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}
