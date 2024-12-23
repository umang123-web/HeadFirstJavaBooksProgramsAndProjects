package Chapter8.SeriousPolumorphism;

public class Catlist {

    private Cat[]cats = new Cat[5];
    private int index =0;


    void add(Cat cat){
        if(index < cats.length){
            cats[index] = cat;
            System.out.println("cat added:"  + index);
            index++;
        }
    }
}
