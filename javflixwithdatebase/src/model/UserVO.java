package model;

public class UserVO {
	private int user_num = 0;
	private String user_name = null;
	private String user_id = null;
	private String user_pass = null;
	private String user_phone = null;
	private String user_membership = null;
	
	public UserVO() {
		super();
	}
	
	public UserVO(String user_name, String user_id, String user_pass, String user_phone,
			 String user_membership) {
			super();
			this.user_name = user_name;
			this.user_id = user_id;
			this.user_pass = user_pass;
			this.user_phone = user_phone;
			this.user_membership = user_membership;
		}
	
	public UserVO(int user_num, String user_name, String user_id, String user_pass, String user_phone,
		 String user_membership) {
		super();
		this.user_num = user_num;
		this.user_name = user_name;
		this.user_id = user_id;
		this.user_pass = user_pass;
		this.user_phone = user_phone;
		this.user_membership = user_membership;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_membership() {
		return user_membership;
	}
	public void setUser_membership(String user_membership) {
		this.user_membership = user_membership;
	}
	@Override
	public String toString() {
		return "[" + user_num + "\t|" + user_name + "\t|" + user_id + "\t|"
				+ user_pass + "\t|" + user_phone + "\t|" + user_membership + "   \t]";
	}
	
	
}
