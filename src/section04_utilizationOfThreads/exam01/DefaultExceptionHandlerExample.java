package section04_utilizationOfThreads.exam01;

public class DefaultExceptionHandlerExample {
    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + " 에서 예외 발생 " + e);
            }
        });

        Thread thread = new Thread(() -> {
            throw new RuntimeException();
        });

        thread.start();
    }
}
