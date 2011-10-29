package com.teres.Liability2011;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import jp.teres.numa08.chofufesdata.ChofufesData;
import net.arnx.jsonic.JSON;
import android.content.res.AssetManager;
import android.util.Log;

public class LoadJson {
	private static final String TAG = LoadJson.class.getSimpleName();
	
	public static ChofufesData loadByJson(AssetManager assetManager) throws Exception{
		InputStream inputStream = assetManager.open("json");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		String jsonString = bufferedReader.readLine();
		
		//JSON解析
		Log.d(TAG, "parse start");
		ChofufesData chofufesData = JSON.decode(jsonString, ChofufesData.class);
		Log.d(TAG, "parse end");
		bufferedReader.close();
		return chofufesData;
	}
	
	public static ChofufesData loadByJson(AssetManager assetManager, String fileName) throws Exception{
		InputStream inputStream = assetManager.open(fileName);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		String jsonString = bufferedReader.readLine();
		
		//JSON解析
		Log.d(TAG, "parse start");
		ChofufesData chofufesData = JSON.decode(jsonString, ChofufesData.class);
		Log.d(TAG, "parse end");
		bufferedReader.close();
		return chofufesData;
	}
}
