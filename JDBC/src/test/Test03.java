package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.JDBCUtil;

public class Test03 {
	
	public static void main(String[] args) {
		
		int deptno = 10;
		
		if(args.length != 0) {
			deptno = Integer.parseInt(args[0]);
		}
		
		String sql = "select * from emp where deptno = "+deptno;
		
		System.out.println("== JDBC TEST ==");

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null; //원래 없었는데 에러잡기 위해 넣음
		ResultSet rs = null;
		int result = 0;
		
		try {

			con = JDBCUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				System.out.print(rs.getInt("empno")+"\t");
				System.out.print(rs.getString("ename")+"\t");
				System.out.print(rs.getString("job")+"\t");
				System.out.print(rs.getString("mgr")+"\t");
				System.out.print(rs.getDate("hiredate")+"\t");
				System.out.print(rs.getString("sal")+"\t");
				System.out.print(rs.getString("deptno")+"\n");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCUtil.close(rs, ps, con);		
		}
		
		System.out.println("===== END =====");
	}
}