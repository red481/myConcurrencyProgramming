package section03_ThreadAPI.exam02;

public class InterruptedJoinExample {

    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();

        Thread longRunningThread = new Thread(() -> {
            try{
                for (int i = 0; i < 10; i++) {
                    System.out.println("긴 작업 스레드가 계속 실행 중...");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e){
                System.out.println("긴 작업 스레드가 인터럽트 되었습니다.");
            }
        });

        Thread interrupterThread = new Thread(() -> {
            System.out.println("인터럽터 스레드가 실행 되었습니다. 3초 후에 메인 스레드를 인터럽트 합니다.");
            try{
                Thread.sleep(3000);
                mainThread.interrupt();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        longRunningThread.start();
        interrupterThread.start();

        System.out.println("MainThread가 longRunningThread에 join을 호출합니다.");
        try{longRunningThread.join();} catch(InterruptedException e){e.printStackTrace();}

        Thread.interrupted();
        System.out.println("메인 스레드가 작동합니다.");
    }
}
