package src.Multithreading;

public class DaemonUserThreadsExample {
    public static void main(String[] args) {
        Thread bgThread = new Thread(new DaemonHelper());
        Thread userThread = new Thread(new UserThread());

        bgThread.setDaemon(true);
        bgThread.start();
        userThread.start();
    }
}

class DaemonHelper implements Runnable{
    @Override
    public void run(){
        int count = 0;
        while (count < 500){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            System.out.println("Daemon Helper running");
        }
    }

}

class UserThread implements Runnable{
    @Override
    public void run(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User Thread running");
    }

}
