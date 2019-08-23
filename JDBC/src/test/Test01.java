package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test01 {

	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.0.126:1521:xe"; //ex:Service ID
		String user = "SCOTT";	//madang hr
		String pw = "TIGER";	//madang hr
		
		String sql = "select * from emp"; //세미콜른 안들어감

		//oracle.jdbc.OracleDriver a; drive명 에러 확인
		
		System.out.println("== JDBC TEST ==");
		//1.JDBC Driver 설치, C:\lib\ojdbc6.jar를 classpath에 추가. String = "";...

		Connection con = null;
		Statement st = null;
		ResultSet rs = null; //SQL select 결과값, 즉 table 받을 객체
		int result = 0; //DML의 결과값 (테이블 데이터)
		//이건 어떤 구문을 처리할거냐에 따라 결과값이 달라지기 때문에 이렇게 해 둠
		
		try {

			//2.드라이버 로딩
			//new oracle.jdbc.OracleDriver() //Compile
			Class.forName(driver);
			//이렇게 로딩하면 드라이버 매니저가 이들을 관리함.
			//compile하는게 아니라 run으로 해야 해. 그래서 이걸 써야 해.
			
			//어떻게 보면, 1,2가 DB에 연결할 준비를 하는거네.
			
			//3.DB로 연결 Connection 생성
			con = DriverManager.getConnection(url, user, pw);
			//오라클 드라이버 매니저가 연결하게 해주는거구나
			//정보만 넘겨주면 커넥션!
			//System.out.println(con); 제대로 작동하는지 보기 위해 잠깐 넣었다 뺌		
			
			//4.SQL구문을 관리해주는 관리객체(Statement) 생성
			st = con.createStatement(); //for sending SQL statements to the database.
			
			//5.SQL구문 실행
			rs = st.executeQuery(sql);
			//실행결과값 rs가 가지고 있음
			//System.out.println(rs.getMetaData().getColumnCount());
			
			//3, 4, 5는 연결
			
			//6.결과값 Handling ; select에 올린 column별로.
			while (rs.next()) {
				System.out.print(rs.getInt("empno")+"\t");
				//System.out.print(rs.getInt("1")+"\t"); 이것도 가능한데 잘 안씀		
				System.out.print(rs.getString("ename")+"\t");
				System.out.print(rs.getString("job")+"\t");
				System.out.print(rs.getString("mgr")+"\t");
				System.out.print(rs.getDate("hiredate")+"\t");
				//모든 객첸 string가능. 근데 여기에선 string보단 date가 어울릴 듯
				System.out.print(rs.getInt("sal")+"\t");
				System.out.print(rs.getString("deptno")+"\n");
			}
			//rs.next()커서가 row 따라 내려갈 수 있어.
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver 확인");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			//7.자원반납
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
			
		System.out.println("===== END =====");
	}
}