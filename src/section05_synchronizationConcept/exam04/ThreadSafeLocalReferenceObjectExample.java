package section05_synchronizationConcept.exam04;

public class ThreadSafeLocalReferenceObjectExample {

    LocalObject localObject = new LocalObject();

    class LocalObject{
        private int value;

        public void increment(){
            value++;
        }

        @Override
        public String toString(){
            return "LocalObject{" + "value=" + value + '}';
        }
    }

    public void useLocalObject(){
        //LocalObject localObject = new LocalObject();

        for (int i = 0; i < 5; i++) {
            localObject.increment();
            System.out.println(Thread.currentThread().getName() + " - " + localObject);
            try{
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadSafeLocalReferenceObjectExample example = new ThreadSafeLocalReferenceObjectExample();

        Thread thread1 = new Thread(() -> {
            example.useLocalObject();
        });

        Thread thread2 = new Thread(() -> {
            example.useLocalObject();
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
