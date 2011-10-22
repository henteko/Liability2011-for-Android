package com.teres.Liability2011;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import jp.teres.numa08.chofufesdata.ChofufesData;
import jp.teres.numa08.chofufesdata.TimeTable;
import net.arnx.jsonic.JSON;
import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/************************************************
 * This source is Liability2011 TimeTableActivity.java
 * 
 * Creator is numa
 */

public class TimeTableActivity extends Activity{
	/** Called when the activity is first created. */
	private static final String TAG = TimeTableActivity.class.getSimpleName();
	
	TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        textView = new TextView(this);
        setContentView(textView);
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		//動作テスト用
		try {
			//JSONファイルを読み込み、ChofufesDataインスタンスを発行する
			Log.d(TAG, "load start");
			ChofufesData chofufesData = loadDataByJson();
			Log.d(TAG, "load end");
			//作成したインスタンスから、データを取り出し画面に出力する
			viewTest(chofufesData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getErrorMessage(e);
		}
		
	}

	private void getErrorMessage(Exception e) {
		// TODO Auto-generated method stub
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		String message = stringWriter.toString();
		
		Log.e(TAG, message);
	}

	private void viewTest(ChofufesData chofufesData) {
		// TODO Auto-generated method stub
		String title = null;
		for(TimeTable timeTable : chofufesData.getAozoraList()){
			title += timeTable.getTitle() + ",";
		}
		textView.setText(title);
	}

	private ChofufesData loadDataByJson() throws Exception {
		// TODO Auto-generated method stub
		//ファイル読み込み
		AssetManager assetManager = getResources().getAssets();
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

}
