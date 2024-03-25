package section07_JavaSynchronization.exam01;

public class SynchronizedExamples {

    private int instanceCount = 0;
    private static int staticCount = 0;


    public synchronized void instanceMethod(){
        for (int i = 0; i < 100000; i++) {
            staticCount++;
        }
        System.out.println("인스턴스 메서드 동기화: " + staticCount);
    }

    public static synchronized void staticMethod(){
        for (int i = 0; i < 100000; i++) {
            staticCount++;
        }
        System.out.println("스테틱 메서드 동기화: " + staticCount);
    }

    public void instanceBlock(){
        synchronized (this){
            instanceCount++;
            System.out.println("인스턴스 블럭 동기화: " + instanceCount);

        }

    }

    public static void staticBlock(){
        synchronized (SynchronizedExamples.class){
            staticCount++;
            System.out.println("스테틱 블럭 동기화: " + staticCount);
        }
    }

    public static void main(String[] args) {

        SynchronizedExamples example = new SynchronizedExamples();

        new Thread(example::instanceMethod).start();
        new Thread(SynchronizedExamples::staticMethod).start();

    }
}
