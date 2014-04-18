package com.example.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import JsonParser.JSONParser;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.database.MyVideoDatabase;
import com.example.vlogger.R;
import com.global.Statics;

public class VideoParser {
	private static String TAG = "VideoParser";

	// JSON Video node
	private static final String TAG_ERROR = "error";
	private static final String TAG_VIDEOS = "videos";
	private static final String TAG_VIDEO_ID = "id";
	private static final String TAG_TITLE = "title";
	private static final String TAG_URL = "url";
	private static final String TAG_DURING_TIME = "during_time";
	private static final String TAG_IMAGE_URL = "image_url";

	private static final String TAG_DESC = "description";
	private static final String TAG_RATING = "rating";
	private static final String TAG_TIME = "create_time";

	// JSON Category Node
	private static final String TAG_CAT = "cats";
	private static final String TAG_CAT_NAME = "cat_name";

	private static final String TAG_CAT_ID = "id";

	public static ArrayList<Video> getVideosByCategory(int catId) {
		ArrayList<Video> arrVideos = new ArrayList<Video>();
		String url = Statics.URL_CAT_VIDEO + catId;
		JSONParser parser = new JSONParser();
		JSONObject json = parser.makeHttpRequest(url, "GET", null);
		if (json == null)
			return null;
		if (Statics.DEBUG) {
			Log.d(TAG, json.toString());
		}
		try {
			JSONArray jVideoArray = json.getJSONArray(TAG_VIDEOS);
			for (int i = 0; i < jVideoArray.length(); i++) {
				JSONObject jVideo = jVideoArray.getJSONObject(i);
				int id = jVideo.getInt(TAG_VIDEO_ID);
				String title = jVideo.getString(TAG_TITLE);
				String urlt = jVideo.getString(TAG_URL);
				String during_time = jVideo.getString(TAG_DURING_TIME);
				String image_url = jVideo.getString(TAG_IMAGE_URL);
				String description = jVideo.getString(TAG_DESC);
				float rating = (float) jVideo.getDouble(TAG_RATING);
				int create_time = jVideo.getInt(TAG_TIME);
				arrVideos.add(new Video(id, title, urlt, during_time,
						image_url, description, rating, create_time));

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.i(TAG, e.getMessage());
		}

		return arrVideos;

	}

	public static ArrayList<VideoCategory> getAllCategorires() {
		ArrayList<VideoCategory> arrCats = new ArrayList<VideoCategory>();
		String url = Statics.URL_CATEGORY;
		JSONParser parser = new JSONParser();
		JSONObject json = parser.makeHttpRequest(url, "GET", null);
		if (json == null)
			return null;
		if (Statics.DEBUG)
			Log.d(TAG, json.toString());

		try {
			JSONArray jCats = json.getJSONArray(TAG_CAT);

			for (int i = 0; i < jCats.length(); i++) {
				JSONObject jCat = jCats.getJSONObject(i);
				String catName = jCat.getString(TAG_CAT_NAME);
				int catId = jCat.getInt(TAG_CAT_ID);
				arrCats.add(new VideoCategory(catId, catName,
						R.drawable.category));

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.i(TAG, e.getMessage());
		}

		return arrCats;
	}

	public static ArrayList<Video> getAllVideos() {
		ArrayList<Video> arrVideos = new ArrayList<Video>();
		String url = Statics.URL_ALL_VIDEO;
		JSONParser parser = new JSONParser();
		JSONObject json = parser.makeHttpRequest(url, "GET", null);
		if (json == null)
			return null;
		if (Statics.DEBUG) {
			Log.d(TAG, json.toString());
		}
		try {
			JSONArray jVideoArray = json.getJSONArray(TAG_VIDEOS);
			for (int i = 0; i < jVideoArray.length(); i++) {
				JSONObject jVideo = jVideoArray.getJSONObject(i);
				int id = jVideo.getInt(TAG_VIDEO_ID);
				String title = jVideo.getString(TAG_TITLE);
				String urlt = jVideo.getString(TAG_URL);
				String during_time = jVideo.getString(TAG_DURING_TIME);
				String image_url = jVideo.getString(TAG_IMAGE_URL);
				String description = jVideo.getString(TAG_DESC);
				float rating = (float) jVideo.getDouble(TAG_RATING);
				int create_time = jVideo.getInt(TAG_TIME);
				arrVideos.add(new Video(id, title, urlt, during_time,
						image_url, description, rating, create_time));

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.i(TAG, e.getMessage());
		}

		return arrVideos;

	}

	public static ArrayList<Video> searchVideo(String key) {
		ArrayList<Video> arrVideos = new ArrayList<Video>();
		String url = Statics.URL_SEARCH + key;
		Log.d(TAG, key);
		JSONParser parser = new JSONParser();
		JSONObject json = parser.makeHttpRequest(url, "GET", null);
		if (json == null)
			return null;
		if (Statics.DEBUG) { 
			Log.d(TAG, json.toString() + url);
		}
		try {
			JSONArray jVideoArray = json.getJSONArray(TAG_VIDEOS);
			for (int i = 0; i < jVideoArray.length(); i++) {
				JSONObject jVideo = jVideoArray.getJSONObject(i);
				int id = jVideo.getInt(TAG_VIDEO_ID);
				String title = jVideo.getString(TAG_TITLE);
		 		String urlt = jVideo.getString(TAG_URL);
				String during_time = jVideo.getString(TAG_DURING_TIME);
				String image_url = jVideo.getString(TAG_IMAGE_URL);
				String description = jVideo.getString(TAG_DESC);
				float rating = (float) jVideo.getDouble(TAG_RATING);
				int create_time = jVideo.getInt(TAG_TIME);
				arrVideos.add(new Video(id, title, urlt, during_time,
						image_url, description, rating, create_time));

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.getMessage() + url);
		}

		return arrVideos;

	}
	
	public static ArrayList<Video> getMyVideos(Context context){
		ArrayList<Video> arrVideos = new ArrayList<Video>();
		MyVideoDatabase db = new MyVideoDatabase(context);
		try{
			db.open();
			arrVideos.addAll(db.getAllMyVideos());
			db.close();
		}catch(SQLiteException e){
			e.printStackTrace();
			if(db != null)
				db.close();
		}
		return arrVideos;
	}
}
