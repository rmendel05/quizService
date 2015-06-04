package com.rmendel.framework;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public final class CsvReader {
	public final int MAX_ERROR_LIMIT = 1000;
	
	private String file;

	private ArrayList<String> errors;
	public ArrayList<String> getErrors() {
		return errors;
	}
	
	private int lineNumber;
	public int getTotalLinesProcessed() {
		return lineNumber;
	}
	
	private int totalLinesProcessedSuccessfully;
	public int getTotalLinesProcessedSuccessfully() {
		return totalLinesProcessedSuccessfully;
	}
	
	public CsvReader(String file) {
		this.file = file;
	}
	
	private void initializeRead() {
		errors = new ArrayList<String>();
		lineNumber = 0;
		totalLinesProcessedSuccessfully = 0;
	}
	
	public void readAllLines(BaseCsvParser parser) throws Exception {
		initializeRead();
		
		// Below "try with resources" statement requires Java 1.7
		// The resources fileReader and reader will be automatically closed
		// because they implement the "Closable" interface
		try (FileReader fileReader = new FileReader(file);
				BufferedReader reader = new BufferedReader(fileReader)) {
			String line;

			// Assume the first line is a header and skip it
			line = reader.readLine();
			if(line == null)
				return;
			
			while((line = reader.readLine()) != null) {
				lineNumber++;
				if(processLine(line, parser, lineNumber))
					totalLinesProcessedSuccessfully++;
			}
		
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("CsvReader.readAllLines() could not find the following input file: [" + file + "] Message: [" + e.getMessage() +"]");
		
		} catch (Exception e) {
			throw new Exception("CsvReader.readAllLines() encountered an exception reading input file [" + file + "]: " + e.getMessage());
		}
	}

	private boolean processLine(
			String line, 
			BaseCsvParser parser, 
			int lineNumber) {
		boolean toReturn = false;
		String[] rawValues = null;
		
		try {
			rawValues = parser.parseValues(line);
		
		} catch(Exception e) {
			putError("Error parsing values on line [" 
					+ Integer.toString(lineNumber) + "]: " 
					+ e.getMessage());
		}
		
		if(rawValues != null) {
			try {
				parser.processValues(rawValues);
				toReturn = true;
			
			} catch(Exception e) {
				putError("Error processing values on line [" 
						+ Integer.toString(lineNumber) + "]: " 
						+ e.getMessage());
			}
		}
		
		return toReturn;
	}
	
	private void putError(String errorMessage) {
		if(this.errors.size() < MAX_ERROR_LIMIT)
			this.errors.add(errorMessage);
	}
}