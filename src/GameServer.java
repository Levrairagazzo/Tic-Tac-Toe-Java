import GUI.MyGrid;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class GameServer {
    public static int PORT = 7001;
    InputStream inputStream = null;
    OutputStream outputStream = null;
    DataInputStream dataInputStream = null;
    Board myBoard = null;
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        new GameServer();
    }

    public GameServer() throws Exception{
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server running on port " + GameServer.PORT);
        System.out.println("Waiting for connection....");
        Socket clientSocket = serverSocket.accept(); //Waiting for the client to connect
        System.out.println("Connection from " + clientSocket + "!");


        // get the input stream from the connected socket
        InputStream inputStream = clientSocket.getInputStream();
        // create a DataInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        MyGrid myGrid = (MyGrid) objectInputStream.readObject();
        System.out.println("Starting game !");
            myGrid.startGUI();



        System.out.println("Closing sockets.");
        serverSocket.close();






//        while(game.isStillGoing()){
//
//        }

//        System.out.println(myGame.message);
//        if(myGame.message.equals("Hello!")){
//            MyPacket packet = new MyPacket("Hi! - From the server");
//            outputStream.writeObject(packet);
//        }

        serverSocket.close();

    }
    public void clientTurn() throws IOException {
        String message = "";
        System.out.println("Choose a row: ");
        message =  dataInputStream.readUTF();
        int row = Integer.parseInt(message);
        System.out.println("Choose a column: ");
        message =  dataInputStream.readUTF();
        int column = Integer.parseInt(message);
        myBoard.updateBoard(row, column, 'X');
        myBoard.printBoard();
    }
    public void serverTurn(){
        System.out.println("This is the server's turn");
        String message = "";
        System.out.println("Choose a row: ");
        message =  scanner.nextLine();
        int row = Integer.parseInt(message);
        System.out.println("Choose a column: ");
        message = scanner.nextLine();
        int column = Integer.parseInt(message);
        myBoard.updateBoard(row, column, 'O');
        myBoard.printBoard();
    }


}
