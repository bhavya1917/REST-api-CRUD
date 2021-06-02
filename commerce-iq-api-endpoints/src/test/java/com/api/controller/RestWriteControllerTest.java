package com.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.api.Main;
import com.api.model.Author;
import com.api.model.MessageResponse;
import com.api.model.Post;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestWriteControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Before
	public void setup() {
		restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	}

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void testCreateAuthor() {
		Author author = new Author();
		author.setID(21);
		author.setFirstName("Anne");
		author.setLastName("Frank");
		author.setPost(61);

		ResponseEntity<MessageResponse> postResponse = restTemplate.postForEntity(getRootUrl() + "/authors", author,
				MessageResponse.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testPutAuthor() {
		Author author = new Author();
		author.setID(1);
		author.setFirstName("Anne");
		author.setLastName("Frank");
		author.setPost(61);
		restTemplate.put(getRootUrl() + "/author/" + 1, author);

		ResponseEntity<MessageResponse> updatedUser = restTemplate.getForEntity(getRootUrl() + "/authors/" + 1,
				MessageResponse.class);
		Assert.assertNotNull(updatedUser);
	}

	@Test
	public void testPutAuthorWithNonExistentId() {
		Author author = new Author();
		author.setID(61);
		author.setFirstName("Anne");
		author.setLastName("Frank");
		author.setPost(61);
		restTemplate.put(getRootUrl() + "/author/" + 61, author);
		ResponseEntity<MessageResponse> updatedUser = restTemplate.getForEntity(getRootUrl() + "/authors/" + 61,
				MessageResponse.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, updatedUser.getStatusCode());
	}

	@Test
	public void testCreatePost() {
		Post post = new Post();
		post.setID(1);
		post.setAuthor("Arundhati Roy");
		post.setReviews(75);
		post.setTitle("The Ministry of Utmost Happiness");
		post.setViews(78);

		ResponseEntity<MessageResponse> postResponse = restTemplate.postForEntity(getRootUrl() + "/posts", post,
				MessageResponse.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testPutPost() {
		Post post = new Post();
		post.setID(1);
		post.setAuthor("Arundhati Roy");
		post.setReviews(75);
		post.setTitle("The Ministry of Utmost Happiness");
		post.setViews(78);
		restTemplate.put(getRootUrl() + "/post/" + 1, post);

		ResponseEntity<MessageResponse> updatedPost = restTemplate.getForEntity(getRootUrl() + "/posts/" + 1,
				MessageResponse.class);
		Assert.assertNotNull(updatedPost);
	}

	@Test
	public void testPutPostWithNonExistentId() {
		Post post = new Post();
		post.setID(18);
		post.setAuthor("Arundhati Roy");
		post.setReviews(75);
		post.setTitle("The Ministry of Utmost Happiness");
		post.setViews(78);
		restTemplate.put(getRootUrl() + "/post/" + 18, post);

		ResponseEntity<MessageResponse> updatedPost = restTemplate.getForEntity(getRootUrl() + "/posts/" + 18,
				MessageResponse.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, updatedPost.getStatusCode());
	}

	@Test
	public void testDeletePost() {
		ResponseEntity<MessageResponse> post = restTemplate.getForEntity(getRootUrl() + "/posts/" + 1,
				MessageResponse.class);
		Assert.assertNotNull(post);

		restTemplate.delete(getRootUrl() + "/posts/" + 1);

		try {
			post = restTemplate.getForEntity(getRootUrl() + "/posts/" + 1, MessageResponse.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

	@Test
	public void testDeleteAuthor() {
		ResponseEntity<MessageResponse> author = restTemplate.getForEntity(getRootUrl() + "/authors/" + 1,
				MessageResponse.class);
		Assert.assertNotNull(author);

		restTemplate.delete(getRootUrl() + "/authors/" + 1);

		try {
			author = restTemplate.getForEntity(getRootUrl() + "/authors/" + 1, MessageResponse.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

	@Test
	public void testDeleteAuthorWithInvalidId() {
		restTemplate.delete(getRootUrl() + "/authors/" + 19);
	}

	@Test
	public void testDeletePostWithInvalidId() {
		restTemplate.delete(getRootUrl() + "/posts/" + 19);
	}

	@Test
	public void testUpdatePost() {
		int id = 2;
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("reviews", 56);
		restTemplate.patchForObject(getRootUrl() + "/posts/" + id, requestMap, MessageResponse.class);
		Post updatedPost = restTemplate.getForObject(getRootUrl() + "/posts/" + id, Post.class);
		Assert.assertEquals(56, updatedPost.getReviews());
	}

	@Test
	public void testUpdatePostWithInvalidRequestId() {
		int id = 200;
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("reviews", 56);
		restTemplate.patchForObject(getRootUrl() + "/posts/" + id, requestMap, MessageResponse.class);
		try {
			restTemplate.getForObject(getRootUrl() + "/posts/" + id, Post.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

	@Test
	public void testUpdateAuthor() {
		int id = 2;
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("post", 65);
		restTemplate.patchForObject(getRootUrl() + "/authors/" + id, requestMap, MessageResponse.class);
		try {
			restTemplate.getForObject(getRootUrl() + "/authors/" + id, Author.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

	@Test
	public void testUpdateAuthorWithInvalidRequestId() {
		int id = 200;
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("post", 56);
		restTemplate.patchForObject(getRootUrl() + "/authors/" + id, requestMap, MessageResponse.class);
		try {
			restTemplate.getForObject(getRootUrl() + "/authors/" + id, Post.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}