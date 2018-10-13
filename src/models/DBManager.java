package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

public class DBManager {

	private Connection conn;

	/**
	 * Legt den angegebenen user in der Datenbank an 
	 * @param user
	 * @throws SQLException 
	 */
	public void createUser(User user) throws SQLException {

		PreparedStatement pstm = getConnection().prepareStatement("INSERT INTO user(email, password, firstname, lastname) VALUES (?, ?, ?, ?)");

		pstm.setString(1, user.getEmail());
		pstm.setString(2, user.getPassword());
		pstm.setString(3, user.getFirstname());
		pstm.setString(4, user.getLastname());

		pstm.execute();
	}
	
	public User getUserByEmail(String email) throws SQLException
	{
		PreparedStatement pstm = getConnection().prepareStatement("SELECT * FROM user WHERE email = ?");
		pstm.setString(1, email);
		
		ResultSet rs = pstm.executeQuery();
		return resultSetToUser(rs);
	}
	
	
	public User loginUser(String email, String password) throws SQLException
	{
		PreparedStatement pstm = getConnection().prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
		pstm.setString(1, email);
		pstm.setString(2, password);
		
		ResultSet rs = pstm.executeQuery();
		return resultSetToUser(rs);
	}

	public User resultSetToUser(ResultSet rs) throws SQLException
	{
		
		boolean empty = !rs.next();
		
		if(empty) {
			return null;
		}
		
		int userid = rs.getInt(1);
		String email2 = rs.getString(2);
		String password2 = rs.getString(3);
		String firstname = rs.getString(4);
		String lastname = rs.getString(5);
		
		User user = new User(userid, email2, password2, firstname, lastname);
		return user;
	}
	


	public Connection getConnection() throws SQLException {
		if(conn == null) {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost/auktionshaus" +
					"?user=root&password=&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Atlantic/Canary"); 
		}
		return conn;
	}

	public void closeConnection() throws SQLException {
		if(conn != null) {
			conn.close();
			conn = null;
		}
	}

	public static void main(String[] args) {
		DBManager db = new DBManager();
		
	}
}
