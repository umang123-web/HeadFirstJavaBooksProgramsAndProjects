package chapter14.averygraphicstory;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InnerButton {
    private JButton jButton;
    public static void main(String[] args) {

        InnerButton button = new InnerButton();
        button.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        frame.setSize(400 , 400);
        frame.setVisible(true);
        jButton = new JButton("A");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(BorderLayout.CENTER , jButton);
        jButton.addActionListener(new ButtonListener());

    }

class ButtonListener implements ActionListener{


    @Override
    public void actionPerformed(ActionEvent e) {
        if(jButton.getText().equals("A")){
            jButton.setText("B");
        }else {
            jButton.setText("A");
        }
    }
}


}
