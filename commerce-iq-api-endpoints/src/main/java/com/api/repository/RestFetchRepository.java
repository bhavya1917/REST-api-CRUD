package com.api.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.api.model.BasicModelAbstract;
import com.api.model.BasicModel;

@Repository
public class RestFetchRepository extends RestParseJsonRepository {
	public List<BasicModelAbstract> filterElementByParam(BasicModel param, String entities) {
		List<BasicModelAbstract> filteredList = new ArrayList<>();
		List<BasicModelAbstract> elementList = findAllEntites(entities);
		for (BasicModelAbstract element : elementList) {
			if (param.equals(element)) {
				filteredList.add(element);
			}
		}
		return filteredList;
	}

	public BasicModelAbstract filterElementById(int elementId, String entities) {
		List<BasicModelAbstract> elementList = findAllEntites(entities);
		for (BasicModelAbstract element : elementList) {
			if (element.getID() == elementId)
				return element;
		}
		return null;
	}

	public List<BasicModelAbstract> findAllEntites(String element) {
		List<BasicModelAbstract> entities = new ArrayList<BasicModelAbstract>();
		for (Object obj : getJsonArray(element)) {
			entities.add(parseElementObject((JSONObject) obj, element));
		}
		return entities;
	}

	public List<Object> fetchAllElements() {
		return Arrays.asList(getJsonArray());
	}

	public boolean validateID(int ID, String type) {
		JSONArray jsonArray = (JSONArray) getJsonArray().get(type);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject key = (JSONObject) jsonArray.get(i);
			Long id = (Long) key.get("id");
			if (id.intValue() == ID) {
				return true;
			}
		}
		return false;
	}

}
