package chapter17.networking;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SimpleChatClientA {

    private JTextField outgoing;
    private PrintWriter writer;

    public void go(){
//        call the method that will connect to the server

        setUpNetworking();
        outgoing = new JTextField(20);

        JButton sendbutton = new JButton("Send");
        sendbutton.addActionListener(e->sendMessage());

        JPanel mainpanel = new JPanel();
        mainpanel.add(outgoing);
        mainpanel.add(sendbutton);

        JFrame frame = new JFrame("Ludicrously simple chat Client");
        frame.getContentPane().add(mainpanel);
        frame.setSize(500 , 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private void setUpNetworking(){
        try{
//            We’re using localhost so you
//can test the client and
//server on one machine.
            InetSocketAddress serveraddress = new InetSocketAddress("localhost" , 5000);
            SocketChannel socketChannel = SocketChannel.open(serveraddress);
//            open the socket channel that connect to the server
//            his is where we make the
//PrintWriter from a writer that
//writes to the SocketChannel.
            writer =new PrintWriter(Channels.newWriter(socketChannel , UTF_8));
            System.out.println("Network establish");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//Now we actually do the writing. Remember,
//the writer is chained to the writer from the
//SocketChannel, so whenever we do a println(),
//it goes over the network to the server!
    private void sendMessage(){
        writer.println(outgoing.getText());
        writer.flush();
        outgoing.setText(" ");
        outgoing.requestFocus();
    }

    public static void main(String[] args) {
        SimpleChatClientA clientA = new SimpleChatClientA();
        clientA.go();
    }
}
