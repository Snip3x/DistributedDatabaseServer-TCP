package Hotel;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Connection implements Runnable{

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("ttttttt");
                int choice = Hotel.in.readInt();
                System.out.println("ssss121");
                if(choice==1){
                    Hotel.out.writeInt(1);
                    ArrayList<String> list = Database.getBack();
                    Hotel.out.writeInt(list.size());
                    for (String s : list) {
                        Hotel.out.writeUTF(s);
                    }
                    Hotel.out.flush();

                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
}
