package Chapter8.SeriousPolumorphism;

public class Animallist {

    private Animal[] animal = new Animal[5];

    private int index =0;

    void addAnimal(Animal a){
        if(index < animal.length){
            animal[index] = a;
            System.out.println("Animal added:" + index);
            index++;
        }
    }
}
