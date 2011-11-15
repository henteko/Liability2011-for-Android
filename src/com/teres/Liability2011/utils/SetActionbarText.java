package com.teres.Liability2011.utils;

import com.teres.Liability2011.R;

import android.app.Activity;
import android.widget.LinearLayout;

public class SetActionbarText {
	private static final String TAG = SetActionbarText.class.getSimpleName();
	
	public static void setActionbarText(Activity activity, String string){
		LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.actionbar_compat);
		ActionbarTextView textView = getActionbarText(activity, string);
		linearLayout.addView(textView);
	}

	private static ActionbarTextView getActionbarText(Activity activity, String string) {
		// TODO Auto-generated method stub
		ActionbarTextView textView = new ActionbarTextView(activity.getApplicationContext());
		textView.setText(string);
		return textView;
	}
}
