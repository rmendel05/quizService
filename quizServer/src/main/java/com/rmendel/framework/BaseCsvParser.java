package com.rmendel.framework;

public abstract class BaseCsvParser {
	public String[] parseValues(String line) {
		// TODO Handle quoted separators
		return line.split(columnSeparator);
	}

	public abstract void processValues(String[] values) throws Exception;
	
	public String getColumnSeparator() {
		return columnSeparator;
	}

	public void setColumnSeparator(String columnSeparator) {
		this.columnSeparator = columnSeparator;
	}

	private String columnSeparator = ",";
}