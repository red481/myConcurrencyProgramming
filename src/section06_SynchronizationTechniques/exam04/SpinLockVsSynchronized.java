package section06_SynchronizationTechniques.exam04;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockVsSynchronized {

    private AtomicBoolean spinLock = new AtomicBoolean(false);
    private final Object syncLock = new Object();
    private int count = 0;

    final static int THREAD_COUNT = 5;
    final static int ITERATIONS = 10_000_000;

    public void useSpinLock(){
        while(!spinLock.compareAndSet(false, true));
        for (int i = 0; i < ITERATIONS; i++) {
            count++;
        }
        spinLock.set(false);
    }

    public void useSynchronized(){
        synchronized (syncLock){
            for (int i = 0; i < ITERATIONS; i++) {
                count++;
            }
        }
    }

    public static void main(String[] args) {
        SpinLockVsSynchronized tester = new SpinLockVsSynchronized();

        //synchronized 성능 테스트
        Thread[] syncThreads = new Thread[THREAD_COUNT];
        long start2 = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads[i] = new Thread(() -> {
                tester.useSynchronized();
            });
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            try {
                syncThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Synchronized time: " + (System.currentTimeMillis() - start2) + "ms");

        System.out.println("Count: " + tester.count);
        System.out.println("Count expected: " + THREAD_COUNT * ITERATIONS);

        long start3 = System.currentTimeMillis();


        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads[i] = new Thread(() -> {
                tester.useSpinLock();
            });
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            try {
                syncThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("SpinLock time: " + (System.currentTimeMillis() - start3) + "ms");

        System.out.println("Count: " + tester.count);
        System.out.println("Count expected: " + (THREAD_COUNT * ITERATIONS) * 2);

    }
}
