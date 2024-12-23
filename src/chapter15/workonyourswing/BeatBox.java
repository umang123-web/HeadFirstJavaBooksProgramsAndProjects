package chapter15.workonyourswing;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

import static javax.sound.midi.ShortMessage.*;

public class BeatBox implements Serializable {

    /*
    * we store the checkbox in an ArrayList
    * */
    private ArrayList<JCheckBox>checkBoxList;

    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;

/*
* These are the names of the instrument as a String  array for buliding
* the Gui label on each row
* */
    String[] instrumentNames ={"Bass Drum" , "closed Hi-Hat" , "open Hi-hat" , "Acoustic Snare" , "Crash Cymbal"
            , "Hand Clap" , "High Tom" , "Hi Bingo" , "Maracus" , "Whistle" , "Low Conga" , "Cowbell" ,
    "Vibraslap" , "Low-mid Tom" , "High Agogo"};


    /*
    * These represent the actual drum "key"
    * The drum channel is like a piano except
    * each "key" on the piano ,except , each "key"
    * on the piano is a different drum. so the number 35 is the key for the bass drum 42 is closed
    * hi hat etc
    *
    * */
    int[]instrument ={35 , 42 , 46 , 38 , 49 , 39 , 50 , 60 , 70 , 72 , 64 , 65 , 56 , 58 , 47 , 67 , 63};

    public static void main(String[] args) {
        BeatBox beatBox = new BeatBox();
        beatBox.buildGui();
    }


    public void buildGui(){
        JFrame frame = new JFrame("Cyber beatbox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel();

        /*
        * An “empty border” gives us a margin
between the edges of the panel and
where the components are placed.
Purely aesthetic
        * */
        background.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
/*
* Lambda expressions are perfect for these
event handlers, since when these buttons
are pressed, all we want to do is call a
specific method
* */
        Box buttonbox = new Box(BoxLayout.Y_AXIS);
        JButton start=new JButton("start");
        start.addActionListener(e->buildTrackAndStart());
        buttonbox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(e->sequencer.stop());
        buttonbox.add(stop);

        /*
        * The default tempo is 1.0, so we’re
adjusting +/- 3% per click.
        * */
        JButton tempo = new JButton("Tempo Up");
        tempo.addActionListener(e->changeTempo(1.03f));
        buttonbox.add(tempo);

        JButton downTempo = new JButton("Tempo down");
        downTempo.addActionListener(e->changeTempo(0.97f));
        buttonbox.add(downTempo);



        Box namebox = new Box(BoxLayout.Y_AXIS);
        for(String instrumentname : instrumentNames){
            JLabel instrumentlabel = new JLabel();

            /*
            * This border on each instrument
name helps them line up with the checkboxes
            * */
            instrumentlabel.setBorder(BorderFactory.createEmptyBorder(4 , 1, 4 , 1));
            namebox.add(instrumentlabel);
        }
// still more Gui code nothing remarkable
        background.add(BorderLayout.EAST , buttonbox);
        background.add(BorderLayout.WEST , namebox);

        frame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16 , 16);
        grid.setVgap(1);
        grid.setHgap(2);
/*
* Another layout manager this one lets you put the components
* in a grid with row and column
* */
        JPanel mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER , mainPanel);
        /*
        * Make the checkboxes, set them to
‘false’ (so they aren’t checked), and
add them to the ArrayList AND to
the GUI panel.
        * */

        checkBoxList = new ArrayList<>();
        for(int i=0; i<256; i++){
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkBoxList.add(c);
            mainPanel.add(c);
        }


        setupMidi();

frame.setBounds(50 , 50 , 300 , 300);
frame.pack();
frame.setVisible(true);
    }


/*
* The usual MIDI setup stuff for
getting the Sequencer, the Sequence,
and the Track. Again, nothing special.
* */
    private void setupMidi(){
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ , 4);
            track = sequence.createTrack();

        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }
/*
*
* This is where it all happens! Where we
turn checkbox state into MIDI events
and add them to the Track.
* */
    private void buildTrackAndStart() {
        /*
        * We’ll make a 16-element array to hold the values for
one instrument, across all 16 beats. If the instrument is
supposed to play on that beat, the value at that element
will be the key. If that instrument is NOt supposed to play on that beat put in zero
        * */
    int [] tracklist;
/*
* get rid of the old track and make a fresh one
* */
    sequence.deleteTrack(track);
    track = sequence.createTrack();

    /*
    * do this for each of the 16 ROWS (i.e., Bass, Congo, etc.)
Set the “key” that represents which instrument this is
(Bass, Hi-Hat, etc.). The instruments array holds the
actual MIDI numbers for each instrument.
    *
    * */
    for(int i=0; i<16; i++){
        tracklist = new int[16];

        int key =instrument[i];


/*
*
* Do this for each of the BEATS for this row.
 */

        for(int j=0; j<16; j++){
            JCheckBox jc= checkBoxList.get(j+16 *i);

            /*
            * Is the checkbox at this beat selected? If yes, put
the key value in this slot in the array (the slot that
represents this beat). Otherwise, the instrument is
NOT supposed to play at this beat, so set it to zero.
            *
            * */
            if(jc.isSelected()){
                tracklist[j]=key;

            }else {
                tracklist[j]=0;
            }
        }
/*
* For this instrument, and for all 16 beats,
make events and add them to the track.

* */
          makeTracks(tracklist);
        track.add(makeEvent(CONTROL_CHANGE, 1 , 127 , 0 , 16));
    }

    /*
    * We always want to make sure that there IS an
event at beat 16 (it goes 0 to 15). Otherwise, the
BeatBox might not go the full 16 beats before it
starts over.
    * */

    track.add(makeEvent(PROGRAM_CHANGE , 9 , 1 , 0 , 15));

    try{
        sequencer.setSequence(sequence);

        /*
        * Lets you specify the number
of loop iterations, or in this
case, continuous looping
        * */
        sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
        sequencer.setTempoInBPM(120);
        sequencer.start();
    }catch (Exception e){
        e.printStackTrace();
    }

    }
/*
* The Tempo Factor scales the
sequencer’s tempo by the factor
provided, slowing the beat down or
speeding it up
* */
private void changeTempo(float tempoMultiplayer){
        float tempofactor = sequencer.getTempoFactor();
        sequencer.setTempoFactor(tempofactor * tempofactor);
}


/*
* This makes events for one instrument at a time, for
all 16 beats. So it might get an int[ ] for the Bass
drum, and each index in the array will hold either
the key of that instrument or a zero. If it’s a zero,
the instrument isn’t supposed to play at that beat.
Otherwise, make an event and add it to the track.
*
* */

private void makeTracks(int [] list){
        for(int i=0; i<16; i++){

            int key =list[i];

            if(key !=0){

                /*
                * Make the NOTE ON and
NOTE OFF events, and
add them to the Track
                * */
                track.add(makeEvent(NOTE_ON , 9 ,key , 100 , i));
                track.add(makeEvent(NOTE_OFF , 9 , key , 100 , i));
            }
        }
}


public  static MidiEvent makeEvent(int cmd , int chnl1 , int one , int two , int tick){
        MidiEvent event=null;

        try {

            /*
            * This is the utility method from the
previous chapter’s Code Kitchen.
Nothing new.
            * */
            ShortMessage message = new ShortMessage();
            message.setMessage(cmd , chnl1 , one , two);
            event = new MidiEvent(message , tick);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
        return event;
}


}


