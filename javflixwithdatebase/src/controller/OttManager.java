package controller;

import java.util.ArrayList;

import model.OttVO;

public class OttManager {
	public void ottList() {
		OttDAO od = new OttDAO();
		od.getOttTotalList();
		od.optionChoose();
	}
	public void searchOttList() {
		OttDAO od = new OttDAO();
		od.searchOttList();
		od.optionChoose();
	}
	public void addList() {
		OttDAO od = new OttDAO();
		od.ottAddList();
	}
	public void downList() {
		OttDAO od = new OttDAO();
		od.ottDownList();
	}
	public void watchList() {
		OttDAO od = new OttDAO();
		od.ottWatchList();
	}
}
