package section10_JavaConcurrencyFramework.exam08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllExample {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Callable<Integer>> tasks = new ArrayList<>();

        tasks.add(()->{
            Thread.sleep(3000);
            return 1;
        });

        tasks.add(()->{
            Thread.sleep(2000);
            return 1;
        });

        tasks.add(()->{
            Thread.sleep(1000);
            throw new RuntimeException("invokeAll error");
        });

        long startTime = 0;

        try {
            startTime = System.currentTimeMillis();
            List<Future<Integer>> futures = executorService.invokeAll(tasks);
            for(Future<Integer> future : futures) {
                try {
                    int result = future.get();
                    System.out.println("result : " + result);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            long endTime = System.currentTimeMillis();
            System.out.println("소요 시간 : " + (endTime - startTime) + "ms");
        }

    }
}
