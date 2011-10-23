package com.teres.Liability2011;

/************************************************
 * This source is Liability2011 MainActivity.java
 * 
 * Creator is henteko
 */

import org.json.JSONObject;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int MENU_ADD_ID = Menu.FIRST;

	private boolean menu_flag = true;

	private JSONObject rootJSON;
	private String Sjson;

	private final Handler handler = new Handler();
	
	private ProgressDialog dlg = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// jsonをアップデート
		Updata();

		Button Indexbutton = (Button) findViewById(R.id.IndexButton);
		Indexbutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,
						IndexActivity.class);
				intent.putExtra(getString(R.string.json),Sjson);
//				MainActivity.this.startActivityForResult(intent, 0);
				startActivity(intent);
			}
		});

		Button Mapbutton = (Button) findViewById(R.id.MapButton);
		Mapbutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, MapActivity.class);
				intent.putExtra(getString(R.string.json),Sjson);
//				MainActivity.this.startActivityForResult(intent, 0);
				startActivity(intent);
			}
		});

		Button TimeTablebutton = (Button) findViewById(R.id.TimeTableButton);
		TimeTablebutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,
						TimeTableActivity.class);
//				 MainActivity.this.startActivityForResult(intent, 0);
				intent.putExtra(getString(R.string.json),Sjson);
				startActivity(intent);
			}
		});

	}

	// メニューの定義
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		if (menu_flag) {

			MenuItem add = menu.add(0, MENU_ADD_ID, 0, "更新する");
			menu_flag = false;
		}

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ADD_ID:
			// jsonの更新処理
			Updata();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void Updata() {
		final SharedPreferences pref = getSharedPreferences("pref",
				MODE_WORLD_READABLE | MODE_PRIVATE);
		final int log_id = pref.getInt("key", 0);

		dlg = new ProgressDialog(this);
		dlg.setTitle("DB更新確認中……");
		dlg.setMessage("しばらくお待ちください.");
		dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);

		dlg.show();
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				//重い処理をここに書く
				//参照:http://d.hatena.ne.jp/sy-2010/20100125/1264391774
				
				DJson dJson = new DJson();
				final int id = dJson.get_id();
				if (id != log_id) {

					dJson.set_URL("http://liability2011.appspot.com/liability2011test?command=Load_data&id="
							+ id);
					rootJSON = dJson.get_Date();

					Sjson = rootJSON.toString();

					Editor e = pref.edit();
					e.putInt("key", id);
					e.commit();

					Log.d("aaaaaaaaaaaaaaaaaaaaaa", "true");
				} else {
					Log.d("aaaaaaaaaaaaaaaaaaaaaaa", "false");
				}
				
				
				handler.post(new Runnable() {
					public void run() {
						
						if (id != log_id) {
							Toast.makeText(MainActivity.this,
									"DBの更新が終わりました", Toast.LENGTH_SHORT).show();
							
						} else {
							Toast.makeText(MainActivity.this,
									"DBは最新の状態です", Toast.LENGTH_SHORT).show();
						}

						dlg.dismiss();
					}
				});

			}

		}).start();
	}

}