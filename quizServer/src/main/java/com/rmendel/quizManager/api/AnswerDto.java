package com.rmendel.quizManager.api;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.rmendel.framework.IApiObject;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "answer")
@JsonIgnoreProperties(ignoreUnknown=true)
@ApiModel(description="Describes an option to reply to a question")
public final class AnswerDto implements IApiObject {
	
	public AnswerDto() {
	}
	
	public AnswerDto(String responseText) {
		this.responseText = responseText;
	}

	@ApiModelProperty(value = "The object type (read-only)", required = false)
	public String getObjectNotion() {
		return "answer";
	}
	public void setObjectNotion() {}

	@ApiModelProperty(value = "Primary key (read-only)", required = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty(value = "A statement of reply for the associated question", required = true)
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String value) {
		responseText = value;  
	}

	@ApiModelProperty(value = "Unique identifier of the associated question (read-only)", required = false)
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
