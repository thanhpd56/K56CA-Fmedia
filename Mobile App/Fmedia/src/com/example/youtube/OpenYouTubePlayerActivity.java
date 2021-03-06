package com.example.youtube;

import com.example.model.Video;
import com.example.vlogger.*;
import java.io.IOException;

import com.example.vlogger.R;
import com.example.youtube.VideoControllerView.MediaPlayerControl;
import com.global.Statics;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;
import android.widget.LinearLayout.LayoutParams;

public class OpenYouTubePlayerActivity extends Activity implements Callback,
		OnPreparedListener, MediaPlayerControl {

	Video currentVideo = new Video();
	
	public static final String SCHEME_YOUTUBE_VIDEO = "ytv";
	public static final String SCHEME_YOUTUBE_PLAYLIST = "ytpl";
	public static final String SCHEME_FILE = "file";

	static final String YOUTUBE_VIDEO_INFORMATION_URL = "http://www.youtube.com/get_video_info?&video_id=";
	static final String YOUTUBE_PLAYLIST_ATOM_FEED_URL = "http://gdata.youtube.com/feeds/api/playlists/";

	protected ProgressBar mProgressBar;
	protected TextView mProgressMessage;
	protected VideoView mVideoView;

	public final static String MSG_INIT = "com.keyes.video.msg.init";
	protected String mMsgInit = "Initializing";

	public final static String MSG_DETECT = "com.keyes.video.msg.detect";
	protected String mMsgDetect = "Detecting Bandwidth";

	public final static String MSG_PLAYLIST = "com.keyes.video.msg.playlist";
	protected String mMsgPlaylist = "Determining Latest Video in YouTube Playlist";

	public final static String MSG_TOKEN = "com.keyes.video.msg.token";
	protected String mMsgToken = "Retrieving YouTube Video Token";

	public final static String MSG_LO_BAND = "com.keyes.video.msg.loband";
	protected String mMsgLowBand = "Buffering Low-bandwidth Video";

	public final static String MSG_HI_BAND = "com.keyes.video.msg.hiband";
	protected String mMsgHiBand = "Buffering High-bandwidth Video";

	public final static String MSG_ERROR_TITLE = "com.keyes.video.msg.error.title";
	protected String mMsgErrorTitle = "Communications Error";

	public final static String MSG_ERROR_MSG = "com.keyes.video.msg.error.msg";
	protected String mMsgError = "An error occurred during the retrieval of the video.  This could be due to network issues or YouTube protocols.  Please try again later.";
	public boolean isFullScreen = false;
	/**
	 * Background task on which all of the interaction with YouTube is done
	 */
	protected QueryYouTubeTask mQueryYouTubeTask;

	protected String mVideoId = null;
	SurfaceView videoSurface;
	MediaPlayer player;
	VideoControllerView controller;

	Uri myUri = null;
	// progress dialog
	ProgressDialog pDialog;
	
	// Toggle Button
	ToggleButton tgLove;
	@Override
	protected void onCreate(Bundle pSavedInstanceState) {
		super.onCreate(pSavedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	
		setContentView(R.layout.activity_video_player);
		setupView();

		// extract the playlist or video id from the intent that started this
		// video

		Uri lVideoIdUri = this.getIntent().getData();

		if (lVideoIdUri == null) {
			Log.i(this.getClass().getSimpleName(),
					"No video ID was specified in the intent.  Closing video activity.");
			finish();
		}
		String lVideoSchemeStr = lVideoIdUri.getScheme();
		String lVideoIdStr = lVideoIdUri.getEncodedSchemeSpecificPart();
		if (lVideoIdStr == null) {
			Log.i(this.getClass().getSimpleName(),
					"No video ID was specified in the intent.  Closing video activity.");
			finish();
		}
		if (lVideoIdStr.startsWith("//")) {
			if (lVideoIdStr.length() > 2) {
				lVideoIdStr = lVideoIdStr.substring(2);
			} else {
				Log.i(this.getClass().getSimpleName(),
						"No video ID was specified in the intent.  Closing video activity.");
				finish();
			}
		}

		// /////////////////
		// extract either a video id or a playlist id, depending on the uri
		// scheme
		YouTubeId lYouTubeId = null;
		if (lVideoSchemeStr != null
				&& lVideoSchemeStr.equalsIgnoreCase(SCHEME_YOUTUBE_PLAYLIST)) {
			lYouTubeId = new PlaylistId(lVideoIdStr);
		} else if (lVideoSchemeStr != null
				&& lVideoSchemeStr.equalsIgnoreCase(SCHEME_YOUTUBE_VIDEO)) {
			lYouTubeId = new VideoId(lVideoIdStr);
		} else if (lVideoSchemeStr != null
				&& lVideoSchemeStr.equalsIgnoreCase(SCHEME_FILE)) {
			lYouTubeId = new FileId(lVideoIdStr);
		}

		if (lYouTubeId == null) {
			Log.i(this.getClass().getSimpleName(),
					"Unable to extract video ID from the intent.  Closing video activity.");
			finish();
		}
		mQueryYouTubeTask = (QueryYouTubeTask) new QueryYouTubeTask()
				.execute(lYouTubeId);
	}

	/**
	 * Determine the messages to display during video load and initialization.
	 */
	private void extractMessages() {
		Intent lInvokingIntent = getIntent();
		String lMsgInit = lInvokingIntent.getStringExtra(MSG_INIT);
		if (lMsgInit != null) {
			mMsgInit = lMsgInit;
		}
		String lMsgDetect = lInvokingIntent.getStringExtra(MSG_DETECT);
		if (lMsgDetect != null) {
			mMsgDetect = lMsgDetect;
		}
		String lMsgPlaylist = lInvokingIntent.getStringExtra(MSG_PLAYLIST);
		if (lMsgPlaylist != null) {
			mMsgPlaylist = lMsgPlaylist;
		}
		String lMsgToken = lInvokingIntent.getStringExtra(MSG_TOKEN);
		if (lMsgToken != null) {
			mMsgToken = lMsgToken;
		}
		String lMsgLoBand = lInvokingIntent.getStringExtra(MSG_LO_BAND);
		if (lMsgLoBand != null) {
			mMsgLowBand = lMsgLoBand;
		}
		String lMsgHiBand = lInvokingIntent.getStringExtra(MSG_HI_BAND);
		if (lMsgHiBand != null) {
			mMsgHiBand = lMsgHiBand;
		}
		String lMsgErrTitle = lInvokingIntent.getStringExtra(MSG_ERROR_TITLE);
		if (lMsgErrTitle != null) {
			mMsgErrorTitle = lMsgErrTitle;
		}
		String lMsgErrMsg = lInvokingIntent.getStringExtra(MSG_ERROR_MSG);
		if (lMsgErrMsg != null) {
			mMsgError = lMsgErrMsg;
		}
	}

	/**
	 * Create the view in which the video will be rendered.
	 */
	private void setupView() {
		videoSurface = (SurfaceView) findViewById(R.id.videoSurface);
		SurfaceHolder videoHolder = videoSurface.getHolder();
		videoHolder.addCallback(this);
		player = new MediaPlayer();
		controller = new VideoControllerView(this);
		//tgLove = findViewById(R.id.t)

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		YouTubeUtility.markVideoAsViewed(this, mVideoId);

		if (mQueryYouTubeTask != null) {
			mQueryYouTubeTask.cancel(true);
		}
		player.stop();
		player.release();
		player = null;

		// clear the flag that keeps the screen ON
		getWindow().clearFlags(
				android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		this.mQueryYouTubeTask = null;

	}

	public void updateProgress(String pProgressMsg) {
		try {
			mProgressMessage.setText(pProgressMsg);
		} catch (Exception e) {
			Log.e(this.getClass().getSimpleName(),
					"Error updating video status!", e);
		}
	}

	private class ProgressUpdateInfo {

		public String mMsg;

		public ProgressUpdateInfo(String pMsg) {
			mMsg = pMsg;
		}
	}

	/**
	 * Task to figure out details by calling out to YouTube GData API. We only
	 * use public methods that don't require authentication.
	 */
	private class QueryYouTubeTask extends
			AsyncTask<YouTubeId, ProgressUpdateInfo, Uri> {

		private boolean mShowedError = false;
		public Uri u = null;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (pDialog == null) {
				pDialog = Statics
						.createProgressDialog(OpenYouTubePlayerActivity.this);
				pDialog.show();
			} else {
				pDialog.show();
			}
		}

		@Override
		protected Uri doInBackground(YouTubeId... pParams) {
			// u = Uri.parse("afb");
			if (pParams[0] instanceof FileId) {
				return Uri.parse(pParams[0].getId());
			} else {
				String lUriStr = null;
				String lYouTubeFmtQuality = "17"; // 3gpp medium quality, which
													// should be fast enough to
													// view over EDGE connection
				String lYouTubeVideoId = null;

				if (isCancelled())
					return null;

				try {

					publishProgress(new ProgressUpdateInfo(mMsgDetect));

					WifiManager lWifiManager = (WifiManager) OpenYouTubePlayerActivity.this
							.getSystemService(Context.WIFI_SERVICE);
					TelephonyManager lTelephonyManager = (TelephonyManager) OpenYouTubePlayerActivity.this
							.getSystemService(Context.TELEPHONY_SERVICE);

					// //////////////////////////
					// if we have a fast connection (wifi or 3g), then we'll get
					// a high quality YouTube video
					if ((lWifiManager.isWifiEnabled()
							&& lWifiManager.getConnectionInfo() != null && lWifiManager
							.getConnectionInfo().getIpAddress() != 0)
							|| ((lTelephonyManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS
									||

									/*
									 * icky... using literals to make backwards
									 * compatible with 1.5 and 1.6
									 */
									lTelephonyManager.getNetworkType() == 9 /* HSUPA */
									|| lTelephonyManager.getNetworkType() == 10 /* HSPA */
									|| lTelephonyManager.getNetworkType() == 8 /* HSDPA */
									|| lTelephonyManager.getNetworkType() == 5 /* EVDO_0 */|| lTelephonyManager
									.getNetworkType() == 6 /* EVDO A */)

							&& lTelephonyManager.getDataState() == TelephonyManager.DATA_CONNECTED)) {
						lYouTubeFmtQuality = "18";
					}

					// /////////////////////////////////
					// if the intent is to show a playlist, get the latest video
					// id from the playlist, otherwise the video
					// id was explicitly declared.
					if (pParams[0] instanceof PlaylistId) {
						publishProgress(new ProgressUpdateInfo(mMsgPlaylist));
						lYouTubeVideoId = YouTubeUtility
								.queryLatestPlaylistVideo((PlaylistId) pParams[0]);
					} else if (pParams[0] instanceof VideoId) {
						lYouTubeVideoId = pParams[0].getId();
					}

					mVideoId = lYouTubeVideoId;

					publishProgress(new ProgressUpdateInfo(mMsgToken));

					if (isCancelled())
						return null;

					// //////////////////////////////////
					// calculate the actual URL of the video, encoded with
					// proper YouTube token
					lUriStr = YouTubeUtility.calculateYouTubeUrl(
							lYouTubeFmtQuality, true, lYouTubeVideoId);

					if (isCancelled())
						return null;

					if (lYouTubeFmtQuality.equals("17")) {
						publishProgress(new ProgressUpdateInfo(mMsgLowBand));
					} else {
						publishProgress(new ProgressUpdateInfo(mMsgHiBand));
					}

				} catch (Exception e) {
					Log.e(this.getClass().getSimpleName(),
							"Error occurred while retrieving information from YouTube.",
							e);
				}

				if (lUriStr != null) {
					myUri = Uri.parse(lUriStr);
					return Uri.parse(lUriStr);
				} else {
					return null;
				}
			}
		}

		@Override
		protected void onPostExecute(Uri pResult) {
			super.onPostExecute(pResult);
			// Dismiss dialog
			pDialog.dismiss();
			try {
				if (isCancelled())
					return;

				if (pResult == null) {
					throw new RuntimeException("Invalid NULL Url.");
				}
				if (pResult != null)
					myUri = pResult;
				u = pResult;
				// mVideoView.setVideoURI(pResult);

				try {
					player.setAudioStreamType(AudioManager.STREAM_MUSIC);
					player.setDataSource(OpenYouTubePlayerActivity.this, myUri);
					player.prepareAsync();
					player.setOnPreparedListener(OpenYouTubePlayerActivity.this);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (isCancelled())
					return;

				if (isCancelled())
					return;

				Bundle bundle = getIntent().getExtras();

				boolean showControllerOnStartup = false;

				if (!(bundle == null))
					showControllerOnStartup = bundle.getBoolean(
							"show_controller_on_startup", false);
				if (isCancelled())
					return;

			} catch (Exception e) {
				Log.e(this.getClass().getSimpleName(), "Error playing video!",
						e);

				if (!mShowedError) {
					showErrorAlert();
				}
			}
		}

		private void showErrorAlert() {

			try {
				Builder lBuilder = new AlertDialog.Builder(
						OpenYouTubePlayerActivity.this);
				lBuilder.setTitle(mMsgErrorTitle);
				lBuilder.setCancelable(false);
				lBuilder.setMessage(mMsgError);

				lBuilder.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface pDialog,
									int pWhich) {
								OpenYouTubePlayerActivity.this.finish();
							}

						});

				AlertDialog lDialog = lBuilder.create();
				lDialog.show();
			} catch (Exception e) {
				Log.e(this.getClass().getSimpleName(),
						"Problem showing error dialog.", e);
			}
		}

		@Override
		protected void onProgressUpdate(ProgressUpdateInfo... pValues) {
			super.onProgressUpdate(pValues);

			// OpenYouTubePlayerActivity.this.updateProgress(pValues[0].mMsg);
		}

	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		player.setDisplay(holder);

		// player.prepareAsync();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		controller.setMediaPlayer(this);
		controller
				.setAnchorView((FrameLayout) findViewById(R.id.videoSurfaceContainer));
		player.setLooping(true);
		player.start();

	}

	// Implement VideoMediaController.MediaPlayerControl
	@Override
	public boolean canPause() {
		return true;
	}

	@Override
	public boolean canSeekBackward() {
		return true;
	}

	@Override
	public boolean canSeekForward() {
		return true;
	}

	@Override
	public int getBufferPercentage() {
		return 0;
	}

	@Override
	public int getCurrentPosition() {
		if (player != null)
			return player.getCurrentPosition();
		return 0;
	}

	@Override
	public int getDuration() {
		if(player != null)
			return player.getDuration();
		return 0;
	}

	@Override
	public boolean isPlaying() {
		if(player != null)
			return player.isPlaying();
		return false;
	}

	@Override
	public void pause() {
		player.pause();
	}

	@Override
	public void seekTo(int i) {
		if(player != null)
			player.seekTo(i);
	}

	@Override
	public void start() {
		if(player != null)
			player.start();
	}

	@Override
	public boolean isFullScreen() {
		return false;
	}

	@Override
	public void toggleFullScreen() {
		String text;
		if (!isFullScreen) {
			isFullScreen = true;
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
			controller.setFull(true);
		} else {
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			isFullScreen = false;
			controller.setFull(false);

		}
		// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		controller.show();
		return false;
	}

}