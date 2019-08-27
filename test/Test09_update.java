package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCUtil;
import vo.DeptVO;

public class Test09_update {

	public static void main(String[] args) {
		
		Emp9 emp = new Emp9();
		DeptVO d = new DeptVO();

		d.setDeptno(50);
		d.setDname("기술부");
		d.setLoc("제주도");
		
		int count = emp.updateDept(d);
		System.out.println(count+"번 update되었습니다.");
	}

}

class Emp9{
	
	public int updateDept(DeptVO dept) {
		
		String sql = "update dept set dname = ?, loc = ? where deptno = ? ";
		//웬만해선 PK 바꾸지 않기를 추천
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, dept.getDname());
			ps.setString(2, dept.getLoc());
			ps.setInt(3, dept.getDeptno());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(null, ps, con);
		}
		
		return result;	
	}
	
	public int updateEmp(int sal) {
		
		String sql = "update emp set sal = ? where ename = '이순신'";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, sal);
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(null, ps, con);
		} 
		
		return result;
	}
}