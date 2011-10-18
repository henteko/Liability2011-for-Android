package com.teres.Liability2011;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonParse {
	private JSONObject rootJSON;
	
	public JsonParse(JSONObject rootJSON) {
		this.rootJSON = rootJSON;
	}
	
	
	public ArrayList<String>[] get_Aogeki() {
        JSONArray aozoraArray;
		try {
			aozoraArray = rootJSON.getJSONArray("aozoraList");
			ArrayList<String>[] list = new ArrayList[aozoraArray.length()];
			for (int i = 0; i < aozoraArray.length(); i++) {
				
				list[i] = new ArrayList<String>();
				
			    JSONObject jsonObject = aozoraArray.getJSONObject(i);
			    Log.d("JSON", jsonObject.getString("description"));
			    list[i].add(jsonObject.getString("description"));
			    list[i].add(jsonObject.getString("endDate"));
			    list[i].add(jsonObject.getString("field"));
			    
			    JSONArray keyArray = jsonObject.getJSONArray("key");
			    for(int j=0;j<keyArray.length();j++) {
			    	JSONObject keyjsonObject = keyArray.getJSONObject(j);
			    	
			    	list[i].add(keyjsonObject.getString("complete"));
				    list[i].add(keyjsonObject.getString("id"));
				    list[i].add(keyjsonObject.getString("kind"));
				    list[i].add(keyjsonObject.getString("name"));
				    list[i].add(keyjsonObject.getString("namespace"));
				    list[i].add(keyjsonObject.getString("parent"));
			    }
			    
			    
			    list[i].add(jsonObject.getString("startDate"));
			    list[i].add(jsonObject.getString("title"));
			    
			}
			
			
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
}
