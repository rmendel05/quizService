package com.rmendel.framework;

public class ExpressionUtilities {

	public ExpressionUtilities() {
	}

	public static boolean isTrivial(String value) {
		return value == null || value.isEmpty();
	}
}
