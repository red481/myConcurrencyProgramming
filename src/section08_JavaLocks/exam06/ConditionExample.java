package section08_JavaLocks.exam06;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConditionExample {

    private final Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean flag = false;

    public void awaiting() throws InterruptedException {
        lock.lock();
        try {
            while(!flag) {
                System.out.println("조건이 만족하지 못해 대기함");
                condition.await();
            }
            System.out.println("임계영역을 추가적으로 수행함");
        } finally {
            lock.unlock();
        }
    }

    public void signaling() {
        lock.lock();
        try {
            flag = true;
            System.out.println("조건을 만족시키고 깨움");
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionExample conditionExample = new ConditionExample();

        Thread thread1 = new Thread(() -> {
            try {
                conditionExample.awaiting();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            conditionExample.signaling();
        });

        thread1.start();
        Thread.sleep(2000);
        thread2.start();

        thread1.join();
        thread2.join();
    }
}

