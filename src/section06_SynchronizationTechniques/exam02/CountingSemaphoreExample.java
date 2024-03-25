package section06_SynchronizationTechniques.exam02;

import section06_SynchronizationTechniques.exam02.BinarySemaphoreExample.SharedResource;

public class CountingSemaphoreExample {
    public static void main(String[] args) {
        int permits = 10;
        CommonSemaphore semaphore = new CountingSemaphore(permits);
        SharedResource sharedResource = new SharedResource(semaphore);

        int threadCount = 15;

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                synchronized (CountingSemaphoreExample.class) {
                    sharedResource.sum();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("SharedData value result: " + sharedResource.getSum());
    }
}
