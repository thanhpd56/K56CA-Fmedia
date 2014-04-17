package com.global;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager.BadTokenException;

import com.example.database.DatabaseHandler;
import com.example.vlogger.R;

public class Statics {
	// For test
	public static final boolean DEBUG = true;
	// for video
	//public static final String URL_IP = "http://192.168.0.64/FmediaApi/v1/";
	public static  String URL_ALL_VIDEO = "http://tuanit.net/thanhpd56_com/FmediaProjectApi/get_all_videos.php";
	public static  String URL_CAT_VIDEO = "http://tuanit.net/thanhpd56_com/FmediaProjectApi/get_video_by_id.php?cat_id=";

	// for category name
	public static  String URL_CATEGORY = "http://tuanit.net/thanhpd56_com/FmediaProjectApi/get_all_categories.php";
	// for search result
	public static  String URL_SEARCH = "http://tuanit.net/thanhpd56_com/FmediaProjectApi/search.php?key=";
	// Model ProgressDialog
	public static ProgressDialog createProgressDialog(Context mContext) {
		ProgressDialog dialog = new ProgressDialog(mContext);
		try {
			dialog.show();
		} catch (BadTokenException e) {

		}
		dialog.setCancelable(true);
		dialog.setContentView(R.layout.custom_progressdialog);
		return dialog;
	}
	

}
