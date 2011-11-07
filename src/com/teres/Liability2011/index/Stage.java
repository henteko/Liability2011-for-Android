package com.teres.Liability2011.index;

public class Stage {
	//private static final String TAG = Stage.class.getSimpleName();
	//場所番号と催事場の対応表のクラスです。
	
	public static String getStage(int field){
		switch(field){
		case 1:
			return "青空劇場";
		case 2:
			return "C棟前ステージ";
		case 3:
			return "その他";
		default:
			return null;
		}
	}
}
