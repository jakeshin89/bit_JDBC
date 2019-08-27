package test;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dao.UsersDAO;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;

public class BookApp {
	
	public static void main(String[] args) {
		new Gui();
	}
}

class Gui extends JFrame{
	
	static int num=0;
	
    BookService service;
    UsersDAO dao = new UsersDAO();
    
    JButton add, list, delete, search, exit;
    TextField bookid, bookname, publisher, price;
    TextArea output;
	
    Gui(){
    	
    	service = new BookServiceImpl();
    	
    	Panel p1 = new Panel();
    	
	    p1.add(add = new JButton("ADD"));
	    p1.add(list = new JButton("List"));
	    p1.add(delete = new JButton("삭제"));
	    p1.add(search = new JButton("검색"));
	    p1.add(exit = new JButton("종료"));
	    add(p1,BorderLayout.SOUTH);
    	
    	Panel p2 = new Panel();
    	
	    p2.setLayout(new GridLayout(4,2));
	    
	    p2.add(new JLabel("  BookID  "));
	    p2.add(bookid = new TextField(20)); 
	    
	    p2.add(new JLabel("  BookName  "));
	    p2.add(bookname = new TextField(20));
	    
	    p2.add(new JLabel("  Publisher  "));
	    p2.add(publisher = new TextField(20));
	    
	   	p2.add(new JLabel("  Price  "));
	   	p2.add(price = new TextField(20));
	   	
    	add(p2,BorderLayout.NORTH);
    	
    	add(output= new TextArea(15, 40));
    	output.setFont(new Font(null, 1, 20));
    	
    	pack();
    	setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	
    	add.addActionListener(e -> {
    		BookVO vo = new BookVO();
    			vo.setBookid(Integer.parseInt(bookid.getText()));
    			vo.setBookname(bookname.getText());
    			vo.setPublisher(publisher.getText());
    			vo.setPrice(Integer.parseInt(price.getText()));
    		service.addBook(vo);
    		
    		bookid.setText("");
    		bookname.setText("");
    		publisher.setText("");
    		price.setText("");
    		
    	});
    	
    	exit.addActionListener(e-> {
    		System.exit(0);
    	});
    	
    	list.addActionListener(e -> {
    		output.setText(" BookList  \n");
    		service.bookList().forEach(i -> System.out.println(i));
    	});
    	
    	delete.addActionListener(e -> {
    		int i = service.deleteBook(Integer.parseInt(bookid.getText()));
    		output.setText(i+"개의 도서 삭제\n");
    	});

    }
}