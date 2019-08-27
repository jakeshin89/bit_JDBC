package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
//코드중복 회피 위한
//Properties?
	
	//Connection 생성
	//driver, url, user, pw, sql query
	
	public static Connection getConnection(){
	
		Connection con = null;
		
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("C:\\lib\\dbinfo.txt"));
			
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String pw = p.getProperty("pw");
			
			Class.forName(driver);
			//drive는 compile대상이 아니라 run
			con = DriverManager.getConnection(url, user, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 확인하세요");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void close(ResultSet rs, PreparedStatement ps, Connection con) {	
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
			//닫을 땐 만들때 역순으로
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

//이렇게 해 두면 txt파일만 관리하면 돼.
//커넥션 풀?
//커넥트 계속 유지할 수 없으니 row mapping
