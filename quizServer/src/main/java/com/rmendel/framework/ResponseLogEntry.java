package com.rmendel.framework;

public class ResponseLogEntry<ResponseObjectType extends ILoggableResponse> extends BaseLogEntry {

	public ResponseLogEntry(String context) {
		super(context);
	}

	@Override
	public String getAdditionalMessageOnClose() {
		String message = "";
		if(isSuccess())
			message += "Processed " + response.getRowCount() + " items|";
		message += "Response id: " + response.getId();
		return message;
	}
	
	public ResponseObjectType getResponse() {
		return response;
	}

	public void setResponse(ResponseObjectType response) {
		this.response = response;
		this.setSuccess(response.isSuccess());
	}

	private ResponseObjectType response = null;
}
