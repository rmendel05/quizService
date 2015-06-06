package com.rmendel.quizManager.application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Function;

import com.rmendel.framework.ExpressionUtilities;
import com.rmendel.framework.RMLog;
import com.rmendel.quizManager.api.FilterSpecDto;
import com.rmendel.quizManager.api.PageSpecDto;
import com.rmendel.quizManager.api.QuerySpecDto;
import com.rmendel.quizManager.api.SortSpecDto;

// TODO Create proper back end classes for QuerySpec, FilterSpec, SortSpec, PageSpec
// TODO Add more business rules to enforce data integrity, such as required values and uniqueness constraints
// TODO Create indexes for supported search keys and key words
// TODO Synchronize access to table data
// TODO Move this to a real database
public final class QuestionTable {
	
	public static final String FILTER_TYPE_WORD_COUNT = "wordCount";
	public static final String FILTER_TYPE_DISTRACTOR_COUNT = "distractorCount"; 
	
	public static final String SORT_BY_WORD_COUNT = "wordCount";

	public QuestionTable() {
	}
	
	public QuestionData newQuestion() {
		QuestionData toReturn = new QuestionData();
		toReturn.setId(
				Integer.toString(getNextQuestionIDSequence()));
		return toReturn;
	}
	
	public AnswerData newAnswer() {
		AnswerData toReturn = new AnswerData();
		toReturn.setId(
				Integer.toString(getNextAnswerIDSequence()));
		return toReturn;
	}
	
	public QuestionData getRowByID(String lookupID) {
		if(ExpressionUtilities.isTrivial(lookupID)) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Must specify a non-trivial value for parameter lookupID.");
			RMLog.error(e);
			throw e;
		}
		
