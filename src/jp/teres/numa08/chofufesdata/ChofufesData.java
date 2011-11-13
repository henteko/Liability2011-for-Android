package jp.teres.numa08.chofufesdata;

import java.util.ArrayList;

public class ChofufesData {
	private ArrayList<TimeTable> timeTableList;
	private ArrayList<Roten> rotenList;
	private ArrayList<Shitsunai> shitsunaiList;
	private int Update_Num;
	
	public ChofufesData() {
		// TODO Auto-generated constructor stub
	}
	public ChofufesData(ArrayList<TimeTable> aozoraList, ArrayList<Roten> rotenList, ArrayList<Shitsunai> shitsunaiList, int Update_Num){
		this.timeTableList = aozoraList;
		this.rotenList = rotenList;
		this.shitsunaiList = shitsunaiList;
		this.Update_Num = Update_Num;
	}

	public ArrayList<TimeTable> getAozoraList() {
		return timeTableList;
	}

	public void setAozoraList(ArrayList<TimeTable> aozoraList) {
		this.timeTableList = aozoraList;
	}

	public ArrayList<Roten> getRotenList() {
		return rotenList;
	}

	public void setRotenList(ArrayList<Roten> rotenList) {
		this.rotenList = rotenList;
	}

	public ArrayList<Shitsunai> getShitsunaiList() {
		return shitsunaiList;
	}

	public void setShitsunaiList(ArrayList<Shitsunai> shitsunaiList) {
		this.shitsunaiList = shitsunaiList;
	}

	public int getUpdate_Num() {
		return Update_Num;
	}

	public void setUpdate_Num(int update_Num) {
		Update_Num = update_Num;
	}

}
