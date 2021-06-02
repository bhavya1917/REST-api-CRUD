package com.api.model;


import org.json.simple.JSONObject;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Configuration
@NoArgsConstructor
public abstract class BasicModelAbstract {
	@Getter
	@Setter
	private int ID;
	public abstract JSONObject getJSONObject();

}
