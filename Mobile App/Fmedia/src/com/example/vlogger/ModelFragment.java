package com.example.vlogger;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.adapter.CustomGridViewAdapter;
import com.example.model.VideoManagement;
import com.example.model.VideoParser;
import com.example.model.Video;
import com.example.youtube.OpenYouTubePlayerActivity;
import com.global.Statics;

import JsonParser.JSONParser;
import android.app.Activity;
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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ModelFragment extends Fragment implements Observer{
	private static String TAG = "ModelFragment";
	private ProgressDialog pDialog;
	// URL to get contacts JSON
	private static String url;
	
	GettingVideosAsynctask mThread;
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

	private static final int ALL_CAT_ID = -1;

	JSONParser jsonParse = new JSONParser();
	JSONArray items = null;

	// Adapter and array for Gridview
	CustomGridViewAdapter adapterGrid = null;
	ArrayList<Video> arrVideos = new ArrayList<Video>();
	GridView gvVideo;
	Context mContext;

	// Auto loadmore
	private static final int NUM_ITEMS = 12;
	private static int start = 0;

	// Info to create specify fragment
	private int CategoryId;
	private static String TAG_KEY = "CATID";

	// For search fragment
	private static final String SEARCH_KEY = "SEARCH_KEY";
	public static final int SEARCH_ID = -2;
	public static final int FAVOURITE_ID = -3;

	public ModelFragment() {
		super();
	}

	public static ModelFragment getInstance(int catId) {
		ModelFragment fragment = new ModelFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(TAG_KEY, catId);
		fragment.setArguments(bundle);
		return fragment;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		CategoryId = getArguments().getInt(TAG_KEY);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mContext = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.main_fragment, container,
				false);
		gvVideo = (GridView) rootView.findViewById(R.id.gvVlod);
		adapterGrid = new CustomGridViewAdapter(mContext, R.layout.video_item,
				arrVideos);
		gvVideo.setAdapter(adapterGrid);
		gvVideo.setOnItemClickListener(onVideoClick);
		mThread = new GettingVideosAsynctask();
		mThread.execute(CategoryId);
		VideoManagement.getInstance().addObserver(this);
		return rootView;

	}
	OnItemClickListener onVideoClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			String videoId = arrVideos.get(arg2).getUrl();

			if (videoId == null || videoId.trim().equals("")) {
				return;
			}
			Intent lVideoIntent = new Intent(null, Uri.parse("ytv://"
					+ videoId), getActivity(),
					OpenYouTubePlayerActivity.class);
			startActivity(lVideoIntent);
		}
	};
	private class GettingVideosAsynctask extends
			AsyncTask<Integer, String, ArrayList<Video>> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected ArrayList<Video> doInBackground(Integer... catId) {
			// TODO Auto-generated method stub
			
			ArrayList<Video> list = new ArrayList<Video>();
			if (CategoryId == ALL_CAT_ID) {
				// Return all videos
				list = VideoParser.getAllVideos();
			} else if (CategoryId == SEARCH_ID) {
		 		
				String key = MainActivity.searchKey;
				// search video
				list = VideoParser.searchVideo(key);

			}else if(CategoryId == FAVOURITE_ID){
				list = VideoParser.getMyVideos(mContext);
			} 
			
			
			else {
				list = VideoParser.getVideosByCategory(CategoryId);

			}
			if (list != null && list.size() >= 0)
				return list;
			else
				return null;

		}

		@Override
		protected void onPostExecute(ArrayList<Video> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result != null && result.size() > 0)
				arrVideos.clear();
			arrVideos.addAll(result);
			adapterGrid.notifyDataSetChanged();

		}

	}
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
		if(mThread != null){
			if(Statics.DEBUG){
				Log.d(TAG, "observer update");
			}
			mThread.cancel(true);
			mThread = new GettingVideosAsynctask();
			mThread.execute(CategoryId);
		}
		
	}
}
