package section03_ThreadAPI.exam05;

public class ThreadPriorityAPIExample {
    public static void main(String[] args) {

        Thread thread = new Thread(() ->{

        });

        System.out.println("스레드 우선 순위 : " + Thread.currentThread().getPriority());
        thread.start();

        Thread minThread = new Thread(() -> {
            System.out.println("최소 우선 순위 : " + Thread.currentThread().getPriority());
        });
        minThread.setPriority(Thread.MIN_PRIORITY);
        minThread.start();

        Thread maxThread = new Thread(() -> {
            System.out.println("최대 우선 순위 : " + Thread.currentThread().getPriority());
        });
        maxThread.setPriority(Thread.MAX_PRIORITY);
        maxThread.start();
    }
}
