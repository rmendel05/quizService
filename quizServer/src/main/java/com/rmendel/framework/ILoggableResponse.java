package com.rmendel.framework;

public interface ILoggableResponse extends IApiObject {

	boolean isSuccess();
	
	String getErrorMessage();
	
	int getRowCount();
}