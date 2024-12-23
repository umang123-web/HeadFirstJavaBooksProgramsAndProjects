package chapter18.dealingwithconcurrencyissue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LostUpdate {
    public static void main(String[] args) throws InterruptedException {
        /*
        * Create a thread pool to run
all the jobs. If you add more
threads here, you may see even
more missing updates.

        * */
        ExecutorService pools = Executors.newFixedThreadPool(6);
        Balance balance = new Balance();

        /*
        * Run 1,000 attempts to update
the balance, on different threads.
        * */
        for(int i=0; i<1000; i++){
            pools.execute(()->balance.increment());
        }
        pools.shutdown();

        /*
        * Make sure the pool has finished
running all the updates before
printing the final balance. In
theory, this should be 1,000. If
it’s any less than that, we’ve lost
an update!
        * */
        if(pools.awaitTermination(1 , TimeUnit.MINUTES)){
            System.out.println("balance =" + balance.balance);
        }
    }


}

class Balance{

    /*
    * Here’s the crucial part! We increment the balance
by adding 1 to whatever the value of balance was AT
THE TIME WE READ IT (rather than adding 1 to
whatever the CURRENT value is). You might think
that “++” is an atomic operation, but it is not
    * */
//    int balance =0;

    /*
    * Classic concurrency gotcha: this looks like
a single operation, but it’s actually more
than one—it's a read of the balance, an
increment, and an update to the balance.
    * */
//    public synchronized void increment(){
//
//        balance++;
//    }
/*
* Use an AtomicInteger initialized to
zero, instead of an int valu
*
* */
    AtomicInteger balance = new AtomicInteger(0);
    public void increment(){
        /*
        * incrementAndGet atomically adds one to the value, i.e., even if it’s used by multiple threads, it will safely increase the
value by one in a single operation. The incrementAndGet
method returns the new, updated value, but we don’t need that for our example; we’re not going to use the
returned value.
        * */
        balance.incrementAndGet();
    }

}
