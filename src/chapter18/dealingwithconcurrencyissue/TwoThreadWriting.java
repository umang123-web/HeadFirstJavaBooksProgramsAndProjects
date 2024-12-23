package chapter18.dealingwithconcurrencyissue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TwoThreadWriting {
    public static void main(String[] args) {
        ExecutorService threadpool = Executors.newFixedThreadPool(2);

Data data = new Data();

        threadpool.execute(()-> {
                    try {
                        addLetterToData('a', data);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        threadpool.execute(()-> {
            try {
                addLetterToData('A' , data);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        threadpool.shutdown();
    }

    private static void addLetterToData(char a, Data data) throws InterruptedException {
        for(int i=0; i<26; i++){
            data.addLetter(a++);

            Thread.sleep(50);

            System.out.println(Thread.currentThread().getName() + data.getLetters());
            System.out.println(Thread.currentThread().getName() + "size =" + data.getLetters().size());

        }


    }
}
 final class Data{

        private final List<String> letters = new CopyOnWriteArrayList<>();
        public List<String>getLetters(){
            return letters;
        }
// we can use synchronized keyword or CopyOnWriteArrayList
        public void addLetter(char letter){
            letters.add(String.valueOf(letter));

        }
}


