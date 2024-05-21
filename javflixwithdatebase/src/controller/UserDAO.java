package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.OttVO;
import model.ProfileVO;
import model.UserVO;

public class UserDAO {
	public static Scanner sc = new Scanner(System.in);
	public static boolean idCheck(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean idCheck = false;
		UserVO user = new UserVO();
		try {
			con = DBUtil.makeConnection();
			String sql = "select count(*) from jav_user where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			int count = -1;
			while (rs.next()) {
				count = rs.getInt("count(*)");
			}
			if (count == 0) {
				idCheck = true;
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
		return idCheck;
	}

	public static boolean phoneCheck(String phone) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean phoneCheck = false;
		UserVO user = new UserVO();
		try {
			con = DBUtil.makeConnection();
			String sql = "select count(*) from jav_user where user_phone = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();
			int count = -1;
			while (rs.next()) {
				count = rs.getInt("count(*)");
			}
			if (count == 0) {
				phoneCheck = true;
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
		return phoneCheck;
	}

	public static void userInsert(UserVO user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			String sql = "insert into jav_user values (user_sep.nextval, ?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUser_name());
			pstmt.setString(2, user.getUser_id());
			pstmt.setString(3, user.getUser_pass());
			pstmt.setString(4, user.getUser_phone());
			pstmt.setString(5, user.getUser_membership());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(user.getUser_id() + " 정보가 입력되었습니다.");
			} else {
				System.out.println("사용자 정보 입력 실패했습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
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

	public UserVO userLogin(String id, String pass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO user = new UserVO();
		boolean flag = false;
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from jav_user where user_id = ? and user_pass = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int user_num = rs.getInt("user_num");
				String user_name = rs.getString("user_name");
				String user_id = rs.getString("user_id");
				String user_pass = rs.getString("user_pass");
				String user_phone = rs.getString("user_phone");
				String user_membership = rs.getString("user_membership");
				user = new UserVO(user_num, user_name, user_id, user_pass, user_phone, user_membership);
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
		return user;
	}

	public void accountPass(String pass, ProfileVO pro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.makeConnection();
			String sql = "update jav_user set user_pass = ? where user_id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, pro.getUser_id());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(pro.getUser_id() + " 계정 비밀번호 변경 완료!");
			} else {
				System.out.println("정보 업데이트 실패했습니다. ");
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

	public void userSearch() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO user = new UserVO();
		boolean flag = false;
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from jav_user order by user_num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int user_num = rs.getInt("user_num");
				String user_name = rs.getString("user_name");
				String user_id = rs.getString("user_id");
				String user_pass = rs.getString("user_pass");
				String user_phone = rs.getString("user_phone");
				String user_membership = rs.getString("user_membership");
				user = new UserVO(user_num, user_name, user_id, user_pass, user_phone, user_membership);
				System.out.println(user.toString());
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

	public void userUpdate(UserVO user, UserVO us) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.makeConnection();
			String sql = "update jav_user set user_name = ?, user_id = ?, user_pass = ?, user_phone = ? where user_id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUser_name());
			pstmt.setString(2, user.getUser_id());
			pstmt.setString(3, user.getUser_pass());
			pstmt.setString(4, user.getUser_phone());
			pstmt.setString(5, us.getUser_id());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(user.getUser_id() + " 계정 비밀번호 변경 완료!");
			} else {
				System.out.println("정보 업데이트 실패했습니다. ");
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

	public UserVO userIdChoose() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO user = new UserVO();
		ArrayList<UserVO> userList = new ArrayList();
		boolean flag = false;
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from jav_user order by user_num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int user_num = rs.getInt("user_num");
				String user_name = rs.getString("user_name");
				String user_id = rs.getString("user_id");
				String user_pass = rs.getString("user_pass");
				String user_phone = rs.getString("user_phone");
				String user_membership = rs.getString("user_membership");
				user = new UserVO(user_num, user_name, user_id, user_pass, user_phone, user_membership);
				userList.add(user);
			}
			System.out.print("사용자 ID를 선택하세요: ");
			for (int i = 0; i < userList.size(); i++) {
				user = userList.get(i);
				System.out.print((i + 1) + ". " + user.getUser_id() + " ");
			}
			user = userList.get(Integer.parseInt(sc.nextLine())-1);
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
		return user;
	}

	public void userDelete() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO user = new UserVO();
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from jav_user order by user_num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int user_num = rs.getInt("user_num");
				String user_name = rs.getString("user_name");
				String user_id = rs.getString("user_id");
				String user_pass = rs.getString("user_pass");
				String user_phone = rs.getString("user_phone");
				String user_membership = rs.getString("user_membership");
				user = new UserVO(user_num, user_name, user_id, user_pass, user_phone, user_membership);
				System.out.println(user.toString());
			}
			System.out.print("삭제할 유저 ID를 입력하세요: ");
			String id = sc.nextLine();
			sql = "delete from jav_profile where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			sql = "delete from jav_user where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("삭제되었습니다.");
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

	public String membershipCheck(ProfileVO pro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO user = new UserVO();
		boolean flag = false;
		String user_membership = null;
		try {
			con = DBUtil.makeConnection();
			String sql = "select u.user_membership from jav_profile p inner join jav_user u on p.user_id = u.user_id where p.profile_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pro.getProfile_name());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user_membership = rs.getString("user_membership");
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
		return user_membership;
	}

	
	public void printUser(ProfileVO pro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO user = new UserVO();
		ArrayList<UserVO> userList = new ArrayList();
		ProfileVO profile = new ProfileVO();
		ArrayList<ProfileVO> profileList = new ArrayList();
		boolean flag = false;
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from jav_user where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pro.getUser_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int user_num = rs.getInt("user_num");
				String user_name = rs.getString("user_name");
				String user_id = rs.getString("user_id");
				String user_pass = rs.getString("user_pass");
				String user_phone = rs.getString("user_phone");
				String user_membership = rs.getString("user_membership");
				user = new UserVO(user_num, user_name, user_id, user_pass, user_phone, user_membership);
				userList.add(user);
			}
			String sql1 = "select * from jav_profile where user_id = ?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, pro.getUser_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int profile_num = rs.getInt("profile_num");
				String profile_name = rs.getString("profile_name");
				String profile_pass = rs.getString("profile_pass");
				String user_id = rs.getString("user_id");
				profile = new ProfileVO(profile_num, profile_name, profile_pass, user_id);
				profileList.add(profile);
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
		for (int i = 0; i < userList.size(); i++) {
			user = userList.get(i);
			System.out.println("성함: \t"+user.getUser_name());
			System.out.println("ID: \t"+user.getUser_id());
			System.out.println("비밀번호:  "+user.getUser_pass());
			System.out.println("전화번호:  "+user.getUser_phone());
			System.out.println("멤버쉽: \t"+user.getUser_membership());
		}
		for (int i = 0; i < profileList.size(); i++) {
			profile = profileList.get(i);
			System.out.println("프로필: \t"+profile.getProfile_name());
			System.out.println("");
		}
	}

	public void membershipChange(int number, ProfileVO pro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO user = new UserVO();
		String membership = null;
		if(number ==1) {
			membership = "premium";
		} else {
			membership = "gold";
		}
		try {
			con = DBUtil.makeConnection();
			String sql = "update jav_user set user_membership = ? where user_id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, membership);
			pstmt.setString(2, pro.getUser_id());
			int i = pstmt.executeUpdate();
			if (i == 1) {
//				System.out.println(user.getUser_id() + " 멤버쉽 변경 완료!");
			} else {
				System.out.println("정보 업데이트 실패했습니다. ");
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
}
