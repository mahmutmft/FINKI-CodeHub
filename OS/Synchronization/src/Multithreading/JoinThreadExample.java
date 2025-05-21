package src.Multithreading;

public class JoinThreadExample {
    public static void main(String[] args) throws InterruptedException{
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Thread One: " + i);
                }
            }
        });
        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 25; i++) {
                    System.out.println("Thread Two: " + i);
                }
            }
        });
        System.out.println("Before executing the threads");
        one.start();
        two.start();
        one.join();
        two.join();
        System.out.println("Done executing the threads!");
    }
}
