package com.rmendel.quizManager.application;

import java.util.ArrayList;

import com.rmendel.framework.ExpressionUtilities;
import com.rmendel.framework.RMLog;
import com.rmendel.quizManager.api.AnswerDto;
import com.rmendel.quizManager.api.QuerySpecDto;
import com.rmendel.quizManager.api.QuestionDto;

// TODO Use generics to convert between server objects and DTOs
public class Question {

	public Question() {
	}
	
	public static QuestionDto getObjectDtoByID(String id) throws Exception {
		QuestionData question = getObjectByID(id);
		return objectToDto(question);
	}
	
	public static QuestionDto[] getObjectDtos(QuerySpecDto query) throws Exception {
		ArrayList<QuestionData> questions = getObjects(query);
		ArrayList<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
		for(QuestionData questionToConvert:questions)
			questionDtos.add(objectToDto(questionToConvert));
		QuestionDto[] toReturn = new QuestionDto[questionDtos.size()];
		return questionDtos.toArray(toReturn);
	}
	
	public static QuestionDto insertObjectDto(QuestionDto toCreate) throws Exception {
		validateDto(toCreate);
		
		QuestionTable table = QuestionCache.getInstanceQuestionTable();
		QuestionData question = table.newQuestion();
		question.setQuestionText(toCreate.getQuestionText());
		
		AnswerDto answerDto = toCreate.getAnswer();
		if(answerDto != null) {
			AnswerData answer = table.newAnswer();
			answer.setQuestionID(question.getId());
			answer.setResponseText(answerDto.getResponseText());
			question.setAnswer(answer);
		}
		
		AnswerDto[] distractorDtos = toCreate.getDistractors();
		if(distractorDtos != null) {
			ArrayList<AnswerData> distractors = question.getDistractors();
			for(AnswerDto distractorDto:distractorDtos) {
				AnswerData distractor = table.newAnswer();
				distractor.setQuestionID(question.getId());
				distractor.setResponseText(distractorDto.getResponseText());
				distractors.add(distractor);
			}
		}
		
		return objectToDto(insertObject(question));
	}
	
	public static synchronized QuestionDto updateObjectDto(QuestionDto toUpdate) throws Exception {
		validateDto(toUpdate);
		
		QuestionTable table = QuestionCache.getInstanceQuestionTable();
		QuestionData question = table.getRowByID(toUpdate.getId());
		
		if(!ExpressionUtilities.isTrivial(toUpdate.getQuestionText()))
			question.setQuestionText(toUpdate.getQuestionText());
		
		AnswerDto answerDto = toUpdate.getAnswer();
		if(answerDto != null) {
			AnswerData answer = table.newAnswer();
			answer.setQuestionID(question.getId());
			answer.setResponseText(answerDto.getResponseText());
			question.setAnswer(answer);
		}
		
		// TODO For distractor updates, support merging of existing items with new items by id
		AnswerDto[] distractorDtos = toUpdate.getDistractors();
		if(distractorDtos != null) {
			ArrayList<AnswerData> distractors = new ArrayList<AnswerData>();
			for(AnswerDto distractorDto:distractorDtos) {
				AnswerData distractor = table.newAnswer();
				distractor.setQuestionID(question.getId());
				distractor.setResponseText(distractorDto.getResponseText());
				distractors.add(distractor);
			}
			question.setDistractors(distractors);
		}
		
		return objectToDto(updateObject(question));
	}
	
	public static void validateDto(QuestionDto question) {
		if(question == null) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Must specify a non-trivial question.");
			RMLog.error(e);
			throw e;
		}
		
		if(ExpressionUtilities.isTrivial(question.getQuestionText())) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Must specify question text for question.");
			RMLog.error(e);
			throw e;
		}

		if(question.getAnswer() == null) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Must specify an answer for the question.");
			RMLog.error(e);
			throw e;
		}
		Answer.validateDto(question.getAnswer());
		
		AnswerDto[] distractors = question.getDistractors();
		if(distractors != null)
			for(AnswerDto distractor:distractors)
				Answer.validateDto(distractor);
	}
	
	public static QuestionDto objectToDto(QuestionData toConvert) {
		QuestionDto toReturn = new QuestionDto();
		toReturn.setId(toConvert.getId());
		toReturn.setQuestionText(toConvert.getQuestionText());
		toReturn.setAnswer(Answer.objectToDto(toConvert.getAnswer()));
		
		ArrayList<AnswerDto> distractorDtos = new ArrayList<AnswerDto>();
		for(AnswerData distractorToConvert:toConvert.getDistractors())
			distractorDtos.add(Answer.objectToDto(distractorToConvert));
		AnswerDto[] distractorsToReturn = new AnswerDto[distractorDtos.size()];
		distractorsToReturn = distractorDtos.toArray(distractorsToReturn);
		toReturn.setDistractors(distractorsToReturn);
		
		toReturn.setWordCount(toConvert.getWordCount());
		return toReturn;
	}
	
	public static QuestionData getObjectByID(String id) throws Exception {
		QuestionTable table = QuestionCache.getInstanceQuestionTable();
		return table.getRowByID(id);
	}

	public static ArrayList<QuestionData> getObjects(QuerySpecDto query) throws Exception {
		QuestionTable table = QuestionCache.getInstanceQuestionTable();
		return table.getRows(query);
	}
	
	public static QuestionData insertObject(QuestionData toCreate) throws Exception {
		updateDerivedProperties(toCreate);
		QuestionTable table = QuestionCache.getInstanceQuestionTable();
		table.putRow(toCreate);
		return toCreate;
	}
	
	public static synchronized QuestionData updateObject(QuestionData toUpdate) {
		updateDerivedProperties(toUpdate);
		return toUpdate;
	}
	
	public static void updateDerivedProperties(QuestionData question) {
		updateWordCount(question);
	}
	
	private static void updateWordCount(QuestionData question) {
		String wordSeparator = "\\s";
		int wordCount = 0;
		
		
		if(!ExpressionUtilities.isTrivial(question.getQuestionText()))
			wordCount += question.getQuestionText().split(wordSeparator).length;
		
		AnswerData AnswerData = question.getAnswer();
		if(AnswerData != null && !ExpressionUtilities.isTrivial(AnswerData.getResponseText()))
			wordCount += AnswerData.getResponseText().split(wordSeparator).length;

		for(AnswerData distractor:question.getDistractors())
			if(!ExpressionUtilities.isTrivial(distractor.getResponseText()))
				wordCount += distractor.getResponseText().split(wordSeparator).length;
		
		question.setWordCount(wordCount);
	}
	

}
