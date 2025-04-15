package src.ThreadSynchronisation;

public class SyncDemo {
    private static int counter = 0;


    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment(); //counter++
                }
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment(); //counter++
                }
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println("Counter value: " + counter);

    }

    private synchronized static void increment(){
        counter++; // without this method we get number less then 20 000
    }

}

/*
* 1.Load
* 2.Increment
* 3.Set back the value
* counter = 0; incrementValue = 1; <- Thread 1
* counter = 0; incrementValue = 1 (2); <- Thread 2
 */

/*
 * 1.Load
 * 2.Increment
 * 3.Set back the value
 * counter = 0; incrementValue = 1; Setting back the value to = 1  <- Thread 1
 * counter = 0; incrementValue = 1 (2); <- Thread 2
 */
