package chapter18.dealingwithconcurrencyissue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class RyanAndMonicaJob implements  Runnable{
    private final String name;
    private final BankAccount account;
    private final int amountToSpend;

    public RyanAndMonicaJob(String name , BankAccount account , int amountToSpend){
        this.name = name;
        this.account =account;
        this.amountToSpend = amountToSpend;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            goShopping(amountToSpend);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void  goShopping(int amount){
//        Check the account balance, and if there’s
//enough money, we go ahead and spend the
//money, just like Ryan and Monica did.

        synchronized (account) {
            if (account.getBalance() >= amount) {
                System.out.println(name + " is about to spend");
                account.spend(amount);
                System.out.println(name + " is finish spending");
            } else {
                System.out.println("Sorry not enough for:" + name);
            }
        }
    }
}

class BankAccount{
//    The account starts with a
//balance of $100.
    private int balance =100;

    public int getBalance() {
        return balance;
    }

    public void spend(int amount){
        balance = balance-amount;
        if(balance < 0){
            System.out.println("Overdrawn!");
        }
    }

}
public class RyanAndMonicaTest {
    public static void main(String[] args) {
//        There will be only ONE instance of the
//BankAccount. That means both threads
//will access this one account.
        BankAccount account = new BankAccount();
//        Make two jobs
//that will do the
//withdrawal from
//the shared bank
//account, one for
//Monica and one for
//Ryan, passing in the
//amount they’re going
//to spend.
        RyanAndMonicaJob ryan = new RyanAndMonicaJob("Ryan" , account , 50);
        RyanAndMonicaJob monica = new RyanAndMonicaJob("Monica" , account , 100);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        start both job
        executorService.execute(ryan);
//        running
        executorService.execute(monica);
        executorService.shutdown();
    }
}
