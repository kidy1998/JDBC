package edu.kh.run;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run2 {

	public static void main(String[] args) {
		//TB 테이블에 3개 입력
		TestService service = new TestService();
		
		TestVO vo1 = new TestVO(70,"제목70","내용70");
		TestVO vo2 = new TestVO(80,"제목80","내용80");
		TestVO vo3 = new TestVO(90,"제목90","내용90");
		
		try {
			int result = service.insert(vo1,vo2,vo3);
			if (result > 0) System.out.println("성공");
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
