package com.api.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
		 JSONParser jsonParser = new JSONParser();
		    String path=System.getProperty("user.home")+"/store.json";
		    File customDir = new File(path);
		    FileReader reader=new FileReader(customDir);
			JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);
			reader.close();
			return jsonObject;
	}

	public BasicModelAbstract parseElementObject(JSONObject object, String element) {
		switch (element) {
		case "authors":
			return new Author((String) object.get("firstName"), (String) object.get("lastName"),
					((Long) object.get("post")).intValue(), ((Long) object.get("id")).intValue());

		case "posts":
			return new Post(((Long) object.get("id")).intValue(), (String) object.get("title"),
					(String) object.get("author"), ((Long) object.get("views")).intValue(),
					((Long) object.get("reviews")).intValue());
		}
		return null;
	}
}
