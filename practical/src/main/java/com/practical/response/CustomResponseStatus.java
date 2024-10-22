package com.practical.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomResponseStatus{

	FAILURE(0, "FAILURE"),
	
	SUCCESS(1, "SUCCESS");
	
	private int status;
	
	private String message;
}
