package com.api.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Configuration;

import com.api.model.Author;
import com.api.model.BasicModelAbstract;
import com.api.model.Post;
@Configuration

public class RestParseJsonRepository {

	public JSONArray getJsonArray(String element) {
		try {
			return (JSONArray) (getJsonObject().get(element));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject getJsonArray() {
		try {
			return getJsonObject();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private JSONObject getJsonObject() throws FileNotFoundException, IOException, ParseException {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("store.json");
		JSONParser jsonParser = new JSONParser();
		return (JSONObject)jsonParser.parse(
				new InputStreamReader(in, "UTF-8"));
	}

	public BasicModelAbstract parseElementObject(JSONObject object, String element) {
		switch (element) {
		case "authors":
			return new Author((String) object.get("lastName"), (String) object.get("firstName"),
					((Long) object.get("id")).intValue(), ((Long) object.get("post")).intValue());

		case "posts":
			return new Post(((Long) object.get("id")).intValue(), (String) object.get("title"),
					(String) object.get("author"), ((Long) object.get("views")).intValue(),
					((Long) object.get("reviews")).intValue());
		}
		return null;
	}
}
