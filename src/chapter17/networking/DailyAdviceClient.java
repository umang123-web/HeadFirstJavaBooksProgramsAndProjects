package chapter17.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class DailyAdviceClient {
public void go() throws IOException {
//    Define the server address as being port 5000 on the same host  this code is running on the localhost
    InetSocketAddress serveraddress = new InetSocketAddress("localhost" , 5000);

//    create a socket channel by opening one for the server address
    try(SocketChannel socketChannel =SocketChannel.open(serveraddress)){

//        This uses try-with-resources to automatically close the SocketChannel when the code is completed

//        create a reader that reads from the socket channel

        Reader chanelreader = Channels.newReader(socketChannel , StandardCharsets.UTF_8);
//Chain a BufferedReader
//to the Reader from the
//SocketChannel.
        BufferedReader reader = new BufferedReader(chanelreader);
/*
* This readLine() is EXACTLY
the same as if you were using a
BufferedReader chained to a FILE..
In other words, by the time you
call a BufferedReader method, the
reader doesn’t know or care where
* */
        String advice = reader.readLine();
        System.out.println("Today you should:" + advice);

        reader.close();

    }
}

    public static void main(String[] args) throws IOException {
        DailyAdviceClient client = new DailyAdviceClient();
        client.go();
    }
}
