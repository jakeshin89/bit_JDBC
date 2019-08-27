package test;

import dao.DeptDAO;

public class Test09_DeptDao {

	public static void main(String[] args) {
		
		DeptDAO dao = new DeptDAO();
		dao.getDeptRec().forEach(i -> System.out.println(i));
		//DB와 상관없이 자바 list구조로 만듦
		System.out.println("----------------------------");
		dao.getDeptRec(1, 4).forEach(i -> System.out.println(i));
		
	}
}