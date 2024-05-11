package section07_JavaSynchronization.exam01.block;

import section07_JavaSynchronization.exam01.method.InstanceStaticMethodSynchronizedExamples;

public class InstanceBlockSynchronizedExamples {

    private int count = 0;

    private Object lockObject = new Object();

    public void incrementBlockThis(){
        synchronized(this){
            count++;
            System.out.println(Thread.currentThread().getName() + "가 This에 의해 블록 동기화 함 : " + count);
        }
    }

    public void incrementBlockLockObject(){
        synchronized(this){
            count++;
            System.out.println(Thread.currentThread().getName() + "가 lockObject에 의해 블록 동기화 함 : " + count);
        }
    }

    public static void main(String[] args) {
        InstanceBlockSynchronizedExamples example = new InstanceBlockSynchronizedExamples();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementBlockThis();
            }
        },"Thread1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementBlockThis();
            }
        }, "Thread2");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementBlockLockObject();
            }
        }, "Thread3");

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementBlockLockObject();
            }
        }, "Thread4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("최종 인스턴스 값: " + example.count);
    }
}
