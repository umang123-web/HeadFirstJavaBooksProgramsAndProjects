package chapter17.networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.concurrent.TimeUnit;

import static java.time.LocalTime.now;

public class PingingClient {

    public static void main(String[] args) throws IOException {
        InetSocketAddress server = new InetSocketAddress("localhost" , 5000);
        try(SocketChannel channel = SocketChannel.open(server)){

            PrintWriter writer= new PrintWriter(Channels.newWriter(channel , StandardCharsets.UTF_8));
            System.out.println("Network establish");
//You should get the
//same output even if
//you move the sleep() to
//somewhere else inside
//this for loop
            for(int i=0; i<10; i++){
                String message = "ping" + i;
                writer.println(message);
                writer.flush();
//                This is one way of getting the current time and turning it into a string in the format of Hours: Minute: seconds
                String currenttime = now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM));
                System.out.println(currenttime + "sent" + message);
                TimeUnit.SECONDS.sleep(2);
            }
//            You can catch the
//InterruptedException thrown by
//sleep() inside the for loop, or you
//can catch all the Exceptions at the
//end of the method
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
