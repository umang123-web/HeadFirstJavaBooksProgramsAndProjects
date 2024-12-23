package chapter17.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChatServer {

    private final List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {
        SimpleChatServer chatServer = new SimpleChatServer();
        chatServer.go();
    }

    public void go() {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(5000));

            while (serverSocketChannel.isOpen()) {
                SocketChannel socketchannel = serverSocketChannel.accept();
                PrintWriter writer = new PrintWriter(Channels.newWriter(socketchannel, StandardCharsets.UTF_8));
                clientWriters.add(writer);
                threadPool.submit(new ClientHandler(socketchannel));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void tellEveryone(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
            writer.flush();
        }
    }


    class ClientHandler implements Runnable {
        BufferedReader reader;
        SocketChannel socket;

        public ClientHandler(SocketChannel socketChannel) {
            socket = socketChannel;
            reader = new BufferedReader(Channels.newReader(socket, StandardCharsets.UTF_8));
        }


        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + message);
                    tellEveryone(message);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
