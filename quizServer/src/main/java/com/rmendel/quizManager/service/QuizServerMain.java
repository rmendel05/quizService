package com.rmendel.quizManager.service;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.rmendel.framework.RMLog;
import com.rmendel.quizManager.application.QuestionCache;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.servlet.ServletContainer;

public class QuizServerMain
{
	public static final String HOST_SERVER = "localhost";
	public static final int HOST_PORT = 8080;
	public static final String CONTEXT_PATH = "/api";

	public static void main(String[] args) throws Exception {
    	RMLog.info("Starting Quiz Server...");
    	
    	// Force Question Cache load first
    	QuestionCache.getInstance();
    	
    	RMLog.info("Initializing servlet...");
        ServletHolder servlet = new ServletHolder(ServletContainer.class);    

        servlet.setName("quizServer");
        servlet.setInitParameter("javax.ws.rs.Application", "com.rmendel.quizManager.service.SwaggerApplication");
//        servlet.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
        servlet.setInitParameter("com.sun.jersey.config.property.packages", "com.rmendel.quizManager.service,com.rmendel.quizManager.api");
        servlet.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        servlet.setInitParameter(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, QuizResponseFilter.class.getName());
        
        Server server = new Server(QuizServerMain.HOST_PORT);
        ServletContextHandler context = new ServletContextHandler(
        		server, 
        		QuizServerMain.CONTEXT_PATH, 
        		ServletContextHandler.SESSIONS);
        context.addServlet(servlet, "/*");

    	RMLog.info("Starting web server...");
        server.start();
        server.join();      
     }

	public static String getHost() {
		return HOST_SERVER + ":" + HOST_PORT;
	}
	
}
