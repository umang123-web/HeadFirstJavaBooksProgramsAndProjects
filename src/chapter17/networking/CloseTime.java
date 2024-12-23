package chapter17.networking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CloseTime {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadpool = Executors.newFixedThreadPool(2);
//Start two jobs, a short one that
//just prints the name and a “long”
//one that uses a sleep so it can
//pretend to be a long-running
//job (LongJob and ShortJob are
//classes that implement Runnable).
        threadpool.execute(()->new LongJob("long job"));
        threadpool.execute(()->new ShortJob("short job"));

//        Ask the ExecutorService
//to shut down. If you call
//execute() with a job
//after this, you will get a
//RejectedExecutionException.
//The ExecutorService will
//continue to run all the jobs
//that are running, and run any
//waiting jobs too.
        threadpool.shutdown();
//Wait up to 5 seconds for the
//ExecutorService to finish
//everything. If this method hits
//the timeout before everything
//has finished, it returns “false.”
        boolean finished = threadpool.awaitTermination(5 , TimeUnit.MILLISECONDS);
//At this point, we tell the ExecutorService to stop
//everything right now. If everything was already shut
//down, that’s fine; this won’t do anything.
        threadpool.shutdownNow();
    }


}
class LongJob{
    String message;

    public LongJob(String message){
        this.message = message;
    }
}

class ShortJob{
    String message;
    public ShortJob(String message){
        this.message = message;
    }
}