package section06_SynchronizationTechniques.exam04;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockExample {

    private AtomicBoolean lock = new AtomicBoolean(false);

    public void lock(){
        while(lock.compareAndSet(false, true)){

        }
    }

    public void unlock(){
        lock.set(false);
    }

    public static void main(String[] args) {
        SpinLockExample spinLock = new SpinLockExample();

        Runnable task = ()->{
            spinLock.lock();
            System.out.println(Thread.currentThread().getName() + " acquired the lock");
            try {
                Thread.sleep(10);
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " is running");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                spinLock.unlock();
                System.out.println(Thread.currentThread().getName() + " released the lock");
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
