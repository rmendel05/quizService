package com.rmendel.quizManager.application.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.rmendel.quizManager.application.QuestionCsvParser;
import com.rmendel.quizManager.application.QuestionTable;

public class QuestionCsvParserTest extends TestCase
{
    public QuestionCsvParserTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( QuestionCsvParserTest.class );
    }

    public void testQuestionCsvParser() throws Exception
    {
    	String columnSeparator = "|";
    	String q1Test = "Who is the Aphex Twin?";
    	String q1Answer = "Richard D. James";
    	String q1Distractors = "Mike Paradinas,A member of Kraftwerk,Composer of 'Reachy Prints',None of the above";
    	String q1 = q1Test + columnSeparator + q1Answer + columnSeparator + q1Distractors;
    	
    	String q2Test = "What game company did Mike Paradinas and Richard D. James parody in their Mike & Rich collaboration?";
    	String q2Answer = "Milton Bradley";
    	String q2Distractors = "Wizards of the Coast,Parker Brothers,Wham-O";
    	String q2 = q2Test + columnSeparator + q2Answer + columnSeparator + q2Distractors;

    	QuestionCsvParser parser = new QuestionCsvParser();
    	parser.setColumnSeparator(columnSeparator);
    	parser.processValues(parser.parseValues(q1));
    	QuestionTable table = parser.getData();
    	
    	assertEquals("Wrong number of rows inserted.", 1, table.getRowCount());
    	
    	parser.processValues(parser.parseValues(q2));
    	assertEquals("Wrong number of rows inserted.", 2, table.getRowCount());
    }
}