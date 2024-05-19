package model;

public class ProfileVO {
	private int profile_num = 0;
	private String profile_name= null;
	private String profile_pass = null;
	private String user_id = null;
	
	public ProfileVO() {
		super();
	}

	public ProfileVO(String profile_name, String profile_pass, String user_id) {
		super();
		this.profile_name = profile_name;
		this.profile_pass = profile_pass;
		this.user_id = user_id;
	}
	
	public ProfileVO(int profile_num, String profile_name, String profile_pass, String user_id) {
		super();
		this.profile_num = profile_num;
		this.profile_name = profile_name;
		this.profile_pass = profile_pass;
		this.user_id = user_id;
	}

	public int getProfile_num() {
		return profile_num;
	}

	public void setProfile_num(int profile_num) {
		this.profile_num = profile_num;
	}

	public String getProfile_name() {
		return profile_name;
	}

	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}

	public String getProfile_pass() {
		return profile_pass;
	}

	public void setProfile_pass(String profile_pass) {
		this.profile_pass = profile_pass;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "[" + profile_num + "\t|" + profile_name + "\t|"
				+ profile_pass + "\t|" + user_id + "]";
	}
	
	
}
