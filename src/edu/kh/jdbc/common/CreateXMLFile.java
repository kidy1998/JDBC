package edu.kh.jdbc.common;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {

	public static void main(String[] args) {
		// XML(Extensible Markup Language) : 단순화된 데이터 기술 형식 
		// XML 에 저장되는 데이터 형식은 MAP 형식이다 (KEY : VALUE) KEY,VALUE 모두 문자열 형식
		
		// XML 파일을 읽고 쓰기 위한 IO 관련 클래스 필요
		
		// * Properties 컬렉션 객체
		// Map의 후손 클래스, key value 모두 문자열 형식
		// XML 파일을 읽고 쓰는데 특화된 메서드 제공
		
		
		try {
			
			Scanner sc = new Scanner(System.in);
			
			//properties 객체 생성
			Properties pro = new Properties();
			
			System.out.print("생성할 파일 이름 :" );
			String name = sc.nextLine();
			
			FileOutputStream fos = new FileOutputStream(name +".xml");
			//  입력받은 파일명에 xml 확장자
			
			pro.storeToXML(fos, name);
			// properties 객체를 이용해서 XML 파일 생성
			System.out.println(name + ".xml 파일 생성 완료");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
