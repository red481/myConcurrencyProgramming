package section02_JavaThreadFundamentals.exam02;

public class ThreadStartRunExample {
    public static void main(String[] args) throws InterruptedException {

        MyRunnable myRunnable = new MyRunnable();

        Thread thread = new Thread(myRunnable);
        thread.start(); // * 1.스레드가 생성되고 2.해당 스레드의 run() 메서드가 실행된다.
        //thread.sleep(500);
        //System.out.println("스레드 소멸하지 않았는가 ? : " + thread.isAlive());
        System.out.println("thread 객체 start() 후...");
        thread.run(); // * 1. main 스레드에서 run() 메서드가 실행된다.
        System.out.println("main 스레드 종료.. ");

        // * 테스트 결과 : main 스레드 종료 후에도 User Thread는 진행 중이던 생성 과정을 거쳐 run()메서드를 실행하고 끝난다. (매번 다름)
    }

    static class MyRunnable implements Runnable{

        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중.. ");
        }

    }
}
