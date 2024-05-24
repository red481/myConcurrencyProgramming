package section06_SynchronizationTechniques.exam01;

public class SharedData {
    private int sharedValue = 0;

    private Mutex mutex;

    public SharedData(){
    }

    public SharedData(Mutex mutex){
        this.mutex = mutex;
    }

    public void sum(){
        try {
            for (int i = 0; i < 10000000; i++) {
                sharedValue++;
            }
        }finally {
        }
    }

    public int getSum(){
        return sharedValue;
    }
}
