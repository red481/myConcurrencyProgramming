package section10_JavaConcurrencyFramework.exam03;

import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable callableTask = () -> {
            System.out.println("Callable 작업 수행중..");
            System.out.println("Callable 작업 완료");

            return 42;
        };

        int result;
        Future<Integer> future = executorService.submit(callableTask);
        try {
            result = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Callable 작업 결과 : " + result);
    }
}
