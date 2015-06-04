package com.rmendel.framework;

// TODO: Add class hierarchy to support logging plugins.
public class RMLog {

	public RMLog() {
	}

	public static void info(String message) {
		System.out.println(message);
	}
	
	public static void error(String message) {
		System.out.println("Error: " + message);
	}
	
	public static void error(Throwable e) {
		error(e.getMessage() + "\n" + "Stack Trace:\n" + e.getStackTrace());
		
		Throwable cause = e.getCause();
		if(cause != null)
			error(cause);
	}

	public static void warning(String message) {
		System.out.println("Warning: " + message);
	}
}
