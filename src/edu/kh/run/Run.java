package edu.kh.run;

import java.sql.SQLException;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run {

	public static void main(String[] args) {
		
		TestService service = new TestService();
		
		//TB_TEST 테이블에 INSERT 수행 
		TestVO vo1 = new TestVO(1,"제목1","내용1");
		
		//TB_TEST 테이블에 insert를 수행하는 서비스 메서드 호출 후 결과 반환하기
		try {
			int result = service.insert(vo1);
			if(result > 0 )
			System.out.println("insert 완료");
			
		}catch(SQLException e) {
			System.out.println("SQL 수행 중 오류발생");
			e.printStackTrace();
		}
		

	}

}
