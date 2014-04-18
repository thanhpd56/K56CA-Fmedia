package com.example.database;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Video;
import com.global.Statics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyVideoDatabase {
	private DatabaseHandler mDBHelper;
	private SQLiteDatabase mDB;
	
	// static variables
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "my_db";
	/**
	 * Table to save all favourite videos
	 */
	private static final String TABLE_NAME = "tbl_video";

	// table column names
	private static final String KEY_ID = "video_id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_URL = "url";
	private static final String KEY_DURING_TIME = "during_time";
	private static final String KEY_IMAGE_URL = "image_url";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_RATING = "rating";
	private static final String KEY_CREATED_AT = "create_time";

	private static final String QUERY_CREATE_TABLE = "CREATE TABLE "
			+ TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_TITLE + " TEXT," + KEY_URL + " TEXT," + KEY_DURING_TIME
			+ " TEXT," + KEY_IMAGE_URL + " TEXT," + KEY_DESCRIPTION
			+ " TEXT," + KEY_RATING + " FLOAT," + KEY_CREATED_AT
			+ " INTEGER" + ")"

	;
	private static final String QUERY_TABLE_DROP = "DROP TABLE IF EXIST "
			+ TABLE_NAME;


	public MyVideoDatabase(Context context) {
		this.mDBHelper = new DatabaseHandler(context);
	}

	public class DatabaseHandler extends SQLiteOpenHelper {
		// All static variables
		
		public DatabaseHandler(Context context) {
			// TODO Auto-generated constructor stub
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(QUERY_CREATE_TABLE);
			if (Statics.DEBUG)
				Log.d("Query", QUERY_CREATE_TABLE);

		}

		// Upgrading
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL(QUERY_TABLE_DROP);

			// create table again
			onCreate(db);

		}

		

		
	}
	
	public SQLiteDatabase open() {
		mDB = mDBHelper.getWritableDatabase();
		return mDB;
	}

	public void close() {
		if (mDB != null)
			mDB.close();

	}
	
	public long addVideo(Video video) {
		ContentValues values = new ContentValues();
		values.put(KEY_ID, video.getId());
		values.put(KEY_TITLE, video.getTitle());
		values.put(KEY_URL, video.getUrl());
		values.put(KEY_DURING_TIME, video.getDuringTime());
		values.put(KEY_IMAGE_URL, video.getImageUrl());
		values.put(KEY_DESCRIPTION, video.getDescription());
		values.put(KEY_RATING, video.getRating());
		values.put(KEY_CREATED_AT, video.getCreateTime());
		return mDB.insert(TABLE_NAME, null, values);
	}

	public void removeVideo(Video video) {
		removeVideo(video.getId());
	}

	public int removeVideo(int videoId) {
		return mDB.delete(TABLE_NAME, KEY_ID + " = ?",
				new String[] { String.valueOf(videoId) });
	}

	public boolean isVideoExist(Video video) {
		Cursor cursor = mDB.query(TABLE_NAME, new String[] { KEY_ID,
				KEY_TITLE }, KEY_ID + "= ?",
				new String[] { String.valueOf(video.getId()) }, null, null,
				null);
		try {
			cursor.moveToFirst();
			if (cursor.getCount() > 0)
				return true;
			return false;
		} finally {
			if (cursor != null)
				cursor.close();
		}
	}

	public List<Video> getAllMyVideos() {
		List<Video> list = new ArrayList<Video>();
		String selectQuery = "SELECT * FROM " + TABLE_NAME;
		Cursor cursor = mDB.rawQuery(selectQuery, null);
		try {
			while (cursor.moveToNext()) {
				Video video = new Video();
				video.setId(cursor.getInt(0));
				video.setTitle(cursor.getString(1));
				video.setUrl(cursor.getString(2));
				video.setDuringTime(cursor.getString(3));
				video.setImageUrl(cursor.getString(4));
				video.setDescription(cursor.getString(5));
				video.setRating(cursor.getFloat(6));
				video.setCreateTime(cursor.getInt(7));
				list.add(video);
			}
			return list;
		} finally {
			if (cursor != null)
				cursor.close();
		}
	}
	
}
