package section02_JavaThreadFundamentals.exam01;

public class ImplementRunnableExample {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중.. ");
    }
}
