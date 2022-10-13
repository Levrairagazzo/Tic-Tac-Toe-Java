import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
        public static void main(String[] args) throws Exception {
            new GameClient();
        }

        public GameClient() throws Exception {
//            Socket socket = new Socket("127.0.0.1", GameServer.PORT );
//
//            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
//            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
//
//            outputStream.close();
//            socket.close();
            Scanner myScan = new Scanner(System.in);
            Socket socket = new Socket("localhost", 7001);
            System.out.println("Connected!");

            // get the output stream from the socket.
            OutputStream outputStream = socket.getOutputStream();
            // create a data output stream from the output stream so we can send data through it
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            System.out.println("What string would you like to send?");


            // write the message we want to send
            String message = "";
            while(!message.equals("stop")){
                message = myScan.nextLine();
                dataOutputStream.writeUTF(message);
                dataOutputStream.flush(); //sends the message
            }

            dataOutputStream.close(); // close the output stream when we're done.

            System.out.println("Closing socket and terminating program.");
            socket.close();
        }

    }


