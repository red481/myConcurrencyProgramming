package section05_synchronizationConcept.exam02;

public class CpuSyncExample {

    private static int count = 0;
    private static final int ITERATION = 100000;

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < ITERATION; i++) {
                synchronized (CpuSyncExample.class){
                    count++;
                }
            }
            System.out.println("Thread 1 종료. Count: " + count);
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < ITERATION; i++) {
                synchronized (CpuSyncExample.class){
                    count++;
                }
            }
            System.out.println("Thread 2 종료. Count: " + count);
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("예상 결과 : " + (ITERATION * 2));
        System.out.println("실제 결과 : " + count);
    }
}
