package chapter15.workonyourswing;

import javax.swing.*;
import java.awt.*;

public class Button {
    public static void main(String[] args) {
        Button button = new Button();
        button.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(400 , 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("hello");
        JButton east = new JButton("hello");
        JButton west = new JButton("hello");
        JButton south = new JButton("hello");
        JButton center = new JButton("hello");
        Font f = new Font("serif" , Font.BOLD , 28);
        button.setFont(f);
        frame.getContentPane().add(BorderLayout.NORTH , button);
         frame.getContentPane().add(BorderLayout.EAST , east);
         frame.getContentPane().add(BorderLayout.WEST , west);
         frame.getContentPane().add(BorderLayout.SOUTH , south);
         frame.getContentPane().add(BorderLayout.CENTER , center);
    }
}
