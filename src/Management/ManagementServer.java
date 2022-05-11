package Management;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ManagementServer {
    static ArrayList<Connection> connectionList = new ArrayList<>();
    static boolean databasebusy = false;
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5500);
        new ManagmentPanel().start();
        while(true){
            Socket connection = server.accept();
            Connection newConn = new Connection(connection);
            new Thread(newConn).start();
        }

    }
}
