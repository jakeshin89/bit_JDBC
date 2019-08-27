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
		// % �� ��ҹ��� ó��
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			// ? ����
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			//ps.setString(1, ename);
			ps.setString(1, "%"+ename.toUpperCase()+"%");
			//�̷��Ե� �� �� ����.
			
			//���� �� ����� �ڵ鸵
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