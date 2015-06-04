package com.rmendel.framework.test;

import com.rmendel.framework.ResponseLogEntry;
import com.rmendel.quizManager.api.QuestionDto;
import com.rmendel.quizManager.api.QuestionResponseDto;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ResponseLogEntryTest extends TestCase
{
    public ResponseLogEntryTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( ResponseLogEntryTest.class );
    }

    public void testResponseLogEntrySuccess()
    {
    	try(ResponseLogEntry<QuestionResponseDto> logEntry = 
				 new ResponseLogEntry<QuestionResponseDto>("ResponseLogEntryTest.testResponseLogEntry")) {
    		QuestionDto result = new QuestionDto();
    		QuestionResponseDto response = new QuestionResponseDto(true, result);
    		logEntry.setResponse(response);
    	}
    }

    public void testResponseLogEntryMultiSuccess()
    {
    	try(ResponseLogEntry<QuestionResponseDto> logEntry = 
				 new ResponseLogEntry<QuestionResponseDto>("ResponseLogEntryTest.testResponseLogEntryMultiSuccess")) {
    		QuestionDto[] result = new QuestionDto[] {
    				new QuestionDto(),
    				new QuestionDto()
    		};
    		QuestionResponseDto response = new QuestionResponseDto(true, result);
    		logEntry.setResponse(response);
    	}
    }

    public void testResponseLogEntryFail()
    {
    	try(ResponseLogEntry<QuestionResponseDto> logEntry = 
				 new ResponseLogEntry<QuestionResponseDto>("ResponseLogEntryTest.testResponseLogEntryFail")) {
    		QuestionResponseDto response = new QuestionResponseDto(new Exception("Test."));
    		logEntry.setResponse(response);
    	}
    }
}