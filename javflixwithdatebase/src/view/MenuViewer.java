package view;

public class MenuViewer {
	// 메인 메뉴
	public static void mainMenuView() {
		System.out.println("메뉴를 선택하세요.");
		System.out.println("1. 검색 2. 오늘의 TOP 5");
		System.out.println("3. 카테고리별 검색 4. 국가별 검색");
		System.out.println("5. 내가 찜한 목록 6. 다운로드 목록");
		System.out.println("7. 시청목록 8. 계정 관리");
		System.out.println("9. 멤버쉽 결제 10. 내 정보 조회  ");
		System.out.print("11. 로그아웃  ");
	}
	//유저 메뉴
	public static void accountMenuView() {
		System.out.println("변경할 정보를 선택하세요.");
		System.out.print("1. 프로필 이름 변경 2. 프로필 비밀번호 변경 3. 새 프로필 만들기 4. 프로필 변경 5. 계정 비밀번호 변경 6. 뒤로가기 ");
	}
	
	public static void ottMenuView() {
		System.out.println("메뉴를 선택하세요.");
		System.out.print("1. OTT 선택 2. 검색 3. 뒤로가기  ");
	}
	
	public static void categoryMenuView() {
		System.out.println("검색할 카테고리를 선택하세요.");
		System.out.print("1. 로맨스 2. 드라마 3. 액션 4. 애니메이션 5. 범죄 6. 스릴러 7. 코미디 8. 뒤로가기  ");
	}
	public static void countryMenuView() {
		System.out.println("검색할 나라를 선택하세요.");
		System.out.print("1. 한국 2. 미국 3. 일본 4. 유럽 5. 스페인 6.뒤로가기  ");
	}
	public static void optionMenuView() {
		System.out.println("메뉴를 선택하세요.");
		System.out.print("1. 시청하기 2. 찜하기 3. 다운로드 4. 뒤로가기  ");
	}
	public static void addListMenuViewer() {
		System.out.println("메뉴를 선택하세요.");
		System.out.print("1. 시청하기 2. 다운로드 3. 삭제하기 4. 뒤로가기  ");
	}
	public static void downListMenuViewer() {
		System.out.println("메뉴를 선택하세요.");
		System.out.print("1. 시청하기 2. 찜하기 3. 삭제하기 4. 뒤로가기  ");
	}
	public static void loginMenu() {
		System.out.println("메뉴를 선택하세요.");
		System.out.print("1. 유저 로그인 2. 관리자 로그인 3. 회원가입 4. 종료  ");
	}
	public static void adminMenuView() {
		System.out.println("메뉴를 선택하세요."); 
		System.out.print("1. 유저 관리 2. ott 관리 3. 뒤로가기  ");
	}
	public static void watchListMenuViewer() {
		System.out.println("메뉴를 선택하세요.");
		System.out.print("1. 시청하기 2. 찜하기 3. 다운로드 4. 뒤로가기  ");
	}
	public static void top5MenuView() {
		System.out.println("메뉴를 선택하세요.");
		System.out.print("1. 시청하기 2. 찜하기 3. 다운로드 4. 뒤로가기  ");
	}
	public static void userManage() {
		System.out.println("메뉴를 선택하세요.");
		System.out.print("1. 유저 조회 2. 유저 수정 3. 유저 삭제 4. 뒤로가기  ");
	}
	public static void ottManage() {
		System.out.println("메뉴를 선택하세요.");
		System.out.print("1. ott 조회 2. ott 수정 3. ott 추가 4. ott 삭제 5. 뒤로가기  ");
	}
	public static void membershipMenuViewer() {
		System.out.println("메뉴를 선택하세요."); 
		System.out.print("1. 멤버쉽 결제 2. 뒤로가기  ");
	}
}
