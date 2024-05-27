package controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;

import model.CartVO;
import model.OttVO;
import model.ProfileVO;
import view.MenuViewer;
import view.OPTION_CHOICE;

public class OttDAO {
	public static Scanner sc = new Scanner(System.in);
	static ProfileVO profile = new ProfileVO();
	static CartVO cart = new CartVO();

	public void getOttTotalList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OttVO> ottList = new ArrayList();
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from jav_ott order by ott_num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int ott_num = rs.getInt("ott_num");
				String ott_title = rs.getString("ott_title");
				String ott_country = rs.getString("ott_country");
				String ott_story = rs.getString("ott_story");
				String ott_genre = rs.getString("ott_genre");
				String ott_actor = rs.getString("ott_actor");
				String ott_director = rs.getString("ott_director");
				String ott_year = rs.getString("ott_year");
				Double ott_rate = rs.getDouble("ott_rate");
				String ott_age = rs.getString("ott_age");
				int ott_view = rs.getInt("ott_view");
				OttVO ott = new OttVO(ott_num, ott_title, ott_country, ott_story, ott_genre, ott_actor, ott_director,
						ott_year, ott_rate, ott_age, ott_view);
				ottList.add(ott);
			}
			printOttList(ottList);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public void printOttList(ArrayList<OttVO> ottList) {
		for (int i = 0; i < ottList.size(); i++) {
			OttVO ott = ottList.get(i);
			System.out.printf("%-3d %1s", ott.getOtt_num(), "|");
			if (ott.getOtt_title().length() < 15) {
				System.out.printf("%-22s %1s", ott.getOtt_title(), "\t|");
			} else {
				System.out.printf("%-17s %1s", ott.getOtt_title().substring(0, 15) + "..", "\t|");
			}
			System.out.print(ott.getOtt_country() + "\t|");
			if (ott.getOtt_story().length() > 25) {
				System.out.printf("%-25s %1s", ott.getOtt_story().substring(0, 23) + "..", "\t|");
			} else {
				System.out.printf("%-25s %1s", ott.getOtt_story(), "\t\t|");
			}
			System.out.print(ott.getOtt_genre() + "\t|");
			if (ott.getOtt_actor().length() < 10) {
				System.out.printf("%-10s %1s", ott.getOtt_actor(), "\t|");
			} else {
				System.out.printf("%-10s %1s", ott.getOtt_actor().substring(0, 8) + "..", "\t|");
			}
			if (ott.getOtt_director().length() > 5) {
				System.out.print(ott.getOtt_director() + "\t|");
			} else {
				System.out.print(ott.getOtt_director() + "\t\t|");
			}
			System.out.print(ott.getOtt_year() + "\t|");
			System.out.print(ott.getOtt_rate() + "\t|");
			System.out.print(ott.getOtt_age() + "|");
			System.out.println("");
		}
	}

	public void searchOttList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OttVO> ottList = new ArrayList();
		try {
			System.out.print("검색할 키워드를 입력하세요: ");
			String keyWord = sc.nextLine();
			con = DBUtil.makeConnection();
			String sql = "select * from jav_ott where ott_title = ? or ott_country = ? or ott_story = ? or ott_genre = ? or ott_actor = ? or ott_director = ? or ott_age = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyWord);
			pstmt.setString(2, keyWord);
			pstmt.setString(3, keyWord);
			pstmt.setString(4, keyWord);
			pstmt.setString(5, keyWord);
			pstmt.setString(6, keyWord);
			pstmt.setString(7, keyWord);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int ott_num = rs.getInt("ott_num");
				String ott_title = rs.getString("ott_title");
				String ott_country = rs.getString("ott_country");
				String ott_story = rs.getString("ott_story");
				String ott_genre = rs.getString("ott_genre");
				String ott_actor = rs.getString("ott_actor");
				String ott_director = rs.getString("ott_director");
				String ott_year = rs.getString("ott_year");
				Double ott_rate = rs.getDouble("ott_rate");
				String ott_age = rs.getString("ott_age");
				int ott_view = rs.getInt("ott_view");
				OttVO ott = new OttVO(ott_num, ott_title, ott_country, ott_story, ott_genre, ott_actor, ott_director,
						ott_year, ott_rate, ott_age, ott_view);
				ottList.add(ott);
			}
			printOttList(ottList);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public void optionChoose(ProfileVO pro) throws InterruptedException {
		System.out.print("Ott번호를 선택하세요: ");
		int number = Integer.parseInt(sc.nextLine());
		int choice = 0;
		MenuViewer.optionMenuView();
		choice = Integer.parseInt(sc.nextLine());
		switch (choice) {
		case OPTION_CHOICE.WATCH:
			watchOtt(number, pro);
			break;
		case OPTION_CHOICE.ADD:
			addOtt(number, pro);
			break;
		case OPTION_CHOICE.DOWN:
			downOtt(number, pro);
			break;
		case OPTION_CHOICE.BACK:
			return;
		default:
			System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			break;
		}
	}

	public static int numberOption() {
		System.out.print("Ott번호를 선택하세요: ");
		int number = Integer.parseInt(sc.nextLine());
		return number;
	}

	public static void downOtt(int number, ProfileVO pro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OttVO ott = new OttVO();
		ArrayList<CartVO> cartList = new ArrayList();
		CallableStatement cstmt = null;
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from jav_ott order by ott_num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int ott_num = rs.getInt("ott_num");
				String ott_title = rs.getString("ott_title");
				String ott_country = rs.getString("ott_country");
				String ott_story = rs.getString("ott_story");
				String ott_genre = rs.getString("ott_genre");
				String ott_actor = rs.getString("ott_actor");
				String ott_director = rs.getString("ott_director");
				String ott_year = rs.getString("ott_year");
				Double ott_rate = rs.getDouble("ott_rate");
				String ott_age = rs.getString("ott_age");
				int ott_view = rs.getInt("ott_view");
				ott = new OttVO(ott_num, ott_title, ott_country, ott_story, ott_genre, ott_actor, ott_director,
						ott_year, ott_rate, ott_age, ott_view);
			}
			String sql2 = "select * from jav_cart where profile_name = ? order by cart_num";
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, pro.getProfile_name());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cart_num = rs.getInt("cart_num");
				String profile_name = rs.getString("profile_name");
				int ott_num = rs.getInt("ott_num");
				String cart_seen = rs.getString("cart_seen");
				String cart_down = rs.getString("cart_down");
				String cart_add = rs.getString("cart_add");
				cart = new CartVO(cart_num, profile_name, ott_num, cart_seen, cart_down, cart_add);
				cartList.add(cart);
			}
			String sql3 = "select u.user_membership from jav_profile p inner join jav_user u on p.user_id = u.user_id where p.user_id = ?";
			pstmt = con.prepareStatement(sql3);
			pstmt.setString(1, pro.getUser_id());
			rs = pstmt.executeQuery();
			String user_membership = null;
			while (rs.next()) {
				user_membership = rs.getString("user_membership");
			}
			if (!user_membership.equals("premium")) {
				System.out.println("오프라인 저장 기능은 premium등급부터 이용가능합니다. ");
			} else {
				if (cartList.stream().anyMatch(s -> s.getProfile_name().equals(pro.getProfile_name())
						&& s.getOtt_num() == number && s.getCart_down().equals("다운완료"))) {
					System.out.println("이미 다운받은 ott입니다.");
				} else if (cartList.stream()
						.anyMatch(s -> s.getProfile_name().equals(pro.getProfile_name()) && s.getOtt_num() == number)) {
					cstmt = con.prepareCall("{CALL downott(?,?,?,?)}");
					cstmt.setString(1, pro.getProfile_name());
					cstmt.setInt(2, number);
					cstmt.setString(3, "다운완료");
					cstmt.registerOutParameter(4, Types.VARCHAR);
					cstmt.executeUpdate();
					String message = cstmt.getString(4);
					System.out.println(message);
				} else {
					cstmt = con.prepareCall("{CALL addott2(?,?,?,?,?,?)}");
					cstmt.setString(1, pro.getProfile_name());
					cstmt.setInt(2, number);
					cstmt.setString(3, "미시청");
					cstmt.setString(4, "다운완료");
					cstmt.setString(5, "미추가");
					cstmt.registerOutParameter(6, Types.VARCHAR);
					cstmt.executeUpdate();
					String message = cstmt.getString(6);
					System.out.println(message);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public static void addOtt(int number, ProfileVO pro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OttVO ott = new OttVO();
		ArrayList<CartVO> cartList = new ArrayList();
		CallableStatement cstmt = null;
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from jav_ott order by ott_num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int ott_num = rs.getInt("ott_num");
				String ott_title = rs.getString("ott_title");
				String ott_country = rs.getString("ott_country");
				String ott_story = rs.getString("ott_story");
				String ott_genre = rs.getString("ott_genre");
				String ott_actor = rs.getString("ott_actor");
				String ott_director = rs.getString("ott_director");
				String ott_year = rs.getString("ott_year");
				Double ott_rate = rs.getDouble("ott_rate");
				String ott_age = rs.getString("ott_age");
				int ott_view = rs.getInt("ott_view");
				ott = new OttVO(ott_num, ott_title, ott_country, ott_story, ott_genre, ott_actor, ott_director,
						ott_year, ott_rate, ott_age, ott_view);
			}
			String sql2 = "select * from jav_cart where profile_name = ? order by cart_num";
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, pro.getProfile_name());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cart_num = rs.getInt("cart_num");
				String profile_name = rs.getString("profile_name");
				int ott_num = rs.getInt("ott_num");
				String cart_seen = rs.getString("cart_seen");
				String cart_down = rs.getString("cart_down");
				String cart_add = rs.getString("cart_add");
				cart = new CartVO(cart_num, profile_name, ott_num, cart_seen, cart_down, cart_add);
				cartList.add(cart);
			}
			if (cartList.stream().anyMatch(s -> s.getProfile_name().equals(pro.getProfile_name())
					&& s.getOtt_num() == number && s.getCart_add().equals("찜완료"))) {
				System.out.println("이미 찜한 ott입니다.");
			} else if (cartList.stream()
					.anyMatch(s -> s.getProfile_name().equals(pro.getProfile_name()) && s.getOtt_num() == number)) {
				cstmt = con.prepareCall("{CALL addott(?,?,?,?)}");
				cstmt.setString(1, pro.getProfile_name());
				cstmt.setInt(2, number);
				cstmt.setString(3, "찜완료");
				cstmt.registerOutParameter(4, Types.VARCHAR);
				cstmt.executeUpdate();
				String message = cstmt.getString(4);
				System.out.println(message);
			} else {
				cstmt = con.prepareCall("{CALL addott2(?,?,?,?,?,?)}");
				cstmt.setString(1, pro.getProfile_name());
				cstmt.setInt(2, number);
				cstmt.setString(3, "미시청");
				cstmt.setString(4, "미다운로드");
				cstmt.setString(5, "찜완료");
				cstmt.registerOutParameter(6, Types.VARCHAR);
				cstmt.executeUpdate();
				String message = cstmt.getString(6);
				System.out.println(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public static void watchOtt(int number, ProfileVO pro) throws InterruptedException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		OttVO ott = new OttVO();
		ArrayList<CartVO> cartList = new ArrayList();
		try {
			con = DBUtil.makeConnection();
			String sql = "select u.user_membership from jav_profile p inner join jav_user u on p.user_id = u.user_id where p.user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pro.getUser_id());
			rs = pstmt.executeQuery();
			String user_membership = null;
			while (rs.next()) {
				user_membership = rs.getString("user_membership");
			}
			if (user_membership.equals("silver")) {
				System.out.println("[AD]취업률 100% 신화!");
				Thread.sleep(800);
				System.out.println("명품 강사진이 먼저일까? 뛰어난 학생들이 먼저일까?");
				Thread.sleep(800);
				System.out.println("당신의 100점 인생의 출발, KH352에서 시작하세요!");
				Thread.sleep(800);
				System.out.print("곧 광고가 종료됩니다..");
				Thread.sleep(300);
				System.out.print("5..");
				Thread.sleep(300);
				System.out.print("4..");
				Thread.sleep(300);
				System.out.print("3..");
				Thread.sleep(300);
				System.out.print("2..");
				Thread.sleep(300);
				System.out.print("1..\n");
			}
			String sql2 = "select ott_story from jav_ott where ott_num = ?";
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String ott_story = rs.getString("ott_story");
				System.out.println(ott_story);
			}
			String sql3 = "select * from jav_cart where profile_name = ? order by cart_num";
			pstmt = con.prepareStatement(sql3);
			pstmt.setString(1, pro.getProfile_name());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cart_num = rs.getInt("cart_num");
				String profile_name = rs.getString("profile_name");
				int ott_num = rs.getInt("ott_num");
				String cart_seen = rs.getString("cart_seen");
				String cart_down = rs.getString("cart_down");
				String cart_add = rs.getString("cart_add");
				cart = new CartVO(cart_num, profile_name, ott_num, cart_seen, cart_down, cart_add);
				cartList.add(cart);
			}
			if (cartList.stream().anyMatch(s -> s.getProfile_name().equals(pro.getProfile_name())
					&& s.getOtt_num() == number && s.getCart_seen().equals("시청함"))) {
				System.out.println(number + "번 ott 시청완료!");
			} else if (cartList.stream().anyMatch(s -> s.getProfile_name().equals(pro.getProfile_name())
					&& s.getOtt_num() == number && !cart.getCart_seen().equals("시청함"))) {
				cstmt = con.prepareCall("{CALL watchott(?,?,?,?)}");
				cstmt.setString(1, pro.getProfile_name());
				cstmt.setInt(2, number);
				cstmt.setString(3, "시청함");
				cstmt.registerOutParameter(4, Types.VARCHAR);
				cstmt.executeUpdate();
				String message = cstmt.getString(4);
				System.out.println(message);
			} else {
				cstmt = con.prepareCall("{CALL watchott2(?,?,?,?,?,?)}");
				cstmt.setString(1, pro.getProfile_name());
				cstmt.setInt(2, number);
				cstmt.setString(3, "시청함");
				cstmt.setString(4, "미다운로드");
				cstmt.setString(5, "미추가");
				cstmt.registerOutParameter(6, Types.VARCHAR);
				cstmt.executeUpdate();
				String message = cstmt.getString(6);
				System.out.println(message);
			}
			cstmt = con.prepareCall("{CALL ottview(?)}");
			cstmt.setInt(1, number);
			cstmt.executeUpdate();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public boolean ottAddList(ProfileVO pro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartVO> cartList = new ArrayList();
		OttVO ott = new OttVO();
		ArrayList<OttVO> ottList = new ArrayList();
		boolean flag = false;
		try {
			con = DBUtil.makeConnection();
			String sql = "select count(*) from jav_cart where cart_add = ? and profile_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "찜완료");
			pstmt.setString(2, pro.getProfile_name());
			rs = pstmt.executeQuery();
			int count = -1;
			while (rs.next()) {
				count = rs.getInt("count(*)");
			}
			if (count == 0) {
				flag = true;
			} else {
				String sql1 = "select cart_num, profile_name, c.ott_num, ott_title, ott_story, ott_genre,ott_rate,cart_seen, cart_down, cart_add from jav_cart c inner join jav_ott o on c.ott_num = o.ott_num where cart_add = ? and profile_name = ? order by ott_num";
				pstmt = con.prepareStatement(sql1);
				pstmt.setString(1, "찜완료");
				pstmt.setString(2, pro.getProfile_name());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int cart_num = rs.getInt("cart_num");
					String profile_name = rs.getString("profile_name");
					int ott_num = rs.getInt("ott_num");
					String ott_title = rs.getString("ott_title");
					String ott_story = rs.getString("ott_story");
					String ott_genre = rs.getString("ott_genre");
					Double ott_rate = rs.getDouble("ott_rate");
					String cart_seen = rs.getString("cart_seen");
					String cart_down = rs.getString("cart_down");
					String cart_add = rs.getString("cart_add");

					cart = new CartVO(cart_num, profile_name, ott_num, cart_seen, cart_down, cart_add);
					cartList.add(cart);
					ott = new OttVO(ott_title, ott_story, ott_genre, ott_rate);
					ottList.add(ott);
				}
				printAddList(cartList, ottList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
		return flag;
	}

	public static void printAddList(ArrayList<CartVO> cartList, ArrayList<OttVO> ottList) {
		for (int i = 0; i < cartList.size(); i++) {
			cart = cartList.get(i);
			OttVO ott = ottList.get(i);
			System.out.print(cart.getProfile_name() + "\t|");
			System.out.print(cart.getOtt_num() + "\t|");
			if (ott.getOtt_title().length() < 18) {
				System.out.printf("%-20s %1s", ott.getOtt_title(), "\t|");
			} else {
				System.out.printf("%-18s %1s", ott.getOtt_title().substring(0, 15) + "..", "\t|");
			}
			if (ott.getOtt_story().length() >= 25) {
				System.out.printf("%-25s %1s", ott.getOtt_story().substring(0, 23) + "..", "\t|");
			} else {
				System.out.printf("%-25s %1s", ott.getOtt_story(), "\t\t|");
			}
			System.out.print(ott.getOtt_genre() + "\t|");
			System.out.print(ott.getOtt_rate() + "\t|");
			System.out.print(cart.getCart_seen() + "\t|");
			System.out.printf("%-8s %1s", cart.getCart_down(), "\t|");
			System.out.print(cart.getCart_add() + "|");
			System.out.println("");
		}
	}

	public static void deleteOtt(int number, ProfileVO pro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		OttVO ott = new OttVO();
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL ottdelete(?,?)}");
			cstmt.setInt(1, number);
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.executeUpdate();
			String message = cstmt.getString(2);
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(con);
		}

	}

	public boolean ottDownList(ProfileVO pro) {
		CartVO cart = new CartVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartVO> cartList = new ArrayList();
		OttVO ott = new OttVO();
		ArrayList<OttVO> ottList = new ArrayList();
		boolean flag = false;
		try {
			con = DBUtil.makeConnection();
			String sql = "select count(*) from jav_cart where cart_down = ? and profile_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "다운완료");
			pstmt.setString(2, pro.getProfile_name());
			rs = pstmt.executeQuery();
			int count = -1;
			while (rs.next()) {
				count = rs.getInt("count(*)");
			}
			if (count == 0) {
				flag = true;
			} else {
				String sql1 = "select cart_num, profile_name, c.ott_num, ott_title, ott_story, ott_genre,ott_rate,cart_seen, cart_down, cart_add from jav_cart c inner join jav_ott o on c.ott_num = o.ott_num where cart_down = ? and profile_name = ? order by ott_num";
				pstmt = con.prepareStatement(sql1);
				pstmt.setString(1, "다운완료");
				pstmt.setString(2, pro.getProfile_name());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int cart_num = rs.getInt("cart_num");
					String profile_name = rs.getString("profile_name");
					int ott_num = rs.getInt("ott_num");
					String ott_title = rs.getString("ott_title");
					String ott_story = rs.getString("ott_story");
					String ott_genre = rs.getString("ott_genre");
					Double ott_rate = rs.getDouble("ott_rate");
					String cart_seen = rs.getString("cart_seen");
					String cart_down = rs.getString("cart_down");
					String cart_add = rs.getString("cart_add");

					cart = new CartVO(cart_num, profile_name, ott_num, cart_seen, cart_down, cart_add);
					cartList.add(cart);
					ott = new OttVO(ott_title, ott_story, ott_genre, ott_rate);
					ottList.add(ott);
				}
				printAddList(cartList, ottList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
		return flag;
	}

	public boolean ottWatchList(ProfileVO pro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartVO> cartList = new ArrayList();
		OttVO ott = new OttVO();
		ArrayList<OttVO> ottList = new ArrayList();
		boolean flag = false;
		try {
			con = DBUtil.makeConnection();
			String sql = "select count(*) from jav_cart where cart_seen = ? and profile_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "시청함");
			pstmt.setString(2, pro.getProfile_name());
			rs = pstmt.executeQuery();
			int count = -1;
			while (rs.next()) {
				count = rs.getInt("count(*)");
			}
			if (count == 0) {
				flag = true;
			} else {
				String sql1 = "select cart_num, profile_name, c.ott_num, ott_title, ott_story, ott_genre,ott_rate,cart_seen, cart_down, cart_add from jav_cart c inner join jav_ott o on c.ott_num = o.ott_num where cart_seen = ? and profile_name = ? order by ott_num";
				pstmt = con.prepareStatement(sql1);
				pstmt.setString(1, "시청함");
				pstmt.setString(2, pro.getProfile_name());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int cart_num = rs.getInt("cart_num");
					String profile_name = rs.getString("profile_name");
					int ott_num = rs.getInt("ott_num");
					String ott_title = rs.getString("ott_title");
					String ott_story = rs.getString("ott_story");
					String ott_genre = rs.getString("ott_genre");
					Double ott_rate = rs.getDouble("ott_rate");
					String cart_seen = rs.getString("cart_seen");
					String cart_down = rs.getString("cart_down");
					String cart_add = rs.getString("cart_add");

					cart = new CartVO(cart_num, profile_name, ott_num, cart_seen, cart_down, cart_add);
					cartList.add(cart);
					ott = new OttVO(ott_title, ott_story, ott_genre, ott_rate);
					ottList.add(ott);
				}
				printAddList(cartList, ottList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
		return flag;
	}

	public static void userList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartVO> cartList = new ArrayList();
		try {
			con = DBUtil.makeConnection();
			String sql = "select A.admin_num, U.user_id,U.user_name,U.user_phone,U.user from jav_admin A inner join jav_user U";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cart_num = rs.getInt("cart_num");
				String profile_name = rs.getString("profile_name");
				int ott_num = rs.getInt("ott_num");
				String cart_seen = rs.getString("cart_seen");
				String cart_down = rs.getString("cart_down");
				String cart_add = rs.getString("cart_add");
				cart = new CartVO(cart_num, profile_name, ott_num, cart_seen, cart_down, cart_add);
				cartList.add(cart);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public void top5List() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OttVO> ottList = new ArrayList();
		int rownum = 0;
		try {
			con = DBUtil.makeConnection();
			String sql = "select rownum, ott_num, ott_title, ott_country, ott_story, ott_genre, ott_actor, ott_director, ott_year, ott_rate, ott_age, ott_view from (select ott_num, ott_title, ott_country, ott_story, ott_genre, ott_actor, ott_director, ott_year, ott_rate, ott_age, ott_view from jav_ott order by ott_view desc) where rownum <=5";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rownum = rs.getInt("rownum");
				int ott_num = rs.getInt("ott_num");
				String ott_title = rs.getString("ott_title");
				String ott_country = rs.getString("ott_country");
				String ott_story = rs.getString("ott_story");
				String ott_genre = rs.getString("ott_genre");
				String ott_actor = rs.getString("ott_actor");
				String ott_director = rs.getString("ott_director");
				String ott_year = rs.getString("ott_year");
				Double ott_rate = rs.getDouble("ott_rate");
				String ott_age = rs.getString("ott_age");
				int ott_view = rs.getInt("ott_view");
				OttVO ott = new OttVO(ott_num, ott_title, ott_country, ott_story, ott_genre, ott_actor, ott_director,
						ott_year, ott_rate, ott_age, ott_view);
				ottList.add(ott);
			}
			printTop5OttList(ottList);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public void printTop5OttList(ArrayList<OttVO> ottList) {
		for (int i = 0; i < ottList.size(); i++) {
			OttVO ott = ottList.get(i);
			System.out.printf("%-3d %1s", i + 1, "|");
			System.out.printf("%-3d %1s", ott.getOtt_num(), "|");
			if (ott.getOtt_title().length() < 15) {
				System.out.printf("%-22s %1s", ott.getOtt_title(), "\t|");
			} else {
				System.out.printf("%-18s %1s", ott.getOtt_title().substring(0, 16) + "..", "\t|");
			}
			System.out.print(ott.getOtt_country() + "\t|");
			if (ott.getOtt_story().length() >= 25) {
				System.out.printf("%-25s %1s", ott.getOtt_story().substring(0, 23) + "..", "\t|");
			} else {
				System.out.printf("%-25s %1s", ott.getOtt_story(), "\t\t|");
			}
			System.out.print(ott.getOtt_genre() + "\t|");
			if (ott.getOtt_actor().length() < 10) {
				System.out.printf("%-10s %1s", ott.getOtt_actor(), "\t|");
			} else {
				System.out.printf("%-10s %1s", ott.getOtt_actor().substring(0, 8) + "..", "\t|");
			}
			if (ott.getOtt_director().length() > 5) {
				System.out.print(ott.getOtt_director() + "\t|");
			} else {
				System.out.print(ott.getOtt_director() + "\t\t|");
			}
			System.out.print(ott.getOtt_year() + "\t|");
			System.out.print(ott.getOtt_rate() + "\t|");
			System.out.print(ott.getOtt_age() + "\t|");
			System.out.print(ott.getOtt_view() + "\t|");
			System.out.println("");
		}
	}

	public void categoryList(int choice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OttVO> ottList = new ArrayList();
		String genre = null;
		try {
			con = DBUtil.makeConnection();
			if (choice == 1) {
				genre = "로맨스";
			} else if (choice == 2) {
				genre = "드라마";
			} else if (choice == 3) {
				genre = "액션";
			} else if (choice == 4) {
				genre = "애니메이션";
			} else if (choice == 5) {
				genre = "범죄";
			} else if (choice == 6) {
				genre = "스릴러";
			} else if (choice == 7) {
				genre = "코미디";
			}
			String sql = "select * from jav_ott where ott_genre = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, genre);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int ott_num = rs.getInt("ott_num");
				String ott_title = rs.getString("ott_title");
				String ott_country = rs.getString("ott_country");
				String ott_story = rs.getString("ott_story");
				String ott_genre = rs.getString("ott_genre");
				String ott_actor = rs.getString("ott_actor");
				String ott_director = rs.getString("ott_director");
				String ott_year = rs.getString("ott_year");
				Double ott_rate = rs.getDouble("ott_rate");
				String ott_age = rs.getString("ott_age");
				int ott_view = rs.getInt("ott_view");
				OttVO ott = new OttVO(ott_num, ott_title, ott_country, ott_story, ott_genre, ott_actor, ott_director,
						ott_year, ott_rate, ott_age, ott_view);
				ottList.add(ott);
			}
			printOttList(ottList);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public void countryList(int choice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OttVO> ottList = new ArrayList();
		String genre = null;
		try {
			con = DBUtil.makeConnection();
			if (choice == 1) {
				genre = "한국";
			} else if (choice == 2) {
				genre = "미국";
			} else if (choice == 3) {
				genre = "일본";
			} else if (choice == 4) {
				genre = "유럽";
			} else if (choice == 5) {
				genre = "스페인";
			}
			String sql = "select * from jav_ott where ott_country = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, genre);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int ott_num = rs.getInt("ott_num");
				String ott_title = rs.getString("ott_title");
				String ott_country = rs.getString("ott_country");
				String ott_story = rs.getString("ott_story");
				String ott_genre = rs.getString("ott_genre");
				String ott_actor = rs.getString("ott_actor");
				String ott_director = rs.getString("ott_director");
				String ott_year = rs.getString("ott_year");
				Double ott_rate = rs.getDouble("ott_rate");
				String ott_age = rs.getString("ott_age");
				int ott_view = rs.getInt("ott_view");
				OttVO ott = new OttVO(ott_num, ott_title, ott_country, ott_story, ott_genre, ott_actor, ott_director,
						ott_year, ott_rate, ott_age, ott_view);
				ottList.add(ott);
			}
			printOttList(ottList);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public void getOttList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OttVO> ottList = new ArrayList();
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from jav_ott order by ott_num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int ott_num = rs.getInt("ott_num");
				String ott_title = rs.getString("ott_title");
				String ott_country = rs.getString("ott_country");
				String ott_story = rs.getString("ott_story");
				String ott_genre = rs.getString("ott_genre");
				String ott_actor = rs.getString("ott_actor");
				String ott_director = rs.getString("ott_director");
				String ott_year = rs.getString("ott_year");
				Double ott_rate = rs.getDouble("ott_rate");
				String ott_age = rs.getString("ott_age");
				int ott_view = rs.getInt("ott_view");
				OttVO ott = new OttVO(ott_num, ott_title, ott_country, ott_story, ott_genre, ott_actor, ott_director,
						ott_year, ott_rate, ott_age, ott_view);
				ottList.add(ott);
			}
			printOttTotalList(ottList);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public void printOttTotalList(ArrayList<OttVO> ottList) {
		for (int i = 0; i < ottList.size(); i++) {
			OttVO ott = ottList.get(i);
			System.out.printf("%-3d %1s", ott.getOtt_num(), "|");
			if (ott.getOtt_title().length() < 18) {
				System.out.printf("%-20s %1s", ott.getOtt_title(), "\t|");
			} else {
				System.out.printf("%-18s %1s", ott.getOtt_title().substring(0, 15) + "..", "\t|");
			}
			System.out.print(ott.getOtt_country() + "\t|");
			if (ott.getOtt_story().length() >= 25) {
				System.out.printf("%-25s %1s", ott.getOtt_story().substring(0, 23) + "..", "\t|");
			} else {
				System.out.printf("%-25s %1s", ott.getOtt_story(), "\t\t|");
			}
			System.out.print(ott.getOtt_genre() + "\t|");
			if (ott.getOtt_actor().length() < 10) {
				System.out.printf("%-10s %1s", ott.getOtt_actor(), "\t|");
			} else {
				System.out.printf("%-10s %1s", ott.getOtt_actor().substring(0, 8) + "..", "\t|");
			}
			if (ott.getOtt_director().length() > 5) {
				System.out.print(ott.getOtt_director() + "\t|");
			} else {
				System.out.print(ott.getOtt_director() + "\t\t|");
			}
			System.out.print(ott.getOtt_year() + "\t|");
			System.out.print(ott.getOtt_rate() + "\t|");
			System.out.print(ott.getOtt_age() + "\t|");
			System.out.print(ott.getOtt_view() + "\t|");
			System.out.println("");
		}
	}

	public void updateOtt(OttVO ott, int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL updateott(?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, ott.getOtt_title());
			cstmt.setString(2, ott.getOtt_country());
			cstmt.setString(3, ott.getOtt_story());
			cstmt.setString(4, ott.getOtt_genre());
			cstmt.setString(5, ott.getOtt_actor());
			cstmt.setString(6, ott.getOtt_director());
			cstmt.setString(7, ott.getOtt_year());
			cstmt.setDouble(8, ott.getOtt_rate());
			cstmt.setString(9, ott.getOtt_age());
			cstmt.setInt(10, num);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.executeUpdate();
			String message = cstmt.getString(11);
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public void createOtt(OttVO ott) {
		Connection con = null;
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
		int view = 0;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL createott(?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, ott.getOtt_title());
			cstmt.setString(2, ott.getOtt_country());
			cstmt.setString(3, ott.getOtt_story());
			cstmt.setString(4, ott.getOtt_genre());
			cstmt.setString(5, ott.getOtt_actor());
			cstmt.setString(6, ott.getOtt_director());
			cstmt.setString(7, ott.getOtt_year());
			cstmt.setDouble(8, ott.getOtt_rate());
			cstmt.setString(9, ott.getOtt_age());
			cstmt.setInt(10, view);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.executeUpdate();
			String message = cstmt.getString(11);
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(pstmt, con);
		}
	}

	public void ottDelete() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OttVO> ottList = new ArrayList();
		CallableStatement cstmt = null;
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from jav_ott order by ott_num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int ott_num = rs.getInt("ott_num");
				String ott_title = rs.getString("ott_title");
				String ott_country = rs.getString("ott_country");
				String ott_story = rs.getString("ott_story");
				String ott_genre = rs.getString("ott_genre");
				String ott_actor = rs.getString("ott_actor");
				String ott_director = rs.getString("ott_director");
				String ott_year = rs.getString("ott_year");
				Double ott_rate = rs.getDouble("ott_rate");
				String ott_age = rs.getString("ott_age");
				int ott_view = rs.getInt("ott_view");
				OttVO ott = new OttVO(ott_num, ott_title, ott_country, ott_story, ott_genre, ott_actor, ott_director,
						ott_year, ott_rate, ott_age, ott_view);
				ottList.add(ott);
			}
			printOttTotalList(ottList);
			System.out.print("삭제할 ott 번호를 입력하세요: ");
			int number = Integer.parseInt(sc.nextLine());
			cstmt = con.prepareCall("{CALL ottdelete(?,?)}");
			cstmt.setInt(1, number);
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.executeUpdate();
			String message = cstmt.getString(2);
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
