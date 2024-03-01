package section02_JavaThreadFundamentals.exam03;

public class BlockedStateThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while(true){

                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("락 획득 하고 싶다");
                }
            }
        });

        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        System.out.println("thread2.getState() = " + thread2.getState());
    }
}
