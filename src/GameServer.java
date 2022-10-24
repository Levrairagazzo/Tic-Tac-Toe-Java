import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class GameServer {
    public static final int PORT = 7001;
    InputStream inputStream = null;
    OutputStream outputStream = null;
    DataInputStream dataInputStream = null;
    Scanner scanner = new Scanner(System.in);
    private int numPlayer;

    public static void main(String[] args) throws Exception {
        new GameServer();
    }

    public GameServer() throws Exception {

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server running on port " + GameServer.PORT);
        System.out.println("Waiting for connection....");
        for (int i = 0; i < 5; i++) {
            Socket clientSocket = serverSocket.accept();//Waiting for the client to connect
            System.out.println("Connection from player number " + (++numPlayer) + "!");
        }


        System.out.println("Closing sockets.");
        serverSocket.close();


    }


}
