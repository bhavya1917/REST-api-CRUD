package com.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.logError.LogExceptionMessage;
import com.api.model.Author;
import com.api.model.MessageResponse;
import com.api.model.Post;
import com.api.service.RestWriteService;

@RestController
public class RestWriteController {
	@Autowired
	RestWriteService writeService;

	@PostMapping("/authors")
	public ResponseEntity<MessageResponse> createAuthor(@RequestBody Author author) {
		try {
			writeService.createAuthor(author);
			return ResponseEntity
					.ok(new MessageResponse("The author provided in the request has been successfully created."));
		} catch (LogExceptionMessage e) {
			return ResponseEntity.status(e.getStatus()).body(new MessageResponse(e.getMessage()));
		}
	}

	@PutMapping("/author/{authId}")
	public ResponseEntity<MessageResponse> putAuthor(@PathVariable("authId") int authId, @RequestBody Author author) {
		try {
			writeService.putAuthor(authId, author);
			return ResponseEntity
					.ok(new MessageResponse("The author provided in the request has been successfully added."));
		} catch (LogExceptionMessage e) {
			return ResponseEntity.status(e.getStatus()).body(new MessageResponse(e.getMessage()));
		}
	}

	@PutMapping("/post/{postId}")
	public ResponseEntity<MessageResponse> putPost(@PathVariable("postId") int postId, @RequestBody Post post) {
		try {
			writeService.putPost(postId, post);
			return ResponseEntity
					.ok(new MessageResponse("The post provided in the request has been successfully added."));
		} catch (LogExceptionMessage e) {
			return ResponseEntity.status(e.getStatus()).body(new MessageResponse(e.getMessage()));
		}
	}

	@PostMapping("/posts")
	public ResponseEntity<MessageResponse> createPost(@RequestBody Post post) {
		try {
			writeService.createPost(post);
			return ResponseEntity
					.ok(new MessageResponse("The post provided in the request has been successfully added."));
		} catch (LogExceptionMessage e) {
			return ResponseEntity.status(e.getStatus()).body(new MessageResponse(e.getMessage()));
		}

	}

	@DeleteMapping("/authors/{authid}")
	public ResponseEntity<MessageResponse> deleteAuthor(@PathVariable("authid") int authid) {
		try {
			writeService.deleteAuthorbyId(authid);
			return ResponseEntity
					.ok(new MessageResponse("The author provided in the request has been successfully deleted."));
		} catch (LogExceptionMessage e) {
			return ResponseEntity.status(e.getStatus()).body(new MessageResponse(e.getMessage()));
		}
	}

	@DeleteMapping("/posts/{postid}")
	public ResponseEntity<MessageResponse> deletePost(@PathVariable("postid") int postid) {
		try {
			writeService.deletePostbyId(postid);
			return ResponseEntity
					.ok(new MessageResponse("The post provided in the request has been successfully deleted."));
		} catch (LogExceptionMessage e) {
			return ResponseEntity.status(e.getStatus()).body(new MessageResponse(e.getMessage()));
		}
	}

	@PatchMapping("/posts/{postId}")
	public ResponseEntity<MessageResponse> updatePost(@PathVariable("postId") int postId,
			@RequestBody Map<String, Object> map) {
		try {
			writeService.updatePost(postId, map);
			return ResponseEntity
					.ok(new MessageResponse("The post provided in the request has been successfully updated."));
		} catch (LogExceptionMessage e) {
			return ResponseEntity.status(e.getStatus()).body(new MessageResponse(e.getMessage()));
		}
	}

	@PatchMapping("/authors/{authorId}")
	public ResponseEntity<MessageResponse> updateAuthor(@PathVariable("authorId") int authId,
			@RequestBody Map<String, Object> map) {
		try {
			writeService.updateAuthor(authId, map);
			return ResponseEntity
					.ok(new MessageResponse("The author provided in the request has been successfully updated."));
		} catch (LogExceptionMessage e) {
			return ResponseEntity.status(e.getStatus()).body(new MessageResponse(e.getMessage()));
		}
	}

}