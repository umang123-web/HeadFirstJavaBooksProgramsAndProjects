package chapter17.networking;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PredicateableLatch {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        /*
        * Create a new
CountDownLatch. This latch
lets us "wait for the signal."
We have one event we want
to wait for (the main thread
prints its message), so we set
this up with a value of “1.”
        * */
        CountDownLatch latch = new CountDownLatch(1);

        executor.execute(()-> {
            try {
//                Pass the
//CountDownLatch to
//the job that’s going to
//run on the new thread.
                waitForLatchPrint(latch);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("back in main");
//        Tell the latch to count down when the
//main method has printed its message.
        latch.countDown();
        executor.shutdown();

    }

    private static void waitForLatchPrint(CountDownLatch latch) throws InterruptedException {

//        Wait for the main thread to print out its message. This
//thread will be in a non-runnable state while its waiting

//        await() can throw an
//InterruptedException, which needs to
//be caught or declared.
        latch.await();
        System.out.println("top o the stack");
    }
}
