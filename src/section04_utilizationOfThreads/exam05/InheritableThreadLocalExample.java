package section04_utilizationOfThreads.exam05;

public class InheritableThreadLocalExample {

    public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        inheritableThreadLocal.set("부모 스레드의 값");

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : " + inheritableThreadLocal.get());
            inheritableThreadLocal.set("자식 스레드의 값");
            System.out.println(Thread.currentThread().getName() + " : " + inheritableThreadLocal.get());
            inheritableThreadLocal.remove();
            System.out.println(Thread.currentThread().getName() + " : " + inheritableThreadLocal.get());
        }, "자식 스레드").start();

        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName() + " : " + inheritableThreadLocal.get());
    }
}
