package com.teres.Liability2011.map;

import java.util.ArrayList;

import jp.teres.numa08.chofufesdata.ChofufesData;
import jp.teres.numa08.chofufesdata.Shitsunai;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import com.teres.Liability2011.LoadJson;
import com.teres.Liability2011.R;

class CreateDialog {

	private Context context;
	private String Sjson;
	private ChofufesData mChofufesData;

	public CreateDialog(Context c, String sjson) {
		context = c;
		Sjson = sjson;
		try {
			this.mChofufesData = LoadJson.loadByJson(Sjson);
			Log.d("#######################", Sjson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.d("####################", "error");
			Log.d("EEEEEEEEEEEEEEEEEEEE", Sjson);
			e.printStackTrace();
		}

	}

	public void showDlg(String params) {
		String[] param = params.split(",");
		int code = Integer.parseInt(param[0]);
		String kind = param[1];
		String name = "test";
		ArrayList<String> item = new ArrayList<String>();
		if (kind.equals("s")) {
			item = makeSitsunaiItems(code);
			for(String i : item){
				Log.d("zzzzzzzzzzzzzzzzzzzzzzzz", i);
			}
		} else if (kind.equals("r")) {

		}
		//showDialog(item);
		/*
		 * // ���X�g�\�����镶���� final String[] ITEM = new String[]{"AAA",
		 * "BBB", "CCC", "DDD", "EEE", "FFF"};
		 * 
		 * AlertDialog.Builder alertDialogBuilder = new
		 * AlertDialog.Builder(context); // �A���[�g�_�C�A���O�̃^�C�g����ݒ�
		 * alertDialogBuilder.setTitle(name); // �A���[�g�_�C�A���O�̍��ڂ�ݒ�
		 * alertDialogBuilder.setItems(ITEM, new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) { //
		 * �A�C�e�����I�����ꂽ�Ƃ��̏���. which���I�����ꂽ�A�C�e���̔ԍ�.
		 * Log.v("Alert", "Item No : " + which); } }); // �߂�{�^��
		 * alertDialogBuilder
		 * .setNeutralButton(this.context.getString(R.string.back), new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) { }
		 * });
		 * 
		 * // �L�����Z���\ alertDialogBuilder.setCancelable(true); AlertDialog
		 * alertDialog = alertDialogBuilder.create(); // �A���[�g�_�C�A���O��\��
		 * alertDialog.show();
		 */
	}

	private void showDialog(ArrayList<String> item) {
		// TODO Auto-generated method stub
		AlertDialog.Builder mBuilder = new AlertDialog.Builder(this.context);
		mBuilder.setTitle("test");
		String[] items = (String[])item.toArray();
		Toast.makeText(context, item.size(), Toast.LENGTH_LONG).show();
		mBuilder.setItems(items, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
		
		mBuilder.setCancelable(true);
//		mBuilder.create().show();

	}

	private ArrayList<String> makeSitsunaiItems(int code) {
		// TODO Auto-generated method stub
		ArrayList<Shitsunai> shitsunaiList = this.mChofufesData
				.getShitsunaiList();
		ArrayList<String> items = new ArrayList<String>();
		for (Shitsunai shitsunai : shitsunaiList) {
			if (code <= 7) {
				if (shitsunai.getNumber() / 1000 == code) {
					Toast.makeText(context, code + "", Toast.LENGTH_LONG).show();
					String buildingName = this.context.getResources()
							.getStringArray(R.array.building_name)[shitsunai
							.getNumber() / 1000 - 1];
					String room;
					int roomNumber = shitsunai.getNumber() % 1000;
					if (roomNumber == 0) {
						room = "";
					} else {
						room = String.valueOf(roomNumber);
					}
					String title = shitsunai.getTitle();
					items.add(buildingName + room + "室" + title);
				}

			}
		}
		return items;
	}
}
