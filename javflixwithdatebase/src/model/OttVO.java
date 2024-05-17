package model;

public class OttVO {
	private int ott_num = 0;
	private String ott_title = null;
	private String ott_country = null;
	private String ott_story = null;
	private String ott_genre = null;
	private String ott_actor = null;
	private String ott_director = null;
	private String ott_year = null;
	private double ott_rate = 0.0;
	private String ott_age = null;
	private int ott_view = 0;
	
	public OttVO() {
		super();
	}
	
	public OttVO(int ott_num, String ott_title, String ott_country, String ott_story, String ott_genre,
			String ott_actor, String ott_director, String ott_year, double ott_rate, String ott_age, int ott_view) {
		super();
		this.ott_num = ott_num;
		this.ott_title = ott_title;
		this.ott_country = ott_country;
		this.ott_story = ott_story;
		this.ott_genre = ott_genre;
		this.ott_actor = ott_actor;
		this.ott_director = ott_director;
		this.ott_year = ott_year;
		this.ott_rate = ott_rate;
		this.ott_age = ott_age;
		this.ott_view = ott_view;
	}

	public int getOtt_num() {
		return ott_num;
	}
	public void setOtt_num(int ott_num) {
		this.ott_num = ott_num;
	}
	public String getOtt_title() {
		return ott_title;
	}
	public void setOtt_title(String ott_title) {
		this.ott_title = ott_title;
	}
	public String getOtt_country() {
		return ott_country;
	}
	public void setOtt_country(String ott_country) {
		this.ott_country = ott_country;
	}
	public String getOtt_story() {
		return ott_story;
	}
	public void setOtt_story(String ott_story) {
		this.ott_story = ott_story;
	}
	public String getOtt_genre() {
		return ott_genre;
	}
	public void setOtt_genre(String ott_genre) {
		this.ott_genre = ott_genre;
	}
	public String getOtt_actor() {
		return ott_actor;
	}
	public void setOtt_actor(String ott_actor) {
		this.ott_actor = ott_actor;
	}
	public String getOtt_director() {
		return ott_director;
	}
	public void setOtt_director(String ott_director) {
		this.ott_director = ott_director;
	}
	public String getOtt_year() {
		return ott_year;
	}
	public void setOtt_year(String ott_year) {
		this.ott_year = ott_year;
	}
	public double getOtt_rate() {
		return ott_rate;
	}
	public void setOtt_rate(double ott_rate) {
		this.ott_rate = ott_rate;
	}
	public String getOtt_age() {
		return ott_age;
	}
	public void setOtt_age(String ott_age) {
		this.ott_age = ott_age;
	}
	public int getOtt_view() {
		return ott_view;
	}
	public void setOtt_view(int ott_view) {
		this.ott_view = ott_view;
	}

	@Override
	public String toString() {
		return "[" + ott_num + "\t|" + ott_title + "\t|" + ott_country
				+ "\t|" + ott_story + "\t|" + ott_genre + "\t|" + ott_actor
				+ "\t|" + ott_director + "\t|" + ott_year + "\t|" + ott_rate
				+ "\t|" + ott_age + "\t|" + ott_view + "]";
	}
	
	
}
