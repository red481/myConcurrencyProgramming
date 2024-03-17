package section03_ThreadAPI.exam02;

public class BasicJoinExample {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try{
                System.out.println("스레드가 3초 동안 작동합니다.");
                Thread.sleep(3000);
                System.out.println("스레드 작동 완료.");
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        });

        thread.start();

        System.out.println("스레드가 시작되었습니다. 메인스레드는 대기 할 예정 입니다.");

        try{thread.join();}catch(InterruptedException e){e.printStackTrace();}

        System.out.println("메인 스레드가 작동합니다.");
    }
}
