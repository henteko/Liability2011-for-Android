package jp.teres.numa08.chofufesdata;

public class Roten extends Chofufes {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Key key;
	
	private int number;
	
	private String title;
	
	private String description;
	
	public Roten() {
		// TODO Auto-generated constructor stub
	}
	
	public Roten(int number, String title, String description){
		this.number = number;
		this.title = title;
		this.description = description;
	}

	@Override
	public Key getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}

	@Override
	public void setKey(Key key) {
		// TODO Auto-generated method stub
		this.key = key;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
