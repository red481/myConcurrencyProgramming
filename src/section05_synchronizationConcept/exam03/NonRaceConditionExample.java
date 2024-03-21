package section05_synchronizationConcept.exam03;

public class NonRaceConditionExample {

    private static int sharedResource = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread[] incrementThreads = new Thread[100];
        for (int i = 0; i < incrementThreads.length; i++) {
            incrementThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    synchronized (NonRaceConditionExample.class){
                        sharedResource++;
                    }
                }
            });


        }

        for(Thread incrementThread : incrementThreads){
            incrementThread.start();
        }

        for(Thread incrementThread : incrementThreads){
            incrementThread.join();
        }

        System.out.println("Expected result: " + 100 * 10000);
        System.out.println("Actual result: " + sharedResource);
    }
}
