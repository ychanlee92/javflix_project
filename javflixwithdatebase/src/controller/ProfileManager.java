package controller;

import java.util.Scanner;

import model.ProfileVO;
import model.UserVO;

public class ProfileManager {
	public static Scanner sc = new Scanner (System.in);
	public static ProfileVO profileCheck(UserVO user) {
		ProfileDAO pd = new ProfileDAO();
		ProfileVO profile = pd.profileCheck(user);
		return profile;
	}

	public static ProfileVO profileLogin(ProfileVO profile) {
		ProfileDAO pd = new ProfileDAO();
		ProfileVO profileo = pd.profileLogin(profile);
		return profileo;
	}

	public void nameChange(ProfileVO pro) {
		ProfileDAO pd = new ProfileDAO();
		System.out.print("새로운 프로필 이름을 입력하세요 :");
		pd.profileName(sc.nextLine(),pro);
	}

	public void passChange(ProfileVO pro) {
		ProfileDAO pd = new ProfileDAO();
		System.out.print("새로운 프로필 비밀번호를 입력하세요 :");
		pd.prifilePass(sc.nextLine(),pro);
	}

	public void passCreate(ProfileVO pro) {
		ProfileDAO pd = new ProfileDAO();
		System.out.print("프로필 이름을 입력하세요: ");
		String profile_name = sc.nextLine();
		System.out.print("프로필 비밀번호를 입력하세요: ");
		String profile_pass = sc.nextLine();
		ProfileVO profile = new ProfileVO(profile_name, profile_pass,pro.getUser_id());
		pd.profileInsert(profile);
	}

	public ProfileVO proChange(ProfileVO pro) {
		ProfileDAO pd = new ProfileDAO();
		ProfileVO profile = pd.profileChoose(pro);
		return profile;
	}

}
