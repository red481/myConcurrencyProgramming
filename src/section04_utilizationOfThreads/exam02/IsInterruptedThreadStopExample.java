package section04_utilizationOfThreads.exam02;

public class IsInterruptedThreadStopExample {

    public static void main(String[] args) throws InterruptedException{

        Thread worker = new Thread(() -> {
            while(!Thread.currentThread().isInterrupted()){
                System.out.println("작업 스레드가 실행 중입니다.");
            }
            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
            System.out.println("스레드 종료");
        });

        Thread stopper = new Thread(() -> {
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            worker.interrupt();
            System.out.println("중단 스레드가 작업 스레드를 중단시켰습니다.");
        });

        worker.start();
        stopper.start();

        worker.join();
        stopper.join();
    }
}
