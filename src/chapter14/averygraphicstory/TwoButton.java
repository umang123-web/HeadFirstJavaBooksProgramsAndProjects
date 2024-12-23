package chapter14.averygraphicstory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TwoButton  {
    private JFrame frame;
    private JLabel label;

    public static void main(String[] args) {
        TwoButton button = new TwoButton();
        button.go();
    }
    public void go(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
        * Instead of passing (this) to the
button’s listener registration
method, pass a new instance of
the appropriate listener class
        *
        * */

        JButton button = new JButton("change Label");
        button.addActionListener(new LabelListener());

        JButton colorbutton = new JButton("Change circle");
        button.addActionListener(new ColorListener());

        label = new JLabel("I am a label");
        MyDrawPanel1 drawpanel = new MyDrawPanel1();

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


    class LabelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText("ouch!");
        }
    }
    class ColorListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
/*
*  When the user click , tell the frame to repaint() ,
* itself. that mean paintComponent() is called on every
* widget in the frame
* */
            frame.repaint();
        }
    }


    class MyDrawPanel1 extends JPanel{
        public void paintComponent(Graphics g){

            g.fillRect(0 , 0 , this.getWidth(), this.getHeight());


            Random random = new Random();
            int red = random.nextInt(256);
            int blue = random.nextInt(256);
            int green = random.nextInt(256);

            Color color = new Color(red , green , blue);

            g.setColor(color);

            g.fillOval(70 , 70 , 100 , 100);
        }

    }



}
