package service;

import java.util.List;

import vo.BookVO;

public interface BookService {

	List<BookVO> bookList();
	int addBook(BookVO vo);
	//클라이언츠, 서버 간 약속
	
	int deleteBook(int bookid);
	int updateBook(BookVO vo);
	
}