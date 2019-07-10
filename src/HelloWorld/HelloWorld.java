package HelloWorld;

public class HelloWorld {

    public static void main(String[] args) throws InterruptedException {
        Thread td = new Thread(new PrintHello());
        td.start();
//        try {
//            td.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        Thread td = new Thread(new Runnable() {
//            public void run() {
//                System.out.println("Hello World~~~~~!");
//            }
//        });

//        Thread td = new Thread((Runnable) () -> {System.out.println("Hello World 123 ~~~~~!");});
//        td.start();


        System.out.println("DONE!");
        try {
            td.join(); //wait until thread td finish, then continue main thread execution
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FINALLY DONE!");
    }

    private static class PrintHello implements Runnable {

        public void run() {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("TD SAY Hello World!");
        }
    }
}
