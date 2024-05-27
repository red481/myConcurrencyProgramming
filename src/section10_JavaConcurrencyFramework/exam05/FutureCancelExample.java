package section10_JavaConcurrencyFramework.exam05;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancelExample {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            System.out.println("비동기 작업 시작..");
            Thread.sleep(2000);
            System.out.println("비동기 작업 완료");

            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);
        Thread.sleep(1000);
        future.cancel(true);
//        try {
//            int result = future.get();
//            System.out.println("result = " + result);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
