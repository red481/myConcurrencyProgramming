package section03_ThreadAPI.exam04;

public class ThreadNameExample {
    public static void main(String[] args) throws InterruptedException{
        Thread myThread = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                System.out.println("스레드 이름 : " + Thread.currentThread().getName());
            }
        }, "myThread");
        myThread.start();

        Thread yourThread = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                System.out.println("스레드 이름 : " + Thread.currentThread().getName());
            }
        });
        yourThread.setName("yourThread"); // * setName() 메소드를 이용해서 스레드를 생성하면 0부터 시작하던 스레드의 이름이 1부터 시작하도록 변경할 수 있다.
        yourThread.start();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println("현재 스레드 이름 : " + Thread.currentThread().getName());
            }).start();
        }

        Thread.sleep(2000);
    }


}
