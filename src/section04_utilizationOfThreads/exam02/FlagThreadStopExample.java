package section04_utilizationOfThreads.exam02;

import java.util.Scanner;

/*
    * volatile이나 atomic 등의 키워드가 없다면 Flag를 false로 선언해도 의도한대로 동작 하지 않을 수 있다.
    * 이러한 문제의 원인은 CPU의 코어마다 캐시가 존재하기 때문에 발생한다.
    * 따라서 메모리에 변경한 값이 실제로 적용되는 시간은 코어마다 다르다.
    * sleep() 등의 메소드로 대기 상태로 전환하면 캐시를 비우고 메모리에 있는 값을 다시 읽어오기 때문에 문제가 발생하지 않는다.
 */

public class FlagThreadStopExample {

    private volatile static boolean running = true;

    public static void main(String[] args) {

        new Thread(() -> {
            int count = 0;
            while(running){
                count++;
            }
            System.out.println(Thread.currentThread().getName() + " 종료, count : " + count);
        }).start();

        new Thread(() -> {
            int count = 0;
            String command = "";
            Scanner sc = new Scanner(System.in);
            command = sc.nextLine();
            if(command.equals("q")){
                running = false;
            }
            System.out.println(Thread.currentThread().getName() + " 종료, count : " + count);
        }).start();
    }
}
