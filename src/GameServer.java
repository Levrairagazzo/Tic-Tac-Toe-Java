import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static int PORT = 7001;

    public static void main(String[] args) throws Exception {
        new GameServer();
    }

    public GameServer() throws Exception{
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server running on port " + GameServer.PORT);
        Socket socket = serverSocket.accept(); //Waiting for the client to connect

        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        Game myGame = (Game) inputStream.readObject();


//        System.out.println(myGame.message);
//        if(myGame.message.equals("Hello!")){
//            MyPacket packet = new MyPacket("Hi! - From the server");
//            outputStream.writeObject(packet);
//        }

        serverSocket.close();

    }

}
