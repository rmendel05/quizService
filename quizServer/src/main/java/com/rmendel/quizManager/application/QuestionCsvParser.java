package com.rmendel.quizManager.application;

import java.util.ArrayList;

import com.rmendel.framework.BaseCsvParser;

public final class QuestionCsvParser extends BaseCsvParser {
	public static final String CSV_FILE_FORMAT = "[question]|[answer]|[distractor 1],[distractor 2],...,[distractor N]";
	
	public QuestionCsvParser() {
		super();
	}
	
	public QuestionTable getData() {
		return table;
	}

	@Override
	public void processValues(String[] values) throws Exception {
		QuestionData question = table.newQuestion();
		int valueCount = values.length;
		if(valueCount >= 1)
			question.setQuestionText(values[0]);
		if(valueCount >= 2) {
			AnswerData answer = table.newAnswer();
			answer.setQuestionID(question.getId());
			answer.setResponseText(values[1]);
			question.setAnswer(answer);
		}
		if(valueCount >= 3) {
			setDistractors(question, values[2]);
		}
		Question.updateDerivedProperties(question);
		
		table.putRow(question);
	}
	
	private void setDistractors(
			QuestionData question,
			String rawDistractors) {
		ArrayList<AnswerData> distractors = new ArrayList<AnswerData>();
		for(String rawDistractor : rawDistractors.split(distractionSeparator)) {
			AnswerData answer = table.newAnswer();
			answer.setQuestionID(question.getId());
			answer.setResponseText(rawDistractor);
			distractors.add(answer);
		}
		question.setDistractors(distractors);
	}

	private QuestionTable table = new QuestionTable();
	private String distractionSeparator = ",";
}