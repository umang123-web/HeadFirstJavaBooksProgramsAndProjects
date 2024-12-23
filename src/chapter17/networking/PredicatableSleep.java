package chapter17.networking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PredicatableSleep {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
//Instead of putting a lambda with an ugly trycatch inside, we’ve put the job code inside a
//method. We’re calling the method from this lambda
//expression.
        executor.execute(()-> {
            try {
                sleepThanprint();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("back in main");
        executor.shutdown();
    }

    private static void sleepThanprint() throws InterruptedException {
//        Calling sleep here will force the new
//thread to leave the currently running
//state. The main thread will get a chance to print out back in main

//        Thread.sleep() throws a checked
//Exception that we need to catch
//or declare. Because catching the
//Exception makes the job’s code a
//bit longer, we’ve put it into its own
//method.
        TimeUnit.SECONDS.sleep(2);
//        There will be a pause
//(for about two
//seconds) before we
//get to this line, which
//prints out “top o’ the
//stack.”
        System.out.println("main thread");
    }
}
