package kh.edu.javflix.cart;

import java.util.ArrayList;
import java.util.List;

import kh.edu.javflix.ott.Ott;

public interface CartInterFace {
	void printViewer(List<Ott> ottStream);
	void printOttList(ArrayList<Ott>ottList);
	void printOttStream(List<Ott> ottStream);
}
