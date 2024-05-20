package section08_JavaLocks.exam02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try {
                    lock.lockInterruptibly();
                    try {
                        System.out.println("스레드 1 이 락을 획득했습니다.");
                    } finally {
                        lock.unlock();
                        System.out.println("스레드 1 이 락을 해제했습니다.");
                    }

            } catch (InterruptedException e) {
                System.out.println("스레드 1 이 인터럽트를 받았습니다.");
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                try {
                    System.out.println("스레드 2 이 락을 획득했습니다.");
                } finally {
                    lock.unlock();
                    System.out.println("스레드 2 이 락을 해제했습니다.");
                }

            } catch (InterruptedException e) {
                System.out.println("스레드 2 이 인터럽트를 받았습니다.");
            }
        });

        thread1.start();
        thread2.start();

        thread1.interrupt();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
