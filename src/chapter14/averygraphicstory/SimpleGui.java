package chapter14.averygraphicstory;

import javax.swing.*;
import java.awt.*;

public class SimpleGui {

    public static void main(String[] args) {

//        make a frame and a button
        JFrame frame = new JFrame();
//        You can pass the button constructor the text you want on the button
        JButton button = new JButton("click me");

        //this line makes the program quit as soon as you close
//        the window if you leave this out it will just sit there on the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        add the button to the frame control pane
        frame.getContentPane().add(button);
        frame.setSize(400 , 400);

        frame.setVisible(true);
        frame.setBackground(Color.cyan);}
}
