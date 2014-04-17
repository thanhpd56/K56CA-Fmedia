package com.example.model;

import java.util.ArrayList;
import java.util.Observable;

import com.example.database.MyVideoDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteException;

public class VideoManagement extends Observable{
	public static VideoManagement mManager;
	public static VideoManagement getInstance(){
		if (mManager == null) {
			synchronized (VideoManagement.class) {
				if (mManager == null)
					mManager = new VideoManagement();
			}
		}

		return mManager;
	}

	public void addMyVideo(Context context, Video video) {
		MyVideoDatabase db = new MyVideoDatabase(context);
		try {
			db.open();
			if (!db.isVideoExist(video)) {
				db.addVideo(video);
				setChanged();
				notifyObservers();
			}
			db.close();
		} catch (SQLiteException e) {
			e.printStackTrace();
			if (db != null)
				db.close();
		}
	}

	public void removeMyVideo(Context context, Video video) {
		MyVideoDatabase db = new MyVideoDatabase(context);
		try {
			db.open();
			db.removeVideo(video);
			setChanged();
			notifyObservers();
			db.close();
		} catch (SQLiteException e) {
			e.printStackTrace();
			if (db != null) {
				db.close();
			}
		}
	}

	public boolean isMyVideo(Context context, Video video) {
		MyVideoDatabase db = new MyVideoDatabase(context);
		try {
			db.open();
			return db.isVideoExist(video);
		} catch (SQLiteException e) {
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}

		}
		return false;
	}
}
