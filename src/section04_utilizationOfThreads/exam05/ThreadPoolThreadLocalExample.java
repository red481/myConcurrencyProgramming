package section04_utilizationOfThreads.exam05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolThreadLocalExample {
    private static ThreadLocal<String> threadLocal = new ThreadLocal();

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            threadLocal.set("작업 1의 값");
            System.out.println(Thread.currentThread().getName() + " 값: " + threadLocal.get());
            threadLocal.remove();
        });

        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                threadLocal.set("1번째 작업의 값");
                System.out.println(Thread.currentThread().getName() + " 값: " + threadLocal.get());
                threadLocal.remove();
            });
        }
    }
}
