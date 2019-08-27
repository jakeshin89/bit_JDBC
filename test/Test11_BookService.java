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
//단위테스트? main함수는 application이 동작. 
//application으로 돌리는게 아니라 잠깐 뭔갈 테스트하고 싶은거임.
	
	BookService service = null;
	
	@Before //환경setup
	public void setUp() {
		System.out.println("setUp() 수행");
		
		UsersDAO dao = new UsersDAO();
		service = new BookServiceImpl(dao);
	}
	
	@After
	public void tearDown() {
		service = null;
		System.out.println("===== teatDown() =====");
	}
	
	//@Test //TEST Method로 동작시키고 싶어
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
		vo.setPublisher("진영사");
		vo.setPrice(30000);
		int num = 0;
		
		try {
			num = service.addBook(vo);			
		} catch (Exception e) {
	
		}
		if(num>0) System.out.println("등록 성공");
		else System.out.println("등록 실패");
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
