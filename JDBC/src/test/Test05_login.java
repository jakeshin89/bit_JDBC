package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.JDBCUtil;

public class Test05_login {
//�α��� ó��
//���� ���̵� �� ��й�ȣ ����ó��
	
	public static void main(String[] args) {
	
		System.out.println("=== �α��� ó�� ===");
	
		Connection con = null;
		PreparedStatement ps = null; //SQL ���� �� ����ǥ(?)�� �ִٸ�, �װ��� ó�����ִ� ���� ��ü. ��� �̰� ��.
		ResultSet rs = null;
		
		String id = "ronaldo7";
		String pw = "juventus";
		
		//String sql = "select * from users where id = '"+id+"' and password = '"+pw+"' ";
		//�� �ڵ�� �б� �����ϰ�, ���ȿ� ���� ���. SQL Injection ����� ��. �ٸ޿�.
		String sql = "select * from users where id = ? and password = ? ";
		//����ǥ �ֱ�. �̷��� statement�� ����ǥ ó�� ������. �ٲ��.
		
		try {
			con = JDBCUtil.getConnection();
			//st = con.createStatement();
			ps = con.prepareStatement(sql); //pre compile��! �׸��� ����ǥ ó�� �� ��� ��
			ps.setString(1, id); //ù��° ����ǥ id
			ps.setString(2, pw); //�ι�° ����ǥ pw
			rs = ps.executeQuery(); //sql���� ���� ps�� ����. ��¥�� ps�� ������.
			
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
		} //ps�� st�� �ڽ��̾ ��������
		
	}
}