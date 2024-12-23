package chapter14.averygraphicstory;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RandomCircle extends JPanel {

    public static void main(String[] args) {

        RandomCircle circle = new RandomCircle();
        JFrame frame = new JFrame();
        frame.setSize(400 , 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(circle);
    }

    public void paintComponent(Graphics g){
        /*
        * The first two args defines(x , y) upper left corner , relative to the panel for
        * where drawing starts , so 0 , 0 , means "starts 0 pixel from the left edge and 0 pixel"
        * from the top edge. The other two args say Make  the width of this rectangle as wide as
        * the panel (this width()) and make the height as tall as the panel ((this.height))
        *
        * */



        g.fillRect(0 ,0 , this.getWidth() , this.getHeight());


        /*
        * Earlier we used Math.random but now we know how to use the java libraries
        * we can use java.Util.Random. It has a nextInt method  that takes a max value
        * and returns a number between 0 (inclusive) and this max value(not inclusive) In this case 0-256
        *
        * */
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        /*
        * You can make a color by passing in 3 ints to represent the rgb values*/
        Color color = new Color(red , green , blue);

        g.setColor(color);

        /*
        * starts 70 pixel from the left , 70 from the top
        * make it 100 pixel wide and 100 pixel top
        * */
        g.fillOval(70 , 70 , 100 , 100);

    }
}
