package section09_JavaSynchronizationTools.exam03;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaerCASExample {
    private static AtomicIntegerFieldUpdater<YourClass> fieldUpdater;
    private static int NUM_THREADS = 3;

    public static class YourClass {
        volatile int counter;
        public int getCounter() {
            return counter;
        }
    }

    public static void main(String[] args) {

        YourClass yourClass = new YourClass();
        fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(YourClass.class, "counter");

        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                int expectedValue;
                int newValue;
                for (int j = 0; j < 1000000; j++) {
                    do {
                        expectedValue = fieldUpdater.get(yourClass);
                        newValue = expectedValue + 1;
                    }
                    while (!fieldUpdater.compareAndSet(yourClass, expectedValue, newValue));
                    System.out.println(Thread.currentThread().getName() + ":" + expectedValue + ":" + newValue);
                }
            });
            threads[i].start();
        }

        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Final value : " + yourClass.getCounter());
    }
}
