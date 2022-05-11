package Hotel;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {


    //Storing The Message Data to Database
    public static Boolean store(String m) throws SQLException {

        String arr[] = m.split("--");
        //connecting to DB file
        String jdc = "jdbc:sqlite:HotelDatabase.db";
        Connection con = null;

        try {
            con = DriverManager.getConnection(jdc);
            //query to store
            String q  = "Insert into checkins(name,email,'from',till) values ('"+arr[0]+"','"+arr[1]+"',"+arr[2]+","+arr[3]+") ;";;
            System.out.println(q);
            Statement stat = con.createStatement();
            stat.execute(q);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            con.close();
            return false;
        }
        return true;
    }







    public static ArrayList<String> getCheckins() throws SQLException {

        String jdc = "jdbc:sqlite:HotelDatabase.db";
        Connection con = null;
        ArrayList<String> retList = new ArrayList<>();
        try {
            con = DriverManager.getConnection(jdc);
            String q = "Select * From checkins;";
            Statement stat = con.createStatement();
            ResultSet res =  stat.executeQuery(q);
            while(res.next()){
                String ret = "";
                ret += ""+res.getString("id");
                ret += "--"+res.getString("name");
                ret += "--"+res.getString("email");
                ret += "--"+res.getString("from");
                ret += "--"+res.getString("till");
                retList.add(ret);


            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            con.close();
            return retList;
        }
        return retList;
    }


    public static ArrayList<String> getBack() throws SQLException {

        String jdc = "jdbc:sqlite:HotelDatabase.db";
        Connection con = null;
        ArrayList<String> retList = new ArrayList<>();
        try {
            con = DriverManager.getConnection(jdc);
            String q = "Select * From checkins;";
            Statement stat = con.createStatement();
            ResultSet res =  stat.executeQuery(q);
            while(res.next()){
                String ret = "";
                ret += ""+res.getString("id");
                ret += "--"+Hotel.HotelID;
                ret += "--"+res.getString("name");
                ret += "--"+res.getString("email");
                ret += "--"+res.getString("from");
                ret += "--"+res.getString("till");
                retList.add(ret);


            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            con.close();
            return retList;
        }
        return retList;
    }

    public static void main(String[] args) {
//        getData("11");
    }

}
