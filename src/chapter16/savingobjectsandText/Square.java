package chapter16.savingobjectsandText;

import java.io.*;

public class Square implements Serializable {

    /*
    * transient says, “don’t
save this variable during
serialization; just skip it
    *
    * */

    transient private int width;
    private int height;

    public Square(int width , int height){
        this.width=width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static void main(String[] args) throws IOException {


        /*
        * Serializing the object and data
        *
        * */
        Square mysquare = new Square(15 , 20);

        FileOutputStream file = new FileOutputStream("hello.text");
        ObjectOutputStream object = new ObjectOutputStream(file);
        object.writeObject(mysquare);

        object.close();

    }
}
