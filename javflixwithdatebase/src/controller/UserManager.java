package controller;

import java.time.LocalDate;
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
		ud.accountPass(pass, pro);
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
		ud.userUpdate(user, us);
	}

	public void userDelete() {
		UserDAO ud = new UserDAO();
		ud.userDelete();
	}

	public String userMembership(ProfileVO pro) {
		UserDAO ud = new UserDAO();
		String membership = ud.membershipCheck(pro);
		LocalDate now = LocalDate.now();
		System.out.println("회원님의 멤버쉽은 " + membership + " 등급입니다.");
		if (membership.equals("premium")) {

		} else if (membership.equals("gold")) {
			System.out.println("premium 등급으로 이번 달 남은 일수 * 요금제 금액차이 = "+ Math.round(now.getDayOfMonth()/30.0*5000)   + "원입니다. 금액은 다음달 핸드폰 요금에 청구됩니다.");
		} else {
			System.out.println("gold 등급으로 이번 달 남은 일수 * 요금제 금액차이 = "+ Math.round(now.getDayOfMonth()/30.0*5000) + "원입니다. 금액은 다음달 핸드폰 요금에 청구됩니다.");
			System.out.println("premium 등급으로 이번 달 남은 일수 * 요금제 금액차이 = "+ Math.round(now.getDayOfMonth()/30.0*5000) + "원입니다. 금액은 다음달 핸드폰 요금에 청구됩니다.");
		}
		return membership;
	}

	public void membershipChoose(String membership) {
		UserDAO ud = new UserDAO();
		if(membership.equals("premium")) {
			System.out.println("이미 결제 중입니다.");
		} else if(membership.equals("gold")){			
			System.out.println("premium 등급으로 변경되었습니다. 감사합니다. ");
		} else {
			System.out.println("멤버쉽를 결정하세요.");
			System.out.print("1. premium 2. gold");
			int number = Integer.parseInt(sc.nextLine());
			if(number==1) {
				System.out.println("premium 등급으로 변경되었습니다. 감사합니다. ");
			} else if(number ==2) {
				System.out.println("gold 등급으로 변경되었습니다. 감사합니다. ");
			} else {
				System.out.println("잘못 입력했습니다.");
			}
		}
	}

}
