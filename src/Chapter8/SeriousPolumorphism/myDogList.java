package Chapter8.SeriousPolumorphism;

public class myDogList {

    private Dog[]dogs = new Dog[5];

    //We’ll increment this each time
    //a new Dog is added.
    private int index =0;



    public void add(Dog d){
        if(index < dogs.length){
            dogs[index] = d;

            System.out.println("Dog added at:"  + index);
            index++;
        }
    }
}
