package chapter14.averygraphicstory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TwoButton1 {
    private JFrame frame;
    private JLabel label;

    public static void main(String[] args) {
        TwoButton1 button = new TwoButton1();
        button.go();
    }
    public void go(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JButton button = new JButton("change Label");
        button.addActionListener(event ->label.setText("ouch"));

        JButton colorbutton = new JButton("Change circle");
        button.addActionListener(event ->frame.repaint());

        label = new JLabel("I am a label");
        MyDrawPanel drawpanel = new MyDrawPanel();

        /*
        * Add two widget
        * button and drawing panel to the two regions of
        * the frame*/
        frame.getContentPane().add(BorderLayout.SOUTH , colorbutton);
        frame.getContentPane().add(BorderLayout.CENTER , drawpanel);
        frame.getContentPane().add(BorderLayout.EAST , button);
        frame.getContentPane().add(BorderLayout.WEST , label);


        frame.setSize(500 , 400);
        frame.setVisible(true);

    }




    class MyDrawPanel extends JPanel{
        public void paintComponent(Graphics g){

            g.fillRect(0 , 0 , this.getWidth(), this.getHeight());


            Random random = new Random();
            int red = random.nextInt(256);
            int blue = random.nextInt(256);
            int green = random.nextInt(256);

            Color color = new Color(red , green , blue);

            g.setColor(color);

/*
*
* 70 pixels from the left,
70 pixels from the top
* */
            g.fillOval(70 , 70 , 100 , 100);
        }

    }



}
