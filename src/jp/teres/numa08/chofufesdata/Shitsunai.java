package jp.teres.numa08.chofufesdata;


public class Shitsunai extends Chofufes {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Key key;
	
	private int number;
	
	private String title;
	
	public Shitsunai() {
		// TODO Auto-generated constructor stub
	}
	
	public Shitsunai(int number, String title){
		this.number = number;
		this.title = title;
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
	
}
