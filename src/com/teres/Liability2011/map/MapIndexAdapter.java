package com.teres.Liability2011.map;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.teres.Liability2011.R;
import com.teres.Liability2011.index.Index;

public class MapIndexAdapter extends ArrayAdapter<Index> {
	private LayoutInflater inflater;

	public MapIndexAdapter(Context context, int textViewResourceId,
			List<Index> objects) {
		super(context, 0, objects);
		// TODO Auto-generated constructor stub
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.index_list, parent, false);
			holder = new ViewHolder();
			holder.timeORplace = (TextView) convertView
					.findViewById(R.id.index_time_or_place);
			holder.title = (TextView) convertView
					.findViewById(R.id.index_title);
			holder.description = (TextView) convertView
					.findViewById(R.id.index_description);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Index index = getItem(position);
		holder.timeORplace.setText(index.getTimeOrplace());
		holder.title.setText(index.getTitle());
		holder.description.setText(index.getDescription());

		return convertView;
	}

}

// 一つのリストの行に表示する内容を保存するクラス
class ViewHolder {
	TextView timeORplace;
	TextView title;
	TextView description;
}
