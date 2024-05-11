package section07_JavaSynchronization.exam01.block;

class MethodBlock{}
public class StaticBlockSynchronizedExamples {
    private static int count = 0;

    private Object lockObject = new Object();

    public static void incrementBlockClass(){
        synchronized(StaticBlockSynchronizedExamples.class){
            count++;
            System.out.println(Thread.currentThread().getName() + "가 StaticBlockSynchronizedExamples.class 의해 블록 동기화 함 : " + count);
        }
    }

    public static void incrementBlockOtherClass(){
        synchronized(MethodBlock.class){
            count++;
            System.out.println(Thread.currentThread().getName() + "가 MethodBlock.class에 의해 블록 동기화 함 : " + count);
        }
    }

    public static void main(String[] args) {
        StaticBlockSynchronizedExamples example1 = new StaticBlockSynchronizedExamples();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                StaticBlockSynchronizedExamples.incrementBlockClass();
            }
        },"Thread1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                StaticBlockSynchronizedExamples.incrementBlockOtherClass();
            }
        }, "Thread2");

        thread1.start();
        thread2.start();


        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("최종 인스턴스 값: " + count);
        System.out.println("최종 인스턴스 값: " + count);
    }
}
