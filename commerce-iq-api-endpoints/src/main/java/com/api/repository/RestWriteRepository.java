package com.api.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.api.model.BasicModelAbstract;

@Repository
public class RestWriteRepository extends RestParseJsonRepository {
	@Autowired
	RestFetchRepository restFetchRepository;

	public void filewriter(JSONObject jsonObject) {
		try {
			FileWriter writer = new FileWriter(new ClassPathResource("store.json").getFile());
			writer.write(jsonObject.toJSONString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public boolean createElement(BasicModelAbstract element, String type) {
		if (!restFetchRepository.validateID(element.getID(), type)) {
			JSONObject object = getJsonArray();
			JSONArray array = (JSONArray) object.get(type);
			array.add(element.getJSONObject());
			object.put(type, array);
			filewriter(object);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean deleteElement(String element, int id) {
		if (restFetchRepository.validateID(id, element)) {
			JSONObject jsonObject = getJsonArray();
			JSONArray jsonArray = (JSONArray) jsonObject.get(element);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject key = (JSONObject) jsonArray.get(i);
				Long ID = (Long) key.get("id");
				if (ID.intValue() == id) {
					jsonArray.remove(i);
					break;
				}
			}
			jsonObject.put(element, jsonArray);
			filewriter(jsonObject);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean updateElement(int id, Map<String, Object> update, String element) {
		if (restFetchRepository.validateID(id, element) && !update.containsKey("id")) {
			JSONObject jsonObject = getJsonArray();
			JSONArray jsonArray = (JSONArray) jsonObject.get(element);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject key = (JSONObject) jsonArray.get(i);
				Long ID = (Long) key.get("id");
				if (ID.intValue() == id) {
					for (String str : update.keySet()) {
						key.put(str, update.get(str));
					}
				}
			}
			jsonObject.put(element, jsonArray);
			filewriter(jsonObject);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean putElement(int id, BasicModelAbstract element, String type) {
		if (restFetchRepository.validateID(id, type)) {
			JSONObject jsonObject = getJsonArray();
			JSONArray jsonArray = (JSONArray) jsonObject.get(type);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject key = (JSONObject) jsonArray.get(i);
				Long ID = (Long) key.get("id");
				if (ID.intValue() == id) {
					jsonArray.remove(i);
					jsonArray.add(element.getJSONObject());
					break;
				}
			}
			Set<Integer> ids=new HashSet<>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject key = (JSONObject) jsonArray.get(i);
				ids.add(Integer.parseInt(String.valueOf(key.get("id"))));
			}
			if(ids.size()!=jsonArray.size())return false;
			jsonObject.put(type, jsonArray);
			filewriter(jsonObject);
			return true;
		}
		return false;
	}

}
