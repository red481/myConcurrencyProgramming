package section10_JavaConcurrencyFramework.exam08;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class InvokeAnyExample {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);
        ArrayList<Callable<String>> tasks = new ArrayList<>();

        tasks.add(() -> {
            Thread.sleep(2000);
            return "Task 1";
        });
        tasks.add(() -> {
            Thread.sleep(1000);
            return "Task 2";
        });
        tasks.add(() -> {
            Thread.sleep(3000);
            return "Task 3";
        });

        long started = 0;
        try {
            started = System.currentTimeMillis();
            String result = executor.invokeAny(tasks);
            System.out.println("result: " + result);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("invokeAny() 소요 시간 : " + (System.currentTimeMillis() - started) + "ms");
        }

        executor.shutdown();
        System.out.println("메인 스레드 소요 시간 : " + (System.currentTimeMillis() - started) + "ms");

    }

}
