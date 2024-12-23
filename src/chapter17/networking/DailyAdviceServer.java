package chapter17.networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Random;

public class DailyAdviceServer {
    final private String[] advicelist= {
            """
       Take a smaller bites ,
       Go for the tight jeans , No they do not make  you look fat,
       One word: inappropriate ,
       Just for today , be honest.  Tell Your boss what you really think ,
       You might want to rethink that haircut.
       
"""};

    private  final Random random= new Random();

    public void go() throws IOException {
//        ServerSocketChannel makes this server
//application “listen” for client requests on the
//port it’s bound to
        try(ServerSocketChannel serverchanel = ServerSocketChannel.open()){
//            You have to bind the ServerSocketChannel to
//the port you want to run the application on.
            serverchanel.bind(new InetSocketAddress(5000));

//            the server goes into permanent loop waiting for (and serving) client request

            while (serverchanel.isOpen()){
//                The accept method blocks (just sits there) until a
//request comes in, and then the method returns a
//SocketChannel for communicating with the client.
                SocketChannel socketChannel = serverchanel.accept();
                PrintWriter writer = new PrintWriter(Channels.newOutputStream(socketChannel));

//                send the client a String advice message
                String advice = getAdvice();

                writer.println(advice);

                writer.close();
//                print the server console so we can see what s' happening
                System.out.println(advice);

            }
        }
    }
        private String getAdvice(){
        int nextadvice = random.nextInt(advicelist.length);
        return advicelist[nextadvice];
        }


    public static void main(String[] args) throws IOException {
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }
}
