package section09_JavaSynchronizationTools.exam03;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicReferenceFieldUpdaerCASExample {

    private static AtomicReferenceFieldUpdater<AtomicReferenceFieldUpdaerCASExample, String> messageUpdater
            = AtomicReferenceFieldUpdater.newUpdater(AtomicReferenceFieldUpdaerCASExample.class, String.class, "message");

    private volatile String message = "";

    public void doSomething() {
        if(messageUpdater.compareAndSet(this, "", "Hello world!")){
            for (int i = 0; i < 10; i++) {
                System.out.println(messageUpdater.get(this));
            }
        } else {
            System.out.println("현재 스레드는 들어오지 못합니다.");
        }
    }

    public static void main(String[] args) {
        AtomicReferenceFieldUpdaerCASExample example = new AtomicReferenceFieldUpdaerCASExample();

        new Thread(() -> {
            example.doSomething();
        }).start();

        new Thread(() -> {
            example.doSomething();
        }).start();
    }


}
