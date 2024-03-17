package section03_ThreadAPI.exam05;

public class ThreadPriorityExample {
    public static void main(String[] args) {

        Thread thread1 = new CountingThread("Thread1", Thread.MAX_PRIORITY);
        Thread thread2 = new CountingThread("Thread2", Thread.MIN_PRIORITY);
        Thread thread3 = new CountingThread("Thread3", Thread.NORM_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();

    }

    static class CountingThread extends Thread{
        private final String threadName;
        private int count = 0;

        public CountingThread(String threadName, int priority){
            this.threadName = threadName;
            setPriority(priority);
        }

        @Override
        public void run() {
            while (count < 1000000000) {
                count++;
            }
            System.out.println(threadName + " : " + count);
        }
    }
}
