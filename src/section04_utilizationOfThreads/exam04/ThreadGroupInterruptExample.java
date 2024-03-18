package section04_utilizationOfThreads.exam04;

public class ThreadGroupInterruptExample {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup topGroup = new ThreadGroup("상위그룹");
        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위그룹");

        Thread upperThread1 = new Thread(topGroup, () -> {
            while (true) {
                System.out.println("상위 스레드 그룹 실행 중");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("상위 스레드 그룹 종료");
                    break;
                }
            }
        }, "상위 그룹 스레드 1");

        Thread lowerThread1 = new Thread(subGroup, () -> {
            while (true) {
                System.out.println("하위 스레드 그룹 실행 중");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("하위 스레드 그룹 종료");
                    break;
                }
            }
        }, "하위 그룹 스레드 1");

        upperThread1.start();
        lowerThread1.start();

//        Thread.sleep(3000);
//        subGroup.interrupt();

        Thread.sleep(3000);

        topGroup.interrupt();

        Thread.sleep(3000);

        System.out.println("메인 스레드 종료");
    }
}
