package kh.edu.javflix.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kh.edu.javflix.ott.Ott;

public class User implements Serializable {
	private String name;
	private String id;
	private int password;
	private String phone;
	private String profileName1;
	private int profilePassword1;
	private String profileName2;
	private int profilePassword2;
	private String membership;
	private static final String adminId = "admin";
	private static final String adminPassword = "admin1234";

	public static String getAdminid() {
		return adminId;
	}

	public static String getAdminpassword() {
		return adminPassword;
	}

	public User() {
		super();
	}

	public User(String name, String id, int password, String phone, String profileName1, int profilePassword1,
			String profileName2, int profilePassword2, String membership) {
		super();
		this.name = name;
		this.id = id;
		this.password = password;
		this.phone = phone;
		this.profileName1 = profileName1;
		this.profilePassword1 = profilePassword1;
		this.profileName2 = profileName2;
		this.profilePassword2 = profilePassword2;
		this.membership = membership;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProfileName1() {
		return profileName1;
	}

	public void setProfileName1(String profileName1) {
		this.profileName1 = profileName1;
	}

	public int getProfilePassword1() {
		return profilePassword1;
	}

	public void setProfilePassword1(int profilePassword1) {
		this.profilePassword1 = profilePassword1;
	}

	public String getProfileName2() {
		return profileName2;
	}

	public void setProfileName2(String profileName2) {
		this.profileName2 = profileName2;
	}

	public int getProfilePassword2() {
		return profilePassword2;
	}

	public void setProfilePassword2(int profilePassword2) {
		this.profilePassword2 = profilePassword2;
	}

	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, profileName1, profileName2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(profileName1, other.profileName1) && Objects.equals(profileName2, other.profileName2);
	}

	 
}
