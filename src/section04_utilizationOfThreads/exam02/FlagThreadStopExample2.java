package section04_utilizationOfThreads.exam02;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class FlagThreadStopExample2 {

    private AtomicBoolean running = new AtomicBoolean(true);

    public static void main(String[] args){
        new FlagThreadStopExample2().flagTest();
    }

    private void flagTest(){
        new Thread(() -> {
            long count = 0;
            while(running.get()){
                count++;
            }
            System.out.println("Thread 0 종료. Count: " + count);
        }).start();

        new Thread(() -> {
            int count = 0;
            String command = "";
            Scanner sc = new Scanner(System.in);
            command = sc.nextLine();
            if(command.equals("q")){
                running.set(false);
            }
            System.out.println(Thread.currentThread().getName() + " 종료, count : " + count);
        }).start();
    }
}
