package section02_JavaThreadFundamentals.exam03;

public class RunnableStateThreadExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                for (int i = 0; i < 10000000; i++) {
                    if (i % 10000000 == 0) {
                        System.out.println("스레드 상태 : " + Thread.currentThread().getState());
                    }
                }
            }
        });
        thread.start();
    }
}
