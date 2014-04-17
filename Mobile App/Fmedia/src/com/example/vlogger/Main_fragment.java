package com.example.vlogger;
import com.global.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.adapter.CustomGridViewAdapter;
import com.example.loading.EndlessScrollListener;
import com.example.model.LoadMoreGridView;
import com.example.model.Video;
import com.example.model.LoadMoreGridView.OnLoadMoreListener;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.vlogger.R;
import com.example.youtube.OpenYouTubePlayerActivity;

import JsonParser.JSONParser;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class Main_fragment extends Fragment {
	private ProgressDialog pDialog;

	// URL to get contacts JSON
	private static String url;
	// JSON Node names
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
	private int indexFragment;
	JSONParser jsonParse = new JSONParser();
	JSONArray items = null;

	// Adapter va array cho Gridview
	CustomGridViewAdapter adapter = null;
	ArrayList<Video> arrItems = new ArrayList<Video>();
	LoadMoreGridView gv; 
	Context context;
	// for auto loadmor
		private static final int NUM_ITEMS = 12;
		private static int start = 0;
	public Main_fragment() {
		super();
		indexFragment = -1;
		start = 0;
		adapter = null;
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.main_fragment, container,
				false);
		//indexFragment = getArguments().getInt("key");
		gv = (LoadMoreGridView) rootView.findViewById(R.id.gvVlod);
		adapter = new CustomGridViewAdapter(getActivity(), R.layout.grid_row,
				arrItems);
		gv.setAdapter(adapter);
		gv.setOnLoadMoreListener(new OnLoadMoreListener() {
			public void onLoadMore() {
				new GetItems().execute();
			}
		});
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String videoId = arrItems.get(arg2).getUrl();

				if (videoId == null || videoId.trim().equals("")) {
					return;
				}
				Class videoClass;
				Intent lVideoIntent = new Intent(null, Uri.parse("ytv://"
						+ videoId), getActivity(),
						OpenYouTubePlayerActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("Video", (Video)arrItems.get(arg2));
				lVideoIntent.putExtras(bundle);
				
				startActivity(lVideoIntent);

			}
		});
		new GetItems().execute();
		return rootView;
	}

	private class GetItems extends AsyncTask<Void, Void, Void> {
		// ProgressDialog
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (pDialog == null) {
				pDialog = Statics.createProgressDialog(getActivity());
				//pDialog.show();
			       } else {
			    	//   pDialog.show();
			       }
		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			// Create service handler class instance
			if (isCancelled()) {
				return null;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			url = Statics.URL_ALL_VIDEO;
			JSONObject json = jsonParse.makeHttpRequest(url, "GET",
					null);
			// show to logcat
			if(Statics.DEBUG)
				Log.d("Videos", json.toString());
			try {
				// check for get video success?
				boolean error = json.getBoolean(TAG_ERROR);
				if (!error) {
					// get video success
					JSONArray videos = json.getJSONArray(TAG_VIDEOS);
					int end =(start + NUM_ITEMS>videos.length()? videos.length(): start + NUM_ITEMS);
					if(start != end)
						for (int i = start; i < end; i++) {
							JSONObject video = videos.getJSONObject(i);
							int id = video.getInt(TAG_VIDEO_ID);
							String title = video.getString(TAG_TITLE);
							String url = video.getString(TAG_URL);
							String during_time = video.getString(TAG_DURING_TIME);
							String image_url = video.getString(TAG_IMAGE_URL);
							String description = video.getString(TAG_DESC);
							float rating = (float) video.getDouble(TAG_RATING);
							int create_time = video.getInt(TAG_TIME);
	
							arrItems.add(new Video(id, title, url, during_time,
									image_url, description, rating, create_time));
	
						}
					if(Statics.DEBUG)
						Log.d("Video Array Size", arrItems.size()+" - "+start +":"+ end);
					start+=NUM_ITEMS;
					if(start > videos.length())
						start = videos.length();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			adapter.notifyDataSetChanged();
			gv.onLoadMoreComplete();
			if (arrItems.size() > 30) {
				gv.setFinishLoadAll();
			}
			super.onPostExecute(result);
			// Dimiss the progress dialog
			pDialog.dismiss();

		}

		@Override
		protected void onCancelled() {
			gv.onLoadMoreComplete();
		}
	}
}