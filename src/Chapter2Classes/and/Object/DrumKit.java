package Chapter2Classes.and.Object;

public class DrumKit {

    boolean topHat=true;
    boolean snare = true;

    void playtoHat(){
        System.out.println("ding ding da-ding");
    }


    void playsnare(){
        System.out.println("bang - bang-bang");
    }

    public static void main(String[] args) {

        DrumKit drum = new DrumKit();

        drum.playsnare();
        drum.snare = false;
        drum.playtoHat();

        if(drum.snare == true){
            drum.playsnare();
        }
    }
}
