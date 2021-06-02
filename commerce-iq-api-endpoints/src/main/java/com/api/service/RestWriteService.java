package com.api.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.logError.LogExceptionMessage;
import com.api.model.Author;
import com.api.model.Post;
import com.api.repository.RestWriteRepository;

@Service
public class RestWriteService {
	@Autowired
	RestWriteRepository writeRepository;

	public void createAuthor(Author author) throws LogExceptionMessage {
		if (!writeRepository.createElement(author, "authors")) {
			throw LogExceptionMessage.status(HttpStatus.BAD_REQUEST)
					.message("The authorID " + author.getID() + " provided in the request already exists");
		}
	}

	public void createPost(Post post) throws LogExceptionMessage {
		if (!writeRepository.createElement(post, "posts")) {
			throw LogExceptionMessage.status(HttpStatus.BAD_REQUEST)
					.message("The postID " + post.getID() + " provided in the request already exists");
		}
	}

	public void putAuthor(int ID, Author author) throws LogExceptionMessage {
		if (!writeRepository.putElement(ID, author, "authors")) {
			throw LogExceptionMessage.status(HttpStatus.BAD_REQUEST)
					.message("Invalid Request. Duplication of ID from request is not allowed or the id "+ID+" that you are trying to update does not exist.");
		}
	}

	public void putPost(int ID, Post post) throws LogExceptionMessage {
		if (!writeRepository.putElement(ID, post, "posts")) {
			throw LogExceptionMessage.status(HttpStatus.BAD_REQUEST)
					.message("Invalid Request. Duplication of ID from request is not allowed or the id "+ID+" that you are trying to update does not exist.");
		}
	}

	public void deletePostbyId(int Id) throws LogExceptionMessage {
		if (!writeRepository.deleteElement("posts", Id)) {
			throw LogExceptionMessage.status(HttpStatus.BAD_REQUEST)
					.message("The postID  " + Id + " provided in the request does not exist");
		}
	}

	public void deleteAuthorbyId(int Id) throws LogExceptionMessage {
		if (!writeRepository.deleteElement("authors", Id)) {
			throw LogExceptionMessage.status(HttpStatus.BAD_REQUEST)
					.message("The authID  " + Id + " provided in the request does not exist");
		}
	}

	public void updatePost(int id, Map<String, Object> mapReq) throws LogExceptionMessage {
		if (!writeRepository.updateElement(id, mapReq, "posts")) {
			throw LogExceptionMessage.status(HttpStatus.BAD_REQUEST).message("PostId cannot be updated.");
		}
	}

	public void updateAuthor(int id, Map<String, Object> mapReq) throws LogExceptionMessage {
		if (!writeRepository.updateElement(id, mapReq, "authors")) {
			throw LogExceptionMessage.status(HttpStatus.BAD_REQUEST).message("AuthId cannot be updated.");
		}
	}
}
