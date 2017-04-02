package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import bean.UserBean;

public class usersdao {
	private Connection con = null;
	private static Connection con2 = null;
	private String dbLocation = "jdbc:mysql://localhost:3306/projectv1";

	public usersdao() throws ClassNotFoundException{

	}

	public static void getAllUsers() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		String query = "SELECT * FROM users";
		try{
			con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectv1", "root", "");
		}catch(Exception e){
			System.out.println(e);
		}
		PreparedStatement p = con2.prepareStatement(query);
		ResultSet r = p.executeQuery();
	
		while (r.next()){
			String userID = r.getString("userID");
			String firstName = r.getString("fName");
			String lastName = r.getString("lName");
			String memberSince = r.getString("memberSince");
			System.out.println(firstName + " " + lastName + "\n");
		}
		r.close();
		p.close();
		con2.close();
	}
	
	public void retrieve(String username, String pw) throws SQLException, ClassNotFoundException{
		//UserBean newUser = null;
		Class.forName("com.mysql.jdbc.Driver");
		String query = "select * from users u, account_info ai where u.userID=ai.userID and ai.email=\"" + username + "\" and ai.pw=\"" + pw + "\"";
		System.out.println(query+ "\n");
		try{
			con = DriverManager.getConnection(dbLocation, "root", "");
		}catch(Exception e){
			System.out.println(e);
		}
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();

		while (r.next()){		
			String firstName = r.getString("fName");
			String lastName = r.getString("lName");
			System.out.println(firstName + " " + lastName + "\n");
		}
		r.close();
		p.close();
		con.close();
		//return newUser;		
	}

	public void registerNewUser(String fname, String lname, String email, String pw) throws SQLException, ClassNotFoundException, ParseException{
		String userID = "";
		
		//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd k:mm:ss");
		Date date = new Date();
		Timestamp ctime = new Timestamp(date.getTime());
		String currenttime = dateFormat.format(ctime);
		System.out.println(currenttime + "\n");
		
		//insert into users(fname, lname, memberSince) values ("firstName", "lastName", "currenttime")
		String query1 = "insert into users(fname, lname, memberSince) values( \"" + fname + "\" , \"" + lname + "\", \"" + currenttime+ "\")";
		
		//select * from users u where u.fname=fname and u.lname=lname and u.memberSince=currenttime;
		String query2 = "select * from users u where u.fName=\"" + fname + "\" and u.lName=\"" + lname + "\" and u.memberSince=\"" + currenttime + "\"";
		
		
		
		Class.forName("com.mysql.jdbc.Driver");

		System.out.println(query1+ "\n");
		try{
			con = DriverManager.getConnection(dbLocation, "root", "");
		}catch(Exception e){
			System.out.println(e);
		}
		PreparedStatement p1 = con.prepareStatement(query1);
		p1.execute();
		
		PreparedStatement p2 = con.prepareStatement(query2);
		ResultSet rs = p2.executeQuery();
		
		while(rs.next()){
			userID = rs.getString("userID");
			System.out.println(userID + "\n");
		}
		
		//insert into account_info(userid, email, pw, accounttype, lastLogin) values(userID, email, pw, AccountType, currentTime);
		String query3 = "insert into account_info(userID, email, pw, AccountType, lastLogin) values(\"" + userID + "\", \"" + email + "\", \"" + pw + "\", \"C\", \"" + currenttime + "\")";
				
		System.out.println(query3 + "\n");
		PreparedStatement p3 = con.prepareStatement(query3);
		p3.execute();
		
		rs.close();
		p1.close();
		p2.close();
		p3.close();
		con.close();
	}
	
	public void setLastLogin(){
		
	}
}
