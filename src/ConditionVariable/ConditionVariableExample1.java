package ConditionVariable;

public class ConditionVariableExample1 {
    public static final long SLEEP_INTERVAL = 1000;
    private boolean running = true;

    public void start() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                print("Hello World, Conditional Variable~ ");
                try {
                    Thread.sleep(SLEEP_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                synchronized (this) {  //NOTE: THIS IS WRONG!!! "this" refers to: anonymous inner class
//                    running = false;
//                    notifyAll();
//                }
                synchronized (ConditionVariableExample1.this) {
                    running = false;
                    ConditionVariableExample1.this.notifyAll();
                }

            }
        });
        thread.start();
    }

    public void join() {
        synchronized (this) {
            while (running) {
                try {
                    print("Still waiting for other thread to finish.");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            print("Peer thread finished.");
        }
    }

    private void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        ConditionVariableExample1 ex = new ConditionVariableExample1();
        ex.start();
        ex.join();
    }
}
