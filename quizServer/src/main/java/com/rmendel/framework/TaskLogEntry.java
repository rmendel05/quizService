package com.rmendel.framework;

public class TaskLogEntry extends BaseLogEntry {

	public TaskLogEntry(String context) {
		super(context);
	}

	@Override
	public String getAdditionalMessageOnClose() {
		return additionalMessage;
	}
	
	public String getAdditionalMessage() {
		return additionalMessage;
	}

	public void setAdditionalMessage(String additionalMessage) {
		this.additionalMessage = additionalMessage;
	}

	private String additionalMessage = null;
}
