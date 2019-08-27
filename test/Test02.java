package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test02 {
	//SQL ���� �����ѵ�...

	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.0.126:1521:xe";
		//jdbc����, driver����(oracle, mysql), �ּ� ã�ư��� connection �õ�
		String user = "SCOTT";
		String pw = "TIGER";
		
		String sql =
		
		"select ename as \"�����\", sal as �޿�, dname as �μ���, loc, grade "+
		"from emp "+ 
			 "join dept "+
			 "on emp.deptno = dept.deptno "+
			 "join salgrade "+
			 "on sal between losal and hisal "+
		"where emp.deptno=10 "+
		"order by emp.deptno";
		//blank �� �־���� �ؿ�
		//�׳� alias ��... ����� �ݿ� ����
		//escaep�ϰ� alias�� �� ������� �־��µ� �ȴ�! ������ ������ ��������... keep goin..
		//QUERY�� �ʹ� ���? View �� �� ���ݾ�. �� �� �غ���.
		
		System.out.println("== JDBC TEST ==");
		
		Connection con = null;
		//�� client�ε� server�� connection����
		Statement st = null;
		ResultSet rs = null; //SQL select �����, ��, statement�� ���� table ���� ��ü
		int result = 0; //DML�� ����� (���̺� ������)
		//�̰� � ������ ó���ҰųĿ� ���� ������� �޶����� ������ �̷��� �� ��
		
		try {

			Class.forName(driver);
			//new oracle.jdbc.OracleDriver() //Compile
			//�̷��� �ε��ϸ� ����̹� �Ŵ����� �̵��� ������
			//����̹� ������ oracle, mysql... ��� ����
			
			con = DriverManager.getConnection(url, user, pw);
			//con.getMetaDate �ϸ� ���� ���� �� ����.
			
			st = con.createStatement();
			//�Դٰ��ٴ� ���� ��ü
			
			//5.SQL���� ����
			rs = st.executeQuery(sql);
			//���� �������� rs�� ������ ����. ������� table
			//rs�� �� ������� ������. 
			//rs.getMetaData�� column���� �ˤ�����.
			//select�ϸ� Ŀ���� column���� ����Ű�� ����.
			//System.out.println(rs.getMetaData().getColumnCount());
			//statement���� query �����ϴµ�, select �����Ϸ��� query
			//st.executeUpdate(sql); DML(insert, delete, update)�� update
			//DML ������� int
			
			
			//6.����� Handling
			while (rs.next()) {				
				System.out.print(rs.getString("�����")+"\t");
				System.out.print(rs.getString("�޿�")+"\t");
				System.out.print(rs.getString("�μ���")+"\t");
				System.out.print(rs.getString(4)+"\t");
				System.out.print(rs.getString(5)+"\n");
			}
			//select�ϸ� Ŀ���� column���� ����Ű�� ����.
			//rs.next()Ŀ���� row ���� ������ �� �־�.
			//�������� �ϸ� data �ִ°Ű�, ������ false return;
			//��°��� �ε������� �̾���. sysout�� �ִ°� column��
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver Ȯ��");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			
			//7.�ڿ��ݳ�
			//Database�� ���� connection ������ ����ȭ; �׷��� �ʿ��Ѱ� �ڿ��ݳ�
			//�ڿ��ݳ� �ϸ� ���̻� ���� �Ұ�
			//���̹� ���� ����, ������ �����ϸ� ������ ����.
			//������������� �Ἥ ��ġ ����Ǿ��ִ� �� ó�� ������.
			//���� �ڵ����� �˾Ƽ� ���� ������, DB�� ������ ������ ��� �Ǿ��־ �ڿ��Ҹ� ����
			//�׷��� �� �� ������ ����� ��
			//rs, st, con�� ����������
			
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

//Database 2 Tier Case, then (3Tier �̻���) Multi Tier Case
//RMI, �л�ȯ��? �ڹ�?