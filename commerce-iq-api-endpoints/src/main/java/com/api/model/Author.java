package com.api.model;

import org.json.simple.JSONObject;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Configuration
public class Author extends BasicModelAbstract{
	private String firstName; 
	private String lastName; 
	private int post;
	private int ID;
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObject()
	{
		JSONObject obj=new JSONObject();
		obj.put("firstName", getFirstName());
		obj.put("lastName", getLastName());
		obj.put("id", getID());
		obj.put("post", getPost());
		return obj;
		
	}

	

}
