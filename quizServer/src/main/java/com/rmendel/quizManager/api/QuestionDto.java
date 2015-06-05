package com.rmendel.quizManager.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.rmendel.framework.IApiObject;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "question")
@JsonIgnoreProperties(ignoreUnknown=true)
@ApiModel(description="Specifies the text and possible replies for a question")
public final class QuestionDto implements IApiObject {
	
	public QuestionDto() {
	}
	
	public QuestionDto(
			String questionText, 
			AnswerDto answerDto,
			AnswerDto[] distractors) {
		this.questionText = questionText;
		this.answerDto = answerDto;
		this.distractors = distractors;
	}

	@ApiModelProperty(value = "The object type (read-only)", required = false)
	public String getObjectNotion() {
		return "question";
	}
	public void setObjectNotion() {}

	@ApiModelProperty(value = "Primary key (read-only). Required for update only", required = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty(value = "Iterogative statement defining the question", required = true)
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	@ApiModelProperty(value = "The correct response option", required = true)
	public AnswerDto getAnswer() {
		return answerDto;
	}
	public void setAnswer(AnswerDto answerDto) {
		this.answerDto = answerDto;
	}

	@XmlElementWrapper(name = "distractors")
	@XmlElement(name = "distractor")
	@ApiModelProperty(value = "Array of incorrect answers", required = false)
	public AnswerDto[] getDistractors() {
		return distractors;
	}
	public void setDistractors(AnswerDto[] distractors) {
		this.distractors = distractors;
	}

	@ApiModelProperty(value = "Number of words in the questionText, answer and distractors combined (read-only)", required = false)
	public Integer getWordCount() {
		return wordCount;
	}
	public void setWordCount(Integer wordCount) {
		this.wordCount = wordCount;
	}

	private String id = null;
	private String questionText = null;
	private AnswerDto answerDto = null;
	private AnswerDto[] distractors = new AnswerDto[]{};
	private Integer wordCount = 0;
}
