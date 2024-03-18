package section04_utilizationOfThreads.exam03;

public class DaemonThreadLifecycleExample {
    public static void main(String[] args) {

        Thread userThread = new Thread(() -> {

                System.out.println("사용자 스레드 1 실행 중...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            System.out.println("사용자 스레드 1 종료");
        });


        Thread daemonThread = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("데몬 스레드 실행 중...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();


        try {
            userThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("메인 스레드 종료");
    }
}
