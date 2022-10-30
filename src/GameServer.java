import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class GameServer {
    public static final int PORT = 7001;
    private final int MAX_TURNS = 9;
    private ServerSocket serverSocket;
    private int numClients, turnsMade;
    private ServerConnection client1, client2;
    private String[] gameBoard;
    private int player1ButtonClicked, player2ButtonClicked;
    private WinChecker winChecker;



    Scanner scanner = new Scanner(System.in);


    public GameServer() throws Exception {
        winChecker = new WinChecker();
        turnsMade = 0;
        numClients = 0;
        gameBoard = new String[9];
        for (int i = 0; i < 9; i++) {
            gameBoard[i] = "";
        }
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
        try {
            System.out.println("Waiting for connections");
            while (numClients < 2) {
                Socket s = serverSocket.accept();
                numClients++;
                System.out.println("Player#" + numClients + " has connected.");
                ServerConnection ssc = new ServerConnection(s, numClients);
                if (numClients == 1 ) {
                    client1 = ssc;
                }else {
                    client2 = ssc;
                }
                Thread t = new Thread(ssc);
                t.start();
            }
            System.out.println("2 players now. No longer accepting connections");
        } catch (IOException e) {
            System.out.println("Error from acceptConnections()");
        }
    }

    private class ServerConnection implements Runnable {
        private Socket s1;
        DataInputStream serverConnectionDataIn;
        DataOutputStream serverConnectionDataOut;
        private int ID;
        public ServerConnection(Socket socket, int id) {
            s1 = socket;
            ID = id;
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
                serverConnectionDataOut.writeInt(ID);
                while (true){
                    if (ID == 1){
                        player1ButtonClicked = serverConnectionDataIn.readInt();
                        System.out.println("Player 1 clicked button #" + player1ButtonClicked);
                        client2.sendIndexFromServer(player1ButtonClicked);
                    }else{
                        player2ButtonClicked = serverConnectionDataIn.readInt();
                        System.out.println("Player 2 clicked button#" + player2ButtonClicked);
                        client1.sendIndexFromServer(player2ButtonClicked);                    }
                    turnsMade++;
                    if(turnsMade == MAX_TURNS){
                        System.out.println("Max turns has been reached.");
                        break;
                    }
                }
                client1.closeServerConnection();
                client2.closeServerConnection();
            } catch (IOException e){
                System.out.println("Error in the run method");
                e.printStackTrace();
            }

        }

        private void closeServerConnection(){
            try {
                s1.close();
                System.out.println("------Connection closed ----- ");

            }catch (IOException e){
                System.out.println("IOException from closeServerConnection() SSC");
            }
        }

        public void sendIndexFromServer(int index){
            try{
                serverConnectionDataOut.writeInt(index);
                serverConnectionDataOut.flush();
            } catch (IOException e) {
                System.out.println("Error in sendButtonNum on serverside");
            }
        }

    }

    public static void main(String[] args) throws Exception {
        var myServer = new GameServer();
        myServer.acceptConnections();




    }



}
