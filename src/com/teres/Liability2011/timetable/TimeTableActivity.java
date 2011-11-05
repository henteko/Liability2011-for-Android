package com.teres.Liability2011.timetable;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.teres.Liability2011.R;

/************************************************
 * This source is Liability2011 TimeTableActivity.java
 * 
 * Creator is numa
 */

public class TimeTableActivity extends Activity {
	/** Called when the activity is first created. */
	private static final String TAG = TimeTableActivity.class.getSimpleName();
	ListView timetableList;
	int FIRST_DAY;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable);

		// リソースファイルを取得する
		getMyResources();

		// リストビューに値をセットする
		ArrayAdapter<String> days = addAdapter();
		timetableList.setAdapter(days);

		// リストビューがクリックされた時のコールバックを設定する
		setOnClickCallback(timetableList);
	}

	private void setOnClickCallback(ListView timetableList) {
		// TODO Auto-generated method stub
		timetableList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						// ListView listView = (ListView) parent;
						Intent intent = new Intent(TimeTableActivity.this,
								TimeTableTabActivity.class);
						int day = 0;
						if (position == 0) {
							day = FIRST_DAY;
							intent.putExtra("DATE", day);
							Log.d(TAG, "day = " + day);
						} else if (position == 1) {
							day = FIRST_DAY + 1;
							intent.putExtra("DATE", day);
							Log.d(TAG, "day = " + day);
						} else if (position == 2) {
							day = FIRST_DAY + 2;
							intent.putExtra("DATE", day);
							Log.d(TAG, "day = " + day);
						}
						Log.d(TAG, "and day = " + day);
						startActivity(intent);
					}
				});
	}

	private ArrayAdapter<String> addAdapter() {
		ArrayList<String> days = new ArrayList<String>();
		days.add(FIRST_DAY + "日");
		days.add(FIRST_DAY + 1 + "日");
		days.add(FIRST_DAY + 2 + "日");
		return new ArrayAdapter<String>(this, R.layout.timetable_and_index_low, R.id.title, days);
	}

	private void getMyResources() {
		// TODO Auto-generated method stub
		timetableList = (ListView) findViewById(R.id.timetable_listview);
		FIRST_DAY = Integer.parseInt(getString(R.string.first_day));
		Log.d(TAG, FIRST_DAY + "");
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
	}
	
}
