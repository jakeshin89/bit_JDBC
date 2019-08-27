package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCUtil;

public class Test06 {

	public static void main(String[] args) {
		Emp6.searchEname("S");
		System.out.println("---------");
		Emp6.searchEname("s");
		
	}
}


class Emp6{
	public static void searchEname(String ename){
		
		//String sql = "select * from emp where upper(ename) like upper('%'||?||'%')";
		String sql = "select * from emp where upper(ename) like ?";
		// % 및 대소문자 처리
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			// ? 세팅
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			//ps.setString(1, ename);
			ps.setString(1, "%"+ename.toUpperCase()+"%");
			//이렇게도 할 수 있음.
			
			//실행 및 결과값 핸들링
			rs = ps.executeQuery();
			//ps.executeUpdate();
			while (rs.next()) {
				System.out.print(rs.getInt("empno")+"  ");
				System.out.print(rs.getString("ename")+"  ");
				System.out.print(rs.getString("job")+"  ");
				System.out.print(rs.getInt("sal")+"  ");
				System.out.print(rs.getInt("comm")+"  ");
				System.out.print(rs.getDate("hiredate")+"  ");
				System.out.print(rs.getInt("deptno")+"  ");
				System.out.println();
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, con);
		}		
		
	}
}