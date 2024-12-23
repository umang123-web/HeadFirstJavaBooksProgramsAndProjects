package chapter12.lambdasandstreamwhatNothow;

import java.util.List;

public class MixForLoop {

    public static void main(String[] args) {
        List<Integer> list =List.of(10 , 12 , 15 , 40 , 20);


        /*
        String output = "";

        for (int num : list){
            output+=num;
            System.out.println(num);
        }

         */

        list.stream().filter(num->num%2==0).forEach(System.out::println);
    }
}
