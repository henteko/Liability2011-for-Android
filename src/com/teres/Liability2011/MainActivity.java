package com.teres.Liability2011;

/************************************************
 * This source is Liability2011 MainActivity.java
 * 
 * Creator is henteko
 */




import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private JSONObject rootJSON;
	private String Sjson;
	private int Log_id = 0;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        Button Indexbutton = (Button) findViewById(R.id.IndexButton);
        Indexbutton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) { 
        		Intent intent = new Intent(MainActivity.this, IndexActivity.class);
        		MainActivity.this.startActivityForResult(intent, 0);
        	}
        });
        
        Button Mapbutton = (Button) findViewById(R.id.MapButton);
        Mapbutton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) { 
        		Intent intent = new Intent(MainActivity.this, MapActivity.class);
        		MainActivity.this.startActivityForResult(intent, 0);
        	}
        });
        
        Button TimeTablebutton = (Button) findViewById(R.id.TimeTableButton);
        TimeTablebutton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) { 
        		Intent intent = new Intent(MainActivity.this, TimeTableActivity.class);
//        		MainActivity.this.startActivityForResult(intent, 0);
        		startActivity(intent);
        	}
        });
		
        
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        
        
        DJson dJson = new DJson();
        
        int id = dJson.get_id();
        if(id != Log_id) {
        	
        	dJson.set_URL("http://liability2011.appspot.com/liability2011test?command=Load_data&id=" + id);
            rootJSON = dJson.get_Date();
            
            Sjson = rootJSON.toString();
            
            
            Log_id = id;
         	
         	Log.d("aaaaaaaaaaaaaaaaaaaaaa", "true");
        }else {
        	Log.d("aaaaaaaaaaaaaaaaaaaaaaa", "false");
        }

        
        
    }
    
    
}