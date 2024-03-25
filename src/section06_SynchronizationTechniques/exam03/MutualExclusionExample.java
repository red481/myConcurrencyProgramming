package section06_SynchronizationTechniques.exam03;

public class MutualExclusionExample {

    private int counter = 0;

    public void increment(){
        counter++;
        System.out.println("스레드 : " + Thread.currentThread().getName() + " 카운터 값 : " + counter);
    }

    public static void main(String[] args){
        MutualExclusionExample example = new MutualExclusionExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                example.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                example.increment();
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
