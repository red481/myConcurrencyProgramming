package section04_utilizationOfThreads.exam05.logger;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalLogger {

    //private static final ThreadLocal<List<String>> THREAD_LOG = ThreadLocal.withInitial(() -> new ArrayList<>());

    private static final List<String> THREAD_LOG = new ArrayList<>();
    public static void addLog(String log){
        THREAD_LOG.add(log);
    }

    public static void printLog(){
        List<String> logs = THREAD_LOG;
        System.out.println("[" + Thread.currentThread().getName() + "]" + String.join("->", logs));
    }

    public static void clearLog(){
       // THREAD_LOG.remove();
    }

    static class ServiceA{
        public void process(){
            addLog("ServiceA 로직 수행");
        }
    }

    static class ServiceB{
        public void process(){
            addLog("ServiceB 로직 수행");
        }
    }

    static class ServiceC{
        public void process(){
            addLog("ServiceC 로직 수행");
        }
    }

}
