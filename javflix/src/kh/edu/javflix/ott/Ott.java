package kh.edu.javflix.ott;

import java.io.Serializable;
import java.util.Objects;

public class Ott implements Comparable<Ott>, Serializable {
	private int number;
	private String title;
	private String country;
	private String story;
	private String genre;
	private String actor;
	private String director;
	private String year;
	private String rate;
	private String age;
	private String isSeen;
	private String isDown;
	private int viewer;

	public Ott() {
		super();
	}

	public Ott(int number, String title, String country, String story, String genre, String actor, String director,
			String year, String rate, String age, String isSeen, String isDown, int viewer) {
		super();
		this.number = number;
		this.title = title;
		this.country = country;
		this.story = story;
		this.genre = genre;
		this.actor = actor;
		this.director = director;
		this.year = year;
		this.rate = rate;
		this.age = age;
		this.isSeen = isSeen;
		this.isDown = isDown;
		this.viewer = viewer;
	}

	public int getViewer() {
		return viewer;
	}

	public void setViewer() {
		this.viewer = (int) (Math.random() * ((1000000 - 1 + 1) + 1));
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getIsSeen() {
		return isSeen;
	}

	public void setIsSeen(String isSeen) {
		this.isSeen = isSeen;
	}

	public String getIsDown() {
		return isDown;
	}

	public void setIsDown(String isDown) {
		this.isDown = isDown;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ott other = (Ott) obj;
		return number == other.number && Objects.equals(title, other.title);
	}

	@Override
	public int compareTo(Ott o) {
		// TODO Auto-generated method stub
		return this.viewer-o.viewer;
	}

}
