package com.teres.Liability2011;

/************************************************
 * This source is Liability2011 MainActivity.java
 * 
 * Creator is henteko
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int MENU_ADD_ID = Menu.FIRST;

	private boolean menu_flag = true;

	private JSONObject rootJSON;
	private String Sjson;

	private final Handler handler = new Handler();
	
	private ProgressDialog dlg = null;
	
	private TextView toolbar_text;
	
	private final int H_year = 2011;
	private final int H_month = 10;
	private final int H_day[] = {18,19,20};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//タイトルバーの削除
		//参考:http://www.adakoda.com/android/000155.html
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.main);

		// jsonをアップデート
		do_Updata();
		
		
		ImageButton Up_dataButton = (ImageButton) findViewById(R.id.Up_dataButton);
		Up_dataButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				//DBのアップデート処理実行
				do_Updata();
			}
		});

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

//	// メニューの定義
//	@Override
//	public boolean onPrepareOptionsMenu(Menu menu) {
//		super.onCreateOptionsMenu(menu);
//
//		if (menu_flag) {
//
//			MenuItem add = menu.add(0, MENU_ADD_ID, 0, "更新する");
//			menu_flag = false;
//		}
//
//		return true;
//	}
//
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case MENU_ADD_ID:
//			// jsonの更新処理
//			Updata();
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
	
	@Override
    protected void onStart() {
    	super.onStart();
    	
    	//日付チェック
		toolbar_text = (TextView) findViewById(R.id.toolbar_text);
		day_Check();
    	
    }
	
	/**
	 * 2つの日付の差を求めます。
	 * 日付文字列 strDate1 - strDate2 が何日かを返します。
	 * 
	 * @param strDate1    日付文字列 yyyy/MM/dd
	 * @param strDate2    日付文字列 yyyy/MM/dd
	 * @return    2つの日付の差
	 * @throws ParseException 日付フォーマットが不正な場合
	 */
	public static int differenceDays(String strDate1,String strDate2) 
	    throws ParseException {
	    Date date1 = DateFormat.getDateInstance().parse(strDate1);
	    Date date2 = DateFormat.getDateInstance().parse(strDate2);
	    return differenceDays(date1,date2);
	}
	/**
	 * 2つの日付の差を求めます。
	 * java.util.Date 型の日付 date1 - date2 が何日かを返します。
	 * 
	 * 計算方法は以下となります。
	 * 1.最初に2つの日付を long 値に変換します。
	 * 　※この long 値は 1970 年 1 月 1 日 00:00:00 GMT からの
	 * 　経過ミリ秒数となります。
	 * 2.次にその差を求めます。
	 * 3.上記の計算で出た数量を 1 日の時間で割ることで
	 * 　日付の差を求めることができます。
	 * 　※1 日 ( 24 時間) は、86,400,000 ミリ秒です。
	 * 
	 * @param date1    日付 java.util.Date
	 * @param date2    日付 java.util.Date
	 * @return    2つの日付の差
	 */
	public static int differenceDays(Date date1,Date date2) {
	    long datetime1 = date1.getTime();
	    long datetime2 = date2.getTime();
	    long one_date_time = 1000 * 60 * 60 * 24;
	    long diffDays = (datetime1 - datetime2) / one_date_time;
	    return (int)diffDays; 
	}

	
	
	public void day_Check() {
		final Calendar calendar = Calendar.getInstance();

		//年
		final int year = calendar.get(Calendar.YEAR);
		//月(+1しろ)
		final int month = calendar.get(Calendar.MONTH);
		//日
		final int day = calendar.get(Calendar.DAY_OF_MONTH);
		//時
		final int hour = calendar.get(Calendar.HOUR_OF_DAY);
		//分
		final int minute = calendar.get(Calendar.MINUTE);
		//秒
		final int second = calendar.get(Calendar.SECOND);
		//ミリ
		final int ms = calendar.get(Calendar.MILLISECOND);
		
		boolean flag = false;
		if(H_year == year && H_month == month) {
			for(int i=0;i<H_day.length;i++) {
				if(H_day[i] == day) {
					//当日の場合
					toolbar_text.setText("調布祭2011当日" + i+1 + "日目");
					flag = true;
					break;
				}
			}
		}
		
		if(!flag) {
			String Hs = "" + H_year + "/" + H_month + "/" + H_day[0];
			String Ns = "" + year + "/" + month + "/" + day;
			
			try {
				int ret = differenceDays(Hs,Ns);
				if(ret < 0) {
					//マイナスの時、最終日から数えさせる
					Hs = "" + H_year + "/" + H_month + "/" + H_day[H_day.length - 1];
					ret = differenceDays(Hs,Ns);
				}
				toolbar_text.setText("調布祭2011まであと" + ret + "日です");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("aaaaaaaaaaaaaaaaa","error" + e);
			}
		}
		
//		if(H_year < year) {
//			int num = year - H_year;
//			toolbar_text.setText("調布祭2011まであと" + num + "年です");
//		}else if(H_year > year){
//			int num = H_year - year;
//			toolbar_text.setText("調布祭2011から" + num + "年たちました");
//		}else {
//			//年がイコールの時
//			if(H_month > month) {
//				int num = H_month - month;
//				toolbar_text.setText("調布祭2011まであと" + num + "ヶ月です");
//			}else if(H_month < month) {
//				int num = month - H_month;
//				toolbar_text.setText("調布祭2011から" + num + "ヶ月たちました");
//			}else {
//				//月も一緒だった時
//				
//				if(H_day[0] > day) {
//					int num = H_day[0] - day;
//					toolbar_text.setText("調布祭2011まであと" + num + "日です");
//				}else if(H_day[H_day.length - 1] < day){
//					int num = day - H_day[H_day.length - 1];
//					toolbar_text.setText("調布祭2011から" + num + "日たちました");
//				}else {
//					for(int i=0;i<H_day.length;i++) {
//						if(H_day[i] == day) {
//							//当日の場合
//							toolbar_text.setText("調布祭2011当日" + i+1 + "日目");
//							break;
//						}
//					}
//				}
//			}
//		}
	}
	
	public void do_Updata() {
		//ネット接続の可否を判定
		//参考:http://dayafterneet.blogspot.com/2011/09/android.html
		ConnectivityManager connectivity = (ConnectivityManager)this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo network = connectivity.getActiveNetworkInfo();
		if (network == null) {
			Toast.makeText(this.getApplicationContext(), "ネットワークに接続してください", Toast.LENGTH_LONG).show();
		} else {
			if (!network.isAvailable()) {
				Toast.makeText(this.getApplicationContext(), "ネットワークに接続してください", Toast.LENGTH_LONG).show();
			} else if (!network.isConnectedOrConnecting()) {
				Toast.makeText(this.getApplicationContext(), "ネットワークに接続してください", Toast.LENGTH_LONG).show();
			} else {
				//ネットに接続済みの時
				Updata();
			}
		}
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
					
					e.putString("sjson", Sjson);
					e.commit();

					Log.d("aaaaaaaaaaaaaaaaaaaaaa", "true");
				} else {
					
					Sjson = pref.getString("sjson", "");
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