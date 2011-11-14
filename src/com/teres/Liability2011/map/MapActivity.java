package com.teres.Liability2011.map;

import com.teres.Liability2011.R;
import com.teres.Liability2011.R.layout;
import com.teres.Liability2011.map.CreateDialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;

/************************************************
 * This source is Liability2011 MapActivity.java
 * 
 * Creator is HK
 */

public class MapActivity extends Activity{
	/** Called when the activity is first created. */
	private final int FP = ViewGroup.LayoutParams.FILL_PARENT; 
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT; 
	private String Sjson;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.Sjson = getIntent().getStringExtra(getString(R.string.json));
		
		//WebViewの宣言
		WebView webview = new WebView(this);
		webview.getSettings().setBuiltInZoomControls(true); 
		setContentView(webview, new LayoutParams(WC, WC));
		
		// javascript有効化
		webview.getSettings().setJavaScriptEnabled(true);
		
		// アラートダイアログを表示するjavascript interfaceを追加
		// 第一引数がオブジェクトで、第二引数がjavascriptから呼び出すときの名前
		webview.addJavascriptInterface(new CreateDialog(this.getApplicationContext(), this.Sjson), "dlg");
		
		// assetのindex.htmlを読み込み
		webview.loadUrl("file:///android_asset/map.html");
		
		}
	}