package Management;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
	
	

	public static Boolean storeBackup(String m) throws SQLException {
		
		String arr[] = m.split("--");
		//connecting to DB file
        String jdc = "jdbc:sqlite:ManagementDB.sqlite3";
        Connection con = null;
        
		try {
			con = DriverManager.getConnection(jdc);
			//query to store
	        String q = "Insert into checkin_backups values("+arr[0]+","+arr[1]+",'"+arr[2]+"','"+arr[3]+"',"+arr[4]+","+arr[5]+") ";
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

	public static Boolean storeReport(String m, boolean check) throws SQLException {

		String arr[] = m.split("--");
		if(check){
			potentiallyInfected(arr[4],arr[5]);
		}
		System.out.println(m+"jkjkjk");
		//connecting to DB file
		String jdc = "jdbc:sqlite:ManagementDB.sqlite3";
		Connection con = null;

		try {
			con = DriverManager.getConnection(jdc);
			//query to store
			String q = "Insert into reports(name,email,checkin_id,hotel_id) values ('"+arr[0]+"','"+arr[1]+"','"+arr[2]+"',"+arr[3]+") ";
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
	public static void potentiallyInfected(String from, String till) throws SQLException {
		String jdc = "jdbc:sqlite:ManagementDB.sqlite3";
		Connection con = null;
		ArrayList<String> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(jdc);
			//query to get data
			String q = "SELECT * FROM checkin_backups WHERE \"from\" BETWEEN "+from+"-1 AND "+till+"+8 OR \"till\" BETWEEN "+from+"-1 AND "+till+"+8;";
			System.out.println(q);
			Statement stat = con.createStatement();
			ResultSet res =  stat.executeQuery(q);
			while(res.next()){
				String ret = "";
				ret += ""+res.getString("name");
				ret += "--"+res.getString("email");
				ret += "--"+res.getString("checkin_id");
				ret += "--"+res.getString("hotel_id");
				list.add(ret);
			}
			con.close();
			for (String s : list) {
			storeReport(s,false);
			}
		} catch (SQLException e) {
			assert con != null;
			con.close();
			e.printStackTrace();
		}


	}

	public static ArrayList<String> getHotels() throws SQLException {
		String jdc = "jdbc:sqlite:ManagementDB.sqlite3";
        Connection con = null;
		ArrayList<String> retList = new ArrayList<>();

		try {
			con = DriverManager.getConnection(jdc);
			//query to get data
			String q = "Select * from Hotels";
			Statement stat = con.createStatement();
			ResultSet res =  stat.executeQuery(q);
			while(res.next()){
				String ret = "";
				ret += ""+res.getString("id");
				ret += "--"+res.getString("verification_id");
				ret += "--"+res.getString("location");
				retList.add(ret);
			}
			con.close();
		} catch (SQLException e) {
			con.close();
			e.printStackTrace();
		}
		return retList;
	}

	public static ArrayList<String> getBackups() throws SQLException {
		String jdc = "jdbc:sqlite:ManagementDB.sqlite3";
		Connection con = null;
		ArrayList<String> retList = new ArrayList<>();

		try {
			con = DriverManager.getConnection(jdc);
			//query to get data
			String q = "Select * from checkin_backups";
			Statement stat = con.createStatement();
			ResultSet res =  stat.executeQuery(q);
			while(res.next()){
				String ret = "";
				ret += ""+res.getString("checkin_id");
				ret += "--"+res.getString("hotel_id");
				ret += "--"+res.getString("name");
				ret += "--"+res.getString("email");
				ret += "--"+res.getString("from");
				ret += "--"+res.getString("till");
				retList.add(ret);
			}
			con.close();
		} catch (SQLException e) {
			con.close();
			e.printStackTrace();
		}
		return retList;
	}

	public static ArrayList<String> getReports() {
		String jdc = "jdbc:sqlite:ManagementDB.sqlite3";
		Connection con;
		ArrayList<String> retList = new ArrayList<>();

		try {
			con = DriverManager.getConnection(jdc);
			//query to get data
			String q = "Select * from reports";
			Statement stat = con.createStatement();
			ResultSet res =  stat.executeQuery(q);
			while(res.next()){
				String ret = "";
				ret += ""+res.getString("id");
				ret += "--"+res.getString("name");
				ret += "--"+res.getString("email");
				ret += "--"+res.getString("checkin_id");
				ret += "--"+res.getString("hotel_id");
				retList.add(ret);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retList;
	}
	
	
	//Getting Data for List Request
	public static int verify(String code) {
		String jdc = "jdbc:sqlite:ManagementDB.sqlite3";
        Connection con;
        
		try {
			con = DriverManager.getConnection(jdc);
        String q = "Select * From Hotels;";
        Statement stat = con.createStatement();
        ResultSet res =  stat.executeQuery(q);
        while(res.next()){
			if(res.getString("verification_id").equals(code)){
				int r =res.getInt("id");
				con.close();
				return r;
			}


        }
       con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

	public static void main(String[] args) throws SQLException {
		storeBackup("1--1--jake2--jake@jj.g--20220202--20220303");
		storeBackup("3--2--jake222--jake@jj2.g--20220202--20220303");

	}

}
