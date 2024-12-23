package chapter16.savingobjectsandText;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class QuizCardPlayer {

    private ArrayList<QuizCard>cardsList = new ArrayList<>();

    private int currentCardIndex;
    private QuizCard currentcard;

    private JTextArea display;
    private JButton nextbutton;
    private boolean isShowAnswer;
    private JFrame frame;

    public static void main(String[] args) {
        QuizCardPlayer card = new QuizCardPlayer();
        card.go();
    }

    public void go() {

        frame = new JFrame();
        JPanel panel = new JPanel();
        Font bigfont = new Font("sanserif", Font.BOLD, 24);


        display = new JTextArea(10, 20);
        display.setFont(bigfont);
        display.setLineWrap(true);
        display.setEditable(false);

        JScrollPane scroller = new JScrollPane(display);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroller);


        nextbutton = new JButton("Show question");
        nextbutton.addActionListener(e->nextCard());

panel.add(nextbutton);


JMenuBar bar = new JMenuBar();
JMenu filemenu = new JMenu("file");
JMenuItem menuItem = new JMenuItem("Load card set");
menuItem.addActionListener(e -> {
    try {
        open();
    } catch (IOException ex) {
        throw new RuntimeException(ex);
    }
});
filemenu.add(menuItem);
bar.add(menuItem);
frame.setJMenuBar(bar);



frame.getContentPane().add(BorderLayout.CENTER,panel);
frame.setSize(500 , 400);
frame.setVisible(true);


    }

    private void nextCard(){
        if(isShowAnswer){
            display.setText(currentcard.getAnswers());
            nextbutton.setText("Next card");
            isShowAnswer = false;

        }else {
            if(currentCardIndex < cardsList.size()){
                showNextCard();
            }else {
                display.setText("That was last card");
                nextbutton.setEnabled(false);
            }
        }
    }


    private void open() throws IOException {
        JFileChooser fileopen =new JFileChooser();
        fileopen.showSaveDialog(frame);
        loadfile(fileopen.getSelectedFile());
    }

    private void loadfile(File file) throws IOException {
        cardsList = new ArrayList<>();
        currentCardIndex =0;

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine())!=null){
            makecard(line);
        }
        reader.close();

        showNextCard();
    }


    private void makecard(String lineToParse){
        String[]result =lineToParse.split("/");
        QuizCard card = new QuizCard(result[0],result[1] );
        cardsList.add(card);
        System.out.println("made a card");
    }

    private void showNextCard(){
        currentcard = cardsList.get(currentCardIndex);
        currentCardIndex++;
        display.setText(currentcard.getQuestions());
        nextbutton.setText("Show Answer");
        isShowAnswer=true;
    }

}
