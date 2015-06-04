package com.rmendel.quizManager.application;

import java.util.ArrayList;

public class QuestionData {

	public QuestionData() {
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	public AnswerData getAnswer() {
		return answerData;
	}
	public void setAnswer(AnswerData answerData) {
		this.answerData = answerData;
	}

	public ArrayList<AnswerData> getDistractors() {
		return distractors;
	}

	public void setDistractors(ArrayList<AnswerData> distractors) {
		this.distractors = distractors;
	}

	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	private String id = null;
	private String questionText = null;
	private AnswerData answerData = null;
	private ArrayList<AnswerData> distractors = new ArrayList<AnswerData>();
	private int wordCount = 0;
}
