package kh.edu.javflix.cart;

import java.util.ArrayList;
import java.util.List;

import kh.edu.javflix.ott.Ott;

public class Cart implements CartInterFace {
	
	public Cart() {
		super();
	}

	@Override
	public void printOttList(ArrayList<Ott> ottList) {
		for (int i = 0; i < ottList.size(); i++) {
			Ott ott = ottList.get(i);
			System.out.print(ott.getNumber() + " | ");
			System.out.print(ott.getTitle() + " | ");
			System.out.print(ott.getCountry() + " | ");
			System.out.print(ott.getStory() + " | ");
			System.out.print(ott.getGenre() + " | ");
			System.out.print(ott.getActor() + " | ");
			System.out.print(ott.getDirector() + " | ");
			System.out.print(ott.getYear() + " | ");
			System.out.print(ott.getRate() + " | ");
			System.out.print(ott.getAge() + " | ");
			System.out.println("");
		}
	}

	@Override
	public void printOttStream(List<Ott> ottStream) {
		for (int i = 0; i < ottStream.size(); i++) {
			Ott ott = ottStream.get(i);
			System.out.print(ott.getNumber() + " | ");
			System.out.print(ott.getTitle() + " | ");
			System.out.print(ott.getCountry() + " | ");
			System.out.print(ott.getStory() + " | ");
			System.out.print(ott.getGenre() + " | ");
			System.out.print(ott.getActor() + " | ");
			System.out.print(ott.getDirector() + " | ");
			System.out.print(ott.getYear() + " | ");
			System.out.print(ott.getRate() + " | ");
			System.out.print(ott.getAge() + " | ");
			System.out.println("");
		}
	}

	@Override
	public void printViewer(List<Ott> ottStream) {
		for (int i = 0; i < ottStream.size(); i++) {
			Ott ott = ottStream.get(i);
			System.out.print(ott.getNumber() + " | ");
			System.out.print(ott.getTitle() + " | ");
			
			System.out.print(ott.getViewer() + " | ");
			System.out.println("");
		}
	}

}
