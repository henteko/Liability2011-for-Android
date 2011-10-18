package com.teres.Liability2011;

/************************************************
 * This source is Liability2011 MainActivity.java
 * 
 * Creator is henteko
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Test start Activity
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
		MainActivity.this.startActivityForResult(intent, 0);
		
//		Intent intent = new Intent(MainActivity.this, TimeTableActivity.class);
//		MainActivity.this.startActivityForResult(intent, 0);
//		
//		Intent intent = new Intent(MainActivity.this, IndexActivity.class);
//		MainActivity.this.startActivityForResult(intent, 0);
        
    }
}