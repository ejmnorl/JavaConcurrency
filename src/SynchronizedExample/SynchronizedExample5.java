package SynchronizedExample;

//Problem:  同一个类的所有instance，how to guarantee同一时间只有一个thread可以执行某一个方法
public class SynchronizedExample5 {
     //All the same as SynchronizedExample1
    private static final long SLEEP_TIME = 3000;

    private static final Object lock = new Object();
//    private final Object lock1 = new Object();

    public void foo() {
        synchronized (lock) {
            System.out.println("This is foo...");
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public  void bar() {
        synchronized (lock) {
            System.out.println("This is bar...");
        }
    }


//    public void foo1() {
//        synchronized (lock1) {
//            System.out.println("This is foo11...");
//            try {
//                Thread.sleep(SLEEP_TIME);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public  void bar1() {
//        synchronized (lock1) {
//            System.out.println("This is bar11...");
//        }
//    }


    public static void main(String[] args) throws InterruptedException{
        SynchronizedExample5 ex1 = new SynchronizedExample5();
            SynchronizedExample5 ex2 = new SynchronizedExample5();

        Thread t1 = new Thread(() -> {
            ex1.foo();
//            ex1.foo1();
        });
        Thread t2 = new Thread(() -> {
            ex2.bar();
//            ex2.bar1();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}
