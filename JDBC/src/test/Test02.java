package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test02 {
	//SQL 구문 복잡한데...

	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.0.126:1521:xe";
		//jdbc연동, driver종류(oracle, mysql), 주소 찾아가서 connection 시도
		String user = "SCOTT";
		String pw = "TIGER";
		
		String sql =
		
		"select ename as \"사원명\", sal as 급여, dname as 부서명, loc, grade "+
		"from emp "+ 
			 "join dept "+
			 "on emp.deptno = dept.deptno "+
			 "join salgrade "+
			 "on sal between losal and hisal "+
		"where emp.deptno=10 "+
		"order by emp.deptno";
		//blank 꼭 넣어줘야 해요
		//그냥 alias 했... 결과값 반영 없네
		//escaep하고 alias한 걸 결과값에 넣었는데 된다! 하지만 앞으론 영문으로... keep goin..
		//QUERY가 너무 기네? View 쓸 수 있잖아. 한 번 해보자.
		
		System.out.println("== JDBC TEST ==");
		
		Connection con = null;
		//난 client인데 server랑 connection해줘
		Statement st = null;
		ResultSet rs = null; //SQL select 결과값, 즉, statement로 나온 table 받을 객체
		int result = 0; //DML의 결과값 (테이블 데이터)
		//이건 어떤 구문을 처리할거냐에 따라 결과값이 달라지기 때문에 이렇게 해 둠
		
		try {

			Class.forName(driver);
			//new oracle.jdbc.OracleDriver() //Compile
			//이렇게 로딩하면 드라이버 매니저가 이들을 관리함
			//드라이버 종류는 oracle, mysql... 등등 있음
			
			con = DriverManager.getConnection(url, user, pw);
			//con.getMetaDate 하면 정보 빼올 수 있음.
			
			st = con.createStatement();
			//왔다갔다는 관리 객체
			
			//5.SQL구문 실행
			rs = st.executeQuery(sql);
			//우행 실행결과값 rs가 가지고 있음. 결과값은 table
			//rs는 그 결과값을 참조함. 
			//rs.getMetaData는 column정보 알ㄹ려줌.
			//select하면 커서가 column명을 가리키고 있음.
			//System.out.println(rs.getMetaData().getColumnCount());
			//statement문이 query 관리하는데, select 관리하려는 query
			//st.executeUpdate(sql); DML(insert, delete, update)은 update
			//DML 결과값은 int
			
			
			//6.결과값 Handling
			while (rs.next()) {				
				System.out.print(rs.getString("사원명")+"\t");
				System.out.print(rs.getString("급여")+"\t");
				System.out.print(rs.getString("부서명")+"\t");
				System.out.print(rs.getString(4)+"\t");
				System.out.print(rs.getString(5)+"\n");
			}
			//select하면 커서가 column명을 가리키고 있음.
			//rs.next()커서가 row 따라 내려갈 수 있어.
			//내려갔다 하면 data 있는거고, 없으면 false return;
			//출력값을 인덱스따라 뽑아줌. sysout에 있는건 column명
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver 확인");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			
			//7.자원반납
			//Database에 많은 connection 물리면 과부화; 그래서 필요한게 자원반납
			//자원반납 하면 더이상 접근 불가
			//네이버 같은 경우는, 누군가 접근하면 연결을 끊음.
			//상태유지기술을 써서 마치 연결되어있는 것 처럼 느껴짐.
			//웹은 자동으로 알아서 끊기 때문에, DB는 무조건 연결이 계속 되어있어서 자원소모 심함
			//그래서 할 일 끝나면 끊어야 함
			//rs, st, con은 연결지향형
			
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
		System.out.println("===== END =====");
	}
}

//Database 2 Tier Case, then (3Tier 이상인) Multi Tier Case
//RMI, 분산환경? 코바?