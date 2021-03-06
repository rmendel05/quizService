package com.rmendel.quizManager.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.rmendel.framework.RMLog;
import com.rmendel.quizManager.api.*;
import com.wordnik.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/api")
public class SwaggerApplication extends Application {
	
	public static final String DOCUMENTATION_TITLE = "Quiz Service";
	public static final String SERVICE_API_VERSION = "1.0.0";
	public static final boolean REBUILD_DOCUMENTATION = true;
	
	public SwaggerApplication() {
		RMLog.info("Initializing Swagger documentation...");
    	initializeBeanConfig();
	}
	
	private void initializeBeanConfig() {

		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setTitle(DOCUMENTATION_TITLE);
        beanConfig.setVersion(SERVICE_API_VERSION);
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost(QuizServerMain.getHost());
        beanConfig.setBasePath(QuizServerMain.CONTEXT_PATH);
        beanConfig.setResourcePackage("com.wordnik.swagger.jaxrs.json,com.wordnik.swagger.jaxrs.listing,com.rmendel.quizManager.service,com.rmendel.quizManager.api,com.rmendel.framework");
        //io.swagger.resources,
        beanConfig.setScan(REBUILD_DOCUMENTATION);
	}

	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<Class<?>>();

        // Quiz Server resources
        resources.add(QuestionService.class);
        resources.add(AnswerDto.class);
        resources.add(FilterSpecDto.class);
        resources.add(PageSpecDto.class);
        resources.add(QuerySpecDto.class);
        resources.add(QuestionDto.class);
        resources.add(QuestionResponseDto.class);
        resources.add(SortSpecDto.class);
        
        // Swagger resources
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.SwaggerSerializers.class);

        return resources;
    }
}