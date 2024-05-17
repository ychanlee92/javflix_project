package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.CartVO;
import model.OttVO;
import model.ProfileVO;
import view.MenuViewer;
import view.OPTION_CHOICE;
import view.OTT_CHOICE;

public class OttDAO {
	public static Scanner sc = new Scanner(System.in);

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

	public void printOttList(ArrayList<OttVO> ottList) {
		for (int i = 0; i < ottList.size(); i++) {
			OttVO ott = ottList.get(i);
			System.out.printf("%-3d %1s", ott.getOtt_num(), "|");
			if (ott.getOtt_title().length() < 18) {
				System.out.printf("%-20s %1s", ott.getOtt_title(), "\t|");
			} else {
				System.out.printf("%-18s %1s", ott.getOtt_title().substring(0, 15) + "..", "\t|");
			}
			System.out.print(ott.getOtt_country() + "\t|");
			System.out.printf("%-25s %1s", ott.getOtt_story().substring(0, 23) + "..", "\t|");
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

	public void optionChoose() {
		System.out.print("Ott번호를 선택하세요: ");
		int number = Integer.parseInt(sc.nextLine());
		int choice = 0;
		MenuViewer.optionMenuView();
		choice = Integer.parseInt(sc.nextLine());
		switch (choice) {
		case OPTION_CHOICE.WATCH:
			watchOtt(number);
			break;
		case OPTION_CHOICE.ADD:
			addOtt(number);
			break;
		case OPTION_CHOICE.DOWN:
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

	public static void downOtt(int number) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OttVO ott = new OttVO();
		ProfileVO profile = new ProfileVO();
		CartVO cart = new CartVO();
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
			String sq2 = "select * from jav_profile order by profile_num";
			pstmt = con.prepareStatement(sq2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int profile_num = rs.getInt("profile_num");
				String profile_name = rs.getString("profile_name");
				String profile_pass = rs.getString("profile_pass");
				String user_id = rs.getString("user_id");
				profile = new ProfileVO(profile_num, profile_name, profile_pass, user_id);
			}
			String sql3 = "select * from jav_cart order by cart_num";
			pstmt = con.prepareStatement(sql3);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cart_num = rs.getInt("cart_num");
				String profile_name = rs.getString("profile_name");
				String ott_num = rs.getString("ott_num");
				String cart_seen = rs.getString("cart_seen");
				String cart_down = rs.getString("cart_down");
				String cart_add = rs.getString("cart_add");
				cart = new CartVO(cart_num, profile_name, ott_num, cart_seen, cart_down, cart_add);
			}
			if (profile.getProfile_name() != null && cart.getProfile_name() != null
					&& cart.getProfile_name().contains(profile.getProfile_name()) && cart.getOtt_num().equals(number)) {
				if (cart.getCart_down().equals("다운완료")) {
					System.out.println("이미 다운받은 ott입니다.");
				} else {
					String sql4 = "update jav_cart set cart_down ?";
					pstmt = con.prepareStatement(sql4);
					pstmt.setString(1, "다운완료");
					int i = pstmt.executeUpdate();
					if (i == 1) {
						System.out.println(number + "번 ott 다운로드가 완료되었습니다.");
					} else {
						System.out.println("정보 입력 실패했습니다.");
					}
				}
			} else {
				String sql4 = "insert into jav_cart values (cart_sep.nextval,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql4);
				pstmt.setString(1, profile.getProfile_name());
				pstmt.setInt(2, number);
				pstmt.setString(3, "미시청");
				pstmt.setString(4, "다운완료");
				pstmt.setString(5, "미추가");
				int i = pstmt.executeUpdate();
				if (i == 1) {
					System.out.println(number + "번 ott 정보가 입력되었습니다.");
				} else {
					System.out.println("정보 입력 실패했습니다.");
				}
			}
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

	public static void addOtt(int number) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OttVO ott = new OttVO();
		ProfileVO profile = new ProfileVO();
		CartVO cart = new CartVO();
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
			String sq2 = "select * from jav_profile order by profile_num";
			pstmt = con.prepareStatement(sq2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int profile_num = rs.getInt("profile_num");
				String profile_name = rs.getString("profile_name");
				String profile_pass = rs.getString("profile_pass");
				String user_id = rs.getString("user_id");
				profile = new ProfileVO(profile_num, profile_name, profile_pass, user_id);
			}
			String sql3 = "select * from jav_cart order by cart_num";
			pstmt = con.prepareStatement(sql3);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cart_num = rs.getInt("cart_num");
				String profile_name = rs.getString("profile_name");
				String ott_num = rs.getString("ott_num");
				String cart_seen = rs.getString("cart_seen");
				String cart_down = rs.getString("cart_down");
				String cart_add = rs.getString("cart_add");
				cart = new CartVO(cart_num, profile_name, ott_num, cart_seen, cart_down, cart_add);
			}
			if (profile.getProfile_name() != null && cart.getProfile_name() != null
					&& cart.getProfile_name().contains(profile.getProfile_name()) && cart.getOtt_num().equals(number)) {
				if (cart.getCart_add().equals("찜완료")) {
					System.out.println("이미 찜한 ott입니다.");
				} else {
					String sql4 = "update jav_cart set cart_add ?";
					pstmt = con.prepareStatement(sql4);
					pstmt.setString(1, "찜완료");
					int i = pstmt.executeUpdate();
					if (i == 1) {
						System.out.println(number + "번 ott 찜하기가 완료되었습니다.");
					} else {
						System.out.println("정보 입력 실패했습니다.");
					}
				}
			} else {
				String sql4 = "insert into jav_cart values (cart_sep.nextval,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql4);
				pstmt.setString(1, profile.getProfile_name());
				pstmt.setInt(2, number);
				pstmt.setString(3, "미시청");
				pstmt.setString(4, "미다운로드");
				pstmt.setString(5, "찜완료");
				int i = pstmt.executeUpdate();
				if (i == 1) {
					System.out.println(number + "번 ott 정보가 입력되었습니다.");
				} else {
					System.out.println("정보 입력 실패했습니다.");
				}
			}
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

	public static void watchOtt(int number) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OttVO ott = new OttVO();
		ProfileVO profile = new ProfileVO();
		CartVO cart = new CartVO();
		try {
			con = DBUtil.makeConnection();
			String sql = "select ott_story from jav_ott where ott_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String ott_story = rs.getString("ott_story");
				System.out.println(ott_story);
			}
			sql = "select * from jav_ott order by ott_num";
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
			String sq2 = "select * from jav_profile order by profile_num";
			pstmt = con.prepareStatement(sq2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int profile_num = rs.getInt("profile_num");
				String profile_name = rs.getString("profile_name");
				String profile_pass = rs.getString("profile_pass");
				String user_id = rs.getString("user_id");
				profile = new ProfileVO(profile_num, profile_name, profile_pass, user_id);
			}
			String sql3 = "select * from jav_cart order by cart_num";
			pstmt = con.prepareStatement(sql3);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cart_num = rs.getInt("cart_num");
				String profile_name = rs.getString("profile_name");
				String ott_num = rs.getString("ott_num");
				String cart_seen = rs.getString("cart_seen");
				String cart_down = rs.getString("cart_down");
				String cart_add = rs.getString("cart_add");
				cart = new CartVO(cart_num, profile_name, ott_num, cart_seen, cart_down, cart_add);
			}
			if (profile.getProfile_name() != null && cart.getProfile_name() != null
					&& cart.getProfile_name().contains(profile.getProfile_name()) && cart.getOtt_num().equals(number)) {
				if (cart.getCart_seen().equals("시청함")) {
					
				} else {
					String sql4 = "update jav_cart set cart_seen ?";
					pstmt = con.prepareStatement(sql4);
					pstmt.setString(1, "시청함");
					int i = pstmt.executeUpdate();
					if (i == 1) {
						System.out.println("시청완료!");
					} else {
						System.out.println("시청 정보를 업데이트 실패했습니다. ");
					}
				}
			} else {
				String sql4 = "insert into jav_cart values (cart_sep.nextval,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql4);
				pstmt.setString(1, profile.getProfile_name());
				pstmt.setInt(2, number);
				pstmt.setString(3, "시청함");
				pstmt.setString(4, "미다운로드");
				pstmt.setString(5, "미추가");
				int i = pstmt.executeUpdate();
				if (i == 1) {
					System.out.println("시청완료!");
				} else {
					System.out.println("시청 정보를 업데이트 실패했습니다. ");
				}
			}
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

	public void ottAddList() {
		CartVO cart = new CartVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartVO> cartList = new ArrayList();
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from jav_cart where cart_add = '찜완료' order by ott_num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cart_num = rs.getInt("cart_num");
				String profile_name = rs.getString("profile_name");
				String ott_num = rs.getString("ott_num");
				String cart_seen = rs.getString("cart_seen");
				String cart_down = rs.getString("cart_down");
				String cart_add = rs.getString("cart_add");
				cart = new CartVO(cart_num, profile_name, ott_num, cart_seen, cart_down, cart_add);
				cartList.add(cart);
			}
			printAddList(cartList);
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

	public static void printAddList(ArrayList<CartVO> cartList) {
		for (int i = 0; i < cartList.size(); i++) {
			CartVO cart = cartList.get(i);
			System.out.print(cart.getProfile_name()+ "\t|");
			System.out.print(cart.getOtt_num() + "\t|");
			System.out.print(cart.getCart_seen() + "\t|");
			System.out.printf("%-8s %1s",cart.getCart_down() ,"\t|");
			System.out.print(cart.getCart_add() + "|");
			System.out.println("");
		}
	}

	public static void deleteOtt(int number) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OttVO ott = new OttVO();
		try {
			con = DBUtil.makeConnection();
			String sql = "delete from jav_cart where ott_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, number);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(number + "번 ott가 삭제되었습니다.");
			} else {
				System.out.println("삭제 실패했습니다.");
			}
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

	public void ottDownList() {
		CartVO cart = new CartVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartVO> cartList = new ArrayList();
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from jav_cart where cart_down ='다운완료' order by ott_num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cart_num = rs.getInt("cart_num");
				String profile_name = rs.getString("profile_name");
				String ott_num = rs.getString("ott_num");
				String cart_seen = rs.getString("cart_seen");
				String cart_down = rs.getString("cart_down");
				String cart_add = rs.getString("cart_add");
				cart = new CartVO(cart_num, profile_name, ott_num, cart_seen, cart_down, cart_add);
				cartList.add(cart);
			}
			printAddList(cartList);
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

	public void ottWatchList() {
		CartVO cart = new CartVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartVO> cartList = new ArrayList();
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from jav_cart where cart_seen ='시청함' order by ott_num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cart_num = rs.getInt("cart_num");
				String profile_name = rs.getString("profile_name");
				String ott_num = rs.getString("ott_num");
				String cart_seen = rs.getString("cart_seen");
				String cart_down = rs.getString("cart_down");
				String cart_add = rs.getString("cart_add");
				cart = new CartVO(cart_num, profile_name, ott_num, cart_seen, cart_down, cart_add);
				cartList.add(cart);
			}
			printAddList(cartList);
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

	public static void userList() {
		CartVO cart = new CartVO();
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
				String ott_num = rs.getString("ott_num");
				String cart_seen = rs.getString("cart_seen");
				String cart_down = rs.getString("cart_down");
				String cart_add = rs.getString("cart_add");
				cart = new CartVO(cart_num, profile_name, ott_num, cart_seen, cart_down, cart_add);
				cartList.add(cart);
			}
			printAddList(cartList);
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
