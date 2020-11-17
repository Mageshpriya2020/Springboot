package com.asminds.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue
	int id;
	
	@NotNull(message = "must be declare Name")
	@Size(min = 2, message = "Name should have atleast 2 characters")
	String name;
	
	@NotBlank(message = "must be declare email")
	String email;
}
