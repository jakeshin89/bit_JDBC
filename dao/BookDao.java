package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.BookVO;

public class BookDao {
//DB 연동하는 코드 갖고있다는 정보를 가진 이름, DAO
	
	public List<BookVO> getBookRec(){
		
			String sql = "select * from book order by bookid";
			
			List<BookVO> list = new ArrayList<BookVO>();
			
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				con = JDBCUtil.getConnection();
				ps = con.prepareStatement(sql);	
				
				rs = ps.executeQuery();
				
				//while(rs.next()) {
				//	list.add(new BookVO(rs.getInt("bookid"), rs.getString("bookname"), 
				//			rs.getString("publisher"), rs.getInt("price")));
				//}
				
				while (rs.next()) {
					BookVO vo = new BookVO();
						vo.setBookid(rs.getInt("bookid"));
						vo.setBookname(rs.getString("bookname"));
						vo.setPublisher(rs.getString("publisher"));
						vo.setPrice(rs.getInt("price"));
						
						list.add(vo);
				}			
				
			} catch (Exception e) {
				e.getStackTrace();
			} finally {
				JDBCUtil.close(rs, ps, con);
			}	
			return list;
		}
	
	public List<BookVO> getBookRec(int page, int n){
	//필요하면 page 만들기
		String sql =
		
		"select * from (" +
		"select rownum row#, bookid, bookname, publisher, price "+
		"from (select * from book order by bookid) "+ 
		") where row# between ? and ? order by bookid ";
		
		int start = n*(page-1)+1;
		int end = start+(n-1);

		List<BookVO> list = new ArrayList<BookVO>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);	
			//ps.setInt(1, 1);
			//ps.setInt(2, 5);
			
			rs = ps.executeQuery();
			
			//while(rs.next()) {
			//	list.add(new BookVO(rs.getInt("bookid"), rs.getString("bookname"), 
			//			rs.getString("publisher"), rs.getInt("price")));
			//}
			
			while (rs.next()) {
				BookVO vo = new BookVO();
					vo.setBookid(rs.getInt("bookid"));
					vo.setBookname(rs.getString("bookname"));
					vo.setPublisher(rs.getString("publisher"));
					vo.setPrice(rs.getInt("price"));
					
					list.add(vo);
			}			
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, con);
		}	
		
		return list;
	}
	
	
	public void searchPublisher(String publisher){
		
		String sql = "select * from book where upper(publisher) like ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+publisher.toUpperCase()+"%");
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.print(rs.getInt("bookid")+"  ");
				System.out.print(rs.getString("bookname")+"  ");
				System.out.print(rs.getString("publisher")+"  ");
				System.out.print(rs.getInt("price")+"  ");
				System.out.println();
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, con);
		}		
		
	}
	
	
	public int insertBook(BookVO book) {
		
		String sql = 
			"insert into book(bookid, bookname, publisher, price) "+
			"values(?, ?, ?, ?) ";

		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, book.getBookid());
			ps.setString(2, book.getBookname());
			ps.setString(3, book.getPublisher());
			ps.setInt(4, book.getPrice());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(null, ps, con);
		}
		return result;
	}
	
	
	public int updateBook(BookVO book) {
		
		String sql = "update book set price = ? where bookid = ?";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, book.getPrice());
			ps.setInt(2, book.getBookid());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(null, ps, con);
		}
		return result;
	}
	
	
	public void searchBook(String bookname) {
		
		String sql = "select * from book where upper(bookname) like ? order by bookid";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+bookname.toUpperCase()+"%");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.print(rs.getInt("bookid")+"   ");
				System.out.print(rs.getString("bookname")+"   ");
				System.out.print(rs.getString("publisher")+"   ");
				System.out.print(rs.getInt("price")+"   ");
				System.out.println();
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, con);
		}
	}

	
	public int deleteBook(int bookid) {
		
		String sql = "delete from book where bookid = ? ";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bookid);
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCUtil.close(null, ps, con);
		}
		return result;
	}
	
}

//select count(*) from book;