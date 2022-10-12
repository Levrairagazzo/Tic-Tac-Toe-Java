import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GameClient {
        public static void main(String[] args) throws Exception {
            new GameClient();
        }

        public GameClient() throws Exception {
            Socket socket = new Socket("127.0.0.1", GameServer.PORT );

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            MyPacket myPacket = new MyPacket("Hello!");
            outputStream.writeObject(myPacket);

            MyPacket recvPacket = (MyPacket)inputStream.readObject();
            System.out.println(recvPacket.message);

            outputStream.close();
            socket.close();
        }
    }


