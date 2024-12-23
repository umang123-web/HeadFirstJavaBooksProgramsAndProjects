package chapter13.riskybehavior;

import javax.sound.midi.*;

public class MiniMiniMusicApp {



    public static void main(String[] args) {

        MiniMiniMusicApp miniMusicApp = new MiniMiniMusicApp();
        miniMusicApp.play();
    }

    private void play() {
        try{

            //Get a Sequencer and open it (so we can use it  a sequencer doesn't come already open)
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            //Don't worry about the arguments to the Sequence constructor just copy these thing of em as  Ready
            //bake arguments
            Sequence sequence = new Sequence(Sequence.PPQ , 4);


            //Ask the sequence for a Track remember the Track lives in the sequence and the MIDI
            //data lives in the track
            Track track = sequence.createTrack();

            ShortMessage message1 = new ShortMessage();

            /*
            Put some MidiEvent into the Track. This part is mostly ready
            Bake code. The only things you have to care about are the arguments to the
            setMessage() method and the arguments to the MidiEvent
            constructor. we will look at those arguments to the MidiEventConstructor .
             */

            message1.setMessage(ShortMessage.NOTE_ON , 1 , 44 , 100);
            MidiEvent noteon = new MidiEvent(message1 , 1);
            track.add(noteon);

            ShortMessage message2 = new ShortMessage();
            message2.setMessage(ShortMessage.NOTE_OFF , 1 , 44 , 100);
            MidiEvent noteoff = new MidiEvent(message2 , 16);
            track.add(noteoff);


            //Give the sequence to the sequencer (like selecting the song to play)
            sequencer.setSequence(sequence);

            //start() the sequencer (play the song)
            sequencer.start();





        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }
}
