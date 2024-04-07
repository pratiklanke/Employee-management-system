package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.app.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDto {

	@JsonProperty(access = Access.READ_ONLY) // used during serialization
	private Integer id;
	
	@NotBlank
	private String name;

	@Email
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY) //required only in de-ser.
	private String password;
	
	private Role role;
	
}
