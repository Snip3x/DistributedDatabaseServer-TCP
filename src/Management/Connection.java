package Management;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class Connection implements Runnable{
    Socket connection;
    DataOutputStream out;
    DataInputStream in;
    Connection(Socket connection){
        this.connection = connection;
    }


    @Override
    public void run() {
        try {
            out = new DataOutputStream(connection.getOutputStream());
            in = new DataInputStream(connection.getInputStream());

            while(true){
                String code = in.readUTF();
                int id = Database.verify(code);
                if(id==-1){
                    out.writeInt(-1);
                    throw new IOException("wrong key");
                }else {
                    out.writeInt(id);
                    ManagementServer.connectionList.add(this);
                    break;
                }
            }
            while (true){
                if(ManagementServer.databasebusy){
                    Thread.sleep(1000);
                continue;}
                System.out.println("ssssss");
                int com = in.readInt();
                if(com==1){
                }else if(com==2){
                    Database.storeReport(in.readUTF(), true);
                }
            }

        } catch (IOException | InterruptedException | SQLException e) {
            e.printStackTrace();
            ManagementServer.connectionList.remove(this);
        }
    }



}
