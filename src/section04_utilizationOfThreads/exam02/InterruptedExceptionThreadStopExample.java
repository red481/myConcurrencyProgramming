package section04_utilizationOfThreads.exam02;

public class InterruptedExceptionThreadStopExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try{
                while(true){
                    System.out.println("작업 스레드가 실행 중입니다.");
                    System.out.println("인터럽트 상태 1: " + Thread.currentThread().isInterrupted());
                    Thread.sleep(500);
                }
            }catch (InterruptedException e){
                System.out.println("인터럽트 상태 2: " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }
            System.out.println("작업 스레드가 중단되었습니다.");
            System.out.println("인터럽트 상태 3: " + Thread.currentThread().isInterrupted());
        });

        Thread stopper = new Thread(() -> {
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            thread.interrupt();
            System.out.println("중단 스레드가 작업 스레드를 중단시켰습니다.");
        });

        thread.start();
        stopper.start();

        try{
            thread.join();
            stopper.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
