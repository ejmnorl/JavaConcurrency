package SynchronizedExample;

//TAKE AWAY:
// Synchronized block/method is reentrant to the same thread, but other threads are blocked.

//NOTE:
//Synchronized is HEAVY WEIGHT LOCK, since java thread is mapped to kernel thread. User Mode --> Kernel Mode, OS gonna get involved
public class SynchronizedExample3 {
    private static final long SLEEP_TIME = 3000;


    public void foo() throws InterruptedException {
        synchronized (this) {
            System.out.println("This is foo...");
            bar();
            Thread.sleep(SLEEP_TIME);
        }
    }

    public  void bar() {
        synchronized (this) {
            System.out.println("This is bar...");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        final SynchronizedExample3 ex1 = new SynchronizedExample3();

        Thread t1 = new Thread(() -> {
            try {
                ex1.foo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> ex1.bar());

        t1.start();
//        t1.join();

        t2.start();

        t1.join();
        t2.join();

    }
}
