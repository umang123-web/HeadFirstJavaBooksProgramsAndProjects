package Chapter6.JavaLibraries;

import java.util.ArrayList;

public class ArrayListMagnet {

    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<String>();
        a.add(0, "Zero");
        a.add(1, "one");
        a.add(2, "two");
        a.add(3, "three");


        printlist(a);

        if (a.contains("three")) {
            a.add("four");
        }
        a.remove(2);
        printlist(a);

        if(a.indexOf("four") !=4){
            a.add(4 , "4.2");
        }
        printlist(a);


        if(a.contains("two")){
            a.add("2.2");
        }

        printlist(a);
    }

    private static void printlist(ArrayList<String> a) {

        for(String element : a){
            System.out.println(element + " ");
        }

        System.out.println();
    }
}
