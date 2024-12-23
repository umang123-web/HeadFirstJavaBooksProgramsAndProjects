package chapter16.savingobjectsandText;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class QuizCardBuilder {

    private ArrayList<QuizCard>cardsList = new ArrayList<>();

    private JTextArea questions;
    private JTextArea answers;
    private JFrame frame;

    public static void main(String[] args) {
        QuizCardBuilder card = new QuizCardBuilder();
        card.go();
    }

    public void go(){

        frame = new JFrame();
        JPanel panel = new JPanel();
        Font bigfont = new Font("sanserif" , Font.BOLD , 24);


        questions = new JTextArea();
        JScrollPane scroller = createScroller(questions);
        panel.add(scroller);
        answers = new JTextArea();
        JScrollPane ascroler = createScroller(answers);
        panel.add(scroller);


        JButton button = new JButton("Next card");
        button.addActionListener(e->nextCard());
        panel.add(button);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu();

        JMenuItem menuItem = new JMenuItem();
        menuItem.addActionListener(e->clearAll());

        JMenuItem saveItem = new JMenuItem();
        saveItem.addActionListener(e -> saveCard());


        menu.add(menuItem);
        menu.add(saveItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        frame.getContentPane().add(BorderLayout.CENTER , panel);
        frame.setSize(500 , 500);
        frame.setVisible(true);


    }

    private JScrollPane createScroller(JTextArea area){
        JScrollPane scroller = new JScrollPane(area);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroller;
    }


    private JTextArea createTextarea(Font font){
        JTextArea text = new JTextArea(6 , 20);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setFont(font);
        return text;
    }


    private void nextCard(){
        QuizCard card = new QuizCard(questions.getText() , answers.getText());
        cardsList.add(card);
        clearCard();
    }

    private void saveCard(){
        QuizCard card = new QuizCard(questions.getText() , answers.getText());
        cardsList.add(card);
        JFileChooser filesave = new JFileChooser();
        filesave.showSaveDialog(frame);
        saveFile(filesave.getSelectedFile());

    }

    private void clearAll(){
        cardsList.clear();
        clearCard();
    }

    private void clearCard(){
        questions.setText(" ");
        answers.setText(" ");
        questions.requestFocus();
    }


    private void saveFile(File file){
        try{

            BufferedWriter br = new BufferedWriter(new FileWriter(file));

            for(QuizCard card : cardsList){
                br.write(card.getQuestions() + "\n");
                br.write(card.getAnswers() + "\n");

            }
            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
