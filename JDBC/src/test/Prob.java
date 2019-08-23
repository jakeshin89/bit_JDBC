package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.JDBCUtil;

public class Prob {

	//show() �޼ҵ�� �Ű������� �μ���ȣ(DEPARTMENT_ID) ������ �Է� �޾Ƽ� �ش�
	//�μ��� �ش��ϴ� ����� ��� �޿������� ����ϴ� �޼����̴�. 
	
	private static void show(int DEPARTMENT_ID) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int d_id = DEPARTMENT_ID;
		
		String sql = 
				
			"select department_id, avg(salary) "+
			"from employees "+
			"where department_id = ? "+
			"group by department_id ";
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, d_id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.printf("%s %s\n", rs.getString("department_id"), rs.getInt("avg(salary)"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, con);
		}	
	}
	
	public static void main(String[] args) {
		
		show(10);
		System.out.println("===============");
		show(50);
	}
}