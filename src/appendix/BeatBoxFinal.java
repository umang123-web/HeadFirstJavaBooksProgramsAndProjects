package appendix;

import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static javax.sound.midi.ShortMessage.*;

public class BeatBoxFinal implements Serializable {
    private JList<String>incominglist;

    private JTextArea usermessage;
    private ArrayList<JCheckBox>checkboxlist;


    private Vector<String> listvector = new Vector<>();

    private String username;
    private int nextsum;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private Sequencer sequencer;
    private Sequence sequence;
    private HashMap<String, boolean[]> otherSeqsMap = new HashMap<>();

    private Track track;


/*
* These are the names of the
instruments, as a String
array, for building the GUI
labels (on each row)
* */

    String[] instrumentName ={"Bass Drum" , "Closed Hi-Hat" , "open Hi-Hat" ,

    "Acoustic Snare" , "Crash Cymbal" , "Hand Clap" , "High Tom" , "Hi Bongo" , "Maracas" , "Whistle"
    , "Low Conga" , "Cowbell" , "Vibrasalp" , "Low-mid Tom" , "High Agogo" , "Open Hi Conga "};
/*
* These represent the
actual drum “keys.” The
drum channel is like
a piano, except each
“key” on the piano is a
different drum. So the
number ‘35’ is the key
for the Bass drum, 42
is Closed Hi-Hat, etc.
* */
    int [] instruments={35 , 42 ,46 , 38 , 49 , 39 , 50 , 60 , 70 ,72 , 64, 56 , 58 , 47, 67, 63};

    public static void main(String args[]) {

        BeatBoxFinal beat = new BeatBoxFinal();
        /*
        * Add a command-line argument for your screen name.
Example: % java BeatBoxFinal theFlash
        * */
        beat.startUp(args[0]);

    }

    public void startUp(String name){

        username = name;
//        open connection to the server

        /*
        * Set up the networking, I/O, and make
(and start) the reader thread. We’re
using Sockets here instead of Channels
because they work better with Object
Input/Output streams.
        * */
        try{
            Socket socket = new Socket("localhost" , 4000);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            ExecutorService threadpool = Executors.newSingleThreadExecutor();
            threadpool.submit(new RemoteReader());

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {

            System.out.println("Could n't connect- you will have to play alone");
        }

        setUpMidi();
        buildGUI();
    }
/*
* You've seen this GUI code
before, in Chapter 15
* */

    public void buildGUI(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
/*
* An “empty border” gives us a margin
between the edges of the panel and
where the components are placed.
(Purely aesthetic.)
* */
        BorderLayout border = new BorderLayout();
        JPanel background = new JPanel(border);
        frame.getContentPane().add(background);
        background.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10  ));

        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        JButton start = new JButton("Start");
        start.addActionListener(e->buildTrackAndStart());
        buttonBox.add(start);
/*
* Lambda expressions call a specific method
on this class when the button is pressed.

* */
        JButton stop = new JButton("Stop");
        stop.addActionListener(e->sequencer.stop());
        buttonBox.add(stop);

        /*
        * The default tempo is 1.0, so we’re
adjusting +/- 3% per
        * */
        JButton upTempo = new JButton("Tempo Up");
        upTempo.addActionListener(e->changeTempo(1.03f));

        buttonBox.add(upTempo);
/*
* The default tempo is 1.0, so we’re
adjusting +/- 3% per
* */
        JButton downTempo = new JButton("Tempo Down");
        downTempo.addActionListener(e->changeTempo(0.97f));
        buttonBox.add(downTempo);

/*
* This is new; send the message and
the current beat sequence to the
music server.

* */
        JButton sendIt = new JButton("sendIt");
        sendIt.addActionListener(e->sendMessageAndTrack());
        buttonBox.add(sendIt);

/*
* Create a text area for the
user to type their message.
* */
        usermessage = new JTextArea();
        usermessage.setLineWrap(true);
        usermessage.setWrapStyleWord(true);
        JScrollPane messageScroller = new JScrollPane(usermessage);
        buttonBox.add(messageScroller);
/*
* We saw JList briefly in Chapter 15. This is where the incoming
messages are displayed. Only instead of a normal chat where
you just LOOK at the messages, in this app you can SELECT a
message from the list to load and play the attached beat pattern.

* */

        incominglist = new JList<>();
        incominglist.addListSelectionListener(new MyListSelectionListener());
        incominglist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane theList = new JScrollPane(incominglist);
        buttonBox.add(theList);
        incominglist.setListData(listvector);


        Box namebox = new Box(BoxLayout.Y_AXIS);
        for(String instrumentname : instrumentName){
            JLabel instrumentlabel = new JLabel(instrumentname);
//            This border on each instrument name
//helps them line up with the checkboxes.
            instrumentlabel.setBorder(BorderFactory.createEmptyBorder(4 , 1 , 4 , 1));
            namebox.add(instrumentlabel);
        }
        background.add(BorderLayout.EAST , buttonBox);
        background.add(BorderLayout.WEST , namebox);
/*
* This layout manager one lets you
put the components in a grid with
rows and columns
* */
        GridLayout grid = new GridLayout(16 , 16);
        grid.setVgap(1);
        grid.setHgap(2);


        JPanel mainpanel = new JPanel(grid);
        background.add(BorderLayout.CENTER , mainpanel);
/*
* Make the check boxes, set them to
“false” (so they aren’t checked), and
add them to the ArrayList AND to
the GUI panel.
* */

        checkboxlist = new ArrayList<>();
        for(int i=0; i<256; i++){
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkboxlist.add(c);
            mainpanel.add(c);

        }


