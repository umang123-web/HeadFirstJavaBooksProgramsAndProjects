package chapter14.averygraphicstory;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class SimpleAnimation {

    private int xpos=70;
    private int ypos =80;
    public static void main(String[] args) {

        SimpleAnimation animation = new SimpleAnimation();
        animation.go();
    }

    public void go() {

        /*
        * Nothing new here. Make the widgets and put them in the frame
        *
        * */
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setVisible(true);
        MyDrawPanel2 drawPanel = new MyDrawPanel2();
        frame.getContentPane().add(drawPanel);
/*
* Repeat 130 times
* Tell the panel to repaint
* */

        for(int i=0; i<130; i++){
            /*
            * Increment the x coordinates and y coordinates*/
    xpos++;
    ypos++;
    /*
    * tell the panel to repaint itself (so we can see the circle in the new location)
    * */
    drawPanel.repaint();

    /*
    *
    * Pause between repaints (otherwise it will move
so quickly you won’t SEE it move). Don’t
worry, you weren’t supposed to already know
    * */
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        }


    }


    class MyDrawPanel2 extends JPanel{
        @Override
        public void paintComponent(Graphics g) {
            g.setColor(Color.green);

            /*
            *getWidth() and getHeight() are
methods inherited from JPanel.
            * */
            g.fillOval(xpos , ypos , this.getWidth() , this.getHeight());

        }
    }
}
