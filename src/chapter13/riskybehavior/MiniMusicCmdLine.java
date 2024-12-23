package chapter13.riskybehavior;

import javax.sound.midi.*;

public class MiniMusicCmdLine {
    public static void main(String[] args) {
        MiniMusicCmdLine mini = new MiniMusicCmdLine();
        if(args.length <2){
            System.out.println("Don't forget the instrument and note args");
        }else {
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            mini.play(instrument , note);
        }
    }

    private void play(int instrument, int note) {

        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            Sequence seq = new Sequence(Sequence.PPQ , 4);
            sequencer.open();
            Track track = seq.createTrack();

            ShortMessage shortMessage = new ShortMessage();
            shortMessage.setMessage(ShortMessage.PROGRAM_CHANGE , 1 , instrument , 0);
            MidiEvent changeInstrument = new MidiEvent(shortMessage , 1);
            track.add(changeInstrument);

            ShortMessage message = new ShortMessage();
            message.setMessage(ShortMessage.NOTE_ON , 1 , note , 100);
            MidiEvent noteon = new MidiEvent(message , 1);
            track.add(noteon);

            ShortMessage message1 = new ShortMessage();
            message.setMessage(ShortMessage.NOTE_OFF , 1 , 100);
            MidiEvent noteoff = new MidiEvent(message1 , 16);
            track.add(noteoff);
             sequencer.setSequence(seq);
             sequencer.start();

        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        } catch (InvalidMidiDataException e) {

        }


        System.getProperty("java.classpath");

    }
}
