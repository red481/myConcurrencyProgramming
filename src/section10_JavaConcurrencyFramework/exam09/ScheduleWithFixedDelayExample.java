package section10_JavaConcurrencyFramework.exam09;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleWithFixedDelayExample {
    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        final long[] start = {0};

        Runnable task = () -> {
            if(start[0] != 0) {
                System.out.println("지연 시간 : " + (System.currentTimeMillis() - start[0]) + "ms");
            }
            try {
                Thread.sleep(2000);
                System.out.println("Thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            start[0] = System.currentTimeMillis();
        };

        ScheduledFuture<?> scheduledFuture = scheduler.scheduleWithFixedDelay(task, 1, 2, TimeUnit.SECONDS);

        Thread.sleep(10000);
        //scheduledFuture.cancel(true);
        scheduler.shutdown();
    }
}
