package com.rmendel.quizManager.service.test;

import java.util.UUID;

import com.rmendel.quizManager.api.AnswerDto;
import com.rmendel.quizManager.api.FilterSpecDto;
import com.rmendel.quizManager.api.PageSpecDto;
import com.rmendel.quizManager.api.QuerySpecDto;
import com.rmendel.quizManager.api.QuestionDto;
import com.rmendel.quizManager.api.QuestionResponseDto;
import com.rmendel.quizManager.api.SortSpecDto;
import com.rmendel.quizManager.application.QuestionTable;
import com.rmendel.quizManager.service.QuestionService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

// TODO Test Question table and business objects more directly
public class QuestionServiceTest 
    extends TestCase
{
    public QuestionServiceTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( QuestionServiceTest.class );
    }

    public void testCrud()
    {
    	QuestionService service = new QuestionService();
    	
    	// Create some test data
    	QuestionDto q1 = getQuestion();
    	QuestionDto q2 = getQuestion();
    	QuestionDto q3 = getQuestion();
    	
    	// insert the data
    	QuestionDto q1Result = getSingleResult(service.insert(q1));
    	QuestionDto q2Result = getSingleResult(service.insert(q2));
    	QuestionDto q3Result = getSingleResult(service.insert(q3));
    	
    	// validate inserted values match
    	validateUpdate(q1, q1Result);
    	validateUpdate(q2, q2Result);
    	validateUpdate(q3, q3Result);
    	
    	// validate word count
    	assertEquals("Incorrect word count.", 7, (int)q1Result.getWordCount());
    	
    	// verify the data was inserted and can be looked up by id
    	QuestionDto q1Lookup = getSingleResult(service.getObject(q1Result.getId()));    	
    	QuestionDto q2Lookup = getSingleResult(service.getObject(q2Result.getId()));    	
    	QuestionDto q3Lookup = getSingleResult(service.getObject(q3Result.getId()));
    	
    	// validate inserted values match
    	validateUpdate(q1, q1Lookup);
    	validateUpdate(q2, q2Lookup);
    	validateUpdate(q3, q3Lookup);
    	
    	// update 
    	QuestionDto q1Updated = getQuestion();
    	q1Updated.setId(q1Result.getId());
    	QuestionDto q1UpdateResult = getSingleResult(service.update(q1Updated));
    	
    	// validate updated values match
    	validateUpdate(q1Updated, q1UpdateResult);

    	// verify the updated data matches after subsequent lookup 
    	QuestionDto q1UpdatedLookup = getSingleResult(service.getObject(q1Result.getId()));    	
    	validateUpdate(q1Updated, q1UpdatedLookup);
    }
    
    // TODO Add more query tests
    public void testQuery() {
    	QuestionService service = new QuestionService();
    	
    	// Create some test data
    	QuestionDto q1 = getQuestion();
    	QuestionDto q2 = getQuestion();
    	QuestionDto q3 = getQuestion();
    	
    	// insert the data
    	QuestionDto q1Result = getSingleResult(service.insert(q1));
    	QuestionDto q2Result = getSingleResult(service.insert(q2));
    	QuestionDto q3Result = getSingleResult(service.insert(q3));
    	
    	// test trivial query
    	QuestionDto[] result = getMultiResult(service.query(new QuerySpecDto()));
    	assertTrue("No results from trivial query.", result.length > 0);
    	
    	// test non-trivial query
    	QuerySpecDto query2 = new QuerySpecDto(
    			new FilterSpecDto(QuestionTable.FILTER_TYPE_DISTRACTOR_COUNT, "2", "5"), 
    			new SortSpecDto(QuestionTable.SORT_BY_WORD_COUNT), 
    			new PageSpecDto(10, 0));
    	QuestionDto[] result2 = getMultiResult(service.query(query2));
    	assertTrue("No results from query2.", result2.length > 0);

    	// test non-trivial query
    	QuerySpecDto query3 = new QuerySpecDto(
    			new FilterSpecDto(QuestionTable.FILTER_TYPE_WORD_COUNT, "6", "8"), 
    			new SortSpecDto(QuestionTable.SORT_BY_WORD_COUNT), 
    			new PageSpecDto(10, 0));
    	QuestionDto[] result3 = getMultiResult(service.query(query3));
    	assertTrue("No results from query3.", result2.length > 0);
    }
    
    private QuestionDto[] getMultiResult(QuestionResponseDto response) {
    	validateResponse(response);
    	return response.getResults();
    }
    
    private QuestionDto getSingleResult(QuestionResponseDto response) {
    	validateResponse(response);
    	assertEquals("Wrong number of objects returned.", 1, response.getRowCount());
    	return response.getResults()[0];
    }
    
    private void validateResponse(QuestionResponseDto response) {
    	assertTrue("Response was null.", response != null);
    	assertTrue("Unsuccessful response: " + response.getErrorMessage(), response.isSuccess());
    }
    
    private void validateUpdate(
    		QuestionDto expected,
    		QuestionDto actual) {
    	assertEquals("questionText does not match.", expected.getQuestionText(), actual.getQuestionText());
    	assertEquals("answer does not match.", 
    			expected.getAnswer().getResponseText(), 
    			actual.getAnswer().getResponseText());
    	assertEquals("distractor count does not match.", 
    			expected.getDistractors().length,
    			actual.getDistractors().length);
    	for(int i = 0; i < expected.getDistractors().length; i++)
    		assertEquals("distractor mismatch.",
    				expected.getDistractors()[i].getResponseText(),
    				actual.getDistractors()[i].getResponseText());
    }
    
    private QuestionDto getQuestion() {
    	QuestionDto toReturn = new QuestionDto();
    	toReturn.setQuestionText(getUniqueID() + " more words?");
    	toReturn.setAnswer(getAnswer());
    	toReturn.setDistractors(new AnswerDto[] {getAnswer(),getAnswer(),getAnswer()});
    	return toReturn;
    }
    
    private AnswerDto getAnswer() {
    	AnswerDto toReturn = new AnswerDto();
    	toReturn.setResponseText(getUniqueID());
    	return toReturn;
    }
    
    private String getUniqueID() {
    	return UUID.randomUUID().toString();
    }
}
