package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.UsersDAO;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;

public class Test11_BookService {
//�����׽�Ʈ? main�Լ��� application�� ����. 
//application���� �����°� �ƴ϶� ��� ���� �׽�Ʈ�ϰ� ��������.
	
	BookService service = null;
	
	@Before //ȯ��setup
	public void setUp() {
		System.out.println("setUp() ����");
		
		UsersDAO dao = new UsersDAO();
		service = new BookServiceImpl(dao);
	}
	
	@After
	public void tearDown() {
		service = null;
		System.out.println("===== teatDown() =====");
	}
	
	//@Test //TEST Method�� ���۽�Ű�� �;�
	public void list() {
		System.out.println("======= Book List =======");		
		service.bookList().forEach(i -> System.out.println(i));
		System.out.println("=======-----------=======");
		System.out.println();
	}

	@Test
	public void insert() {
		BookVO vo = new BookVO();
		
		vo.setBookid(3);
		vo.setBookname("Python");
		vo.setPublisher("������");
		vo.setPrice(30000);
		int num = 0;
		
		try {
			num = service.addBook(vo);			
		} catch (Exception e) {
	
		}
		if(num>0) System.out.println("��� ����");
		else System.out.println("��� ����");
	}
	
	//@Test
	public void update() {
		BookVO vo = new BookVO();
		vo.setPrice(30000);
		vo.setBookid(1);
		service.updateBook(vo);
	}
	
	//@Test
	public void delete() {
		service.deleteBook(2);
	}
	
}
