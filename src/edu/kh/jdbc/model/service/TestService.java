package edu.kh.jdbc.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.model.dao.TestDAO;
//import static 구문 : static이 붙은 구문,필드를 호출할 때 클래스명을 생략할 수 있게 해줌
import edu.kh.jdbc.model.vo.TestVO;

public class TestService {

	private TestDAO dao = new TestDAO();
	
	// 하나의 insert 메서드에서 n개의 DAO 호출

	public int insert(TestVO vo1)throws SQLException {
		// 커넥션 생성
		Connection conn = getConnection();
		
		//DAO 메서드 호출하여 수행 후 결과 반환받기
		int result = dao.insert(conn,vo1);
		
		//트랜잭션 제어
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		// 커넥션 반환 
		close(conn);
		
		//결과 반환
		return result;
	}
	
	
	
	
	public int insert(TestVO vo1, TestVO vo2, TestVO vo3)throws Exception {
				// 커넥션 생성
				Connection conn = getConnection();
				int result = 0;
				//DAO 메서드 호출하여 수행 후 결과 반환받기
				int result1 = dao.insert(conn,vo1);
				int result2 = dao.insert(conn,vo2);
				int result3 = dao.insert(conn,vo3);
				
				int resultSum = result1+result2+result3;
				
				//트랜잭션 제어
				if(resultSum == 3) {
					commit(conn);
					result = 1;
				}
				else	rollback(conn);
				
				// 커넥션 반환 
				close(conn);
				
				//결과 반환
				return result;
		
		
	}
	
	

}
