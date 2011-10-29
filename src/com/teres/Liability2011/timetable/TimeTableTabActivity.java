package com.teres.Liability2011.timetable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import jp.teres.numa08.chofufesdata.ChofufesData;
import jp.teres.numa08.chofufesdata.TimeTable;
import android.app.TabActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.teres.Liability2011.LoadJson;
import com.teres.Liability2011.R;

public class TimeTableTabActivity extends TabActivity {
	private static final String TAG = TimeTableTabActivity.class
			.getSimpleName();
	int DATE;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    // TODO Auto-generated method stub
	    setContentView(R.layout.timetable_tablayout);
	    this.DATE = getDateByIntent();
	}

	private int getDateByIntent() {
		// TODO Auto-generated method stub
		Bundle extras = getIntent().getExtras();
		int date = 0;
		if(extras != null){
			date = extras.getInt("DATE");
		}
		return date;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		TabHost tabHost = getTabHost();
		addTabs(tabHost);
		
		//テスト用
		ChofufesData chofufesData = null;
		try {
			//chofufesData = loadDataByJson();
			chofufesData = LoadJson.loadByJson(getAssets());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setContent(chofufesData, tabHost);
	}

	private void setContent(ChofufesData chofufesData, TabHost tabHost) {
		// TODO Auto-generated method stub
		setTab(chofufesData, R.id.aozora_list, 1);
/*		ListView aozoraList = (ListView) findViewById(R.id.aozora_list);
		TimeTableAdapter adapter = new TimeTableAdapter(this,0, chofufesData.getAozoraList());
		aozoraList.setAdapter(adapter);*/
	}

	private void setTab(ChofufesData chofufesData, int id, int field){
		// TODO Auto-generated method stub
		ListView aozoraList = (ListView) findViewById(id);
		ArrayList<TimeTable> timeTableList = new ArrayList<TimeTable>();
		for(TimeTable timeTable : chofufesData.getAozoraList()){
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
			cal.setTimeInMillis(timeTable.getStartDate());
			int date = cal.get(Calendar.DATE);
			Log.d(TAG, date + "");
			if(date == this.DATE && timeTable.getField() == field){
				timeTableList.add(timeTable);
			}
		}
		Log.d(TAG, this.DATE + "");
		TimeTableAdapter adapter = new TimeTableAdapter(this, 0, timeTableList);
		aozoraList.setAdapter(adapter);
		setOnClickListener(aozoraList);
	}

/*	private ChofufesData loadDataByJson() throws Exception {
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
	}*/

	private void setOnClickListener(ListView aozoraList) {
		// TODO Auto-generated method stub
		aozoraList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				ListView listView = (ListView) parent;
				TimeTable clickedItem = (TimeTable) listView.getItemAtPosition(position);
			}
			
		});
	}

	private void addTabs(TabHost tabHost) {
		// TODO Auto-generated method stub
		TabSpec firstTab = tabHost.newTabSpec("FirstTab").setIndicator("青空劇場").setContent(R.id.first_tab);
		tabHost.addTab(firstTab);
		TabSpec secontTab = tabHost.newTabSpec("SecondTab").setIndicator("secondTab").setContent(R.id.secont_tab);
		tabHost.addTab(secontTab);
		TabSpec thirdTab = tabHost.newTabSpec("ThirdTab").setIndicator("thirdTab").setContent(R.id.third_tab);
		tabHost.addTab(thirdTab);
	}
}
