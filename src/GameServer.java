import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.InstantSource;
import java.util.Scanner;

public class GameServer {
    public static final int PORT = 7001;
    private ServerSocket serverSocket;
    int numClients;

    Scanner scanner = new Scanner(System.in);
    private int numPlayer;


    public GameServer() throws Exception {
        numClients = 0;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server running on port " + GameServer.PORT);
        } catch (IOException e) {
            System.out.println("IOException, could nit launch server!");
        }
    }


    private void closingServer() throws IOException {
        serverSocket.close();
        System.out.println("Server is now closed.");
    }

    private void acceptConnections() {
        System.out.println("Waiting for connection....");
        try {
            while (numPlayer < 5) {
                Socket mySocket = serverSocket.accept();
                numPlayer++;
                System.out.println("Player " + numPlayer + "is now connected");
            }
        } catch (IOException e) {
            System.out.println("IOexception could not accept connections.");
        }
    }

    private class ServerConnection implements Runnable {
        private Socket s1;
        DataInputStream serverConnectionDataIn;
        DataOutputStream serverConnectionDataOut;

        public ServerConnection(Socket socket) {
            s1 = socket;
            try {
                serverConnectionDataIn = new DataInputStream(s1.getInputStream());
                serverConnectionDataOut = new DataOutputStream(s1.getOutputStream());
            } catch (IOException e) {
                System.out.println("IOException in ServerConnection");
            }

        }


        @Override
        public void run() {
            try {
                serverConnectionDataOut.write(65);
            } catch (IOException e){
                System.out.println("Error in the run method");
            }

        }
    }

    public static void main(String[] args) throws Exception {
        var myServer = new GameServer();
        myServer.acceptConnections();




    }



}
