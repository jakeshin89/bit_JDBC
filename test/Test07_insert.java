package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCUtil;
import vo.EmployeeVO;

public class Test07_insert {

	public static void main(String[] args) {
		
		Emp7 emp = new Emp7();
		//int count = emp.insertDept(50, "������", "SEOUL");
		//System.out.println(count+" : insert");
		
		System.out.println("--------------------------");
		
		//Employee data = new Employee(9997, "ȫ�浿", 3900, 10);
		EmployeeVO data = new EmployeeVO();
		data.setEmpno(1111);
		data.setEname("�̼���");
		data.setSal(9900);
		data.setDeptno(50);
		
		int count = emp.insertEmp(data);
		
		System.out.println(count+"ea : insert");
		System.out.println("end");
	} //JDBC�� ����Ŀ��

}

class Emp7{
	public int insertDept(int deptno, String dname, String loc) {
		
		String sql = "insert into dept(deptno, dname, loc) values(?, ?, ?)";
		Connection con = null;
		PreparedStatement ps = null;
		//ResultSet rs = null;
		int result = 0;
		
		try {
			// ? ����
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, deptno);
			ps.setString(2, dname);
			ps.setString(3, loc);
			
			//ps.executeQuery();
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(null, ps, con);
		}	//null�� �ϸ� �ݳ��� �ڿ� ����! �̷� �ǹ�.	
		
		return result;
		
	}
	
	public int insertEmp(EmployeeVO emp) {
		
		String sql = 
				"insert into emp(empno, ename, hiredate, sal, deptno) "+
				"values(?, ?, sysdate, ?, ?)";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, emp.getEmpno());
			ps.setString(2, emp.getEname());
			ps.setInt(3, emp.getSal());
			ps.setInt(4, emp.getDeptno());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(null, ps, con);
		}		
		
	return result;
	}
}