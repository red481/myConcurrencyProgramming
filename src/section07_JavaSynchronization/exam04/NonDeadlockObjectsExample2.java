package section07_JavaSynchronization.exam04;

public class NonDeadlockObjectsExample2 {
    public static void main(String[] args) {

        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();

        Thread thread1 = new Thread(() -> {
            resourceA.methodA(resourceB);
        });

        Thread thread2 = new Thread(() -> {
            resourceB.methodB(resourceA);
        });

        thread1.start();
        thread2.start();
    }

    static class ResourceA {

        public void methodA(ResourceB resourceB) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + ": methodA의 ResourceA 부분 실행");
            }
            resourceB.methodB2();
        }

        public synchronized void methodA2() {
            System.out.println(Thread.currentThread().getName() + ": methodA2 실행");
        }
    }

    static class ResourceB {

        public void methodB(ResourceA resourceA) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + ": methodB의 ResourceB 부분 실행");
            }
            resourceA.methodA2();
        }

        public synchronized void methodB2() {
                System.out.println(Thread.currentThread().getName() + ": methodB2 실행");
        }
    }
}
