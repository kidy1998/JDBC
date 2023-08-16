package edu.kh.jdbc.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.model.vo.TestVO;

public class TestDAO {
	//Data Acess Object : 데이터가 저장된 DB에 접근하는 객체
	// 					-> SQL 수행, 결과 반환받는 기능을 수행
	
	// JDBC 객체를 참조하기 위한 참조 변수 선언
	private Statement st;
	private PreparedStatement pst;
	
	
	
	//xml 로 SQL 사용 위해 Properties 객체 사용
	private Properties pro = new Properties();
	
	public TestDAO() { //기본 생성자
		// TestDAO 객체 생성 시 test-query.xml 파일의 내용을 읽어와 Properties 객체에 저장
		
		try {
			pro.loadFromXML(new FileInputStream("test-query.xml"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public int insert(Connection conn, TestVO vo1)throws SQLException{
		
		// 1.결과 저장용 변수
		int result = 0; 
		
		try {
		
		// 2. SQL 작성(test-query.xml 에 작성된 SQL얻어오기)
		String sql = pro.getProperty("insert");
		// key 가 insert 인 sql구문의 value를 읽어옴 
		// -> INSERT INTO TB_TEST 
		//	  VALUES(?,?,?)
		
		//3.PrepareStatement
		pst = conn.prepareStatement(sql);
		
		//4. 위치 홀더(value(?,?,?)) 에 알맞은 값 세팅
		pst.setInt(1, vo1.getTestNo());
		pst.setString(2, vo1.getTestTitle());
		pst.setString(3, vo1.getTestContent());
		
		//5. SQL (INSERT) 수행 후 결과 반환하기
		result = pst.executeUpdate(); 
		// DML 수행하고 반영된 행의 개수 반환
		}finally {
		close(pst);
			
		}
		return result;
		
	}

}
