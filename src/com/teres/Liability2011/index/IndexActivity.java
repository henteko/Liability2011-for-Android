package com.teres.Liability2011.index;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.teres.Liability2011.R;

/************************************************
 * This source is Liability2011 IndexActivity.java
 * 
 * Creator is numa
 */

public class IndexActivity extends Activity{
	/** Called when the activity is first created. */
	ListView indexList;
	private String Sjson;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        
        //リソースファイルを取得する
        getMyReseouces();
        
        //リストビューに値をセットする
        ArrayAdapter<String> indexes = addAdapter();
        indexList.setAdapter(indexes);
        
        //リストビューがクリックされた時のコールバックを設定する
        setOnClicCallback(indexList);
    }
    
	private void setOnClicCallback(ListView indexList) {
		// TODO Auto-generated method stub
		indexList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(IndexActivity.this, IndexesActivity.class);
				//クリックされた項目を判定し、項目名（露店、室内展示、ステージのいずれか）を次のアクティビティに渡す
				if(position == 0){
					intent.putExtra("KIND", getResources().getStringArray(R.array.kind)[0]);
				} else if(position == 1){
					intent.putExtra("KIND", getResources().getStringArray(R.array.kind)[1]);
				} else if(position ==2){
					intent.putExtra("KIND", getResources().getStringArray(R.array.kind)[2]);
				}
				intent.putExtra(getString(R.string.json), Sjson);
				startActivity(intent);
			}
		});
	}
	
	private ArrayAdapter<String> addAdapter() {
		// TODO Auto-generated method stub
		ArrayList<String> indexes = new ArrayList<String>();
		indexes.add("露店");
		indexes.add("室内展示");
		indexes.add("ステージ");
		return new ArrayAdapter<String>(this, R.layout.timetable_and_index_low, R.id.title, indexes);
	}
	private void getMyReseouces() {
		// TODO Auto-generated method stub
		indexList = (ListView) findViewById(R.id.index_listview);
		this.Sjson = getIntent().getStringExtra(getString(R.string.json));
	}

}
