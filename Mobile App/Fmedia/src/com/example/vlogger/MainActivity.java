package com.example.vlogger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import JsonParser.JSONParser;
import java.util.ArrayList;
import java.util.List;

import com.example.adapter.NavDrawerListAdapter;
import com.example.model.VideoCategory;
import com.example.model.VideoParser;
import com.example.youtube.OpenYouTubePlayerActivity;
import com.global.Statics;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Watson.OnOptionsItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView lvCats;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<VideoCategory> mArrCats;
	private NavDrawerListAdapter adapterCat;
	// for category
	private JSONParser jsonParser = new JSONParser();
	private static final String TAG_ERROR = "error";
	private static final String TAG_CAT = "cats";
	private static final String TAG_CAT_NAME = "cat_name";

	private static final String TAG_ID = "id";
	public static final int ALL_VIDEO_CATID = -1;
	private String TAG_KEY = "CATID";
	JSONArray catList;
	ArrayList<String> arrCats = new ArrayList<String>();

	// For search video
	private static final String SEARCH_KEY = "SEARCH_KEY";
	private static final int SEARCH_ID = -2;
	public static String searchKey;

	// FOR FAVOURITE VIDEOS
	private static final int FAVOURITE_ID = -3;
	public static final String FAVOURITE_TITLE = "Favourite Videos";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_x);
		mTitle = mDrawerTitle = getTitle();
		// load slide menu items
		new GettingCategoriesAsynctask().execute();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		lvCats = (ListView) findViewById(R.id.lvCats);

		mArrCats = new ArrayList<VideoCategory>();
		lvCats.setOnItemClickListener(new SlideMenuClickListener());
		// enabling action bar app icon and behaving it as toggle button
		setActionBar();
		// Set adapter
		adapterCat = new NavDrawerListAdapter(getApplicationContext(), mArrCats);
		lvCats.setAdapter(adapterCat);

	}

	private void setActionBar() {
		// TODO Auto-generated method stub
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
				R.string.app_name // nav drawer close - description for
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private class GettingCategoriesAsynctask extends
			AsyncTask<String, String, ArrayList<VideoCategory>> {
		@Override
		protected ArrayList<VideoCategory> doInBackground(String... args) {
			// TODO Auto-generated method stub

			ArrayList<VideoCategory> arrVideoCats = new ArrayList<VideoCategory>();
			arrVideoCats.add(new VideoCategory(ALL_VIDEO_CATID, "ALL VIDEOS",
					R.drawable.category));
			arrVideoCats.add(new VideoCategory(FAVOURITE_ID, FAVOURITE_TITLE,
					R.drawable.category));
			arrVideoCats.addAll(VideoParser.getAllCategorires());
			if (arrVideoCats != null && arrVideoCats.size() > 0)
				return arrVideoCats;
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<VideoCategory> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result != null && result.size() > 0) {
				mArrCats.clear();
				mArrCats.addAll(result);
				adapterCat.notifyDataSetChanged();
				displayView(0);
			}
		}

	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main_actions, menu);
		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchView.setSubmitButtonEnabled(true);
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String arg0) {
				returnSearchResult(arg0);
				return false;
			}

			private void returnSearchResult(String key) {
				Fragment fragment = null;
				fragment = ModelFragment.getInstance(-2);
				searchKey = key;
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, fragment).commit();
				setTitle("Search Results");
			}

			@Override
			public boolean onQueryTextChange(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		case R.id.action_refresh:
			// DO REFRESH
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		fragment = ModelFragment.getInstance(mArrCats.get(position).getId());
		;
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, fragment).commit();

		// update selected item and title, then close the drawer
		lvCats.setItemChecked(position, true);
		lvCats.setSelection(position);
		setTitle(mArrCats.get(position).getTitle());
		mDrawerLayout.closeDrawer(lvCats);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
