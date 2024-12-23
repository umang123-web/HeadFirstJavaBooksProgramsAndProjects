package chapter14.averygraphicstory;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static javax.sound.midi.ShortMessage.*;

/*
* Version Three: drawing graphics in time with the music
*
* */
public class MiniMusicPlayer3 {

    private MyDrawPanel panel;
    private Random random = new Random();

    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException {
        MiniMusicPlayer3 mini = new MiniMusicPlayer3();
        mini.go();
    }

    public void setgui(){
        JFrame frame = new JFrame("my first music video");
        panel = new MyDrawPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400 , 400);
        frame.setBounds(30 , 30 , 300 , 300);
         frame.setContentPane(panel);
    }

    public void go() throws MidiUnavailableException, InvalidMidiDataException {
        setgui();

        Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.open();
        Sequence seq = new Sequence(Sequence.PPQ , 4);
        Track track = seq.createTrack();

        int note;
        for(int i=0; i<60; i+=4){
            note = random.nextInt(50)+1;
            track.add(makeEvent(NOTE_ON , 1 , note , 100 , i));
            track.add(makeEvent(CONTROL_CHANGE , 1 , 127 , 0 , i));
            track.add(makeEvent(NOTE_OFF , 1 , note , 100 , i+2));

        }

            sequencer.setSequence(seq);
             sequencer.start();
             sequencer.setTempoInBPM(120);



    }

    public static MidiEvent makeEvent(int cmd , int chn1 , int one , int two , int tick){
        MidiEvent event =null;
        try {
            ShortMessage message = new ShortMessage();
            message.setMessage(cmd , chn1 , one , two);
            event = new MidiEvent(message , tick);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }

        return event;
    }

    class MyDrawPanel extends JPanel implements ControllerEventListener{

        private boolean mssg =false;
        @Override
        public void controlChange(ShortMessage event) {

            mssg =true;
            repaint();
        }

        public void paintComponent(Graphics g){
            if(mssg){
                int red=random.nextInt(250);
                int green = random.nextInt(250);
                int blue = random.nextInt(250);

                g.setColor(new Color(red , green , blue));

                int height = random.nextInt(120)+ 10;
                int width = random.nextInt(120)+10;
                int xpos =random.nextInt(40)+10;
                int ypos = random.nextInt(40)+10;
                g.fillRect(xpos , ypos , width , height);

                mssg =false;



            }
        }
    }

}
