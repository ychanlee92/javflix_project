package controller;

import java.util.Scanner;

import model.OttVO;
import model.ProfileVO;

public class OttManager {
	public static Scanner sc = new Scanner(System.in);

	public void ottList(ProfileVO pro) throws InterruptedException {
		OttDAO od = new OttDAO();
		od.getOttTotalList();
		od.optionChoose(pro);
	}

	public void searchOttList(ProfileVO pro) throws InterruptedException {
		OttDAO od = new OttDAO();
		od.searchOttList();
		od.optionChoose(pro);
	}

	public boolean addList(ProfileVO pro) {
		OttDAO od = new OttDAO();
		boolean flag = od.ottAddList(pro);
		return flag;
	}

	public boolean downList(ProfileVO pro) {
		OttDAO od = new OttDAO();
		boolean flag = od.ottDownList(pro);
		return flag;
	}

	public boolean watchList(ProfileVO pro) {
		OttDAO od = new OttDAO();
		boolean flag = od.ottWatchList(pro);
		return flag;
	}

	public static void top5List() {
		OttDAO od = new OttDAO();
		od.top5List();
	}

	public static void categoryList(int choice) {
		OttDAO od = new OttDAO();
		od.categoryList(choice);
	}

	public static void countryList(int choice) {
		OttDAO od = new OttDAO();
		od.countryList(choice);
	}

	public void ottSearch() {
		OttDAO od = new OttDAO();
		od.getOttList();
	}

	public void ottUpdate() {
		OttDAO od = new OttDAO();
		od.getOttList();
		System.out.print("수정할 ott 번호를 입력하세요: ");
		int num = Integer.parseInt(sc.nextLine());
		System.out.print("수정할 ott 제목을 입력하세요: ");
		String ott_title = sc.nextLine();
		System.out.print("수정할 ott 국가를 입력하세요: ");
		String ott_country = sc.nextLine();
		System.out.print("수정할 ott 줄거리를 입력하세요: ");
		String ott_story = sc.nextLine();
		System.out.print("수정할 ott 장르를 입력하세요: ");
		String ott_genre = sc.nextLine();
		System.out.print("수정할 ott 배우를 입력하세요: ");
		String ott_actor = sc.nextLine();
		System.out.print("수정할 ott 감독을 입력하세요: ");
		String ott_director = sc.nextLine();
		System.out.print("수정할 ott 연도를 입력하세요: ");
		String ott_year = sc.nextLine();
		System.out.print("수정할 ott 평점을 입력하세요: ");
		Double ott_rate = Double.parseDouble(sc.nextLine());
		System.out.print("수정할 ott 나이제한을 입력하세요: ");
		String ott_age = sc.nextLine();
		OttVO ott = new OttVO(ott_title, ott_country, ott_story, ott_genre, ott_actor, ott_director, ott_year, ott_rate,
				ott_age);
		od.updateOtt(ott,num);
	}

	public void ottCreate() {
		OttDAO od = new OttDAO();
		System.out.print("새로운 ott 제목을 입력하세요: ");
		String ott_title = sc.nextLine();
		System.out.print("새로운 ott 국가를 입력하세요: ");
		String ott_country = sc.nextLine();
		System.out.print("새로운 ott 줄거리를 입력하세요: ");
		String ott_story = sc.nextLine();
		System.out.print("새로운 ott 장르를 입력하세요: ");
		String ott_genre = sc.nextLine();
		System.out.print("새로운 ott 배우를 입력하세요: ");
		String ott_actor = sc.nextLine();
		System.out.print("새로운 ott 감독을 입력하세요: ");
		String ott_director = sc.nextLine();
		System.out.print("새로운 ott 연도를 입력하세요: ");
		String ott_year = sc.nextLine();
		System.out.print("새로운 ott 평점을 입력하세요: ");
		Double ott_rate = Double.parseDouble(sc.nextLine());
		System.out.print("새로운 ott 나이제한을 입력하세요: ");
		String ott_age = sc.nextLine();
		OttVO ott = new OttVO(ott_title, ott_country, ott_story, ott_genre, ott_actor, ott_director, ott_year, ott_rate,
				ott_age);
		od.createOtt(ott);
	}

	public void ottDelete() {
		OttDAO od = new OttDAO();
		od.ottDelete();
	}
}
