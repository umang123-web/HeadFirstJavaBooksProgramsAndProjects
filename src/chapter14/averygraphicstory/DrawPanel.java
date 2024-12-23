package chapter14.averygraphicstory;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

    public static void main(String[] args) {

        DrawPanel panel = new DrawPanel();
        JFrame frame = new JFrame();

        frame.setSize(400 , 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);

    }

    public void paintComponent(Graphics g){

        g.setColor(Color.orange);

        g.fillRect(20 , 50 , 100 ,100);

        /*
        * Yourfilename goes here. Note: if you re using an IDE and have difficulty
        * try this line of code instead:
        * Image image = new ImageIcon(getClass().getResource("download.jpeg")).getImage();
        * */
        Image image = new ImageIcon(getClass().getResource("download.jpeg")).getImage();


        /*
        * The x , y coordinates for where
        * the picture top left corner should go. This says 3 pixel from the left edge
        * of the panel and 4 pixel from the top edge of the panel. these number are always
        * relative to the widget (In this case your Jpanel subclass) , not the entire frame
        * */
        g.drawImage(image , 3 , 4 , this);


    }
}
