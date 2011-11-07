package com.teres.Liability2011.index;

public class Building {
	//private static final String TAG = Building.class.getSimpleName();
	//場所番号と建物の対応表を保存するクラスです
	
	public static String getBuilding(int buildingNumber){
		String buildingName = null;
		switch(buildingNumber){
		case 1:
			buildingName = "A棟";
			break;
		case 2:
			buildingName = "B棟";
			break;
		case 3:
			buildingName = "C棟";
			break;
		default:
			break;
		}
		return buildingName;
	}
}
