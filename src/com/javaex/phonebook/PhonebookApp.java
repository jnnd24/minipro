package com.javaex.phonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class PhonebookApp {
	
	public static void main(String[] args) throws IOException {
		

		Scanner sc = new Scanner(System.in);
		
		Writer fw = new FileWriter("./PhoneDB.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		//시작화면
		System.out.println("**********************************");
		System.out.println("*        전화번호 관리 프로그램        *");
		System.out.println("**********************************");
		System.out.println();
		System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
		System.out.println("----------------------------------");
		
		
		List<Person> pList = new ArrayList<Person>(); 
		
		
		//리스트 등록
		Reader fr = new FileReader("./PhoneDB.txt");
		BufferedReader br = new BufferedReader(fr);
		
		//리스트 올리기
		while(true) {
			
			String data = br.readLine();
			
			if(data==null) {
				break;
			}
			
			String[] pbData =  data.split(",");
			
			String name = pbData[0];
			String hp = pbData[1];
			String company = pbData[2];
			
			Person person = new Person(name, hp, company);//클래스잡고
			pList.add(person); // ㄴ 추가하기
			
			
			
		}
		
		//System.out.println(pList.toString()); // 리스트 등록 되었는지 체크
		
		while(true){
			//메뉴
			System.out.print("메뉴번호: ");
			String sel = sc.nextLine(); // int쓰면 숫자아닌경우 오류나서 String사용
		
			switch(sel) {
			case "1":
				//1.리스트
				int i = 0; // 리스트 순번용
				System.out.println("<1.리스트>");
				for(Person person :pList) {
					
					i = i+1;
					//System.out.println(person.);//test
					System.out.println(i + "\t" + person.getName() + "\t"+ person.getHp() +
					"\t" + person.getCompany() + 
					"\t" + person.getCompany());
				}
				System.out.println("----------------------------------");
				break;
			
			case "2":
				//2.등록
				System.out.println("<2.등록>");
				
				Person p = new Person(); // p 로 연결
				System.out.print(">이름: ");
				p.setName(sc.next());
				System.out.print(">휴대전화: ");
				p.setHp(sc.next());
				System.out.print(">회사전화: ");
				p.setCompany(sc.next());
				
				pList.add(p); // 배열트에 추가
				
				System.out.println("[등록되었습니다]");
				
				

			
				//txt파일과 연동하여 쓰기 , 1번리스트 부터 메모장을 아예 새롭게 씀
				for(Person person : pList) {
					String newData = person.getName() + "," + person.getHp() + "," + person.getCompany();
					
					bw.write(newData);
					bw.newLine();
				
				}//for Person
				bw.close();
				br.close();
	
				break;
				
			case "3":
				//3.삭제
				System.out.println("<3.삭제>");
				System.out.print(">번호: ");
				int removeNum=sc.nextInt();
				pList.remove(removeNum-1); // 방번호는0번부터고, 리스트는 1번부터임 

				//txt파일과 연동하여 쓰기
				for(Person person : pList) {
					String newData = person.getName() + "," + person.getHp() + "," + person.getCompany();
					//삭제 된 리스트로 txt파일을 아예 다시 작성하기
					bw.write(newData);
					bw.newLine();
				
				}//for Person
				bw.close();
				br.close();
				
				break;
				
			case "4":
				//4.검색
				System.out.println("**검색 메뉴는 준비중입니다.**"); // 4. 검색 메뉴
				
				break;
				
			case "5":
				//5.종료
				System.out.println("**********************************");
				System.out.println("*            감사합니다            *");
				System.out.println("**********************************");
			
				break;//스위치 탈출해서 아래 if문으로 이동함
				
			default: 	//없는 메뉴
				System.out.println("다시 입력해 주세요.");
				break;
				
			}  //switch
			if (sel.equals("5")) { // 5 입력 시 while문까지 탈출
				break;
			}
		}   //while	
		sc.close();	
		
		
	}

}
