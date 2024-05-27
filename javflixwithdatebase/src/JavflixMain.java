import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import controller.DBUtil;
import controller.OttDAO;
import controller.OttManager;
import controller.ProfileManager;
import controller.UserManager;
import model.ProfileVO;
import model.UserVO;
import view.ACCOUNT_CHOICE;
import view.ADDLIST_CHOICE;
import view.ADMINMENU_CHOICE;
import view.DOWNLIST_CHOICE;
import view.LOGIN_CHOICE;
import view.MEMBERSHIP_CHOICE;
import view.MENU_CHOICE;
import view.MenuViewer;
import view.OTTMANAGE_CHOICE;
import view.OTT_CHOICE;
import view.TOP5_CHOICE;
import view.USERMANAGER_CHOICE;
import view.WATCHLIST_CHOICE;

public class JavflixMain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
		mainmenu();
	}

	public static void mainmenu() throws InterruptedException {
		System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
		System.out.println("");
		System.out.println("");
		System.out.println("                   Welcome to Javflix!");
		System.out.println("");
		System.out.println("");
		System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");

		// 메인 메뉴
		int num;
		while (true) {
			MenuViewer.loginMenu();
			num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case LOGIN_CHOICE.USER: // 유저 로그인
				userLogin();
				break;
			case LOGIN_CHOICE.ADMIN: // 관리자 로그인
				adminLogin();
				break;
			case LOGIN_CHOICE.SIGNUP: // 회원가입
				UserManager.signUp();
				break;
			case LOGIN_CHOICE.END: // 사용종료
				System.out.println("The program has been terminated");
				return;
			default:
				System.out.println("Incorrect choice, please choose the option again");
				break;
			}
		}
	}

	// 유저 로그인
	public static void userLogin() throws InterruptedException {
		int choiceNum;
		boolean flag = false;
		UserVO user = UserManager.login();
		if (user.getUser_id() == null) {
			System.out.println("Failed to login due to incorrect user information");
		} else {
			System.out.println("Login Success!");
			ProfileVO profile = ProfileManager.profileCheck(user);
			ProfileVO pro = ProfileManager.profileLogin(profile, user);
			while (!flag) {
				MenuViewer.mainMenuView();
				choiceNum = Integer.parseInt(sc.nextLine());
				switch (choiceNum) {

				case MENU_CHOICE.SEARCH: // 검색
					searchMenu(pro);
					break;
				case MENU_CHOICE.TOP5: // 조회수 top5
					top5Menu(pro);
					break;
				case MENU_CHOICE.CATEGORY: // 카테고리별 검색
					categoryMenu(pro);
					break;
				case MENU_CHOICE.COUNTRY: // 국가별 검색
					countryMenu(pro);
					break;
				case MENU_CHOICE.ADDLIST: // 찜 목록
					addListMenu(pro);
					break;
				case MENU_CHOICE.DOWNLIST: // 다운로드 목록
					downListMenu(pro);
					break;
				case MENU_CHOICE.WATCHLIST: // 시청 목록
					watchListMenu(pro);
					break;
				case MENU_CHOICE.ACCOUNT: // 계정 관리
					accountMenu(pro);
					break;
				case MENU_CHOICE.MEMBERSHIP: // 멤버쉽
					membershipMenu(pro);
					break;
				case MENU_CHOICE.USER: // 유저 정보 조회
					userMenu(pro);
					break;
				case MENU_CHOICE.LOGOUT: // 로그아웃
					flag = true;
					break;
				default:
					System.out.println("Incorrect choice, please choose the option again");
					break;
				}
			}
		}
	}
	
	// 검색 메뉴
	public static void searchMenu(ProfileVO pro) throws InterruptedException {
		int choice = 0;
		OttManager ottManager = new OttManager();
		MenuViewer.ottMenuView();
		choice = Integer.parseInt(sc.nextLine());

		switch (choice) {
		case OTT_CHOICE.CHOOSE:
			ottManager.ottList(pro);
			break;
		case OTT_CHOICE.SEARCH:
			ottManager.searchOttList(pro);
			break;
		case OTT_CHOICE.BACK:
			return;
		default:
			System.out.println("Incorrect choice, please choose the option again");
			break;
		}
	}

	// top5 메뉴
	public static void top5Menu(ProfileVO pro) throws InterruptedException {
		int choice = 0;
		int number = 0;
		OttManager ottManager = new OttManager();
		OttManager.top5List();
		MenuViewer.top5MenuView();
		choice = Integer.parseInt(sc.nextLine());

		switch (choice) {
		case TOP5_CHOICE.WATCH:
			number = OttDAO.numberOption();
			OttDAO.watchOtt(number, pro);
			break;
		case TOP5_CHOICE.ADD:
			number = OttDAO.numberOption();
			OttDAO.addOtt(number, pro);
			break;
		case TOP5_CHOICE.DOWN:
			number = OttDAO.numberOption();
			OttDAO.downOtt(number, pro);
			return;
		case TOP5_CHOICE.BACK:
			return;
		default:
			System.out.println("Incorrect choice, please choose the option again");
			break;
		}
	}

	// 카테고리 메뉴
	public static void categoryMenu(ProfileVO pro) throws InterruptedException {
		int choice = 0;
		OttManager ottManager = new OttManager();
		MenuViewer.categoryMenuView();
		choice = Integer.parseInt(sc.nextLine());
		OttManager.categoryList(choice);
		MenuViewer.watchListMenuViewer();
		int number = 0;
		number = Integer.parseInt(sc.nextLine());
		switch (number) {
		case TOP5_CHOICE.WATCH:
			number = OttDAO.numberOption();
			OttDAO.watchOtt(number, pro);
			break;
		case TOP5_CHOICE.ADD:
			number = OttDAO.numberOption();
			OttDAO.addOtt(number, pro);
			break;
		case TOP5_CHOICE.DOWN:
			number = OttDAO.numberOption();
			OttDAO.downOtt(number, pro);
			return;
		case TOP5_CHOICE.BACK:
			return;
		default:
			System.out.println("Incorrect choice, please choose the option again");
			break;
		}
	}

	// 국가별 메뉴
	public static void countryMenu(ProfileVO pro) throws InterruptedException {
		int choice = 0;
		OttManager ottManager = new OttManager();
		MenuViewer.countryMenuView();
		choice = Integer.parseInt(sc.nextLine());
		OttManager.countryList(choice);
		MenuViewer.watchListMenuViewer();
		int number = 0;
		number = Integer.parseInt(sc.nextLine());
		switch (number) {
		case TOP5_CHOICE.WATCH:
			number = OttDAO.numberOption();
			OttDAO.watchOtt(number, pro);
			break;
		case TOP5_CHOICE.ADD:
			number = OttDAO.numberOption();
			OttDAO.addOtt(number, pro);
			break;
		case TOP5_CHOICE.DOWN:
			number = OttDAO.numberOption();
			OttDAO.downOtt(number, pro);
			return;
		case TOP5_CHOICE.BACK:
			return;
		default:
			System.out.println("Incorrect choice, please choose the option again");
			break;
		}
	}

	// 찜 목록 메뉴
	public static void addListMenu(ProfileVO pro) throws InterruptedException {
		int choice = 0;
		int number = 0;
		OttManager ottManager = new OttManager();
		boolean flag = ottManager.addList(pro);
		if (flag == true) {
			System.out.println("My List is empty");
		} else {
			MenuViewer.addListMenuViewer();
			choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case ADDLIST_CHOICE.WATCH:
				number = OttDAO.numberOption();
				OttDAO.watchOtt(number, pro);
				break;
			case ADDLIST_CHOICE.DOWN:
				number = OttDAO.numberOption();
				OttDAO.downOtt(number, pro);
				break;
			case ADDLIST_CHOICE.DELETE:
				number = OttDAO.numberOption();
				OttDAO.deleteOtt(number, pro);
				break;
			case ADDLIST_CHOICE.BACK:
				return;
			default:
				System.out.println("Incorrect choice, please choose the option again");
				break;
			}
		}
	}

	// 다운로드 목록 메뉴
	public static void downListMenu(ProfileVO pro) throws InterruptedException {
		int choice = 0;
		int number = 0;
		OttManager ottManager = new OttManager();
		boolean flag = ottManager.downList(pro);
		if (flag == true) {
			System.out.println("Download list is empty");
		} else {
			MenuViewer.downListMenuViewer();
			choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case DOWNLIST_CHOICE.WATCH:
				number = OttDAO.numberOption();
				OttDAO.watchOtt(number, pro);
				break;
			case DOWNLIST_CHOICE.ADD:
				number = OttDAO.numberOption();
				OttDAO.addOtt(number, pro);
				break;
			case DOWNLIST_CHOICE.DELETE:
				number = OttDAO.numberOption();
				OttDAO.deleteOtt(number, pro);
				break;
			case DOWNLIST_CHOICE.BACK:
				return;
			default:
				System.out.println("Incorrect choice, please choose the option again");
				break;
			}
		}
	}

	// 시청 목록 메뉴
	public static void watchListMenu(ProfileVO pro) throws InterruptedException {
		int choice = 0;
		int number = 0;
		OttManager ottManager = new OttManager();
		boolean flag = ottManager.watchList(pro);
		if (flag == true) {
			System.out.println("You haven't seen any otts yet");
		} else {
			MenuViewer.watchListMenuViewer();
			choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case WATCHLIST_CHOICE.WATCH:
				number = OttDAO.numberOption();
				OttDAO.watchOtt(number, pro);
				break;
			case WATCHLIST_CHOICE.ADD:
				number = OttDAO.numberOption();
				OttDAO.addOtt(number, pro);
				break;
			case WATCHLIST_CHOICE.DOWN:
				number = OttDAO.numberOption();
				OttDAO.downOtt(number, pro);
				break;
			case WATCHLIST_CHOICE.BACK:
				return;
			default:
				System.out.println("Incorrect choice, please choose the option again");
				break;
			}
		}
	}
	
	//유저 정보 조회
	public static void userMenu(ProfileVO pro) {
		UserManager userManager = new UserManager();
		userManager.userPrint(pro);
	}

	// 관리자 로그인
	public static void adminLogin() {
		int choiceNum;
		try {
			DBUtil.loginConnection();
			while (true) {
				MenuViewer.adminMenuView();
				choiceNum = Integer.parseInt(sc.nextLine());
				switch (choiceNum) {
				case ADMINMENU_CHOICE.USER:
					MenuViewer.userManage();
					userManageMenu();
					break;
				case ADMINMENU_CHOICE.OTT:
					MenuViewer.ottManage();
					ottManageMenu();
					break;
				case ADMINMENU_CHOICE.BACK:
					return;
				default:
					System.out.println("Incorrect choice, please choose the option again");
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ott 관리 메뉴
	public static void ottManageMenu() {
		int choiceNum;
		OttManager ottManager = new OttManager();
		choiceNum = Integer.parseInt(sc.nextLine());
		switch (choiceNum) {
		case OTTMANAGE_CHOICE.OTTSEARCH:
			ottManager.ottSearch();
			break;
		case OTTMANAGE_CHOICE.OTTUPDATE:
			ottManager.ottUpdate();
			break;
		case OTTMANAGE_CHOICE.OTTCREATE:
			ottManager.ottCreate();
			break;
		case OTTMANAGE_CHOICE.OTTDELETE:
			ottManager.ottDelete();
			break;
		case OTTMANAGE_CHOICE.BACK:
			break;
		default:
			System.out.println("Incorrect choice, please choose the option again");
			break;
		}
	}

	// 유저 관리 메뉴
	public static void userManageMenu() {
		int choiceNum;
		UserManager userManager = new UserManager();
		choiceNum = Integer.parseInt(sc.nextLine());
		switch (choiceNum) {
		case USERMANAGER_CHOICE.USERSEARCH:
			userManager.userSearch();
			break;
		case USERMANAGER_CHOICE.USERUPDATE:
			userManager.userUpdate();
			break;
		case USERMANAGER_CHOICE.USERDELETE:
			userManager.userDelete();
			break;
		case USERMANAGER_CHOICE.BACK:
			break;
		default:
			System.out.println("Incorrect choice, please choose the option again");
			break;
		}
	}

	// 멤버쉽 메뉴
	public static void membershipMenu(ProfileVO pro) {
		int choice = 0;
		UserManager userManager = new UserManager();
		String membership = userManager.userMembership(pro);
		MenuViewer.membershipMenuViewer();
		choice = Integer.parseInt(sc.nextLine());
		switch (choice) {
		case MEMBERSHIP_CHOICE.BUY:
			userManager.membershipChoose(membership,pro);
			break;
		case MEMBERSHIP_CHOICE.BACK:
			return;
		default:
			System.out.println("Incorrect choice, please choose the option again");
			break;
		}
	}

	// 계정 관리 메뉴
	public static ProfileVO accountMenu(ProfileVO pro) {
		int choice = 0;
		ProfileManager profileManager = new ProfileManager();
		UserManager userManager = new UserManager();
		MenuViewer.accountMenuView();
		choice = Integer.parseInt(sc.nextLine());
		ProfileVO profile = new ProfileVO();
		switch (choice) {
		case ACCOUNT_CHOICE.NAMECHANGE:
			profileManager.nameChange(pro);
			break;
		case ACCOUNT_CHOICE.PASSCHANGE:
			profileManager.passChange(pro);
			break;
		case ACCOUNT_CHOICE.NEWPROFILE:
			profileManager.passCreate(pro);
			break;
		case ACCOUNT_CHOICE.PROFILECHANGE:
			profile = profileManager.proChange(pro);
			break;
		case ACCOUNT_CHOICE.ACCOUNTPASS:
			userManager.accountPass(pro);
			break;
		case ACCOUNT_CHOICE.BACK:
			break;
		default:
			System.out.println("Incorrect choice, please choose the option again");
			break;
		}
		return profile;
	}
}
