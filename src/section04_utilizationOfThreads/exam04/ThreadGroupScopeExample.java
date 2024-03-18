package section04_utilizationOfThreads.exam04;

public class ThreadGroupScopeExample {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup topGroup = new ThreadGroup("최상위 스레드 그룹");

        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위 스레드 그룹1");

        Thread topGroupThread = new Thread(topGroup, () -> {
            System.out.println("상위 그룹 스레드에서 하위 그룹의 최대 우선 순위 설정 변경 전 : " + subGroup.getMaxPriority());
            subGroup.setMaxPriority(7);
            System.out.println("상위 그룹 스레드에서 하위 그룹의 최대 우선 순위 설정 변경 후 : " + subGroup.getMaxPriority());
        }, "상위 스레드 그룹");

        Thread subGroupThread = new Thread(subGroup, () -> {
            System.out.println("하위 그룹 스레드에서 상위 그룹의 최대 우선 순위 설정 변경 전 : " + topGroup.getMaxPriority());
            topGroup.setMaxPriority(4);
            System.out.println("하위 그룹 스레드에서 상위 그룹의 최대 우선 순위 설정 변경 후 : " + topGroup.getMaxPriority());
        }, "하위 스레드 그룹");

        topGroupThread.start();
        Thread.sleep(100);
        subGroupThread.start();

        Thread.sleep(1000);

        System.out.println(topGroupThread.getName() + " : " + topGroupThread.getPriority());
        System.out.println(subGroupThread.getName() + " : " + subGroupThread.getPriority());

        new Thread(topGroup, () -> {
            System.out.println("상위 유저 스레드의 우선 순위 : " + Thread.currentThread().getPriority());
        }, "상위 유저 스레드").start();

        new Thread(subGroup, () -> {
            System.out.println("하위 유저 스레드의 우선 순위 : " + Thread.currentThread().getPriority());
        }, "하위 유저 스레드").start();

        System.out.println("메인 스레드 종료");
    }
}
