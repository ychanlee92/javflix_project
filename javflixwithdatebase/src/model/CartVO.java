package model;

public class CartVO {
	private int cart_num = 0;
	private String profile_name = null;
	private String ott_num = null;
	private String cart_seen = null;
	private String cart_down = null;
	private String cart_add = null;
	
	public CartVO() {
		super();
	}
	
	public CartVO(int cart_num, String profile_name, String ott_num, String cart_seen, String cart_down,
			String cart_add) {
		super();
		this.cart_num = cart_num;
		this.profile_name = profile_name;
		this.ott_num = ott_num;
		this.cart_seen = cart_seen;
		this.cart_down = cart_down;
		this.cart_add = cart_add;
	}

	public int getCart_num() {
		return cart_num;
	}

	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}

	public String getProfile_name() {
		return profile_name;
	}

	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}

	public String getOtt_num() {
		return ott_num;
	}

	public void setOtt_num(String ott_num) {
		this.ott_num = ott_num;
	}

	public String getCart_seen() {
		return cart_seen;
	}

	public void setCart_seen(String cart_seen) {
		this.cart_seen = cart_seen;
	}

	public String getCart_down() {
		return cart_down;
	}

	public void setCart_down(String cart_down) {
		this.cart_down = cart_down;
	}

	public String getCart_add() {
		return cart_add;
	}

	public void setCart_add(String cart_add) {
		this.cart_add = cart_add;
	}

	@Override
	public String toString() {
		return "[" + cart_num + "\t|" + profile_name + "\\t|" + ott_num
				+ "\\t|" + cart_seen + "\\t|" + cart_down + "\\t|" + cart_add + "]";
	}
	
	
}
