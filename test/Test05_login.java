package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.JDBCUtil;

public class Test05_login {
//로그인 처리
//역시 아이디 및 비밀번호 변수처리
	
	public static void main(String[] args) {
	
		System.out.println("=== 로그인 처리 ===");
	
		Connection con = null;
		PreparedStatement ps = null; //SQL 구문 중 물음표(?)가 있다면, 그것을 처리해주는 관리 객체. 없어도 이거 써.
		ResultSet rs = null;
		
		String id = "ronaldo7";
		String pw = "juventus";
		
		//String sql = "select * from users where id = '"+id+"' and password = '"+pw+"' ";
		//이 코드는 읽기 복잡하고, 보안에 아주 취약. SQL Injection 대상이 됨. 다메요.
		String sql = "select * from users where id = ? and password = ? ";
		//물음표 넣기. 이러면 statement는 물음표 처리 안해줌. 바꿔라.
		
		try {
			con = JDBCUtil.getConnection();
			//st = con.createStatement();
			ps = con.prepareStatement(sql); //pre compile해! 그리고 물음표 처리 해 줘야 함
			ps.setString(1, id); //첫번째 물음표 id
			ps.setString(2, pw); //두번째 물음표 pw
			rs = ps.executeQuery(); //sql구문 빼고 ps를 실행. 어짜피 ps에 있으니.
			
			if(rs.next()) {
				System.out.println(rs.getString("id")+"님 로그인 되었습니다. ");
				System.out.printf("%s / %s", rs.getString("id"), rs.getString("name"));
			} else {
				System.out.println("로그인 실패");
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, con);
		} //ps는 st의 자식이어서 문제없음
		
	}
}