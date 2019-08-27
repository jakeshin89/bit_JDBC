package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCUtil;

public class Test08_delete {

	public static void main(String[] args) {
		
		Emp8 emp = new Emp8();
		int count = emp.deleteEmp(9999);
		System.out.println(count == 1 ? "9999 삭제" : "삭제 데이터 없음");
		//JDBC는 빠꾸없는 Auto Commit;
		//트랜젝션@!@@!@#!@#
	}

}

class Emp8{
	
	public int deleteEmp(int empno) {
		
		String sql = "delete from emp where empno=? ";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, empno);
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(null, ps, con);
		}		
	
	return result;
	}
}