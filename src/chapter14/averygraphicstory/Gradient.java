package chapter14.averygraphicstory;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Gradient extends JPanel {

    public static void main(String[] args) {
        Gradient gradient = new Gradient();
        JFrame frame = new JFrame();
        frame.setSize(400 , 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(gradient);
    }

    /*
    Graphics g is really a Graphics2D object masquerding as a mere Graphics Object
    * */
    public void paintComponent(Graphics g){

        /*
        * Cast it so we can call something that Graphics2D has but Graphics doesn't
        * */
        Graphics2D g2d = (Graphics2D) g;

                                                           //starting point and starting color
        GradientPaint gradientPaint = new GradientPaint(70 , 70 , Color.blue ,

                //ending point and ending color
                150 , 150 ,Color.orange);
        g2d.setPaint(gradientPaint);

        /*
        *This fillOval() method really means fill the oval with
        * whatever is loaded on your paintbrush(i.e the gradient)
        * */
        g2d.fillOval(70 , 70, 100 , 100);


        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        Color color = new Color(red , green , blue);


        GradientPaint gradientPaint1 = new GradientPaint(70 , 70 , color , 150 , 150 , color);

        g2d.setPaint(gradientPaint1);

        g2d.fillOval(70 , 70 , 100 , 100);

    }
}
