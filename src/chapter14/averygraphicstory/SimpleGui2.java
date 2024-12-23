package chapter14.averygraphicstory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
* Implement the interface. This says an instance of SimpleGui2 IS-A ActionListener
*
* This button will give event only to ActionListener implementors
*
*
* Note: You wouldn't make your main Gui class implements ActionListener like this, this is
* just the simplest way to get started. We will see better ways of creating ActionListener
* as we go through this chapter
*
*
*
* */
public class SimpleGui2 implements ActionListener {

    private JButton button;
    public static void main(String[] args) {

     SimpleGui2 gui = new SimpleGui2();
     gui.go();

    }

    public void go(){
        JFrame frame = new JFrame();
        button = new JButton("click me");
        frame.getContentPane().add(button);
        frame.setSize(400 , 400);
        frame.setVisible(true);

        /*
        * Register your interest with
        * the button. This says to the button "Add me to your list of listener
        * The arguments you pass must be an object from a class that implements
        * Action Listener*/
        button.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*
    * implement the action Listener interface action performed() method.
    * This is the actual event handling method
    *
    *
    * The button calls this method to let you know an event
    * happened it sends you an ActionEvent object as the
    * argument , but we don't need it here. Knowing
    * the event happened is enough info for us.
    * */
    @Override
    public void actionPerformed(ActionEvent e) {

        button.setText("I have been clicked");
    }
}
