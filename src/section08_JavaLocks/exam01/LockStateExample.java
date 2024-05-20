package section08_JavaLocks.exam01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStateExample {
    private static final Lock lock1 = new ReentrantLock();
    public static void main(String[] args) {
        new Thread(() -> {
            lock1.lock();
            try {
                System.out.println("스레드가 락을 1번 획득했습니다.");
                lock1.lock();
                try {
                    System.out.println("스레드가 락을 2번 획득했습니다.");
                    lock1.lock();
                    try {
                        System.out.println("스레드가 3번 락을 획득했습니다.");
                    } finally {
                        System.out.println("스레드가 3번 락을 해제했습니다.");
                        lock1.unlock();
                    }
                } finally {
                    System.out.println("스레드가 2번 락을 해제했습니다.");
                    lock1.unlock();
                }
            } finally {
                lock1.unlock();
                System.out.println("스레드가 1번 락을 해제했습니다.");
            }
        }).start();

        new Thread(() -> {
            lock1.lock();
            try {
                System.out.println("스레드 2가 락을 획득했습니다.");
            } finally {
                lock1.unlock();
                System.out.println("스레드 2가 락을 해제했습니다.");
            }
        }).start();
    }
}
