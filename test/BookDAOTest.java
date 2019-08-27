package test;

import dao.UsersDAO;
import vo.BookVO;

public class BookDAOTest {

	public static void main(String[] args) {
		
		UsersDAO book = new UsersDAO(); //메소드
		
		BookVO d = new BookVO();
		
		//Insert 수행
/*		d.setBookid(1);
		d.setBookname("Java");
		d.setPublisher("Infinity");
		d.setPrice(30000);
		
		int count = book.insertBook(d);
		
		System.out.println(count+"번 insert 수행하였습니다.");
*/
		
/*		//Update 수행
		d.setBookid(1);
		d.setBookname("Java Programming");
		d.setPublisher("Hanbit");
		d.setPrice(25000);
		int ucount = book.updateBook(d);
		
		System.out.println(ucount+"번 update 수행하였습니다.");
*/
		
/*		//Delete 수행
		int dcount = book.deleteBook(3);
		System.out.println(dcount+"번 delete 수행하였습니다.");
*/		

		//Search 수행
		book.searchBook("java");
		
		book.getBookRec().forEach(i -> System.out.println(i));
		System.out.println();
		
	}
}