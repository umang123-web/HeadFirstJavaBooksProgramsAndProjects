package chapter16.savingobjectsandText;

import java.io.*;

public class GameSaverTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GameCharacter character = new GameCharacter(50 , "Elf" , new String[]{"bow" , "sword" , "dust"});
        GameCharacter character1 = new GameCharacter(60 , "Troll hand" , new String[]{
                "bare hands" , "big ax"});
        GameCharacter character2 = new GameCharacter(80 , "Magician" , new String[]{"spells" , "invisibility"});


        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("game.txt"));
        obj.writeObject(character);
        obj.writeObject(character1);
        obj.writeObject(character2);

        obj.close();


//        To serialize an object, you need an ObjectOutputStream (from the java.io
//package).

        ObjectInputStream object = new ObjectInputStream(new FileInputStream("game.txt"));
        GameCharacter restore1 = (GameCharacter) object.readObject();
        GameCharacter restore2 =(GameCharacter) object.readObject();
        GameCharacter restore3 = (GameCharacter) object.readObject();

        System.out.println("One type:" + restore1.getType() + "\n" +
                "Two type:"  + restore2.getType() + "\n" +
                "three type:" + restore3.getType());



    }
}
