package chapter11.datastructures;

import java.util.ArrayList;
import java.util.List;

class MockSong{
    public static List<String>getSongs(){
        List<String> songs = new ArrayList<>();
        songs.add("somersault");
        songs.add("cassidy");
        songs.add("$10");
        songs.add("havana");
        songs.add("50 ways");
        return songs;
    }
}


public class JukeBox {

    public void go(){
        List<String> songlist = MockSong.getSongs();
        System.out.println(songlist);
    }
    public static void main(String[] args) {

JukeBox box = new JukeBox();

box.go();

    }
}
