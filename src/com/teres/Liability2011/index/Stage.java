package com.teres.Liability2011.index;

import com.teres.Liability2011.R;

public class Stage {
	//private static final String TAG = Stage.class.getSimpleName();
	//場所番号と催事場の対応表のクラスです。
	
	public static int getStage(int field){
		switch(field){
		case 1:
			return R.string.stage1; 
		case 2:
			return R.string.stage2; 
		case 3:
			return R.string.stage3; 
		default:
			return 0;
		}
	}
}
