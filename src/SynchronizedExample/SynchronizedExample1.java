package SynchronizedExample;

public class SynchronizedExample1 {

    private static final long SLEEP_TIME = 5000;
    public synchronized void foo() {
        System.out.println("This is foo...");
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized  void bar() {
        System.out.println("This is bar...");
    }

    public void foo_bar() {
        System.out.println("This is foo_bar...");
    }

    public static void main(String[] args) throws InterruptedException{
        SynchronizedExample1 ex1 = new SynchronizedExample1();

        Thread t1 = new Thread(() -> {
            ex1.foo();
        });
        Thread t2 = new Thread(() -> ex1.bar());
        Thread t3 = new Thread(() -> ex1.foo_bar());

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

    }
}
