package com.rmendel.quizManager.service;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.rmendel.framework.RMLog;
import com.rmendel.quizManager.application.QuestionCache;
import com.sun.jersey.spi.container.servlet.ServletContainer;

public class QuizServerMain
{
    public static void main(String[] args) throws Exception {
    	RMLog.info("Starting quizServer...");
    	
    	// Force Question Cache load first
    	QuestionCache.getInstance();
    	
    	RMLog.info("Initializing servlet");
        ServletHolder sh = new ServletHolder(ServletContainer.class);    
        sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
        sh.setInitParameter("com.sun.jersey.config.property.packages", "com.rmendel.quizManager.service");//Set the package where the services reside
        sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
      
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        context.addServlet(sh, "/*");

    	RMLog.info("Starting web server");
        server.start();
        server.join();      
     }
}
