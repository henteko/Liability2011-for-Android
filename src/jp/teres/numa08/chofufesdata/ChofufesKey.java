package jp.teres.numa08.chofufesdata;

import java.util.ArrayList;

public class ChofufesKey {
	private Long id;
	
	private ArrayList<Key> aozoraKeyList;
	
	private ArrayList<Key> rotenKeyList;
	
	private ArrayList<Key> shitsunaiKeyList;
	
	private int UpdateNum;
	
	public ChofufesKey(ArrayList<Key> aozoraKeyList, ArrayList<Key> rotenKeyList, ArrayList<Key> shitsunaiKeyList, int UpdateNum){
		this.aozoraKeyList = aozoraKeyList;
		this.rotenKeyList = rotenKeyList;
		this.shitsunaiKeyList = shitsunaiKeyList;
		this.UpdateNum = UpdateNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<Key> getAozoraKeyList() {
		return aozoraKeyList;
	}

	public void setAozoraKeyList(ArrayList<Key> aozoraKeyList) {
		this.aozoraKeyList = aozoraKeyList;
	}

	public ArrayList<Key> getRotenKeyList() {
		return rotenKeyList;
	}

	public void setRotenKeyList(ArrayList<Key> rotenKeyList) {
		this.rotenKeyList = rotenKeyList;
	}

	public ArrayList<Key> getShitsunaiKeyList() {
		return shitsunaiKeyList;
	}

	public void setShitsunaiKeyList(ArrayList<Key> shitsunaiKeyList) {
		this.shitsunaiKeyList = shitsunaiKeyList;
	}

	public int getUpdateNum() {
		return UpdateNum;
	}

	public void setUpdateNum(int updateNum) {
		UpdateNum = updateNum;
	}

}
