package com.teres.Liability2011.index;

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
				if(position == 0){
					intent.putExtra("KIND", KIND.Roten.toString());
				} else if(position == 1){
					intent.putExtra("KIND", KIND.Shitsunai.toString());
				} else if(position ==2){
					intent.putExtra("KIND", KIND.TimeTable.toString());
				}
				startActivity(intent);
			}
		});
	}
	
	private ArrayAdapter<String> addAdapter() {
		// TODO Auto-generated method stub
		ArrayAdapter<String> indexes = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
		indexes.add("露店");
		indexes.add("室内展示");
		indexes.add("ステージ");
		return indexes;
	}
	private void getMyReseouces() {
		// TODO Auto-generated method stub
		indexList = (ListView) findViewById(R.id.index_listview);
	}

}
