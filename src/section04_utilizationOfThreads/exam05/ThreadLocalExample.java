package section04_utilizationOfThreads.exam05;

public class ThreadLocalExample {

    //private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(()-> "Hello, World!");

    public static void main(String[] args) {

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " 값 변경 전: " + threadLocal.get());
            threadLocal.set("스레드 1의 값");
            System.out.println(Thread.currentThread().getName() + " 값 변경 후: " + threadLocal.get());
            threadLocal.remove();
            System.out.println(Thread.currentThread().getName() + " 값 삭제 후: " + threadLocal.get());
            }, "Thread-1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 값 변경 전: " + threadLocal.get());
            threadLocal.set("스레드 2의 값");
            System.out.println(Thread.currentThread().getName() + " 값 변경 후: " + threadLocal.get());
            threadLocal.remove();
            System.out.println(Thread.currentThread().getName() + " 값 삭제 후: " + threadLocal.get());
        }, "Thread-2").start();

    }
}
