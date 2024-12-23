package chapter18.dealingwithconcurrencyissue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class RyanAndMonicaJob1 implements  Runnable{
    private final String name;
    private final BankAccount1 account;
    private final int amountToSpend;

    public RyanAndMonicaJob1(String name , BankAccount1 account , int amountToSpend){
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
        account.spend(name , amount);
              System.out.println(name + " is about to spend");
                System.out.println(name + " is finish spending");

        }
    }


class BankAccount1{

    /*
    * Store the balance in an AtomicInteger, with
the same initial value ($100) as before.
    * */
    private final AtomicInteger balance =new AtomicInteger(100);
    public int getBalance() {
        return balance.get();
    }

    public void spend(String  name  , int amount){
        /*
        * Like before, check if there’s enough money. This
time, keep a record of the balance.
        * */
        int initalbalance = balance.get();
        if(initalbalance >=amount){
            /*
            * The balance will NOT be changed if
the initial balance does not match
the actual balance right now
*
*
* Pass in the balance from
when we checked if there
was enough money.
            * */
            boolean success = balance.compareAndSet(initalbalance , initalbalance -amount);

            if(!success){

                /*
                * If success was false, the
money was NOT spent.
Tell Ryan or Monica it
didn’t workand they can
decide what to do
                * */
                System.out.println("sorry" + name + "You haven't spend the money");
            }else {
                System.out.println("sorry not enough for :" + name);
            }
        }
    }

}
public class RyanAndMonicaTest1 {
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
