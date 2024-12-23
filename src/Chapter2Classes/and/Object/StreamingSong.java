package Chapter2Classes.and.Object;

public class StreamingSong {

    String title;
    String artist;

    int duration;

    void play(){
        System.out.println("the song is playing");
    }

    void playlistDetails(){
        System.out.print("the title is:"  + title    + "the artist is:" + artist + " " +   "duration is :" + duration);
    }

    public static void main(String[] args) {

        StreamingSong song = new StreamingSong();
        song.title="the crow wol";
        song.artist="Breathless";
        song.duration=4;

        song.play();

        song.playlistDetails();

    }
}
