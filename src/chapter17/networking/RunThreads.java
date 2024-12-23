package chapter17.networking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunThreads {
    public static void main(String[] args) {
//        Create an ExecutorService with a
//fixed-sized thread pool (we know
//we’re going to run only two jobs).
        ExecutorService threadpools = Executors.newFixedThreadPool(2);
//        A lambda expression that represents our
//Runnable job. If you don’t want to use
//lambdas, here you’d pass in a new instance
//of your Runnable class, like we did when we
//created MyRunnable earlier in the chapter.

        threadpools.execute(()->runJob("1"));
        threadpools.execute(()->runJob("2"));

        threadpools.shutdown();
    }
    private static void runJob(String jobname){
        for(int i =0; i<25; i++){
            String threadname=Thread.currentThread().getName();
//            The job is to run through this loop,
//printing the thread’s name each time.
            System.out.println(jobname  + "is running on" + threadname);
        }
    }
}
