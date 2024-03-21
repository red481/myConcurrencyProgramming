package section05_synchronizationConcept.exam04;

public class ThreadSafeLocalVariableExample {

    int localSum = 0;
    public void printNumbers(int plus){
        //int localSum = 0;

        for (int i = 1; i <= 5; i++) {
            localSum += i;
            try{
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        localSum += plus;
        System.out.println(Thread.currentThread().getName() + " - 현재 합계 : " + localSum);
    }

    public static void main(String[] args){

        ThreadSafeLocalVariableExample example = new ThreadSafeLocalVariableExample();

        Thread thread1 = new Thread(() -> {
            example.printNumbers(10);
        });

        Thread thread2 = new Thread(() -> {
            example.printNumbers(20);
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
