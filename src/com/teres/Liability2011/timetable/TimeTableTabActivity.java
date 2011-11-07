package com.teres.Liability2011.timetable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import jp.teres.numa08.chofufesdata.ChofufesData;
import jp.teres.numa08.chofufesdata.TimeTable;
import android.app.TabActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.teres.Liability2011.LoadJson;
import com.teres.Liability2011.R;

public class TimeTableTabActivity extends TabActivity {
	// private static final String TAG = TimeTableTabActivity.class
	// .getSimpleName();
	int DATE;
	private String Sjson;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		setContentView(R.layout.timetable_tablayout);
		// リソースを取得する。
		getMyResources();
		
		// タブの設定
		TabHost tabHost = getTabHost();
		addTabs(tabHost);
		// JSONからの読み込み
		ChofufesData chofufesData;
		try {
			chofufesData = LoadJson.loadByJson(this.Sjson);
			setContent(chofufesData, tabHost);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "データを読み込めません", Toast.LENGTH_SHORT).show();
			this.finish();
		}
	}

	private void getMyResources() {
		// TODO Auto-generated method stub
		this.DATE = getIntent().getIntExtra("DATE", 0);
		this.Sjson = getIntent().getStringExtra(getString(R.string.json));
	}

	private void setContent(ChofufesData chofufesData, TabHost tabHost) {
		// TODO Auto-generated method stub
		setTab(chofufesData, R.id.aozora_list, 1);
		/*
		 * ListView aozoraList = (ListView) findViewById(R.id.aozora_list);
		 * TimeTableAdapter adapter = new TimeTableAdapter(this,0,
		 * chofufesData.getAozoraList()); aozoraList.setAdapter(adapter);
		 */
	}

	private void setTab(ChofufesData chofufesData, int id, int field) {
		// TODO Auto-generated method stub
		ListView aozoraList = (ListView) findViewById(id);
		ArrayList<TimeTable> timeTableList = new ArrayList<TimeTable>();
		for (TimeTable timeTable : chofufesData.getAozoraList()) {
			Calendar cal = Calendar.getInstance(TimeZone
					.getTimeZone("Asia/Tokyo"));
			cal.setTimeInMillis(timeTable.getStartDate());
			int date = cal.get(Calendar.DATE);
			if (date == this.DATE && timeTable.getField() == field) {
				timeTableList.add(timeTable);
			}
		}
		TimeTableAdapter adapter = new TimeTableAdapter(this, 0, timeTableList);
		aozoraList.setAdapter(adapter);
		// setOnClickListener(aozoraList);
	}

	/*
	 * private ChofufesData loadDataByJson() throws Exception { // TODO
	 * Auto-generated method stub //ファイル読み込み AssetManager assetManager =
	 * getResources().getAssets(); InputStream inputStream =
	 * assetManager.open("json"); BufferedReader bufferedReader = new
	 * BufferedReader(new InputStreamReader(inputStream, "UTF-8")); String
	 * jsonString = bufferedReader.readLine();
	 * 
	 * //JSON解析 Log.d(TAG, "parse start"); ChofufesData chofufesData =
	 * JSON.decode(jsonString, ChofufesData.class); Log.d(TAG, "parse end");
	 * bufferedReader.close(); return chofufesData; }
	 */

	/*
	 * private void setOnClickListener(ListView aozoraList) { // TODO
	 * Auto-generated method stub aozoraList.setOnItemClickListener(new
	 * OnItemClickListener(){
	 * 
	 * @Override public void onItemClick(AdapterView<?> parent, View view, int
	 * position, long id) { // TODO Auto-generated method stub ListView listView
	 * = (ListView) parent; TimeTable clickedItem = (TimeTable)
	 * listView.getItemAtPosition(position); }
	 * 
	 * }); }
	 */

	private void addTabs(TabHost tabHost) {
		// TODO Auto-generated method stub
		TabSpec firstTab = tabHost.newTabSpec("FirstTab").setIndicator(new CustomTabContentView(this, getString(R.string.stage1), CustomTabContentView.LEFT))
				.setContent(R.id.first_tab);
		tabHost.addTab(firstTab);
		TabSpec secontTab = tabHost.newTabSpec("SecondTab")
				.setIndicator(new CustomTabContentView(this, getString(R.string.stage2), CustomTabContentView.CENTER)).setContent(R.id.secont_tab);
		tabHost.addTab(secontTab);
		TabSpec thirdTab = tabHost.newTabSpec("ThirdTab")
				.setIndicator(new CustomTabContentView(this, getString(R.string.stage3), CustomTabContentView.RIGHT)).setContent(R.id.third_tab);
		tabHost.addTab(thirdTab);
	}
	
	public class CustomTabContentView extends FrameLayout{
		LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
		final static int NONE = 0;
		final static int CENTER = 1;
		final static int LEFT = 2;
		final static int RIGHT = 3;
		
		public CustomTabContentView(Context context, String title){
			this(context, title, CENTER);
		}
		
		public CustomTabContentView(Context context, String title, int position){
			super(context);
			View view = inflater.inflate(R.layout.custom_tab_widget, this);
			((TextView) view.findViewById(R.id.tab_text)).setText(title);
			
			switch(position){
			case LEFT:
				setPadding(0, 0, 2, 0);
				break;
			case RIGHT:
				setPadding(2, 0, 0, 0);
				break;
			case CENTER:
				setPadding(2, 0, 2, 0);
				break;
			default:
				break;
			}
		}
	}
}
