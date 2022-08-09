package com.example.demoHttpResponse;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponse {

	private int httpStatusCode;
	private HttpStatus httpStatus;
	private String reason;
	private String message;
}
