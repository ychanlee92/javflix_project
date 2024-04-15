package kh.edu.javflix.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kh.edu.javflix.ott.Ott;

public class Cart implements CartInterFace {

	public Cart() {
		super();
	}

	// 아이디 출력 조건: 최대 5자리, 왼쪽 정렬 System.out.print("|아이디:"); System.out.printf("%-7s",
	// food.getId()); // 카테고리 출력 조건: 2자리, 왼쪽 정렬 System.out.print("\t카테고리:");
	// System.out.printf("%-2s", food.getCategory());
	// System.out.println("\t"+"|");// 줄바꿈 // 음식 이름 출력 조건: 최대 7자리, 왼쪽 정렬
	// System.out.print("|메뉴:"); System.out.printf("%-7s", food.getName()); // 가격 출력
	// 조건: 최대 5자리, 오른쪽 정렬 System.out.print("\t"+"가격:"); System.out.printf("%-5d",
	// food.getPrice()); System.out.println("\t"+"|");// 줄바꿈 // "코멘트:"
	// System.out.print("|"); System.out.printf("%-16s","코멘트:");
	// System.out.println("\t\t"+"|");// 줄바꿈 System.out.print("|");
	// System.out.printf("%-29s", food.getComment());

	@Override
	public void printOttList(ArrayList<Ott> ottList) {
		for (int i = 0; i < 9; i++) {
			Ott ott = ottList.get(i);
			System.out.printf("%1d %2s", ott.getNumber(), "|");
			if (ott.getTitle().length() < 10) {
				System.out.printf("%1s %1s", ott.getTitle(), "\t\t|");
			} else {
				System.out.printf("%1s %1s", ott.getTitle().substring(0, 10), "\t|");
			}
			System.out.print(" " + ott.getCountry() + "\t|\t");
			System.out.print(" " + ott.getStory() + "\t|\t");
			System.out.print(" " + ott.getGenre() + "\t|\t");
			System.out.print(" " + ott.getActor() + "\t|\t");
			System.out.print(" " + ott.getDirector() + "\t|\t");
			System.out.print(" " + ott.getYear() + "\t|\t");
			System.out.print(" " + ott.getRate() + "\t|\t");
			System.out.print(" " + ott.getAge() + "\t|\t");
			System.out.println("");
		}
		for (int i = 9; i < ottList.size(); i++) {
			Ott ott = ottList.get(i);
			System.out.printf("%1d %1s", ott.getNumber(), "|");
			if (ott.getTitle().length() < 10) {
				System.out.printf("%1s %1s", ott.getTitle(), "\t\t|");
			} else {
				System.out.printf("%1s %1s", ott.getTitle().substring(0, 10), "\t|");
			}
			System.out.print(" " + ott.getCountry() + "\t|\t");
			System.out.print(" " + ott.getStory() + "\t|\t");
			System.out.print(" " + ott.getGenre() + "\t|\t");
			System.out.print(" " + ott.getActor() + "\t|\t");
			System.out.print(" " + ott.getDirector() + "\t|\t");
			System.out.print(" " + ott.getYear() + "\t|\t");
			System.out.print(" " + ott.getRate() + "\t|\t");
			System.out.print(" " + ott.getAge() + "\t|\t");
			System.out.println("");
		}
	}

	@Override
	public void printOttStream(List<Ott> ottStream) {
		for (int i = 0; i < ottStream.size(); i++) {
			Ott ott = ottStream.get(i);
			System.out.printf("%1d %2s", ott.getNumber(), "|");
			System.out.print("\t" + ott.getTitle() + "\t|\t");
			System.out.print("\t" + ott.getCountry() + "\t|\t");
			System.out.print("\t" + ott.getStory() + "\t|\t");
			System.out.print("\t" + ott.getGenre() + "\t|\t");
			System.out.print("\t" + ott.getActor() + "\t|\t");
			System.out.print("\t" + ott.getDirector() + "\t|\t");
			System.out.print("\t" + ott.getYear() + "\t|\t");
			System.out.print("\t" + ott.getRate() + "\t|\t");
			System.out.print("\t" + ott.getAge() + "\t|\t");
			System.out.println("");
		}
	}

	@Override
	public void printViewer(List<Ott> ottStream) {
		for (int i = 0; i < ottStream.size(); i++) {
			Ott ott = ottStream.get(i);
			System.out.print(ott.getNumber() + " |\t");
			System.out.print(ott.getTitle() + "\t|\t");
			System.out.print(ott.getViewer() + "\t|\t");
			System.out.println("");
		}
	}

}
