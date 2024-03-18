package section04_utilizationOfThreads.exam04;

public class ThreadGroupExample {

    public static void main(String[] args) {
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup customThreadGroup = new ThreadGroup("Custom Thread Group");

        Thread defaultGroupThread = new Thread(new GroupRunnable(), "DefaultGroupThread");

        Thread mainGroupThread = new Thread(mainThreadGroup, new GroupRunnable(), "MainGroupThread");

        GroupThread customGroupThread = new GroupThread(customThreadGroup, new GroupRunnable(), "CustomGroupThread");

        GroupThread customGroupThreadOfSon = customGroupThread.createThread();

        defaultGroupThread.start();
        mainGroupThread.start();
        customGroupThread.start();
        customGroupThreadOfSon.start();



    }

    static class GroupRunnable implements Runnable {
        @Override
        public void run(){
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName() + " 는 " + currentThread.getThreadGroup().getName() + "에 속해있습니다.");
        }
    }

    static class GroupThread extends Thread {
        public GroupThread(ThreadGroup group, Runnable target, String name){
            super(group, target, name);
        }

        public GroupThread createThread(){
            return new GroupThread(this.getThreadGroup(), new GroupRunnable(), "CustomGroupThread2");
        }
    }
}
