package model;

public class AdminVO {
	private int admin_num = 0;
	private String user_id = null;
	private int ott_num = 0;
	
	public AdminVO() {
		super();
	}

	public AdminVO(int admin_num, String user_id, int ott_num) {
		super();
		this.admin_num = admin_num;
		this.user_id = user_id;
		this.ott_num = ott_num;
	}

	public int getAdmin_num() {
		return admin_num;
	}

	public void setAdmin_num(int admin_num) {
		this.admin_num = admin_num;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getOtt_num() {
		return ott_num;
	}

	public void setOtt_num(int ott_num) {
		this.ott_num = ott_num;
	}

	@Override
	public String toString() {
		return "[" + admin_num + "\t|" + user_id + "\t|" + ott_num + "]";
	}
	
	
}
