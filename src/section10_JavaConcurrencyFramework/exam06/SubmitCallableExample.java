package section10_JavaConcurrencyFramework.exam06;

import java.util.concurrent.*;

public class SubmitCallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public String call() throws Exception {
                System.out.println("비동기 작업 실행");
                return "Hello World";
            }
        });

        Object result = future.get();
        System.out.println("result = " + result);

        executorService.shutdown();
    }
}

