package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class LoadXMLFile {

	public static void main(String[] args) {
	   
		// XML 파일 읽어오기 (Properties, FileInputStream) 필요
		
		try {
			Properties pro = new Properties();
			
			//driver.xml 파일을 읽어오기 위한 InputStream 객체 생성
			FileInputStream fis = new FileInputStream("driver.xml");
			
			//연결된 driver.xml 파일의 모든 내용을 읽어와 properties 객체에 K:V 형식으로 저장
			pro.loadFromXML(fis);
			System.out.println(pro);
			
			//property : 속성(데이터)
			//pro.getProperty(key); : key가 일치하는 속성을 얻어옴
			
			String driver = pro.getProperty("driver");
			String url = pro.getProperty("url");
			String user = pro.getProperty("user");
			String password = pro.getProperty("password");
			
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,user,password);
			
			System.out.println(conn);
			
			
			
		}catch(Exception e ) {
			e.printStackTrace();
		}

	}

}
