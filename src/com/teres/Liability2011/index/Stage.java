package com.teres.Liability2011.index;

public class Stage {
	private static final String TAG = Stage.class.getSimpleName();
	
	public static String getStage(int field){
		switch(field){
		case 1:
			return "青空劇場";
		case 2:
			return "C塔前ステージ";
		case 3:
			return "その他";
		default:
			return null;
		}
	}
}
