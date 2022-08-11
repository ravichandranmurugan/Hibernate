package com.example.demo.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.example.demo.constant.SecurityConstant;
import com.example.demoHttpResponse.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		HttpResponse httpResponse = new HttpResponse(new Date(),HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED,
				HttpStatus.UNAUTHORIZED.getReasonPhrase(), SecurityConstant.ACCESS_DENIED_MESSAGE);
		response.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Un-Authorized");
		OutputStream outputStream = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputStream, httpResponse);
		outputStream.flush();

	}

}
