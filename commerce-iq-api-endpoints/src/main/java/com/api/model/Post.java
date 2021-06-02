package com.api.model;

import org.json.simple.JSONObject;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class Post extends BasicModelAbstract{
	
	private int ID;
	private String title;
	private String author;
	private int views;
	private int reviews;
		
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObject()
	{
		JSONObject obj=new JSONObject();
		obj.put("title", getTitle());
		obj.put("author",getAuthor());
		obj.put("views", getViews());
		obj.put("reviews",getReviews());
		obj.put("id", getID());
		return obj;
	}
	
}
