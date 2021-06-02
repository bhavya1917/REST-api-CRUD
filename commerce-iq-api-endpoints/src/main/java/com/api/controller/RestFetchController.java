package com.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.logError.LogExceptionMessage;
import com.api.model.AuthorModel;
import com.api.model.BasicModelAbstract;
import com.api.model.BasicModel;
import com.api.model.MessageResponse;
import com.api.model.PostModel;
import com.api.service.RestFetchService;

@RestController
public class RestFetchController {

	@Autowired
	RestFetchService restFetchService;

	@GetMapping("/")
	public ResponseEntity<List<Object>> getAll() {
		return ResponseEntity.ok(restFetchService.fetchAll());
	}

	@GetMapping("/authors")
	public ResponseEntity<List<BasicModelAbstract>> getAllAuthors() {
		return ResponseEntity.ok(restFetchService.fetchAllAuthors());
	}

	@GetMapping("/authors/{authid}")
	public ResponseEntity<?> getAuthor(@PathVariable("authid") int authid) {
		try {
			return ResponseEntity.of(restFetchService.fetchAuthorById(authid));
		} catch (LogExceptionMessage e) {
			return ResponseEntity.status(e.getStatus()).body(new MessageResponse(e.getMessage()));
		}
	}

	@GetMapping("/post")
	public ResponseEntity<?> getFileteredPosts(@RequestParam("title") String title,
			@RequestParam("author") String author) {
		BasicModel param = new PostModel(title, author);
		return ResponseEntity.of(restFetchService.fetchPostsByParam(param));
	}

	@GetMapping("/author")
	public ResponseEntity<?> getFileteredAuthors(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		BasicModel param = new AuthorModel(firstName, lastName);
		return ResponseEntity.of(restFetchService.fetchAuthorsByParams(param));
	}

	@GetMapping("/posts")
	public ResponseEntity<List<BasicModelAbstract>> getAllPosts() {
		return ResponseEntity.ok(restFetchService.fetchAllPosts());
	}

	@GetMapping("/posts/{postid}")
	public ResponseEntity<?> getPost(@PathVariable("postid") int postid) {
		try {
			return ResponseEntity.of(restFetchService.fetchPostById(postid));
		} catch (LogExceptionMessage e) {
			return ResponseEntity.status(e.getStatus()).body(new MessageResponse(e.getMessage()));
		}
	}

}