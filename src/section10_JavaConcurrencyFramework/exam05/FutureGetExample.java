package section10_JavaConcurrencyFramework.exam05;

import java.util.concurrent.*;

public class FutureGetExample {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            System.out.println("비동기 작업 시작..");
            Thread.sleep(2000);
            System.out.println("비동기 작업 완료");

            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);

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
