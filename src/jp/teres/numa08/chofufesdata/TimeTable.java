package jp.teres.numa08.chofufesdata;

import java.util.Date;


public class TimeTable extends Chofufes{
	private Key key;
	
	private Date startDate;
	
	private Date endDate;
	
	private int field;
	
	private String title;
	
	private String description;
	
	public TimeTable() {
		// TODO Auto-generated constructor stub
	}

	public TimeTable(Date startDate, Date endDate, int field, String title, String description){
		this.startDate = startDate;
		this.endDate = endDate;
		this.field = field;
		this.title =title;
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


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getField() {
		return field;
	}

	public void setField(int field) {
		this.field = field;
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
