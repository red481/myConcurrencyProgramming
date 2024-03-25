package section07_JavaSynchronization.exam01.method;

public class InstanceMethodSynchronizedExamples2 {
    private int count = 0;

    public synchronized void increment(){
        count++;
        System.out.println(Thread.currentThread().getName() + " 가 증가시켰습니다. 현재 값: " + count);
    }

    public synchronized void decrement(){
        count--;
        System.out.println(Thread.currentThread().getName() + " 가 감소시켰습니다. 현재 값: " + count);
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) {
        InstanceMethodSynchronizedExamples2 counter = new InstanceMethodSynchronizedExamples2();
        InstanceMethodSynchronizedExamples2 counter2 = new InstanceMethodSynchronizedExamples2();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
                counter2.decrement();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrement();
                counter2.increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