        frame.setBounds(50 , 50 , 300 , 300);
        frame.pack();
    }

/*
* Get the Sequencer, make a
Sequence, and make a Track.
* */
    public void setUpMidi(){
        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ , 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }
/*
* Build a track by walking through the
checkboxes to get their state and mapping
that to an instrument (and making the
MidiEvent for it). This is pretty complex,
but it is EXACTLY as it was in the previous
chapters, so refer to the Code Kitchen in
Chapter 15 to get the full explanation again.

*
* */
    private void buildTrackAndStart(){
        ArrayList<Integer>tracklist;
        sequence.deleteTrack(track);
        track = sequence.createTrack();
        for(int i=0; i<16; i++){
            tracklist = new ArrayList<>();
            int key = instruments[i];
            for(int j=0; j<16; j++){
                JCheckBox jc = checkboxlist.get(j +16 *i);
                if(jc.isSelected()){
                    tracklist.add(key);

                }else {
                    tracklist.add(null);
                }
            }
            makeTrack(tracklist);
            track.add(makeEvent(CONTROL_CHANGE , 1 , 127 , 0 , 16));
        }
        track.add(makeEvent(PROGRAM_CHANGE , 9 , 1 , 0 , 15));


        try{
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.setTempoInBPM(120);
            sequencer.start();

        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }

/*
* The Tempo Factor scales the
sequencer’s tempo by the factor
provided, slowing the beat down or
speeding it up
* */
    private void changeTempo(float tempoMultiplier){
        float tempofactor = sequencer.getTempoFactor();
        sequencer.setTempoFactor(tempofactor * tempoMultiplier);
    }
/*
* This is new...it’s a lot like the SimpleChatClient, except instead
of sending a String message, we serialize two objects (the
String message and the beat pattern) and write those two
objects to the socket output stream (to the server).

*
* */
    private void sendMessageAndTrack(){
        boolean [] checkboxstate = new boolean[256];
        for(int i=0; i<256; i++){
            JCheckBox check = checkboxlist.get(i);

            if(check.isSelected()){
                checkboxstate[i] = true;
            }
        }
        try{
            out.writeObject(username + nextsum++ + ": " + usermessage.getText());
            out.writeObject(checkboxstate);
        } catch (IOException e) {
            System.out.println("Terribly sorry could not send it to the server");
            throw new RuntimeException(e);
        }
        usermessage.setText(" ");
    }
/*
* This is also new—a
ListSelectionListener that tells
us when the user made a selection
on the list of messages. When
the user selects a message, we
IMMEDIATELY load the associated
beat pattern (it’s in the HashMap
called otherSeqsMap) and start
playing it. There’s some if tests
because of little quirky things about
getting ListSelectionEvents.
*
* */

    class MyListSelectionListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(!e.getValueIsAdjusting()){
                String selected =incominglist.getSelectedValue();
                if(selected !=null){
//                    now go to map and change the sequence

                    boolean[] selectedstate = otherSeqsMap.get(selected);
                    changeSequence(selectedstate);
                    sequencer.stop();
                    buildTrackAndStart();
                }
            }
        }
    }


    /*
    * This method is called when the user selects
something from the list. We IMMEDIATELY
change the pattern to the one they selected.

    * */

    private void changeSequence(boolean[] checkboxstate){
        for(int i=0; i<256; i++){
            JCheckBox check = checkboxlist.get(i);
            check.setSelected(checkboxstate[i]);
        }
    }


    public void makeTrack(ArrayList<Integer>list){
        for(int i=0; i<list.size(); i++){
            Integer instrumentkey = list.get(i);
            if(instrumentkey !=null){
                track.add(makeEvent(NOTE_ON , 9 , instrumentkey , 100 , i));
                track.add(makeEvent(NOTE_OFF , 9 , instrumentkey , 100 , i+1));
            }
        }
    }
/*
* All the MIDI stuff is exactly the same as it
was in the previous versions.
* */
    public static MidiEvent makeEvent(int cmd , int chnl , int one , int two , int tick){
        MidiEvent event=null;
        try{
            ShortMessage message =new ShortMessage();
            message.setMessage(cmd , chnl , one , two);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }

        return event;
    }
/*
* This is the thread job—it reads
in data from the server. In this
code, “data” will always be two
serialized objects: the String
message and the beat pattern (a
boolean array of checkbox state
values).
* */
    class RemoteReader implements Runnable{

        @Override
        public void run() {
            try{
                Object obj;
                while ((obj = in.readObject())!=null){

                    System.out.println("got an object  from server");
                    System.out.println(obj.getClass());
                    /*
                    * When a message comes in, we read
(deserialize) the two objects (the
message and the array of boolean
checkbox state values), which
we want to add to the JList
component.
                    * */
                    String nameToShow=(String) obj;
                    boolean[] checkboxState =(boolean[]) in.readObject();
                    otherSeqsMap.put(nameToShow , checkboxState);
                    listvector.add(nameToShow);
                    /*
                    * Adding to a JList is a
two-step thing: you keep
a Vector of the lists data
(Vector is an old-fashioned
ArrayList), and then tell
the JList to use that
Vector as it’s source for
what to display in the list.
                    * */
                    incominglist.setListData(listvector);
                }

                /*
                * Multi-catch: if you want to catch two
different Exceptions but do the same
thing with them (like here, we just want
to print them out), you can separate the
two Exception classes with a pipe.
                * */
            }catch (IOException | ClassNotFoundException e){

                e.printStackTrace();
            }
        }
    }
}


