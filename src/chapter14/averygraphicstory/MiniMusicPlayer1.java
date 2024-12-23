package chapter14.averygraphicstory;


import javax.sound.midi.*;

import static javax.sound.midi.ShortMessage.NOTE_OFF;
import static javax.sound.midi.ShortMessage.NOTE_ON;

/*
*Version One: using the new static
makeEvent() method
* */
public class MiniMusicPlayer1 {
    public static void  main(String[] args) throws MidiUnavailableException, InvalidMidiDataException {

//        Make (and open) a sequencer.
        Sequencer sequencer = MidiSystem.getSequencer();
//        make a sequence track
        sequencer.open();

        Sequence seq = new Sequence(Sequence.PPQ , 4);
        Track track =seq.createTrack();

        /*
        * Make a bunch of events to make the notes keep
going up (from piano note 5 to piano note 61).
        * */
        for(int i=5; i<61; i+=4){

            /*
            * Call our new makeEvent()
method to make the message
and event; then add the result
(the MidiEvent returned from
makeEvent()) to the track.
These are NOTE ON and NOTE
OFF pairs.
            * */
            track.add(makeEvent(NOTE_ON , 1 , i , 100 , i));
            track.add(makeEvent(NOTE_OFF , 1 , i , 100 , i+2));
        }

        /*
        *start running
        * */
        sequencer.setSequence(seq);
        sequencer.setTempoInBPM(220);
        sequencer.start();
    }

    private static MidiEvent makeEvent(int cmd , int chnl1 , int one , int two , int tick) {

        MidiEvent event =null;

        try {
            ShortMessage message = new ShortMessage();
            message.setMessage(cmd ,chnl1 , one , two );
            event=new MidiEvent(message , tick);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }

        return event;
    }
}
