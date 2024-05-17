import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import controller.DBUtil;
import controller.OttDAO;
import controller.OttManager;
import view.ADDLIST_CHOICE;
import view.ADMINMENU_CHOICE;
import view.DOWNLIST_CHOICE;
import view.LOGIN_CHOICE;
import view.MENU_CHOICE;
import view.MenuViewer;
import view.OTT_CHOICE;
import view.WATCHLIST_CHOICE;

public class JavflixMain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		mainmenu();
	}

	public static void mainmenu() {
		System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
		System.out.println("");
		System.out.println("");
		System.out.println("                   Welcome to Javflix!");
		System.out.println("");
		System.out.println("");
		System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
		int choiceNum;
		int num;
		boolean flag = false;
		while (true) {
			MenuViewer.loginMenu();
			num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case LOGIN_CHOICE.USER:
				while (!flag) {
					MenuViewer.mainMenuView();
					choiceNum = Integer.parseInt(sc.nextLine());

					switch (choiceNum) {
					case MENU_CHOICE.SEARCH:
						searchMenu();
						break;
					case MENU_CHOICE.TOP5:
						top5Menu();
						break;
					case MENU_CHOICE.CATEGORY:
						categoryMenu();
						break;
					case MENU_CHOICE.COUNTRY:
						countryMenu();
						break;
					case MENU_CHOICE.ADDLIST:
						addListMenu();
						break;
					case MENU_CHOICE.DOWNLIST:
						downListMenu();
						break;
					case MENU_CHOICE.WATCHLIST:
						watchListMenu();
						break;
					case MENU_CHOICE.ACCOUNT:
						break;
					case MENU_CHOICE.QNA:
						break;
					case MENU_CHOICE.LOGOUT:
						flag = true;
						break;
					default:
						System.out.println("잘못 입력했습니다. 다시 입력하세요.");
						break;
					}
				}
				break;
			case LOGIN_CHOICE.ADMIN:
				try {
					DBUtil.loginConnection();
					MenuViewer.adminMenuView();
					choiceNum = Integer.parseInt(sc.nextLine());
					switch (choiceNum) {
					case ADMINMENU_CHOICE.USER:
						OttDAO.userList();
						break;
					case ADMINMENU_CHOICE.OTT:
						top5Menu();
						break;
					case ADMINMENU_CHOICE.BACK:
						return;
					default:
						System.out.println("잘못 입력했습니다. 다시 입력하세요.");
						break;
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case LOGIN_CHOICE.END:
				System.out.println("종료되었습니다.");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요.");
				break;
			}
		}
	}

	public static void watchListMenu() {
		int choice = 0;
		int number = 0;
		OttManager ottManager = new OttManager();
		ottManager.watchList();
		MenuViewer.watchListMenuViewer();
		choice = Integer.parseInt(sc.nextLine());
		switch (choice) {
		case WATCHLIST_CHOICE.WATCH:
			number = OttDAO.numberOption();
			OttDAO.watchOtt(number);
			break;
		case WATCHLIST_CHOICE.ADD:
			number = OttDAO.numberOption();
			OttDAO.addOtt(number);
			break;
		case WATCHLIST_CHOICE.DOWN:
			number = OttDAO.numberOption();
			OttDAO.downOtt(number);
			break;
		case WATCHLIST_CHOICE.BACK:
			return;
		default:
			System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			break;
		}
	}

	public static void downListMenu() {
		int choice = 0;
		int number = 0;
		OttManager ottManager = new OttManager();
		ottManager.downList();
		MenuViewer.downListMenuViewer();
		choice = Integer.parseInt(sc.nextLine());
		switch (choice) {
		case DOWNLIST_CHOICE.WATCH:
			number = OttDAO.numberOption();
			OttDAO.watchOtt(number);
			break;
		case DOWNLIST_CHOICE.ADD:
			number = OttDAO.numberOption();
			OttDAO.addOtt(number);
			break;
		case DOWNLIST_CHOICE.DELETE:
			number = OttDAO.numberOption();
			OttDAO.deleteOtt(number);
			break;
		case DOWNLIST_CHOICE.BACK:
			return;
		default:
			System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			break;
		}
	}

	public static void addListMenu() {
		int choice = 0;
		int number = 0;
		OttManager ottManager = new OttManager();
		ottManager.addList();
		MenuViewer.addListMenuViewer();
		choice = Integer.parseInt(sc.nextLine());
		switch (choice) {
		case ADDLIST_CHOICE.WATCH:
			number = OttDAO.numberOption();
			OttDAO.watchOtt(number);
			break;
		case ADDLIST_CHOICE.DOWN:
			number = OttDAO.numberOption();
			OttDAO.downOtt(number);
			break;
		case ADDLIST_CHOICE.DELETE:
			number = OttDAO.numberOption();
			OttDAO.deleteOtt(number);
			break;
		case ADDLIST_CHOICE.BACK:
			return;
		default:
			System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			break;
		}
	}

	public static void top5Menu() {

	}

	public static void categoryMenu() {
		int choice = 0;
		OttManager ottManager = new OttManager();
		MenuViewer.categoryMenuView();
		choice = Integer.parseInt(sc.nextLine());

		switch (choice) {
		case OTT_CHOICE.CHOOSE:
			break;
		case OTT_CHOICE.SEARCH:
			break;
		case OTT_CHOICE.BACK:
			return;
		default:
			System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			break;
		}
	}

	public static void countryMenu() {
		int choice = 0;
		OttManager ottManager = new OttManager();
		MenuViewer.countryMenuView();
		choice = Integer.parseInt(sc.nextLine());

		switch (choice) {
		case OTT_CHOICE.CHOOSE:
			break;
		case OTT_CHOICE.SEARCH:
			break;
		case OTT_CHOICE.BACK:
			return;
		default:
			System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			break;
		}
	}

	public static void searchMenu() {
		int choice = 0;
		OttManager ottManager = new OttManager();
		MenuViewer.ottMenuView();
		choice = Integer.parseInt(sc.nextLine());

		switch (choice) {
		case OTT_CHOICE.CHOOSE:
			ottManager.ottList();
			break;
		case OTT_CHOICE.SEARCH:
			ottManager.searchOttList();
			break;
		case OTT_CHOICE.BACK:
			return;
		default:
			System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			break;
		}
	}
}
