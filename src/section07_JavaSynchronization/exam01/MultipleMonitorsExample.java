package section07_JavaSynchronization.exam01;

class BankAccount {
    private double balance;
    private final Object lock = new Object();

    public BankAccount(double initialBalance){
        this.balance = initialBalance;
    }

    public void deposit(double amount){
        synchronized(lock){
            balance += amount;
        }
    }

    public boolean withdraw(double amount){
        synchronized(lock){
            if(balance < amount){
                return false;
            }
            balance -= amount;
            return true;
        }
    }

    public boolean transfer(BankAccount to, double amount){
        synchronized (this.lock){
            if(this.withdraw(amount)){
                synchronized(to.lock){
                    to.deposit(amount);
                    return true;
                }
            }
            return false;
        }
    }

    public String getBalance(){
        return Double.toString(this.balance);
    }
}
public class MultipleMonitorsExample {

    public static void main(String[] args) throws InterruptedException {
        BankAccount accountA = new BankAccount(1000);
        BankAccount accountB = new BankAccount(1000);

        Thread t1 = new Thread(()-> {
            for(int i = 0; i < 10; i++){
                boolean result = accountA.transfer(accountB, 10);
                if(result){
                    System.out.println("accountA에서 accountB로 10 송금 성공");
                }else{
                    System.out.println("accountA에서 accountB로 10 송금 실패");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 10; i++){
                boolean result = accountB.transfer(accountA, 10);
                if(result){
                    System.out.println("accountB에서 accountA로 10 송금 성공");
                }else{
                    System.out.println("accountB에서 accountA로 10 송금 실패");
                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("accountA의 잔액 : " + accountA.getBalance());
        System.out.println("accountB의 잔액 : " + accountB.getBalance());
    }
}
