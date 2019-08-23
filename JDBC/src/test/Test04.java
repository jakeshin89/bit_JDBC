package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.JDBCUtil;

public class Test04 {
//�α��� ó��
	
	public static void main(String[] args) {
	
		System.out.println("=== �α��� ó�� ===");
	
		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null; //���� �����µ� ������� ���� ����
		ResultSet rs = null;
		
		String sql = "select * from users where id = 'ronaldo7' and password = 'juventus' ";
		
		try {
			con = JDBCUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			if(rs.next()) {
				System.out.println(rs.getString("id")+"�� �α��� �Ǿ����ϴ�. ");
				System.out.printf("%s / %s", rs.getString("id"), rs.getString("name"));
			} else {
				System.out.println("�α��� ����");
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, con);
		}
		
	}
}