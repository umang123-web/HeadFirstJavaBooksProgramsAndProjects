package chapter12.lambdasandstreamwhatNothow;

import Chapter2Classes.and.Object.StreamingSong;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LimitWithStream {

    public static void main(String[] args) {
        List<String> list = List.of("I" , "am" , "very" , "best" , "programmer");

        /*
        Stream<String> limit = list.stream().limit(5);
        //long result = limit.count();
        List<String> result = limit.collect(Collectors.toList());
        System.out.println(result);

         */

        //list.stream().limit(5).collect(Collectors.toList()).forEach(System.out::println);

        //collect are Terminal operations and skip , sorted , limit are intermidate operations
        //list.stream().skip(2).sorted().collect(Collectors.toList()).forEach(System.out::println);


        Comparator<String> comparator = (s1 , s2)->s1.compareTo(s2);
        Runnable runnable = ()->System.out.println("hello");
        Consumer<String> consumer = (str)-> System.out.println(str);

    }

}
