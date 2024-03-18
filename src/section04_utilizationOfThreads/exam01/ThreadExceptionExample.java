package section04_utilizationOfThreads.exam01;

public class ThreadExceptionExample {
    public static void main(String[] args) {

        Thread thread = new Thread(()->{
            throw new RuntimeException();
        });
        try {
            thread.start();
        } catch (Exception e) {
            System.out.println("예외 처리");
        }
    }
}
