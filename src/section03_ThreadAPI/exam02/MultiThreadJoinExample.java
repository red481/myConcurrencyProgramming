package section03_ThreadAPI.exam02;

public class MultiThreadJoinExample {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try{
                System.out.println("스레드1이 3초 동안 작동합니다.");
                Thread.sleep(3000);
                System.out.println("스레드1 작동 완료.");
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try{
                System.out.println("스레드2가 3초 동안 작동합니다.");
                Thread.sleep(3000);
                System.out.println("스레드2 작동 완료.");
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        });

        thread1.start();
        System.out.println("스레드1이 시작되었습니다. 메인스레드는 대기 할 예정 입니다.");

        try{
            thread1.join();

        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("스레드1이 작동 완료되었습니다.");

        thread2.start();
        System.out.println("스레드2가 시작되었습니다. 메인스레드는 대기 할 예정 입니다.");
        try{
            thread2.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("메인 스레드가 작동합니다.");
    }

}
