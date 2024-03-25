package section06_SynchronizationTechniques.exam02;

public class BinarySemaphoreExample {
    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource(new BinarySemaphore());

        Thread thread1 = new Thread(() -> {
            sharedResource.sum();
        });

        Thread thread2 = new Thread(() -> {
            sharedResource.sum();
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("SharedData value result: " + sharedResource.getSum());
    }


    public static class SharedResource {
        private int sharedValue = 0;

        private CommonSemaphore commonSemaphore;

        public SharedResource(CommonSemaphore mutex){
            this.commonSemaphore = mutex;
        }

        public void sum(){
            try {
                commonSemaphore.acquired();
                for (int i = 0; i < 10000000; i++) {
                    sharedValue++;
                }
            }finally {
                commonSemaphore.release();
            }
        }

        public int getSum(){
            return sharedValue;
        }
    }
}


