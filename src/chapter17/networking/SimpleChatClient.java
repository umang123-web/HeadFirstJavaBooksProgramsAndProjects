package chapter17.networking;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChatClient {
    private JTextArea incoming;
    private JTextField outgoing;
    private BufferedReader reader;
    private PrintWriter writer;


    public void go() {
        setUpNetworking();

        JScrollPane scroller = createScrollableTextArea();

        outgoing = new JTextField(20);
        JButton button = new JButton("send");
        button.addActionListener(e -> sendMessage());

        JPanel panel = new JPanel();
        panel.add(scroller);
        panel.add(outgoing);
        panel.add(button);
//We’ve got a new job, an inner
//class, which is a Runnable.
//The job is to read from
//the server’s socket stream,
//displaying any incoming
//messages in the scrolling
//text area. We start this
//job using a single thread
//executor since we know we
//want to run only this one job.
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new IncomingReader());


        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private JScrollPane createScrollableTextArea() {
//        A helper method, like we
//saw back in Chapter 16, to
//create a scrolling text area.
        incoming = new JTextArea(15, 30);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane scroller = new JScrollPane(incoming);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroller;
    }


    private void setUpNetworking() {
        try {
            InetSocketAddress serveraddress = new InetSocketAddress("localhost", 5000);
            SocketChannel socketChannel = SocketChannel.open(serveraddress);

//We’re using Channels to create a new reader
//and writer for the SocketChannel that’s
//connected to the server. The writer sends
//messages to the server, and now we’re using
//a reader so that the reader job can get
//messages from the server
            reader = new BufferedReader(Channels.newReader(socketChannel, StandardCharsets.UTF_8));
            writer = new PrintWriter(Channels.newWriter(socketChannel, StandardCharsets.UTF_8));

            System.out.println("Network established");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//Nothing new here. When the user clicks
//the send button, this method sends the
//contents of the text field to the server.
    private void sendMessage() {
        writer.println(outgoing.getText());
        writer.flush();
        outgoing.setText(" ");
        outgoing.requestFocus();
    }

//This is what the thread does!!
//In the run() method, it stays in a
//loop (as long as what it gets from
//the server is not null), reading a
//line at a time and adding each line
//to the scrolling text area (along
//with a new line character
    class IncomingReader implements Runnable {

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine())!=null){
                    System.out.println("read" + message);
                    incoming.append(message + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        SimpleChatClient client = new SimpleChatClient();
        client.go();
    }
}