package com.rmendel.framework;

import java.util.Date;

public abstract class BaseLogEntry implements AutoCloseable {

	public BaseLogEntry(String context) {
		this.context = context;
		this.startTime = new Date().getTime();
		RMLog.info("Executing " + context + "...");
	}

	@Override
	public void close() {
		if(exception != null)
			RMLog.error(exception);
		
		String message = context + " ";
		message += isSuccess ? "succeeded" : "failed";
		message += " after " + getDuration() + "ms";
		
		String additional = getAdditionalMessageOnClose();
		if(!ExpressionUtilities.isTrivial(additional))
			message += "|" + additional;
		
		if(!isSuccess)
			message += "|Please see previous log entries for more details";
				
		if(isSuccess)
			RMLog.info(message);
		else
			RMLog.error(message);
	}
	
	public abstract String getAdditionalMessageOnClose();

	public String getContext() {
		return context;
	}

	public void setContext(String message) {
		this.context = message;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public long getDuration() {
		return new Date().getTime() - startTime;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	private String context = null;
	private boolean isSuccess = false;
	private long startTime = 0;
	private Exception exception = null;
}
