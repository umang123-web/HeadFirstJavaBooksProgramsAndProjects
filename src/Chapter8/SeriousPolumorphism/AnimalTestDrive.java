package Chapter8.SeriousPolumorphism;

public class AnimalTestDrive {

    public static void main(String[] args) {
        Animallist animallist = new Animallist();
        Dog dog = new Dog("Tom");
        Cat cat = new Cat("kitty");
        Dog dog1 = new Dog("Hell");
        Cat cat1 = new Cat("Bell");
        animallist.addAnimal(cat);
        animallist.addAnimal(dog);
        animallist.addAnimal(dog1);
        animallist.addAnimal(cat1);


    }
}
