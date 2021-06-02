package com.api.model;

import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Configuration
public class AuthorModel implements BasicModel {
	private String firstName; 
	private String lastName;
	
	public AuthorModel(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	} 
	
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		Author otherObject = (Author) object;
		if (firstName == null) {
			if (otherObject.getFirstName() != null)
				return false;
		} else if (!firstName.equals(otherObject.getFirstName()))
			return false;
		if (lastName == null) {
			if (otherObject.getLastName() != null)
				return false;
		} else if (!lastName.equals(otherObject.getLastName()))
			return false;
		return true;
	}
}
