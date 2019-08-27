package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.DeptVO;

public class DeptDao {

	//Dept ���̺��� ��� ���ڵ� ����
	//���� ���̾� ����?
	public List<DeptVO> getDeptRec() {
	
		String sql = "select * from dept order by deptno";
		//���� row ������ ���ٸ�..
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		List<DeptVO> list = new ArrayList<DeptVO>();
				
		try {
			System.out.println("*****Con �Ҵ�*****");
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new DeptVO(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc")));
			}//row mapper �۾�; java�� �ش� ���̺� �Ȱ��� ������.
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, con);
			System.out.println("******�ڿ��ݳ�******");
		}
		return list;
		
		//������ ����� �ִٸ�, �޼ҵ� ���ο��� �����ϴ� ���� �ƴ϶�
		//���������� �Ѱ��� �� �� �ֵ��� �� �����
	}
	
		
	public int updateDept(DeptVO dept) {
			
			String sql = "update dept set dname = ?, loc = ? where deptno = ? ";
			//�����ؼ� PK �ٲ��� �ʱ⸦ ��õ
			Connection con = null;
			PreparedStatement ps = null;
			int result = 0;
			
			try {
				con = JDBCUtil.getConnection();
				ps = con.prepareStatement(sql);
				
				ps.setString(1, dept.getDname());
				ps.setString(2, dept.getLoc());
				ps.setInt(3, dept.getDeptno());
				
				result = ps.executeUpdate();
				
			} catch (Exception e) {
				e.getStackTrace();
			} finally {
				JDBCUtil.close(null, ps, con);
			}
			return result;	
	}
	
	
	public int insertDept(DeptVO dept) {
		
		String sql = "insert into dept(deptno, dname, loc) values(?, ?, ?)";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, dept.getDeptno());
			ps.setString(2, dept.getDname());
			ps.setString(3, dept.getLoc());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(null, ps, con);
		}	
		return result;	
	}

	
	public List<DeptVO> getDeptRec(int page, int n) {
		
		//���� row ������ ���ٸ�.. paging ó��
		String sql = 
				"select * from (" +
				"select rownum row#, deptno, dname, loc " +
				"from (select * from dept order by deptno) " + 
				") where row# between ? and ? ";
		
		int start = n*(page-1)+1;
		int end = start+(n-1);
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		List<DeptVO> list = new ArrayList<DeptVO>();
				
		try {
			System.out.println("*****Con �Ҵ�*****");
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new DeptVO(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc")));
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, con);
			System.out.println("******�ڿ��ݳ�******");
		}
		return list;
	}
	
}
