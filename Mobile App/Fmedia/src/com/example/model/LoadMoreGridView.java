package com.example.model;

import com.example.vlogger.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LoadMoreGridView extends GridView implements OnScrollListener {

	private OnScrollListener mOnScrollListener;
	
	private LayoutInflater mLayoutInflater;
	
	private RelativeLayout mFooterView;
	
	private ProgressBar mProgressBar;
	
	private OnLoadMoreListener mOnLoadMoreListener;
	
	private boolean mIsLoadingMore = false;
	
	private int mCurrentScrollState;
	
	private boolean mIsFinishLoadAll = false;
	Button b;
	public LoadMoreGridView(Context context) {
		super(context);
		init(context);
	}

	public LoadMoreGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public LoadMoreGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	public void init(Context context) {
		mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		b = (Button) findViewById(R.id.btn_LoadMoreGrid);
		super.setOnScrollListener(this);
	}
	
	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);
	}

	@Override
	public void setOnScrollListener(OnScrollListener l) {
		mOnScrollListener = l;
	}
	
	public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
		mOnLoadMoreListener = onLoadMoreListener;
	}

	public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
		if(mOnScrollListener != null) {
			mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		}
		if(mOnLoadMoreListener != null && mIsFinishLoadAll == false) {
			if (visibleItemCount == totalItemCount) {
				//mFooterView.setVisibility(View.GONE);
				
				return;
			}
			boolean loadmore = firstVisibleItem + visibleItemCount >= totalItemCount;
			if(!mIsLoadingMore && loadmore && mCurrentScrollState != SCROLL_STATE_IDLE) {
				mIsLoadingMore = true;
				
				//mFooterView.setVisibility(View.VISIBLE);
				//b.setVisibility(View.VISIBLE);
				onLoadMore();
			}
		}
	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {
		mCurrentScrollState = scrollState;
		if(mOnScrollListener != null) {
			mOnScrollListener.onScrollStateChanged(view, scrollState);
		}
	}
	
	public void setFinishLoadAll() {
		mIsFinishLoadAll = true;
		//mFooterView.setVisibility(View.GONE);
	}
	
	public void onLoadMore() {
		//mProgressBar.setVisibility(View.VISIBLE);
		
		/*Toast toast = Toast.makeText(getContext(), "Loading More", 20);
		toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.setDuration(20);
		
		toast.show();*/
		if(mOnLoadMoreListener != null) {
			mOnLoadMoreListener.onLoadMore();
		}
	}
	
	public void onLoadMoreComplete() {
		//mProgressBar.setVisibility(View.GONE);
		mIsLoadingMore = false;
	}
	
	public interface OnLoadMoreListener {
		public void onLoadMore();
	}
 
}
