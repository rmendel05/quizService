package com.rmendel.quizManager.application;

import com.rmendel.framework.CsvReader;
import com.rmendel.framework.TaskLogEntry;

public class QuestionCache {

	public QuestionCache() {
		loadQuestionData();
	}
	
	public static QuestionCache getInstance() throws Exception {
		if(instance == null) {
			initializeSingleton();
		}
		
		return instance;
	}
	
	public static QuestionTable getInstanceQuestionTable() throws Exception {
		return getInstance().getQuestionTable();
	}
	
	public QuestionTable getQuestionTable() {
		return questionTable;
	}

	public static String getSourceFile() {
		return sourceFile;
	}

	public static void setSourceFile(String sourceFile) {
		QuestionCache.sourceFile = sourceFile;
	}

	private static synchronized void initializeSingleton() throws Exception {
		instance = new QuestionCache();
	}
	
	private void loadQuestionData() {
    	CsvReader reader = new CsvReader(sourceFile);
    	QuestionCsvParser parser = new QuestionCsvParser();
    	parser.setColumnSeparator("\\|");

    	try(TaskLogEntry logEntry = new TaskLogEntry("Initializing QuestionCache")) {
	    	try {
				reader.readAllLines(parser);
				logEntry.setSuccess(true);
				logEntry.setAdditionalMessage("Successfully loaded [" 
						+ reader.getTotalLinesProcessedSuccessfully() + "] of ["
						+ reader.getTotalLinesProcessed() + "] total lines from file ["
						+ sourceFile + "]");
			} catch (Exception e) {
				Exception loadException = new Exception(
						"Failed to load question data into cache from file [" + sourceFile + "]",
						e);
				logEntry.setException(loadException);
			}
    	}
    	
    	questionTable = parser.getData();
	}
	
	private static QuestionCache instance = null;
	private QuestionTable questionTable = new QuestionTable();
	private static String sourceFile = "./QuestionData.csv";
}
