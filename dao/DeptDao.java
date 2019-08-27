package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.DeptVO;

public class DeptDao {

	//Dept 테이블의 모든 레코드 정보
	//서비스 레이어 구축?
	public List<DeptVO> getDeptRec() {
	
		String sql = "select * from dept order by deptno";
		//만약 row 갯수가 많다면..
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		List<DeptVO> list = new ArrayList<DeptVO>();
				
		try {
			System.out.println("*****Con 할당*****");
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new DeptVO(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc")));
			}//row mapper 작업; java로 해당 테이블 똑같이 만들고싶.
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, con);
			System.out.println("******자원반납******");
		}
		return list;
		
		//동일한 기능이 있다면, 메소드 내부에서 수행하는 것이 아니라
		//누군가에게 넘겨줄 수 도 있따는 걸 기억해
	}
	
		
	public int updateDept(DeptVO dept) {
			
			String sql = "update dept set dname = ?, loc = ? where deptno = ? ";
			//웬만해선 PK 바꾸지 않기를 추천
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
		
		//만약 row 갯수가 많다면.. paging 처리
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
			System.out.println("*****Con 할당*****");
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
			System.out.println("******자원반납******");
		}
		return list;
	}
	
}
