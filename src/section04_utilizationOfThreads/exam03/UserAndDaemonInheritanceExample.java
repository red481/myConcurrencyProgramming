package section04_utilizationOfThreads.exam03;

public class UserAndDaemonInheritanceExample {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            new Thread(() -> {
                System.out.println("사용자 스레드의 자식 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
            }).start();
            System.out.println("사용자 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
        });

        thread.setDaemon(true);
        thread.start();

        thread.join();

        System.out.println("메인 스레드 종료");
    }
}
