package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCUtil;

public class Prob_a {

	public static void main(String[] args) {
		
		Emp.show(10);
		System.out.println("===============");
		Emp.show(50);

	}
}


class Emp{
	
	public static void show(int deptn) {
		
		String sql = "select avg(sal) from emp where deptno = ? ";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int deptno = deptn;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, deptno);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(deptno+"�μ�");
				System.out.println(rs.getString("�μ��� ��� �޿�"));			
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, con);
		}
		
		}	
}