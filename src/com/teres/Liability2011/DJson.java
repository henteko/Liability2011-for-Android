package com.teres.Liability2011;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class DJson {
	private JSONObject rootObject = null;
	private String URL;

	public DJson() {
	}

	private void Down_Json() {
		HttpClient httpClient = new DefaultHttpClient();

		StringBuilder uri = new StringBuilder(URL);
		HttpGet request = new HttpGet(uri.toString());
		HttpResponse httpResponse = null;

		try {
			httpResponse = httpClient.execute(request);
		} catch (Exception e) {
			Log.d("JSON", "Error Execute");
			return;
		}

		int status = httpResponse.getStatusLine().getStatusCode();

		if (HttpStatus.SC_OK == status) {
			try {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				httpResponse.getEntity().writeTo(outputStream);
				String data;
				data = outputStream.toString(); // JSONデータ

				rootObject = new JSONObject(data);

			} catch (Exception e) {
				Log.d("JSON", "Error");
			}
		}
	}

	public void set_URL(String URL) {
		this.URL = URL;
		Down_Json();
	}

	public JSONObject get_Date() {
		return rootObject;
	}
	
	
	
	public int get_id() {
		
		//idを取得する
		int id = 0;
		JSONObject idJson = null;
		HttpClient httpClient = new DefaultHttpClient();

		StringBuilder uri = new StringBuilder("http://liability2011.appspot.com/liability2011test?command=Get_key");
		HttpGet request = new HttpGet(uri.toString());
		HttpResponse httpResponse = null;

		try {
			httpResponse = httpClient.execute(request);
		} catch (Exception e) {
			Log.d("JSON", "Error Execute");
		}

		int status = httpResponse.getStatusLine().getStatusCode();

		if (HttpStatus.SC_OK == status) {
			try {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				httpResponse.getEntity().writeTo(outputStream);
				String data;
				data = outputStream.toString(); // JSONデータ

				idJson = new JSONObject(data);

			} catch (Exception e) {
				Log.d("JSON", "Error");
			}
		}
		
		
        try {
        	id= idJson.getInt("id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
		
	}

}
