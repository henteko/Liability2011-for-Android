package com.teres.Liability2011;

/************************************************
 * This source is Liability2011 MainActivity.java
 * 
 * Creator is henteko
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button Indexbutton = (Button) findViewById(R.id.IndexButton);
        Indexbutton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) { 
        		Intent intent = new Intent(MainActivity.this, MapActivity.class);
        		MainActivity.this.startActivityForResult(intent, 0);
        	}
        });
        
        Button Mapbutton = (Button) findViewById(R.id.MapButton);
        Mapbutton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) { 
        		Intent intent = new Intent(MainActivity.this, TimeTableActivity.class);
        		MainActivity.this.startActivityForResult(intent, 0);
        	}
        });
        
        Button TimeTablebutton = (Button) findViewById(R.id.TimeTableButton);
        TimeTablebutton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) { 
        		Intent intent = new Intent(MainActivity.this, IndexActivity.class);
        		MainActivity.this.startActivityForResult(intent, 0);
        	}
        });
		
        
    }
}