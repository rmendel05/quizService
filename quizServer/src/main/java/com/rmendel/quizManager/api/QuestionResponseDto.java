package com.rmendel.quizManager.api;

import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.rmendel.framework.ILoggableResponse;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "questionResponse")
@JsonIgnoreProperties(ignoreUnknown=true)
@ApiModel(description="Container for a question service response")
public final class QuestionResponseDto implements ILoggableResponse {

	public QuestionResponseDto() {
	}

	public QuestionResponseDto(
			boolean isSuccess,
			QuestionDto result) {
		this.results = new QuestionDto[] {result};
		this.isSuccess = isSuccess;
	}

	public QuestionResponseDto(
			boolean isSuccess,
			QuestionDto[] results) {
		this.results = results;
		this.isSuccess = isSuccess;
	}

	public QuestionResponseDto(Exception e) {
		errorMessage = e.getMessage();
	}

	@ApiModelProperty(value = "The object type (read-only)", required = false)
	public String getObjectNotion() {
		return "questionResponse";
	}
	public void setObjectNotion() {}

	@ApiModelProperty(value = "Unique identifier for the response", required = true)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty(value = "True of the service method completed successfully", required = true)
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@ApiModelProperty(value = "Describes any errors that occurred during service method execution", required = false)
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@XmlElementWrapper(name = "results")
	@XmlElement(name = "result")
	@ApiModelProperty(value = "List of zero or more items returned by a service method", required = true)
	public QuestionDto[] getResults() {
		return results;
	}
	public void setResults(QuestionDto[] results) {
		this.results = results;
	}

	@ApiModelProperty(value = "Number of elements in the results array", required = true)
	public int getRowCount() {
		return getResults().length;
	}
	public void setRowCount() {}

	private String id = UUID.randomUUID().toString();
	private boolean isSuccess = false;
	private String errorMessage = null;
	private QuestionDto[] results = new QuestionDto[]{};
}
