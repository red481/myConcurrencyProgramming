package section09_JavaSynchronizationTools.exam02;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerGetAndUpdateExample {
    private static AtomicInteger accountBalance = new AtomicInteger(1000);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int withdrawalAmount = 500;
                int updatedBalance = accountBalance.getAndUpdate(balance -> {

                    if(balance >= withdrawalAmount) {
                        return balance - withdrawalAmount;
                    } else {
                        return balance;
                    }
                });

                if(updatedBalance < 0) {
                    System.out.println("잔고 부족으로 출금 실패");
                } else {
                    System.out.println("출금 후 잔고: " + updatedBalance);
                }
            }).start();
        }
    }
}
