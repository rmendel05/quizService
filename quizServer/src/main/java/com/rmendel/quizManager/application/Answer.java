package com.rmendel.quizManager.application;

import com.rmendel.framework.ExpressionUtilities;
import com.rmendel.framework.RMLog;
import com.rmendel.quizManager.api.AnswerDto;

public class Answer {

	public Answer() {
	}

	public static AnswerDto objectToDto(AnswerData toConvert) {
		AnswerDto toReturn = new AnswerDto();
		toReturn.setId(toConvert.getId());
		toReturn.setQuestionID(toConvert.getQuestionID());
		toReturn.setResponseText(toConvert.getResponseText());
		return toReturn;
	}
	
	public static void validateDto(AnswerDto answer) {
		if(answer == null) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Must specify a non-trivial answer.");
			RMLog.error(e);
			throw e;
		}
		
		if(ExpressionUtilities.isTrivial(answer.getResponseText())) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Must specify response text for answer.");
			RMLog.error(e);
			throw e;
		}
	}
}
