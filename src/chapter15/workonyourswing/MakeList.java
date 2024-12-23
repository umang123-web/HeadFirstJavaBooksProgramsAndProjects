package chapter15.workonyourswing;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MakeList implements ListSelectionListener {
/*
*       Jlist constructor takes an array of any object type.
* They don't have to be strings but a String representation
* will appear at a list
* */
    String[] Listentries={"alpha" , "beta " , "gamma" , "delta" , "epsilon" , "zeta"};

    /*
    * JList is a generic class so you can declare  what type of obejct
    * are in the list
    * */
    JList<String>list = new JList<>(Listentries);


    public static void main(String[] args) {

        MakeList makelist = new MakeList();
        makelist.go();
    }

    public void go() {

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);


        list.setVisibleRowCount(4);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list.addListSelectionListener(this);

        /*
        * This is just  like with Jtext area you make a scroll pane(and give it to the list)and
        * then add the scroll pane (not in the list) to the panel
        * */

        JScrollPane scroller = new JScrollPane(list);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scroller);


    }

    /*
    * You will get the event twice if you don't put
    * in this if test
    *
    * */

    public void valueChanged(ListSelectionEvent event){
        if(!event.getValueIsAdjusting()){

            /*
            * get selectedvalue() actually return an object. A list isn't limited to only String objects
            * */
            String selection = list.getSelectedValue();

            System.out.println(selection);
        }


    }
}

