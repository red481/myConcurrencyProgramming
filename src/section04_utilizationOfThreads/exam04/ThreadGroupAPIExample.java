package section04_utilizationOfThreads.exam04;

public class ThreadGroupAPIExample {
    public static void main(String[] args) {

        ThreadGroup topGroup = new ThreadGroup("상위그룹");

        Thread[] topThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            topThreads[i] = new Thread(topGroup, () -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("상위 스레드 " + Thread.currentThread().getName() + " -> 인터럽트로 인해 종료");
                        break;
                    }
                }
            }, "상위 스레드 " + i);
            topThreads[i].start();
        }

        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위그룹");

        Thread[] subThreads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            subThreads[i] = new Thread(subGroup, () -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("하위 스레드 " + Thread.currentThread().getName() + " -> 인터럽트로 인해 종료");
                        break;
                    }
                }
            }, "하위 스레드 " + i);
            subThreads[i].start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("상위 스레드 그룹의 활성화된 스레드 수 : " + topGroup.activeCount());
        System.out.println("상위 스레드 그룹의 활성화된 하위 스레드 그룹 수 : " + topGroup.activeGroupCount());
        System.out.println("하위 스레드 그룹의 활성화된 스레드 수 : " + subGroup.activeCount());
        System.out.println("하위 스레드 그룹의 활성화된 하위 스레드 그룹 수 : " + subGroup.activeGroupCount());

    }
}
