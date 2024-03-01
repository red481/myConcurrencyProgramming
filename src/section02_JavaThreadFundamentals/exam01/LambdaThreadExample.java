package section02_JavaThreadFundamentals.exam01;

public class LambdaThreadExample {
    public static void main(String[] args) {

        new Thread(() -> System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중.. ")).start();
    }
}
