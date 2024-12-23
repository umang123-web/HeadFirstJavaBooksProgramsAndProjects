package chapter13.riskybehavior;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class MusicTest1 {

    public void play(){
        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            System.out.println("Successfull got sequencer");
        } catch (MidiUnavailableException e) {
            System.out.println("Bummer");

         }
    }



    public static void main(String[] args) {

        MusicTest1 test1 = new MusicTest1();
         test1.play();

    }
}
