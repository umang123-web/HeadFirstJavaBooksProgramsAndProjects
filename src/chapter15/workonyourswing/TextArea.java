package chapter15.workonyourswing;

import javax.swing.*;
import java.awt.*;

public class TextArea {

    public static void main(String[] args) {
        TextArea area = new TextArea();
        area.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        JButton button = new JButton("click me");
// 10 means 10 lines (sets the perferred height)
//        20 means 20 columns (set the perferred width)
        JTextArea textArea = new JTextArea(10 , 20);
//Make a JScrollPane and give it to the text area that s' going to scroll for
        JScrollPane scroller = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        button.addActionListener(s->textArea.append("clicked" + "\n"));

//        Tell the scroll pane to use only vertical scrollbar
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy       (ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        /*
        * Important!! You give the text area to the scroll pane (through the
scroll pane constructor), and then add the scroll pane to the panel.
You don’t add the text area directly to the panel
        *
        * */
        panel.add(scroller);

        frame.setSize(350 , 350);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.SOUTH , button);
        frame.getContentPane().add(BorderLayout.CENTER , panel);
    }
}
