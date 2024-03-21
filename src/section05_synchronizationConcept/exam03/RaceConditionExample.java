package section05_synchronizationConcept.exam03;

public class RaceConditionExample {

    private static int sharedResource = 0;

    public static void main(String[] args) {

        Thread[] incrementThreads = new Thread[100];
        for (int i = 0; i < incrementThreads.length; i++) {
            incrementThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    sharedResource++;
                }
            });
        }

        for (Thread incrementThread : incrementThreads) {
            incrementThread.start();
        }

        for (Thread incrementThread : incrementThreads) {
            try {
                incrementThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Expected result: " + 100 * 10000);
        System.out.println("Actual result: " + sharedResource);
    }
}
