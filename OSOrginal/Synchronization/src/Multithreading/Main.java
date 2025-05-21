package src.Multithreading;

public class Main {
    public static void main(String[] args) {
        Thread one = new Thread(new ThreadOne());
        Thread two = new Thread(new ThreadTwo());
        Thread three = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    System.out.println("3:" + i);
                }
            }
        });

        one.start();
        two.start();
        three.start();

    }
}

class ThreadOne implements Runnable{
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println("One:" + i);
        }
    }
}

class ThreadTwo implements Runnable{
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println("Two:" + i);
        }
    }
}
