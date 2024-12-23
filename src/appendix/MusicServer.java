package appendix;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MusicServer implements Serializable {

    /*
    * List of all the client output
streams to send messages to
when a message is received
    * */
final List<ObjectOutputStream> clientOutputStream = new ArrayList<>();

    public static void main(String[] args) {
        new MusicServer().go();
    }

    public void go(){
        try {
            /*
            * Open a server socket at port 4242
            * */
            ServerSocket serversock = new ServerSocket(  4000);
            ExecutorService threadpool = Executors.newCachedThreadPool();

            while (!serversock.isClosed()){
                Socket clientsocket = serversock.accept();
                /*
                * Keep listening for client
connections; create a
new Socket and new
ClientHandler for each
connected client.

                * */
                ObjectOutputStream out = new ObjectOutputStream(clientsocket.getOutputStream());
                clientOutputStream.add(out);

                ClientHandler clientHandler = new ClientHandler(clientsocket);
                threadpool.execute(clientHandler);
                System.out.println("Got a connection");
            }
        } catch (IOException e) {
                  e.printStackTrace();
        }

    }
    public void tellEveryone(Object one , Object two){
        for(ObjectOutputStream objectOutputStream : clientOutputStream){
            try {
                objectOutputStream.writeObject(one);
                objectOutputStream.writeObject(two);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
/*
* Create an ObjectInputStream for
reading messages from this client.
* */
    class ClientHandler implements Runnable{

        private ObjectInputStream in;

        public ClientHandler(Socket socket){

            try{
                in=new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public void run() {
Object userName;
Object beatSequence;
/*
* When the client sends a message, it's made
of two parts: a String that contains the
username and their message; and a Object
that represents the beat sequence (this
is actually a boolean array, but the server
doesn't care about that).
*
* */
try {
    while ((userName = in.readObject()) != null){
        beatSequence = in.readObject();

        System.out.println("read two objects");

        /*
        * Once we've got the message and
beat sequence, send these to all
the clients (including this one).
        * */
        tellEveryone(userName , beatSequence);

    }
}catch (IOException | ClassNotFoundException e){

    e.printStackTrace();
}
        }
    }
}
