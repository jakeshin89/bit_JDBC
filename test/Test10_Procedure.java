package test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCUtil;

public class Test10_Procedure {

	public static void main(String[] args) {
		
		//execute InsertBook(1, 'Java Programming', '�Ѻ�', 27000);
		String sql = "{call InsertBook(?, ?, ?, ?)}";
		Connection con = null;
		CallableStatement ps = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareCall(sql);
			
			ps.setInt(1, 2);
			ps.setString(2, "JSP");
			ps.setString(3, "��浿");
			ps.setInt(4, 30000);
			
			ps.executeQuery();
			System.out.println("Procedure Call InsertBook �ߵ�");
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(null, ps, con);
		}		
	}

}