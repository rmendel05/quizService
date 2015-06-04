package com.rmendel.quizManager.application;

public class AnswerData {
	public AnswerData() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String value) {
		responseText = value;  
	}

	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String quizQuestionID) {
		this.questionID = quizQuestionID;
	}

	private String id = null;
	private String responseText = null;
	private String questionID = null;
}
