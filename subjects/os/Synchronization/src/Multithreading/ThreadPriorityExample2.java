package src.Multithreading;

public class ThreadPriorityExample2 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " says HI!");

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread one says Hi as well!");
            }
        });

        one.setPriority(Thread.MAX_PRIORITY);
        one.start();

    }
}
