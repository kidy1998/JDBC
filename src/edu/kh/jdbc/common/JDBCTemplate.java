package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	/*  DB 연결 (Connection 생성), 자동 커밋 off, 트랜잭션 제어, JDBC 객체 자원 반환(close)
	 * 
	 * 이러한 JDBC 에서 반복 사용되는 코드를 모아둔 클래스
	 * 
	 *  * 모든 필드,메서드가 static * -> 별도 객체 생성 X
	 *  -> 어디서든지 클래스명.필드명 / 클래스명.메서드명으로 호출 가능
	 * 
	 */
	
	private static Connection conn = null;
	
	
	/**  DB 연결정보를 담고있는 Connection 객체 생성 및 반환 메서드
	 *  
	 */
	public static Connection getConnection() {
		
		try {
			//현제 커넥션 객체가 없을 경우에만 새 커넥션 객체 생성
			if(conn == null || conn.isClosed()) {
				Properties pro = new Properties();
				// Map<String , String> 형태의 객체 XML 입출력 특화
				
				pro.loadFromXML(new FileInputStream("driver.xml"));
				//driver.xml 파일 읽어오기
				
				String driver = pro.getProperty("driver");
				String url = pro.getProperty("url");
				String user = pro.getProperty("user");
				String password = pro.getProperty("password");
				//읽어온 값을 변수에 저장
				
				//커넥션 생성
				Class.forName(driver); // Oracle JDBC Driver 객체 메모리 로드
				
				 conn = DriverManager.getConnection(url,user,password);
				// DriverManager를 이용해 Connection 객체 생성
				 
				 conn.setAutoCommit(false); //자동 커밋 비활성화
				
			}
			
		}catch(Exception e) {
			System.out.println("Connection 생성 중 예외 발생");
			e.printStackTrace();
		}
		
		return conn;
	}

	
	public static void close(Connection conn) {
		
		try {
			//전달받은 conn이 참조하는 Connection 객체가 있으면서 그 객체가 close상태가 아니면
			if(conn != null && !conn.isClosed()) conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/** Statement(부모) PreparedStatment(자식) 객체 자원 반환 메서드
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) stmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed() ) rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**트랜젝션 Commit 메서드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** 트랜젝션 rollback 메서드
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.rollback();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
}
