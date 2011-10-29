package com.teres.Liability2011.index;

public enum KIND {
	Roten("Roten"),
	Shitsunai("Shitsunai"),
	TimeTable("TimeTable"),;
	
	private String kind;
	
	KIND(String kind){
		this.kind = kind;
	}
	
	public String toString(){
		return this.kind;
	}
}
