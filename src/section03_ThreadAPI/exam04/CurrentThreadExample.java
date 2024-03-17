package section03_ThreadAPI.exam04;

public class CurrentThreadExample {
    public static void main(String[] args) {

        System.out.println("현재 스레드: " + Thread.currentThread()); // * [스레드 이름, 우선순위, 그룹]을 출력한다.
        System.out.println("현재 스레드 이름 : " + Thread.currentThread().getName()); // * 현재 스레드의 이름을 출력한다.

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("현재 스레드: " + Thread.currentThread());
                System.out.println("현재 스레드: " + getName());
            }
        };

        thread.start();

        Thread thread1 = new Thread(new ThreadName());
        thread1.start();
    }

    static class ThreadName implements Runnable {

        @Override
        public void run() {
            System.out.println("현재 스레드: " + Thread.currentThread());
            System.out.println("현재 스레드 이름 : " + Thread.currentThread().getName());
        }
    }
}
