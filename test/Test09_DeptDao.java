package test;

import dao.DeptDao;

public class Test09_DeptDao {

	public static void main(String[] args) {
		
		DeptDao dao = new DeptDao();
		dao.getDeptRec().forEach(i -> System.out.println(i));
		//DB�� ������� �ڹ� list������ ����
		System.out.println("----------------------------");
		dao.getDeptRec(1, 4).forEach(i -> System.out.println(i));
		
	}
}