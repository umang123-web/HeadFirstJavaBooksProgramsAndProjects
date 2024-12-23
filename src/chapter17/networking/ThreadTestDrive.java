package chapter17.networking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ExecutorDrive{
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->{
            System.out.println(" top o the stack");
            System.out.println("back in main");
            executorService.shutdown();

        });


    }
}
public class ThreadTestDrive {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            System.out.println("new thread");
            System.out.println("main thread");
        });
        t.start();
    }
}
