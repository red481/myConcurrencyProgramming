package section04_utilizationOfThreads.exam04;

public class NestedThreadGroupExample {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup topGroup = new ThreadGroup("최상위 스레드 그룹");
        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위 스레드 그룹1");
        ThreadGroup subGroup2 = new ThreadGroup(topGroup, "하위 스레드 그룹2");
        ThreadGroup subsubGroup = new ThreadGroup(subGroup, "하위 하위 스레드 그룹");

        Thread topThread1 = new Thread(topGroup, new GroupRunnable(), "topGroup 스레드 1");
        Thread topThread2 = new Thread(topGroup, new GroupRunnable(), "topGroup 스레드 2");
        Thread subThread1 = new Thread(subGroup, new GroupRunnable(), "subGroup 스레드 1");
        Thread subThread2 = new Thread(subGroup, new GroupRunnable(), "subGroup 스레드 2");

        topThread1.start();
        topThread2.start();
        subThread1.start();
        subThread2.start();

        Thread.sleep(1000);

        System.out.println("------------------------------------------------------");
        System.out.println("최상위 스레드 그룹의 정보");
        topGroup.list();
        System.out.println("------------------------------------------------------");
        System.out.println("최상위 스레드 그룹 활성화 스레드 수: " + topGroup.activeCount());
    }

    static class GroupRunnable implements Runnable {
        @Override
        public void run(){
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName() + " 는 " + currentThread.getThreadGroup().getName() + "에 속해있습니다.");
        }
    }
}
