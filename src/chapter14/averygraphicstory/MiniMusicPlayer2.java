package chapter14.averygraphicstory;

import javax.sound.midi.*;

import static javax.sound.midi.ShortMessage.*;

/*
* Version Two: registering and getting ControllerEvents
* */
public class MiniMusicPlayer2 {
    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException {
        MiniMusicPlayer2 mini = new MiniMusicPlayer2();
        mini.go();
    }

    public void go() throws MidiUnavailableException, InvalidMidiDataException {
        Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.open();
        int[]eventsIwant={127};
        /*
        * Register for events with the sequencer.
The event registration method takes the
listener AND an int array representing
the list of ControllerEvents you want. We
want care about one event, #
*
*
* Each time we get the event, we’ll print “la”
to the command line. We're using a lambda
expression here to handle this ControllerEvent
        * */
        sequencer.addControllerEventListener(event -> System.out.println("la") , eventsIwant);
        Sequence seq = new Sequence(Sequence.PPQ , 4);
        Track track =seq.createTrack();

/*
* Here’s how we pick up the beat—we
insert our OWN ControllerEvent
(CONTROL_CHANGE) with an argument
for event number #127. This event will
do NOTHING! We put it in JUST so
that we can get an event each time a
note is played. In other words, its sole
purpose is so that something will fire
that WE can listen for (we can’t listen
for NOTE ON/OFF events). We’re making
this event happen at the SAME tick as
the NOTE_ON. So when the NOTE_ON
event happens, we’ll know about it because
OUR event will fire at the same time
*
*
* */
        for(int i=5; i<61; i+=4){
            track.add(makeEvent(NOTE_ON , 1 , 127 , 0 , i));
            track.add(makeEvent(CONTROL_CHANGE, 1 , 127 , 0 , i));
           track.add(makeEvent(NOTE_OFF , 1 , i , 100 , i+2));
        }

        sequencer.setSequence(seq);
        sequencer.setTempoInBPM(220);
        sequencer.start();

    }

    public static MidiEvent makeEvent(int cmd , int chn1 , int one , int two , int tick){
        MidiEvent event = null;
        try {
            ShortMessage message = new ShortMessage();
            message.setMessage(cmd , chn1 , one , two);
            event = new MidiEvent(message , tick);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }

        return event;
    }
}
