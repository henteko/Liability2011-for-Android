package com.teres.Liability2011.index;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.TimeZone;

import jp.teres.numa08.chofufesdata.ChofufesData;
import jp.teres.numa08.chofufesdata.Roten;
import jp.teres.numa08.chofufesdata.Shitsunai;
import jp.teres.numa08.chofufesdata.TimeTable;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.teres.Liability2011.LoadJson;
import com.teres.Liability2011.R;

public class IndexesActivity extends Activity {
	private static final String TAG = IndexesActivity.class.getSimpleName();
	private ListView indexListView;
	private String kindIndex;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    // TODO Auto-generated method stub
	    setContentView(R.layout.index);
	    //リソースを取得する
	    getMyResources();
	    
	    //リストビューに値をセットする
	    //Kindの値によって、セットする値は異なる
	    try {
			IndexAdapter adapter = addAdapter(kindIndex);
			indexListView.setAdapter(adapter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private IndexAdapter addAdapter(String kindIndex) throws Exception {
		// TODO Auto-generated method stub
		IndexAdapter adapter;
		ArrayList<Index> indexList = new ArrayList<Index>();
		ChofufesData chofufesData = LoadJson.loadByJson(getAssets());
		//indexListに項目を追加する。IndexActivityから渡されたKINDの種類で判断して
		//どの項目を渡すのかを選択する
		if(kindIndex.equals(KIND.Roten.toString())){
			ArrayList<Roten> rotenList = chofufesData.getRotenList();
			Collections.sort(rotenList, new Comparator<Roten>(){
				@Override
				public int compare(Roten arg0, Roten arg1) {
					// TODO Auto-generated method stub
					return arg0.getTitle().compareTo(arg1.getTitle());
				}
			});
			for(Roten roten : rotenList){
				indexList.add(new Index(roten.getNumber() + "", roten.getTitle(), roten.getDescription()));
			}
		} else if (kindIndex.equals(KIND.Shitsunai.toString())){
			ArrayList<Shitsunai> shitsunaiList = chofufesData.getShitsunaiList();
			Collections.sort(shitsunaiList, new Comparator<Shitsunai>(){

				@Override
				public int compare(Shitsunai arg0, Shitsunai arg1) {
					// TODO Auto-generated method stub
					return arg0.getTitle().compareTo(arg1.getTitle());
				}
				
			});
			for(Shitsunai shitsunai : shitsunaiList){
				String building = Building.getBuilding(shitsunai.getNumber() / 1000);
				int room = shitsunai.getNumber() % 1000;
				indexList.add(new Index(building + room, shitsunai.getTitle(), ""));
			}
		} else if (kindIndex.equals(KIND.TimeTable.toString())){
			ArrayList<TimeTable> timeTableList = chofufesData.getAozoraList();
			Collections.sort(timeTableList, new Comparator<TimeTable>(){

				@Override
				public int compare(TimeTable arg0, TimeTable arg1) {
					// TODO Auto-generated method stub
					return arg0.getTitle().compareTo(arg1.getTitle());
				}
				
			});
			for(TimeTable timeTable : timeTableList){
				String date = setTime(timeTable); 
				String stage = Stage.getStage(timeTable.getField());
				indexList.add(new Index(date + "@" + stage, timeTable.getTitle(), timeTable.getDescription()));
			}
		}
		adapter = new IndexAdapter(this, 0, indexList);
		return adapter;
	}
/*	private ChofufesData loadByJson() throws Exception {
		// TODO Auto-generated method stub
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
	private void getMyResources() {
		// TODO Auto-generated method stub
		indexListView = (ListView) findViewById(R.id.index_listview);
		kindIndex = getDataByIntent();
	}
	private String getDataByIntent() {
		// TODO Auto-generated method stub
		Bundle extras = getIntent().getExtras();
		String kind = "";
		if(extras != null){
			kind = extras.getString("KIND");
		}
		return kind;
	}
	
	private String setTime(TimeTable data) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
		cal.setTimeInMillis(data.getStartDate());
		String startTime = getTime(cal);
		cal.setTimeInMillis(data.getEndDate());
		String endTime = getTime(cal);
		return startTime + "～" + endTime;
	}

	private String getTime(Calendar cal) {
		// TODO Auto-generated method stub
		int d = cal.get(Calendar.DATE);
		int h = cal.get(Calendar.HOUR_OF_DAY);
		int m = cal.get(Calendar.MINUTE);
		String hour, minute;
		if(h < 10){
			hour = "0" + h;
		} else {
			hour = "" + h;
		}
		if(m < 10){
			minute = "0" + m;
		} else {
			minute = "" + m;
		}
		return d + "日" + hour + ":" + minute;
	}
}