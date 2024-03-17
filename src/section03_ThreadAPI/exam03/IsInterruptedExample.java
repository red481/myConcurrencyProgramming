package section03_ThreadAPI.exam03;

public class IsInterruptedExample {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            while(Thread.currentThread().isInterrupted() == false){
                System.out.println("스레드가 작업중입니다.");

            }
            System.out.println("스레드가 인터럽트 되었습니다.");
            System.out.println("스레드 인터럽트 상태 : " + Thread.currentThread().isInterrupted());

            System.out.println("""
                    스레드의 인터럽트 상태를 false로 변경하고,
                    스레드의 작업을 종료하겠습니다.
                    """);
            Thread.currentThread().interrupted();
            System.out.println("스레드의 인터럽트 상태가 interrupted() 메소드의 호출로 인해 false로 변경되었습니다.");
            System.out.println("스레드 인터럽트 상태 : " + Thread.currentThread().isInterrupted());
        });
        thread.start();

        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        thread.interrupt();
        System.out.println("메인 스레드가 스레드의 인터럽트를 요청합니다.");
        try {
            System.out.println("메인 스레드가 스레드의 작업이 종료될 때까지 대기합니다.");
            thread.join();

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("메인 스레드가 종료합니다.");
    }
}
