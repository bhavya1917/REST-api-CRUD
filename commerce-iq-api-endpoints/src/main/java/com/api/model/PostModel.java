package com.api.model;

import org.springframework.context.annotation.Configuration;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@Configuration
public class PostModel implements BasicModel {
	private String title;
	private String author;
	public PostModel(String title, String author) {
		super();
		this.title = title;
		this.author = author;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Post other = (Post) obj;
		if (author == null) {
			if (other.getTitle() != null)
				return false;
		} else if (!author.equals(other.getAuthor()))
			return false;
		if (title == null) {
			if (other.getTitle() != null)
				return false;
		} else if (!title.equals(other.getTitle()))
			return false;
		return true;
	}
}
