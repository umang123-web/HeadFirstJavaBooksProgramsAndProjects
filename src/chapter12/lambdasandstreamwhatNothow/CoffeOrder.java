package chapter12.lambdasandstreamwhatNothow;

import java.util.List;
import java.util.stream.Collectors;

public class CoffeOrder {
    public static void main(String[] args) {
        List<String> coffee = List.of("Cappucinno" , "Americano" , "Espresso" , "Cortado" ,
                "Mocha" , "Cappucinno" , "Flat White" , "Latte");


        List<String>coffeeorder = coffee.stream().filter(s->s.endsWith("o")).sorted().distinct().collect(Collectors.toList());

        System.out.println(coffeeorder);

    }
}
