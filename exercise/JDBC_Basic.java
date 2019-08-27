package exercise;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Basic {

	public static void main(String[] args) {
		
		//1.JDBC Driver ��ġ, C:\lib\ojdbc6.jar�� classpath�� �߰�. String = "";...
		//2.����̹� �ε�
		//3.DB�� ���� Connection ����
		//4.SQL������ �������ִ� ������ü(Statement) ����
		//5.SQL���� ����
		//6.����� Handling ; select�� �ø� column����.
		//7.�ڿ��ݳ�
		
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.0.126:1521:xe";
		String user = "scott";
		String pw = "TIGER";

		String sql = "select * from emp ";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, user, pw);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.print(rs.getInt("EMPNO")+"\t");
				System.out.print(rs.getString("ENAME")+"\t");
				System.out.print(rs.getString("JOB")+"\t");
				System.out.print(rs.getInt("MGR")+"\t");
				System.out.print(rs.getDate("HIREDATE")+"\t");
				System.out.print(rs.getInt("SAL")+"\t");
				System.out.print(rs.getInt("COMM")+"\t");
				System.out.print(rs.getInt("DEPTNO")+"\t");
				System.out.println();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}