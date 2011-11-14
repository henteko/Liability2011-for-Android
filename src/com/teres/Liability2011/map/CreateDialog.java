package com.teres.Liability2011.map;

import java.util.ArrayList;

import jp.teres.numa08.chofufesdata.ChofufesData;
import jp.teres.numa08.chofufesdata.Roten;
import jp.teres.numa08.chofufesdata.Shitsunai;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.teres.Liability2011.LoadJson;
import com.teres.Liability2011.R;
import com.teres.Liability2011.index.Index;

class CreateDialog {
	private static final String TAG = CreateDialog.class.getSimpleName();

	private MapActivity activity;
	private ChofufesData chofufesData;

	public CreateDialog(MapActivity activit, String sjson) {
		super();
		this.activity = activit;
		try {
			this.chofufesData = LoadJson.loadByJson(sjson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this.activity, "データを読み込めませんでした。", Toast.LENGTH_SHORT)
					.show();
			this.activity.finish();
			e.printStackTrace();
		}
	}

	public void showDlg(String params) {
		Log.d(TAG, params);
		int code = Integer.parseInt(params.split(",")[0]); // 建物の番号や、露店の位置を表す
		String kind = params.split(",")[1]; // 展示の種類を表す。Sitsunai　or　Roten

		String[] kinds = activity.getResources().getStringArray(R.array.kind);
		MapIndexAdapter indexAdapter = null;
		if (kind.equals(kinds[0])) {
			// 露店のとき
			 indexAdapter = loadRoten(code);
		} else if (kind.equals(kinds[1])) {
			// 室内展示の時
			indexAdapter = loadShitsunai(code);
		}
		showCustomDialog(indexAdapter);
	}

	private void showCustomDialog(MapIndexAdapter indexAdapter) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(this.activity
				.getApplicationContext());
		View layout = inflater.inflate(R.layout.map_dialog,
				(ViewGroup) activity.findViewById(R.id.map_dialog));

		ListView listView = (ListView) layout
				.findViewById(R.id.map_index_listview);
		listView.setAdapter(indexAdapter);

		AlertDialog.Builder mBuilder = new AlertDialog.Builder(this.activity);
		mBuilder.setView(layout);
		mBuilder.setNegativeButton(
				activity.getResources().getString(R.string.back),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
		mBuilder.show();

	}

	private MapIndexAdapter loadShitsunai(int code) {
		// TODO Auto-generated method stub
		ArrayList<Index> indexList = new ArrayList<Index>();
		ArrayList<Shitsunai> shitsunaiList = chofufesData.getShitsunaiList();
		for (Shitsunai shitsunai : shitsunaiList) {
			if (code <= 7) {
				if (shitsunai.getNumber() / 1000 == code) {
					indexList.add(getShitsunaiIndex(shitsunai));
				}
			} else if (code == 8 || code == 10) {
				if (shitsunai.getNumber() / 1000 == code
						|| shitsunai.getNumber() / 1000 == (code + 1)) {
					indexList.add(getShitsunaiIndex(shitsunai));
				}
			}
		}
		Log.d(TAG, indexList.size() + "");
		return new MapIndexAdapter(activity, 0, indexList);
	}

	private Index getShitsunaiIndex(Shitsunai shitsunai) {
		// TODO Auto-generated method stub
		String building = activity.getResources().getStringArray(
				R.array.building_name)[shitsunai.getNumber() / 1000 - 1];
		// int room = shitsunai.getNumber() % 1000;
		String room;
		int roomNumber = shitsunai.getNumber() % 1000;
		if (roomNumber == 0) {
			room = "";
		} else {
			room = String.valueOf(roomNumber);
		}

		return new Index(building + room, shitsunai.getTitle(), "");
	}

	private MapIndexAdapter loadRoten(int code) {
		ArrayList<Index> indexList = new ArrayList<Index>();
		ArrayList<Roten> rotenList = chofufesData.getRotenList();
		for (Roten roten : rotenList) {
			switch (code) {
			case 15:
				if (roten.getNumber() <= 15) {
					indexList.add(new Index(roten.getNumber() + "", roten
							.getTitle(), roten.getDescription()));
				}
				break;
			case 31:
				if (roten.getNumber() > 15 && roten.getNumber() <= 31) {
					indexList.add(new Index(roten.getNumber() + "", roten
							.getTitle(), roten.getDescription()));
				}
				break;
			case 51:
				if (roten.getNumber() > 31 && roten.getNumber() <= 51) {
					indexList.add(new Index(roten.getNumber() + "", roten
							.getTitle(), roten.getDescription()));
				}
				break;
			case 63:
				if (roten.getNumber() > 51) {
					indexList.add(new Index(roten.getNumber() + "", roten
							.getTitle(), roten.getDescription()));
				}
				break;
			}
		}
		return new MapIndexAdapter(activity, 0 ,indexList);
	}
}
