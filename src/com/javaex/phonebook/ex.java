package com.javaex.phonebook;

public class ex {
	public void menuDisplay() {
		System.out.println("----------------");
		System.out.println("   전화번호부   ");
		System.out.println("----------------");
		System.out.println("1. 전체 출력");
		System.out.println("2. 검색");
		System.out.println("3. 추가");
		System.out.println("4. 수정");
		System.out.println("5. 삭제");
		System.out.println("0. 종료");
	}
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		int sel = 0;
		
		while(true) {
			menuDisplay();
			System.out.println("선택: ");
			while(true) {
				try {
					sel = sc.nextInt();
					while(sel<0 || sel>5) {
						sel = sc.nextInt();
					}
					break;
				} catch (InputMismatchException ime) {
					sc = new Scanner(System.in);
				}
			}
			
		switch(sel) {
			case 1:
				output(); break;
			case 2:
				search(); break;
			case 3:
				append(); break;
			case 4:
				modify(); break;
			case 5:
				remove(); break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}		
	}
	
	public void output() {
		dao.sort();
		
		List<List<AddressDto>> list = dao.getList();
		for(int i=0; i<list.size(); i++) {
			dao.familyOutput(i);
		}
	}
	
	int find(String name) {
		int index = CommonUtil.familyToIndex(name.charAt(0));
		List<AddressDto> familyList = dao.getList().get(index);
		for(int i=0; i<familyList.size(); i++) {
			AddressDto dto = familyList.get(i);
			if(dto.getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	public void search() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("찾는 이름: ");
		String name = sc.next();
		int index = CommonUtil.familyToIndex(name.charAt(0));
		int pos = find(name);
		
		if(pos==-1) {
			System.out.println("해당 이름은 목록에 없습니다.");
			return;
		}
		
		List<AddressDto> familyList = dao.getList().get(index);
		AddressDto dto = familyList.get(pos);
		dto.eachOutput();
	}
	
	public void append() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("추가할 이름: ");
		String name = sc.next();
		int index = CommonUtil.familyToIndex(name.charAt(0));
				
		List<AddressDto> familyList = dao.getList().get(index);
		AddressDto dto = new AddressDto();
		dto.setName(name);
		System.out.println("전화번호: ");
		dto.setNum(sc.next());
		System.out.println("메일주소: ");
		dto.setEmail(sc.next());		
		familyList.add(dto);

		dao.sort();
	}
	
	public void modify() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("수정할 이름: ");
		String name = sc.next();
		int index = CommonUtil.familyToIndex(name.charAt(0));
		int pos = find(name);
		
		if(pos==-1) {
			System.out.println("해당 이름은 목록에 없습니다.");
			return;
		}
		
		List<AddressDto> familyList = dao.getList().get(index);
		AddressDto dto = familyList.get(pos);
		System.out.println("이름: ");
		dto.setName(sc.next());
		System.out.println("전화번호: ");
		dto.setNum(sc.next());
		System.out.println("메일주소: ");
		dto.setEmail(sc.next());
		System.out.println("수정되었습니다.");

		dao.sort();
	}
	
	public void remove() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("삭제할 이름: ");
		String name = sc.next();
		int index = CommonUtil.familyToIndex(name.charAt(0));
		int pos = find(name);
		
		if(pos==-1) {
			System.out.println("해당 이름은 목록에 없습니다.");
			return;
		}
		
		List<AddressDto> familyList = dao.getList().get(index);
		familyList.remove(pos);
		System.out.println("삭제되었습니다.");
	}
}