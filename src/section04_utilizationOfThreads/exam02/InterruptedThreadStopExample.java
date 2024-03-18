package section04_utilizationOfThreads.exam02;

public class InterruptedThreadStopExample {
    public static void main(String[] args) {

        Thread worker = new Thread(() -> {
            while(!Thread.interrupted()){
                System.out.println("작업 스레드가 실행 중입니다.");
            }
            System.out.println("작업 스레드가 중단되었습니다.");
            System.out.println("인터럽트 상태 1: " + Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();
            System.out.println("인터럽트 상태 2: " + Thread.currentThread().isInterrupted());
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

        try{
            worker.join();
            stopper.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
