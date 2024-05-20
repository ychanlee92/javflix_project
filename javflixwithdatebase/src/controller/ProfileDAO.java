package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.CartVO;
import model.OttVO;
import model.ProfileVO;
import model.UserVO;

public class ProfileDAO {
	public static Scanner sc = new Scanner(System.in);

	public static void profileInsert(ProfileVO profile) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			String sql = "insert into jav_profile values (profile_sep.nextval,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, profile.getProfile_name());
			if (profile.getProfile_pass().equals("-1")) {
				pstmt.setString(2, null);
			} else {
				pstmt.setString(2, profile.getProfile_pass());
			}
			pstmt.setString(3, profile.getUser_id());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(profile.getProfile_name() + " 정보가 입력되었습니다.");
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

	public ProfileVO profileCheck(UserVO user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProfileVO profile = new ProfileVO();
		boolean flag = false;
		try {
			con = DBUtil.makeConnection();
			String sql = "select profile_num, profile_name, profile_pass,p.user_id from jav_profile p inner join jav_user u on p.user_id = u.user_id where p.user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUser_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int profile_num = rs.getInt("profile_num");
				String profile_name = rs.getString("profile_name");
				String profile_pass = rs.getString("profile_pass");
				String user_id = rs.getString("user_id");
				profile = new ProfileVO(profile_num, profile_name, profile_pass, user_id);
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
		return profile;
	}

	public ProfileVO profileLogin(ProfileVO profile, UserVO user) {
		ArrayList<ProfileVO> profileList = new ArrayList();
		ArrayList<UserVO> userList = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;

		try {
			con = DBUtil.makeConnection();
			String sql = "select profile_num,profile_name, profile_pass,P.user_id from jav_profile p inner join jav_user u on p.user_id = u.user_id where u.user_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUser_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int profile_num = rs.getInt("profile_num");
				String profile_name = rs.getString("profile_name");
				String profile_pass = rs.getString("profile_pass");
				String user_id = rs.getString("user_id");
				profile = new ProfileVO(profile_num, profile_name, profile_pass, user_id);
				profileList.add(profile);
			}
			List<String> list = profileList.stream().map(s -> s.getProfile_name()).collect(Collectors.toList());
			if (list.getFirst().equals(list.getLast())) {

			} else {
				for (;;) {
					System.out.print("프로필을 선택하세요: ");
					for (int i = 0; i < profileList.size(); i++) {
						profile = profileList.get(i);
						System.out.print((i + 1) + ". " + profile.getProfile_name() + " ");
					}
					profile = profileList.get(Integer.parseInt(sc.nextLine())-1);
					if (profile.getProfile_pass() != null) {
						System.out.print("비밀번호를 입력하세요: ");
						if (profile.getProfile_pass().equals(sc.nextLine())) {
							System.out.println("현재 프로필: " + profile.getProfile_name());
							break;
						} else {
							System.out.println("로그인 정보가 맞지 않습니다. ");
						}
					} else {
						System.out.println("현재 프로필: " + profile.getProfile_name());
						break;
					}
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
		return profile;
	}

	public ProfileVO profileChoose(ProfileVO pro) {
		ArrayList<ProfileVO> profileList = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProfileVO profile = new ProfileVO();
		boolean flag = false;
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from jav_profile where user_id = ?";
			pstmt = con.prepareStatement(sql);
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
			System.out.println(profileList);
			List<String> list = profileList.stream().map(s -> s.getProfile_name()).collect(Collectors.toList());
			if (list.getFirst().equals(list.getLast())) {

			} else {
				for (;;) {
					System.out.print("프로필을 선택하세요: ");
					for (int i = 0; i < profileList.size(); i++) {
						profile = profileList.get(i);
						System.out.print((i + 1) + ". " + profile.getProfile_name() + " ");
					}
					profile = profileList.get(Integer.parseInt(sc.nextLine()) - 1);
					if (profile.getProfile_pass() != null) {
						System.out.print("비밀번호를 입력하세요: ");
						if (profile.getProfile_pass().equals(sc.nextLine())) {
							System.out.println("현재 프로필: " + profile.getProfile_name());
							break;
						} else {
							System.out.println("로그인 정보가 맞지 않습니다. ");
						}
					}

				}
			}
		} catch (

		IOException e) {
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
		return profile;
	}

	public void profileName(String string, ProfileVO pro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.makeConnection();
			String sql = "update jav_profile set profile_name = ? where profile_name =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, string);
			pstmt.setString(2, pro.getProfile_name());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(string + " 프로필 이름 변경 완료!");
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

	public void prifilePass(String nextLine, ProfileVO pro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.makeConnection();
			String sql = "update jav_profile set profile_pass = ? where profile_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nextLine);
			pstmt.setString(2, pro.getProfile_name());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(nextLine + " 프로필 비밀번호 변경 완료!");
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
