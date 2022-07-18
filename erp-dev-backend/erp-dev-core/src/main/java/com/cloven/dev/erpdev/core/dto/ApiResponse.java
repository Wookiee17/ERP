package com.cloven.dev.erpdev.core.dto;

import lombok.Data;

@Data
public class ApiResponse {

	private int statusCode;
	private boolean isError;
	private String uri;
	private String message;
	
	//api specific response
	private Object responseBody;
	
	
}