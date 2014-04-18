package com.global;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager.BadTokenException;

import com.example.vlogger.R;

public class Statics {
	// For test
	public static final boolean DEBUG = true;
	// for video
	//public static final String URL_IP = "http://192.168.0.64/FmediaApi/v1/";
	public static final String URL = "http://tuanit.net/thanhpd56_com";
	//public static final String URL = " 192.168.56.101";
	
	public static String URL_ALL_VIDEO = URL + "/FmediaProjectApi/get_all_videos.php";
	public static  String URL_CAT_VIDEO = URL + "/FmediaProjectApi/get_video_by_id.php?cat_id=";

	// for category name
	public static  String URL_CATEGORY = URL + "/FmediaProjectApi/get_all_categories.php";
	// for search result
	public static  String URL_SEARCH = URL + "/FmediaProjectApi/search.php?key=";
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
	public static float IMAGE_RATIO = (float) 0.5625;
	

}
