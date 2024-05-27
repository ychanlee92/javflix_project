package view;

public class MenuViewer {
	// 메인 메뉴
	public static void mainMenuView() {
		System.out.println("");
		System.out.println("Choose the options below.");
		System.out.println("1. Search             2. Today's TOP 5");
		System.out.println("3. Categories         4. Regions");
		System.out.println("5. My List            6. Downloads");
		System.out.println("7. Recently Watched   8. Account");
		System.out.println("9. Membership Payment 10. My Javflix  ");
		System.out.print("11. Logout  ");
	}
	//유저 메뉴
	public static void accountMenuView() {
		System.out.println("Choose the options below.");
		System.out.print("1. Edit Profile Name 2. Edit Profile Password 3. Add New Profile 4. Switch Profile 5. Edit Account Password 6. Main Menu ");
	}
	
	public static void ottMenuView() {
		System.out.println("Choose the options below.");
		System.out.print("1. Choose OTT 2. Search 3. Back  ");
	}
	
	public static void categoryMenuView() {
		System.out.println("Choose the genre below.");
		System.out.print("1. Romance 2. Drama 3. Action 4. Animation 5. Crime 6.Thriller 7. Comedy 8. Back  ");
	}
	public static void countryMenuView() {
		System.out.println("Choose the region below.");
		System.out.print("1. South Korea 2. United States 3. Japan 4. Europe 5. Spain 6. Back  ");
	}
	public static void optionMenuView() {
		System.out.println("Choose the options below.");
		System.out.print("1. Watch 2. Add to my list 3. Download 4. Back  ");
	}
	public static void addListMenuViewer() {
		System.out.println("Choose the options below.");
		System.out.print("1. Watch 2. Download 3. Delete 4. Back  ");
	}
	public static void downListMenuViewer() {
		System.out.println("Choose the options below.");
		System.out.print("1. Watch 2. Add to my list 3. Delete 4. Back  ");
	}
	public static void loginMenu() {
		System.out.println("Choose the options below.");
		System.out.print("1. User Login 2. Administrator Login 3. Sign Up 4. Program Exit  ");
	}
	public static void adminMenuView() {
		System.out.println("Choose the options below.");
		System.out.print("1. User Management 2. Ott Management 3. Back  ");
	}
	public static void watchListMenuViewer() {
		System.out.println("Choose the options below.");
		System.out.print("1. Watch 2. Add to my list 3. Download 4. Back  ");
	}
	public static void top5MenuView() {
		System.out.println("Choose the options below.");
		System.out.print("1. Watch 2. Add to my list 3. Download 4. Back   ");
	}
	public static void userManage() {
		System.out.println("Choose the options below.");
		System.out.print("1. User List 2. Edit User 3. Delete User 4. Back  ");
	}
	public static void ottManage() {
		System.out.println("Choose the options below.");
		System.out.print("1. Ott List 2. Edit Ott 3. Add ott 4. Delete Ott 5. Back  ");
	}
	public static void membershipMenuViewer() {
		System.out.println("Choose the options below.");
		System.out.print("1. Purchase Membership 2. Back  ");
	}
}
