package section02_JavaThreadFundamentals.exam01;

public class ExtendThreadExample {
    public static void main(String[] args) {
        MyThread mythread1 = new MyThread();
        mythread1.start();
    }
}

class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중.. ");
    }
}