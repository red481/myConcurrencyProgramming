package section03_ThreadAPI.exam04;

public class ThreadAliveExample {
    public static void main(String[] args) {
        Thread task1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("작업 스레드 1 실행 중...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread task2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("작업 스레드 2 실행 중...");
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        task1.start();
        task2.start();

        System.out.println("task1 스레드가 살아있는가? " + task1.isAlive());
        System.out.println("task2 스레드가 살아있는가? " + task2.isAlive());

        try {
            Thread.sleep(1000);
        }catch (Exception e){}

        System.out.println("task1 스레드가 살아있는가? " + task1.isAlive());
        System.out.println("task2 스레드가 살아있는가? " + task2.isAlive());
    }
}
