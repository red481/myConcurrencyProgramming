package section08_JavaLocks.exam06;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducerExample {

    private static int CAPACITY = 5;
    private Queue<Integer> queue = new LinkedList<>();
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public void produce() throws InterruptedException {
        int value = 0;
        while(true) {
            lock.lock();
            try {
                while(queue.size() == CAPACITY){
                    System.out.println("큐가 가득 차서 대기함");
                    notFull.await();
                }
                queue.offer(value);
                System.out.println(Thread.currentThread().getName() + " - 생산: " + value + ", 큐 크기: " + queue.size());
                value++;
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
            Thread.sleep(1000);
        }
    }

    public void consume() throws InterruptedException {
        while(true) {
            lock.lock();
            try {
                while(queue.isEmpty()){
                    System.out.println("큐가 비어서 대기함");
                    notEmpty.await();
                }
                int polled = queue.poll();
                System.out.println("소비: " + polled + ", 큐 크기: " + queue.size());
                notFull.signal();
            } finally {
                lock.unlock();
            }
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        ConsumerProducerExample example = new ConsumerProducerExample();

        new Thread(() -> {
            try {
                example.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                example.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
