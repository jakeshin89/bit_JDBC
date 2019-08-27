package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCUtil;

public class TemplateJDBC {

	public static void main(String[] args) {
		
		String sql = "";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			// ? ¼¼ÆÃ
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.executeQuery();
			ps.executeUpdate();
			
			//rs = ps.executeQuery();
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, con);
		}
		
	}
}
