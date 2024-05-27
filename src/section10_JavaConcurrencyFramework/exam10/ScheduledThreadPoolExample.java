package section10_JavaConcurrencyFramework.exam10;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("Task is running..");
        };

        int initialDelay = 0;
        int initialPeriod = 1;
        int updatedPeriod = 3;

        ScheduledFuture<?> future = executor.scheduleAtFixedRate(task, initialDelay, initialPeriod, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000);
            future.cancel(true);

            future= executor.scheduleAtFixedRate(task, 0, updatedPeriod, TimeUnit.SECONDS);

            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        future.cancel(false);
        executor.shutdown();
    }
}
