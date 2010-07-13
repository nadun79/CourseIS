package db;

import java.sql.*;

import com.mysql.jdbc.Driver;

public class MySQLConnect {
		private static final String usr = "abc";
        private static final String pwd = "123";	        
        private static final String url = "jdbc:mysql://localhost:3306/prac10";
        
        private Connection conn = null;
	
	public MySQLConnect() throws Exception {
		try {
			DriverManager.registerDriver(new Driver());
			connect();
		} catch(Exception ex) {
			System.out.println("Couldn't open connection.");
			throw(ex);
		}
	}
	
	private void connect() throws SQLException {
        conn = DriverManager.getConnection(url,usr,pwd);
		//System.out.println("DB Connection Opened.");
	}
	
	public void close() throws SQLException {
		conn.close();
		//System.out.println("DB Connection Closed.");
	}
	
	public ResultSet query(String sql) throws SQLException{
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
	}
	
	public void execute(String sql) throws SQLException{
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
	}
}
