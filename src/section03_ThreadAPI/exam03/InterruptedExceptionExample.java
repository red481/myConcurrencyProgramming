package section03_ThreadAPI.exam03;

public class InterruptedExceptionExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("스레드 인터럽트 상태 1: " + Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("스레드가 인터럽트 되었습니다.");
                System.out.println("스레드의 현재 상태 2: " + Thread.currentThread().getState());
                System.out.println("스레드 인터럽트 상태 2: " + Thread.currentThread().isInterrupted());
                System.out.println("스레드의 인터럽트 상태를 true로 복구합니다.");
                Thread.currentThread().interrupt();
                System.out.println("스레드의 현재 상태 3: " + Thread.currentThread().getState());
                System.out.println("스레드 인터럽트 상태 3: " + Thread.currentThread().isInterrupted());

            }
        });

        thread.start();

        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        thread.interrupt();
        thread.join();
        System.out.println(("인터럽트 상태 4: " + thread.isInterrupted()));
    }
}
