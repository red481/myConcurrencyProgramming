package section11_ThreadPoolExecutor.exam01;

import java.util.concurrent.*;

public class PrestartThreadsExample {
    public static void main(String[] args) {

        int corePoolSize = 2;
        int maxPoolSize = 10;
        long keepAliveTime = 0L;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        //BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(4);
        int taskNum = 10;

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        //executor.prestartCoreThread();

        executor.prestartAllCoreThreads();

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 가 테스크" + taskId + " 를 실행하고 있습니다.");
            });
        }

        executor.shutdown();

    }
}
