package chapter15.workonyourswing;

import javax.swing.*;
import java.awt.*;

public class Panel {
    public static void main(String[] args) {
        Panel panel = new Panel();
        panel.go();
    }


    public void go(){
        JFrame frame = new JFrame();
        frame.setSize(400 , 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        frame.getContentPane().add(BorderLayout.EAST , panel1);
        /*

        Change the layout manager to be a new
instance of BoxLayout.
        * The BoxLayout constructor needs to know
the component it’s laying out (i.e., the panel)
and which axis to use (we use Y_AXIS for a
vertical stack).

        * */
        panel1.setLayout(new BoxLayout(panel1 , BoxLayout.Y_AXIS));
        JButton button = new JButton("Hello world");
        JButton button1 = new JButton("shock me");
        panel1.add(button);
        panel1.add(button1);
        panel1.setBackground(Color.darkGray);
    }
}
