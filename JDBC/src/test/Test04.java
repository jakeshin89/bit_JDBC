package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.JDBCUtil;

public class Test04 {
//로그인 처리
	
	public static void main(String[] args) {
	
		System.out.println("=== 로그인 처리 ===");
	
		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null; //원래 없었는데 에러잡기 위해 넣음
		ResultSet rs = null;
		
		String sql = "select * from users where id = 'ronaldo7' and password = 'juventus' ";
		
		try {
			con = JDBCUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			if(rs.next()) {
				System.out.println(rs.getString("id")+"님 로그인 되었습니다. ");
				System.out.printf("%s / %s", rs.getString("id"), rs.getString("name"));
			} else {
				System.out.println("로그인 실패");
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, con);
		}
		
	}
}