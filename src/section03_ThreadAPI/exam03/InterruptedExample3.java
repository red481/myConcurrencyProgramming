package section03_ThreadAPI.exam03;

public class InterruptedExample3 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while(true){
                System.out.println("스레드 작동 중...");
                if(Thread.interrupted()){
                    System.out.println("스레드가 인터럽트 되었습니다.");
                    System.out.println("스레드의 현재 상태 : " + Thread.currentThread().getState());
                    break;
                }
            }
            System.out.println("스레드 인터럽트 상태 : " + Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();
            System.out.println("스레드 인터럽트 상태 : " + Thread.currentThread().isInterrupted());
        });

        thread.start();

        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        thread.interrupt();
    }
}
