package section10_JavaConcurrencyFramework.exam06;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitRunnableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Integer> future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("비동기 작업 실행중");
            }
        }, 100);

        Object result = future.get();

        System.out.println(result);

    }
}
