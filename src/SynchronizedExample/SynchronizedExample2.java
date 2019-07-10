package SynchronizedExample;

public class SynchronizedExample2 { //All the same as SynchronizedExample1
    private static final long SLEEP_TIME = 3000;

    private final Object lock = new Object();

    public void foo() {
//        synchronized (this) {
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
//        synchronized (this) {
        synchronized (lock) {
            System.out.println("This is bar...");
        }
    }

    public void foo_bar() {
        System.out.println("This is foo_bar...");
    }

    public static void main(String[] args) throws InterruptedException{
        SynchronizedExample2 ex = new SynchronizedExample2();

        Thread t1 = new Thread(() -> {
            ex.foo();
        });
        Thread t2 = new Thread(() -> ex.bar());
        Thread t3 = new Thread(() -> ex.foo_bar());

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

    }
}