		if(rowsByID.containsKey(lookupID)) {
			return rowsByID.get(lookupID);
		} else {
			IllegalStateException e = new IllegalStateException(
					"Could not find a row with id [" + lookupID + "].");
			RMLog.error(e);
			throw e;
		}
	}
	
	public ArrayList<QuestionData> getRows(QuerySpecDto query) throws Exception {
		if(query == null) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Must specify a non-trivial value for parameter query.");
			RMLog.error(e);
			throw e;
		}
		
		ArrayList<QuestionData> toReturn = new ArrayList<QuestionData>(rowsByID.values());
		
		toReturn = applyFilter(toReturn, query.getFilter());
		sort(toReturn, query.getSort());
		toReturn = applyPage(toReturn, query.getPage());

		return toReturn;
	}
	
	private ArrayList<QuestionData> applyFilter(
			ArrayList<QuestionData> questions,
			FilterSpecDto filter) {
		if(filter == null)
			return questions;
		
		if(ExpressionUtilities.isTrivial(filter.getFilterType())) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Must specify a non-trivial value for filterType.");
			RMLog.error(e);
			throw e;
		}
		
		// TODO Add more ways to filter
		String filterType = filter.getFilterType();
		switch(filterType) {
			case FILTER_TYPE_WORD_COUNT:
				return applyWordCountFilter(questions, filter);

			case FILTER_TYPE_DISTRACTOR_COUNT:
				return applyDistractorCountFilter(questions, filter);
			
			default:
				IllegalArgumentException e = new IllegalArgumentException(
						"Value [" + filterType + "] is not a valid filterType. Acceptable values include [" + 
						FILTER_TYPE_WORD_COUNT + ", " + FILTER_TYPE_DISTRACTOR_COUNT + "].");
				RMLog.error(e);
				throw e;
		}
	}

	private ArrayList<QuestionData> applyWordCountFilter(
			ArrayList<QuestionData> questions, 
			FilterSpecDto filter) {
		
		return applyRangeFilter(
				questions,
				filter,
				(question)->question.getWordCount());
	}

	private ArrayList<QuestionData> applyDistractorCountFilter(
			ArrayList<QuestionData> questions, 
			FilterSpecDto filter) {
		
		return applyRangeFilter(
				questions,
				filter,
				(question)->question.getDistractors().size());
	}
	
	private ArrayList<QuestionData> applyRangeFilter(
			ArrayList<QuestionData> questions, 
			FilterSpecDto filter,
			Function<QuestionData,Integer> valueRule) {
		Integer minValue = parseIntegerFilter(filter.getMinValue(), "minValue");
		Integer maxValue = parseIntegerFilter(filter.getMaxValue(), "maxValue");
		
		if(minValue == null && maxValue == null)
			return questions;
		
		ArrayList<QuestionData> toReturn = new ArrayList<QuestionData>();
		for(QuestionData question:questions) {
			if(minValue != null && valueRule.apply(question) < minValue)
				continue;
			if(maxValue != null && valueRule.apply(question) > maxValue)
				continue;
			
			toReturn.add(question);
		}
		
		return toReturn;
	}
	
	private Integer parseIntegerFilter(
			String filterValue,
			String propertyName) {
		Integer toReturn = null;
		String rawMin = filterValue.trim();
		if(!ExpressionUtilities.isTrivial(rawMin)) {
			try {
				toReturn = Integer.parseInt(rawMin);
			} catch(NumberFormatException numberFormatException) {
				IllegalArgumentException e = new IllegalArgumentException(
						"Value [" + filterValue + "] is not a valid " + propertyName + ". Must specify an integer.");
				RMLog.error(e);
				throw e;
			}
		}
		
		return toReturn;
	}

	// TODO Add more ways to sort
	private void sort(
			ArrayList<QuestionData> questions,
			SortSpecDto sort) {
		if(sort == null)
			return;
		
		if(ExpressionUtilities.isTrivial(sort.getSortByAttribute())) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Must specify a non-trivial value for sortByAttribute.");
			RMLog.error(e);
			throw e;
		}
		
		String sortByAttribute = sort.getSortByAttribute();
		switch(sortByAttribute) {
		
			case SORT_BY_WORD_COUNT:
				questions.sort(new Comparator<QuestionData>() {
					public int compare(QuestionData first, QuestionData second) {
						if(first.getWordCount() == second.getWordCount())
							return 0;
						else if(first.getWordCount() < second.getWordCount())
							return -1;
						else
							return 1;
					}
				});
				break;
			
			default:
				IllegalArgumentException e = new IllegalArgumentException(
						"Value [" + sortByAttribute + "] is not a valid sortByAttribute. Acceptable values include [" + 
								SORT_BY_WORD_COUNT + "].");
				RMLog.error(e);
				throw e;
		}
	}
	
	// TODO Return an indication of whether the specified page is the last one containing data
	private ArrayList<QuestionData> applyPage(
			ArrayList<QuestionData> questions,
			PageSpecDto page) throws Exception {
		if(page == null)
			return questions;
		
		if(page.getRowsPerPage() == null || page.getRowsPerPage() < 1) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Must specify an integer value greater than zero for rowsPerPage.");
			RMLog.error(e);
			throw e;
		}
		
		if(page.getPageOffset() == null || page.getPageOffset() < 0) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Must specify a non-negative integer value for pageOffset.");
			RMLog.error(e);
			throw e;
		}
		
		int startOffset;
		try {
			startOffset = page.getPageOffset() * page.getRowsPerPage();
		} catch(Exception e) {
			Exception startOffsetException = new Exception(
					"Unable to calculate row offset for pageOffset [" + page.getPageOffset() + "] and rowsPerPage [" + page.getRowsPerPage() + "].", 
					e);
			RMLog.error(startOffsetException);
			throw startOffsetException;
		}
		
		if(startOffset >= questions.size()) {
			// Beyond list length, return empty list
			return new ArrayList<QuestionData>();
		}
		
		int upperBound;
		try {
			upperBound = Math.min(startOffset + page.getRowsPerPage(), questions.size());		
		} catch(Exception e) {
			Exception startOffsetException = new Exception(
					"Unable to calculate ending row offset for pageOffset [" + page.getPageOffset() + "] and rowsPerPage [" + page.getRowsPerPage() + "].", 
					e);
			RMLog.error(startOffsetException);
			throw startOffsetException;
		}
		
		return new ArrayList<QuestionData>(questions.subList(startOffset, upperBound));
	}

	public void putRow(QuestionData toAdd) {
		if(toAdd == null) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Must specify a question to add.");
			RMLog.error(e);
			throw e;
		}
		
		AnswerData answer = toAdd.getAnswer();
		if(answer == null) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Must specify an answer for the question to add.");
			RMLog.error(e);
			throw e;
		}
		
		String id = toAdd.getId();
		if(ExpressionUtilities.isTrivial(id)) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Must specify an id for the question to add.");
			RMLog.error(e);
			throw e;
		}
		
		if(!rowsByID.containsKey(id)) {
			rowsByID.put(id, toAdd);
		} else {
			IllegalStateException e = new IllegalStateException(
					"Could not add question with id [" + id + "] because a question with that id already exists.");
			RMLog.error(e);
			throw e;
		}
	}
	
	public int getRowCount() {
		return rowsByID.size();
	}
	
	private int getNextQuestionIDSequence() {
		return questionIDSequence++;
	}

	private int getNextAnswerIDSequence() {
		return answerIDSequence++;
	}
	
	private int questionIDSequence = 0;
	private int answerIDSequence = 0;
	private HashMap<String, QuestionData> rowsByID =
			new HashMap<String, QuestionData>();
}
