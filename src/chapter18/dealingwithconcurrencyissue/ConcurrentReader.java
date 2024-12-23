package chapter18.dealingwithconcurrencyissue;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentReader {

    public static void main(String[] args) {
        /*
        *
        * new ArrayList used then its thrown the exception
        *
        * Stores the Chat
objects in an ArrayList,
which is NOT thread
safe
Create a writing thread
that adds to the List, and
two threads that read
from the list. Loop a few
times to try to provoke
the problem.*/

        /*
        * The java.util.concurrent package has a number of thread-safe data structures, and we’re
going to look at CopyOnWriteArrayList to solve this specific problem
        *
        * */
        List<Chat> chathistory =new CopyOnWriteArrayList<>();

        ExecutorService pool = Executors.newFixedThreadPool(3);
        for(int i=0; i<5; i++){
            pool.execute(()->chathistory.add(new Chat("Hii there")));
            pool.execute(()-> System.out.println(chathistory));
            pool.execute(()-> System.out.println(chathistory));

        }
        pool.shutdown();

    }
}

class Chat{
 private final String message;
 private final LocalDateTime timestamp;

 /*
 * Making an Object field “final” doesn’t guarantee
the data inside that object won’t change, just
that the reference won’t change. String and
LocalDateTime are immutable, so this is safe.
*
* Instances of Chat are immutable
*
 * */
 public Chat(String message){
     this.message = message;
     timestamp = LocalDateTime.now();
 }

    @Override
    public String toString() {
        return "Chat{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
