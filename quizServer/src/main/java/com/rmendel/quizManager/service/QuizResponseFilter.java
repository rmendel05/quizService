package com.rmendel.quizManager.service;

import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Provider
public class QuizResponseFilter implements ContainerResponseFilter {
	 
	@Override
	public ContainerResponse filter(
			ContainerRequest request,
			ContainerResponse response) {

		// Support x-site access for use with Swagger and other tools
		response.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
		return response;
	}
}
