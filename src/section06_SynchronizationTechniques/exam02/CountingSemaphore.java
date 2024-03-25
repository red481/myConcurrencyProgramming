package section06_SynchronizationTechniques.exam02;

public class CountingSemaphore implements CommonSemaphore{

    private int signal;
    private int permits;

    public CountingSemaphore(int permits){
        this.permits = permits;
        this.signal = permits;
    }
    @Override
    public synchronized void acquired() {
        while(signal == 0){
            try{
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.signal--;
        System.out.println(Thread.currentThread().getName() + " acquired semaphore. Remaining permits: " + signal);
    }

    @Override
    public synchronized void release() {
        if(this.signal < permits){
            this.signal++;
            System.out.println(Thread.currentThread().getName() + " released semaphore. Remaining permits: " + signal);
            this.notify();
        }
        this.signal = 1;
        notify();

    }
}
