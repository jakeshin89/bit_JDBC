package test;

import dao.UsersDAO;
import vo.BookVO;

public class BookDAOTest {

	public static void main(String[] args) {
		
		UsersDAO book = new UsersDAO(); //�޼ҵ�
		
		BookVO d = new BookVO();
		
		//Insert ����
/*		d.setBookid(1);
		d.setBookname("Java");
		d.setPublisher("Infinity");
		d.setPrice(30000);
		
		int count = book.insertBook(d);
		
		System.out.println(count+"�� insert �����Ͽ����ϴ�.");
*/
		
/*		//Update ����
		d.setBookid(1);
		d.setBookname("Java Programming");
		d.setPublisher("Hanbit");
		d.setPrice(25000);
		int ucount = book.updateBook(d);
		
		System.out.println(ucount+"�� update �����Ͽ����ϴ�.");
*/
		
/*		//Delete ����
		int dcount = book.deleteBook(3);
		System.out.println(dcount+"�� delete �����Ͽ����ϴ�.");
*/		

		//Search ����
		book.searchBook("java");
		
		book.getBookRec().forEach(i -> System.out.println(i));
		System.out.println();
		
	}
}