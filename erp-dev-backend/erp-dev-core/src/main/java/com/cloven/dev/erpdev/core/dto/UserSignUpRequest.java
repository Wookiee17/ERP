package com.cloven.dev.erpdev.core.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserSignUpRequest {

	private String fistname;
	private String email;
	private String password;

	private String lastname;

	// comma separated roles
	private String roles;

}