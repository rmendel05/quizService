package com.rmendel.quizManager.api;

import javax.xml.bind.annotation.XmlRootElement;

import com.rmendel.framework.IApiObject;

@XmlRootElement(name = "answer")
public final class AnswerDto implements IApiObject {
	
	public AnswerDto() {
	}
	
	public AnswerDto(String responseText) {
		this.responseText = responseText;
	}

	public String getObjectNotion() {
		return "answer";
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
