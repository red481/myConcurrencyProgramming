package section08_JavaLocks.exam03;

import section06_SynchronizationTechniques.exam01.SharedData;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        SharedData sharedData = new SharedData();

        Thread reader1 = new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println("읽기 스레드가 데이터를 읽고 있습니다. 데이터: " + sharedData.getSum());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                readWriteLock.readLock().unlock();
            }
        });

        Thread reader2 = new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println("읽기 스레드가 데이터를 읽고 있습니다. 데이터: " + sharedData.getSum());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                readWriteLock.readLock().unlock();
            }
        });

        Thread writer = new Thread(() -> {
            readWriteLock.writeLock().lock();
            try {
                System.out.println("쓰기 스레드가 데이터를 쓰고 있습니다. 데이터: " + sharedData.getSum());
                sharedData.sum();
                System.out.println("쓰기 스레드가 데이터를 변경 했습니다. 데이터: " + sharedData.getSum());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                readWriteLock.writeLock().unlock();
            }
        });

        //case 1 : read - write - read
//        reader1.start();
//        writer.start();
//        Thread.sleep(100);
//        reader2.start();

        //case 2 : read - read - write
        reader1.start();
        reader2.start();
        writer.start();
    }
}
