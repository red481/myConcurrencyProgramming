package section03_ThreadAPI.exam03;

public class InterruptedExample1 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while(true){
                if(Thread.interrupted()){
                    System.out.println("스레드가 인터럽트 되었습니다.");
                    System.out.println("스레드의 현재 상태 : " + Thread.currentThread().getState());
                    System.out.println("스레드 인터럽트 상태 : " + Thread.currentThread().isInterrupted());
                    break;
                }
            }
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
