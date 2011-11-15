package com.teres.Liability2011.utils;

import com.teres.Liability2011.R;
import com.teres.Liability2011.R.color;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class ActionbarTextView extends TextView {
	private final int FP = ViewGroup.LayoutParams.FILL_PARENT; 
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT; 
	public ActionbarTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setLayoutParams(new LayoutParams(this.WC, 45));
		this.setTextColor(context.getResources().getColor(R.color.actionbar_text_color));
		this.setTextSize(20);
		this.setPadding(5, 5, 0, 5);
	}
}
