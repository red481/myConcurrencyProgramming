package section04_utilizationOfThreads.exam03;

public class UserThreadLifecycleExample {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("사용자 스레드 1 실행 중...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("사용자 스레드 1 종료");
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                System.out.println("사용자 스레드 2 실행 중...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("사용자 스레드 2 종료");
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("메인 스레드 종료");
    }
}
