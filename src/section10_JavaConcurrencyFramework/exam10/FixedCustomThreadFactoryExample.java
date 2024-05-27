package section10_JavaConcurrencyFramework.exam10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FixedCustomThreadFactoryExample {
    public static void main(String[] args) {

        CustomThreadFactory customThreadFactory = new CustomThreadFactory("CustomThread");
        ExecutorService executor = Executors.newFixedThreadPool(3, customThreadFactory);
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            Callable<Integer> task = () -> {
                System.out.println("Thread : " + Thread.currentThread().getName() + ", Result: " + taskNumber + 1);
                return taskNumber + 1;
            };

            Future<Integer> future = executor.submit(task);
            futures.add(future);
        }

        for (Future<Integer> future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();
    }

    static class CustomThreadFactory implements ThreadFactory{
        private final String name;
        private int threadCount = 0;
        public CustomThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            threadCount++;
            String threadName = name + " - " + threadCount;
            System.out.println("스레드 이름 : " + threadName);
            return new Thread(r, threadName);
        }
    }
}
