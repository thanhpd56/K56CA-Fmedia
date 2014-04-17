package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import com.bumptech.glide.Glide;
import com.example.model.Video;
import com.example.model.VideoManagement;
import com.example.vlogger.R;
import com.example.vlogger.R.id;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class CustomGridViewAdapter extends ArrayAdapter<Video> {
	
	Context context;
	int layoutResourceId;
	ArrayList<Video> arrItem = new ArrayList<Video>();
	public CustomGridViewAdapter(Context context, int resource,
			ArrayList<Video> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.layoutResourceId = resource;
		this.arrItem = objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
		RecordHolder holder = null;
		if(row== null)
		{
			holder = new RecordHolder();
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(R.layout.video_item, parent, false);
			holder.txtTitle = (TextView) row.findViewById(R.id.txtVideo);
			holder.imageItem = (ImageView) row.findViewById(R.id.imgLogoVideo);
			holder.tgButton = (ToggleButton) row.findViewById(R.id.tgLike);
			
			row.setTag(holder);
		} else{
			holder = (RecordHolder) row.getTag();
		}
		Video  item = arrItem.get(position);
		holder.txtTitle.setText(item.getTitle()+"");
		String image_url = item.getImageUrl();
		Glide.load(image_url)
        .centerCrop()
        .into(holder.imageItem);
		
		holder.tgButton.setOnCheckedChangeListener(null);
		holder.tgButton.setChecked(VideoManagement.getInstance().isMyVideo(context, item));
		holder.tgButton.setTag(item.getId());
		
		OnCheckedChangeListener onCheckLikeChange = new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				int id = (Integer) buttonView.getTag();
				for (Video video : arrItem) {
					if(video.getId() == id){
						if(isChecked){
							VideoManagement.getInstance().addMyVideo(context, video);
							
						}else
						{
							VideoManagement.getInstance().removeMyVideo(context, video);
						}
					}
				}
			}
		};
		holder.tgButton.setOnCheckedChangeListener(onCheckLikeChange);
		return row;
	}
	static class RecordHolder{
		TextView txtTitle;
		ImageView imageItem;
		ToggleButton tgButton;
	}
	
	public void appendItems(ArrayList items) {
		 arrItem.addAll(items);
		 notifyDataSetChanged();
		}

	
	
	
	
	
	
	
	
	
	
}
