package controller;

import java.util.Scanner;

import model.ProfileVO;
import model.UserVO;

public class UserManager {
	public static Scanner sc = new Scanner(System.in);

	public static void signUp() {
		System.out.print("성함을 입력하세요: ");
		String name = sc.nextLine();
		String id = null;
		for (; true;) {
			System.out.print("ID를 입력하세요: ");
			id = sc.nextLine();
			boolean idCheck = UserDAO.idCheck(id);
			if (idCheck == false) {
				System.out.println("중복된 아이디입니다. 다시 입력해주세요: ");
			} else {
				break;
			}
		}
		System.out.print("비밀번호를 입력하세요: ");
		String pass = sc.nextLine();
		String phone = null;
		for (; true;) {
			System.out.print("전화번호 11자리를 입력하세요: ");
			phone = sc.nextLine();
			if (phone.length() != 11) {
				System.out.println("전화번호 11자리가 아닙니다. 다시 입력하세요.");
			} else {
				boolean phoneCheck = UserDAO.phoneCheck(phone);
				if (phoneCheck == false) {
					System.out.println("중복된 전화번호입니다. 다시 입력해주세요: ");
				} else {
					break;
				}
			}
		}
		System.out.print("프로필 이름을 입력하세요: ");
		String profile_name = sc.nextLine();
		System.out.print("프로필 비밀번호를 입력하세요: (-1 입력시 미 설정)");
		String profile_pass = sc.nextLine();
		UserVO user = new UserVO(name, id, pass, phone, "silver");
		UserDAO.userInsert(user);
		ProfileVO profile = new ProfileVO(profile_name, profile_pass, id);
		ProfileDAO.profileInsert(profile);
	}

	public static UserVO login() {
		UserDAO ud = new UserDAO();
		System.out.print("ID를 입력하세요: ");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력하세요: ");
		String pass = sc.nextLine();
		UserVO user = ud.userLogin(id, pass);
		return user;
	}

	public void accountPass(ProfileVO pro) {
		UserDAO ud = new UserDAO();
		System.out.print("새로운 비밀번호를 입력하세요: ");
		String pass = sc.nextLine();
		ud.accountPass(pass,pro);
	}

	public void userSearch() {
		UserDAO ud = new UserDAO();
		ud.userSearch();
	}

	public void userUpdate() {
		UserDAO ud = new UserDAO();
		UserVO us = ud.userIdChoose();
		System.out.print("새로운 이름을 입력하세요: ");
		String name = sc.nextLine();
		String id = null;
		for (; true;) {
			System.out.print("새로운 ID를 입력하세요: ");
			id = sc.nextLine();
			boolean idCheck = UserDAO.idCheck(id);
			if (idCheck == false) {
				System.out.println("중복된 아이디입니다. 다시 입력해주세요: ");
			} else {
				break;
			}
		}
		System.out.print("새로운 비밀번호를 입력하세요: ");
		String pass = sc.nextLine();
		String phone = null;
		for (; true;) {
			System.out.print("새로운 전화번호 11자리를 입력하세요: ");
			phone = sc.nextLine();
			if (phone.length() != 11) {
				System.out.println("전화번호 11자리가 아닙니다. 다시 입력하세요.");
			} else {
				boolean phoneCheck = UserDAO.phoneCheck(phone);
				if (phoneCheck == false) {
					System.out.println("중복된 전화번호입니다. 다시 입력해주세요: ");
				} else {
					break;
				}
			}
		}
		System.out.print("새로운 프로필 이름을 입력하세요: ");
		String profile_name = sc.nextLine();
		System.out.print("새로운 프로필 비밀번호를 입력하세요: (-1 입력시 미 설정)");
		String profile_pass = sc.nextLine();
		UserVO user = new UserVO(name, id, pass, phone, "silver");
		UserDAO.userInsert(user);
		ProfileVO profile = new ProfileVO(profile_name, profile_pass, id);
		ud.userUpdate(user,us);
	}

	public void userDelete() {
		UserDAO ud = new UserDAO();
		ud.userDelete();
	}
	
}
