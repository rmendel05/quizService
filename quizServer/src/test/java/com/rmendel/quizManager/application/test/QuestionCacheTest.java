package com.rmendel.quizManager.application.test;

import com.rmendel.quizManager.application.QuestionCache;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class QuestionCacheTest extends TestCase
{
    public QuestionCacheTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( QuestionCacheTest.class );
    }

    public void testQuestionCache() throws Exception
    {
    	QuestionCache.setSourceFile("./src/test/java/testdata.csv");
    	assertTrue("Failed to load question data",
    			QuestionCache.getInstance().getQuestionTable().getRowCount() > 0);
    	
    }
}