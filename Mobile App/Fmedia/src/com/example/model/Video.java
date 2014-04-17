package com.example.model;

/*
"id": "PL8FT98hnQUiendyuyKHheMsH0WKNf3Gnb",
"snippet": {
 "title": "Món Quà Của Thượng Đế",
 "thumbnails": {
  "high": {
   "url": "https://i.ytimg.com/vi/v6wZVTZiFlo/hqdefault.jpg"
*/
import java.io.Serializable;

import android.graphics.Bitmap;
import android.media.Image;

public class Video implements Serializable{
	int id;
	String title;
	String url;
	String during_time;
	String image_url;
	String description;
	float rating;
	int create_time;
	
	public Video(int id, String title, String url, String during_time, String image_url,
			String desc, float rating, int create_time)
	{
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.during_time = during_time;
		this.image_url = image_url;
		this.description = desc;
		this.rating = rating;
		this.create_time = create_time;
	}
	public Video()
	{
		super();
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getUrl()
	{
		return url;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getDuringTime()
	{
		return during_time;
	}
	
	public void setDuringTime(String dr)
	{
		this.url = dr;
	}
	public String getImageUrl()
	{
		return image_url;
	}
	
	public void setImageUrl(String dr)
	{
		this.image_url = dr;
	}
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String dr)
	{
		this.description = dr;
	}
	
	
	public float getRating()
	{
		return rating;
	}
	
	public void setRating(float dr)
	{
		this.rating = dr;
	}
	
	public int getCreateTime()
	{
		return create_time;
	}
	
	public void setCreateTime(int dr)
	{
		this.create_time = dr;
	}
	
	
	
	
	public Bitmap getImage()
	{
		return null;
	}
	
}
