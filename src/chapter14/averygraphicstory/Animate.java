package chapter14.averygraphicstory;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Animate {

    int x =1;
    int y=1;

    public static void main(String[] args) {
        Animate animate = new Animate();
        animate.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        frame.setSize(400 , 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyDrawP drawP = new MyDrawP();
        frame.setBackground(Color.white);

        frame.getContentPane().add(drawP);

        for(int i=0;i<124; i++,y++, x++){
            x++;
            drawP.repaint();


            try{
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    class MyDrawP extends JPanel{
        public void paintComponent(Graphics g){
            g.setColor(Color.red);
            g.fillRect(0, 0 , 500 , 250);
            g.setColor(Color.blue);
            g.fillRect(x, y , 500-x*2, 250*y-2);
        }
    }
}
