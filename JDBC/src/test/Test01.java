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
		
		String sql = "select * from emp"; //�����ݸ� �ȵ�

		//oracle.jdbc.OracleDriver a; drive�� ���� Ȯ��
		
		System.out.println("== JDBC TEST ==");
		//1.JDBC Driver ��ġ, C:\lib\ojdbc6.jar�� classpath�� �߰�. String = "";...

		Connection con = null;
		Statement st = null;
		ResultSet rs = null; //SQL select �����, �� table ���� ��ü
		int result = 0; //DML�� ����� (���̺� ������)
		//�̰� � ������ ó���ҰųĿ� ���� ������� �޶����� ������ �̷��� �� ��
		
		try {

			//2.����̹� �ε�
			//new oracle.jdbc.OracleDriver() //Compile
			Class.forName(driver);
			//�̷��� �ε��ϸ� ����̹� �Ŵ����� �̵��� ������.
			//compile�ϴ°� �ƴ϶� run���� �ؾ� ��. �׷��� �̰� ��� ��.
			
			//��� ����, 1,2�� DB�� ������ �غ� �ϴ°ų�.
			
			//3.DB�� ���� Connection ����
			con = DriverManager.getConnection(url, user, pw);
			//����Ŭ ����̹� �Ŵ����� �����ϰ� ���ִ°ű���
			//������ �Ѱ��ָ� Ŀ�ؼ�!
			//System.out.println(con); ����� �۵��ϴ��� ���� ���� ��� �־��� ��		
			
			//4.SQL������ �������ִ� ������ü(Statement) ����
			st = con.createStatement(); //for sending SQL statements to the database.
			
			//5.SQL���� ����
			rs = st.executeQuery(sql);
			//�������� rs�� ������ ����
			//System.out.println(rs.getMetaData().getColumnCount());
			
			//3, 4, 5�� ����
			
			//6.����� Handling ; select�� �ø� column����.
			while (rs.next()) {
				System.out.print(rs.getInt("empno")+"\t");
				//System.out.print(rs.getInt("1")+"\t"); �̰͵� �����ѵ� �� �Ⱦ�		
				System.out.print(rs.getString("ename")+"\t");
				System.out.print(rs.getString("job")+"\t");
				System.out.print(rs.getString("mgr")+"\t");
				System.out.print(rs.getDate("hiredate")+"\t");
				//��� ��þ string����. �ٵ� ���⿡�� string���� date�� ��︱ ��
				System.out.print(rs.getInt("sal")+"\t");
				System.out.print(rs.getString("deptno")+"\n");
			}
			//rs.next()Ŀ���� row ���� ������ �� �־�.
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver Ȯ��");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			//7.�ڿ��ݳ�
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