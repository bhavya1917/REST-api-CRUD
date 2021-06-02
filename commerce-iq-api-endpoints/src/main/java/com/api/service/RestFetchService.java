package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.BasicModelAbstract;
import com.api.logError.LogExceptionMessage;
import com.api.model.BasicModel;
import com.api.repository.RestFetchRepository;

import java.util.Optional;

import org.springframework.http.HttpStatus;

@Service
public class RestFetchService {
	@Autowired
	RestFetchRepository restFetchRepository;

	public Optional<BasicModelAbstract> fetchAuthorById(int id) throws LogExceptionMessage {
		if (!restFetchRepository.validateID(id, "authors")) {
			throw LogExceptionMessage.status(HttpStatus.BAD_REQUEST)
					.message("Author id " + id + " provided in the request is invalid and does not exist");
		}
		return Optional.ofNullable(restFetchRepository.filterElementById(id, "authors"));
	}

	public List<BasicModelAbstract> fetchAllPosts() {
		return restFetchRepository.findAllEntites("posts");
	}

	public Optional<BasicModelAbstract> fetchPostById(int id) throws LogExceptionMessage {
		if (!restFetchRepository.validateID(id, "posts")) {
			throw LogExceptionMessage.status(HttpStatus.BAD_REQUEST)
					.message("Post id " + id + " provided in the request is invalid and does not exist");
		}
		return Optional.ofNullable(restFetchRepository.filterElementById(id, "posts"));
	}

	public List<BasicModelAbstract> fetchAllAuthors() {
		return restFetchRepository.findAllEntites("authors");
	}

	public Optional<List<BasicModelAbstract>> fetchPostsByParam(BasicModel param) {
		return Optional.ofNullable(restFetchRepository.filterElementByParam(param, "posts"));
	}

	public Optional<List<BasicModelAbstract>> fetchAuthorsByParams(BasicModel param) {
		return Optional.ofNullable(restFetchRepository.filterElementByParam(param, "authors"));
	}

	public List<Object> fetchAll() {
		return restFetchRepository.fetchAllElements();
	}
}
