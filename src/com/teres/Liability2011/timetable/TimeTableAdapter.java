package com.teres.Liability2011.timetable;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.teres.Liability2011.R;
import com.teres.Liability2011.R.id;
import com.teres.Liability2011.R.layout;

import jp.teres.numa08.chofufesdata.TimeTable;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TimeTableAdapter extends ArrayAdapter<TimeTable> {
	private static final String TAG = TimeTableAdapter.class.getSimpleName();
	private LayoutInflater inflater;
	
	public TimeTableAdapter(Context context, int textViewResourceId,
			List<TimeTable> objects) {
		super(context, 0, objects);
		// TODO Auto-generated constructor stub
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.timetable_list, parent, false);
			holder = new ViewHolder();
			holder.eventTime = (TextView)convertView.findViewById(R.id.event_time);
			holder.eventTitle = (TextView)convertView.findViewById(R.id.event_title);
			holder.eventDescription = (TextView) convertView.findViewById(R.id.event_description);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		TimeTable data = getItem(position);
		holder.eventTime.setText(setTime(data));
		holder.eventTitle.setText(data.getTitle());
		holder.eventDescription.setText(data.getDescription());
		
		return convertView;
	}

	private CharSequence setTime(TimeTable data) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
		cal.setTimeInMillis(data.getStartDate());
		String startTime = getTime(cal);
		cal.setTimeInMillis(data.getEndDate());
		String endTime = getTime(cal);
		return startTime + "～" + endTime;
	}

	private String getTime(Calendar cal) {
		// TODO Auto-generated method stub
		int h = cal.get(Calendar.HOUR_OF_DAY);
		int m = cal.get(Calendar.MINUTE);
		String hour, minute;
		if(h < 10){
			hour = "0" + h;
		} else {
			hour = "" + h;
		}
		if(m < 10){
			minute = "0" + m;
		} else {
			minute = "" + m;
		}
		return hour + ":" + minute;
	}
}

class ViewHolder{
	TextView eventTime;
	TextView eventTitle;
	TextView eventDescription;
}