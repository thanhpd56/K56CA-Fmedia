package com.example.database;

import com.example.model.Video;
import com.global.Statics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	// All static variables
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "my_db";
	/**
	 * Table to save all favourite videos
	 */
	private static final String TABLE_NAME = "tbl_video";

	// table column names
	private static final String KEY_ID = "id";
	private static final String KEY_VIDEO_ID = "video_id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_URL = "url";
	private static final String KEY_DURING_TIME = "during_time";
	private static final String KEY_IMAGE_URL = "image_url";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_RATING = "rating";
	private static final String KEY_CREATED_AT = "create_time";

	private static final String QUERY_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY," 
			+ KEY_VIDEO_ID + " INTEGER,"
            + KEY_TITLE + " TEXT,"  
            + KEY_URL + " TEXT," 
            + KEY_DURING_TIME + " TEXT," 
            + KEY_IMAGE_URL + " TEXT," 
            + KEY_DESCRIPTION + " TEXT," 
            + KEY_RATING + " FLOAT,"
            + KEY_CREATED_AT + " INTEGER"
            +")"
			
            ;
	private static final String QUERY_TABLE_DROP = "DROP TABLE IF EXIST "
			+ TABLE_NAME;

	public DatabaseHandler(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(QUERY_CREATE_TABLE);
		if(Statics.DEBUG)
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

	public void addVideo(Video video) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_VIDEO_ID, video.getId());
		values.put(KEY_TITLE, video.getTitle());
		values.put(KEY_URL, video.getUrl());
		values.put(KEY_DURING_TIME, video.getDuringTime());
		values.put(KEY_IMAGE_URL, video.getImageUrl());
		values.put(KEY_DESCRIPTION, video.getDescription());
		values.put(KEY_RATING, video.getRating());
		values.put(KEY_CREATED_AT, video.getCreateTime());
		db.insert(TABLE_NAME, null, values);
		db.close();
	}

	public boolean isVideoExist(int videoId) {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM " + TABLE_NAME;
		Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID },
				KEY_VIDEO_ID + "=?", new String[] { String.valueOf(videoId) },
				null, null, null);
		cursor.close();
		if (cursor.getCount() >= 1)
			return true;
		return false;
	}

	public int getVideosCount() {
		if(Statics.DEBUG)
			Log.d("Query", QUERY_CREATE_TABLE);
		String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int result = cursor.getCount();
        cursor.close();
 
        // return count
        return result;
	}

}
