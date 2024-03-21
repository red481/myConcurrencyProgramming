package section05_synchronizationConcept.exam04;

public class ThreadSafeMemberReferenceObjectExample {
    public static void main(String[] args) throws InterruptedException {

        new Thread(new MyRunnable(new Company("User"))).start();
        new Thread(new MyRunnable(new Company("User"))).start();

        Thread.sleep(1000);
        System.out.println("======================================");

        Company company = new Company("User");
        new Thread(new MyRunnable(company)).start();
        new Thread(new MyRunnable(company)).start();
    }
}

class MyRunnable implements Runnable {
    private Company company;

    public MyRunnable(Company company) {
        this.company = company;
    }

    @Override
    public void run() {
        company.changeName(Thread.currentThread().getName());
    }
}

class Company{
    private Member member;

    public Company(String name){
        member = new Member(name);
    }

    public synchronized void changeName(String name){
        String oldName = member.getName();
        member.setName(name);
        System.out.println(oldName + ": " + member.getName());
    }
}

class Member{
    private String name;

    public Member(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return "Member{" + "name='" + name + '\'' + '}';
    }
}
