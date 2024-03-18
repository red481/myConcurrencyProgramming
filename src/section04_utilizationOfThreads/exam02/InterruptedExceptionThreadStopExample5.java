package section04_utilizationOfThreads.exam02;


/*
   * 동작하는 스레드 자신이 스스로에게 인터럽트를 거는게 아니라 다른 스레드가 인터럽트를 걸어서 예외를 발생시키면 스레드가 중단된다. 그리고 인터럽트 상태는 true로 변경된 채로 끝난다.
 */
public class InterruptedExceptionThreadStopExample5 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try{
                while(true){
                    System.out.println("작업 스레드가 실행 중입니다.");
                    System.out.println("인터럽트 상태 1: " + Thread.currentThread().isInterrupted());

                    if(Thread.currentThread().isInterrupted())
                    {
                        throw new InterruptedException("thread is interrupted");
                    }
                }
            }catch (InterruptedException e){
                System.out.println("인터럽트 상태 2: " + Thread.currentThread().isInterrupted());
            }
            System.out.println("작업 스레드가 중단되었습니다.");
            System.out.println("인터럽트 상태 3: " + Thread.currentThread().isInterrupted());
        });

        Thread stopper = new Thread(() -> {
            thread.interrupt();
            System.out.println("중단 스레드가 작업 스레드를 중단시켰습니다.");
        });

        thread.start();
        Thread.sleep(2000);
        stopper.start();

        try{
            thread.join();
            stopper.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
