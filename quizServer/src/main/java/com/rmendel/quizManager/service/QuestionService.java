package com.rmendel.quizManager.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rmendel.framework.ResponseLogEntry;
import com.rmendel.quizManager.api.QuerySpecDto;
import com.rmendel.quizManager.api.QuestionDto;
import com.rmendel.quizManager.api.QuestionResponseDto;
import com.rmendel.quizManager.application.Question;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiOperation;

//TODO Handle when incoming JSON objects are formatted incorrectly, such as missing comma from array spec

@Path("/question")
@Api(value = "question")
public final class QuestionService {
	
	 @GET
	 @Path("/getObject/{id}")
	 @ApiOperation(value = "Gets a single question for the specified id",
	 	response = QuestionResponseDto.class)	 
	 @ApiImplicitParams(
		{@ApiImplicitParam(name = "id", value = "Internal question id", required = true, dataType = "String", paramType = "path")})
	 @Produces(MediaType.APPLICATION_JSON)
	 public QuestionResponseDto getObject(@PathParam("id") String id) {
		 
		 try(ResponseLogEntry<QuestionResponseDto> logEntry = 
				 new ResponseLogEntry<QuestionResponseDto>("QuestionService.getQuestion")) {
	
			 try {
				 QuestionDto result = Question.getObjectDtoByID(id);
				 logEntry.setResponse(new QuestionResponseDto(true, result));
				 
			 } catch(Exception e) {
				 logEntry.setResponse(new QuestionResponseDto(e));
				 logEntry.setException(e);
			 }

			 return logEntry.getResponse();
		 }
	 }

	 // TODO Document filter types for QuestionDto queries: wordCount
	 // TODO Document sort elements for QuestionDto queries: wordCount, distractorCount
	 @POST
	 @Path("/query")
	 @ApiOperation(value = "Returns multiple questions based on the specified query",
	    response = QuestionResponseDto.class)	 
	 @ApiImplicitParams(
		{@ApiImplicitParam(name = "query", value = "Specifies filter, sort and page for return list of questions", required = true, dataType = "QuerySpecDto", paramType = "body")})
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public QuestionResponseDto query(QuerySpecDto query) {
		 
		 try(ResponseLogEntry<QuestionResponseDto> logEntry = 
				 new ResponseLogEntry<QuestionResponseDto>("QuestionService.query")) {
	
			 try {
				 QuestionDto[] result = Question.getObjectDtos(query);
				 logEntry.setResponse(new QuestionResponseDto(true, result));
				 
			 } catch(Exception e) {
				 logEntry.setResponse(new QuestionResponseDto(e));
				 logEntry.setException(e);
			 }

			 return logEntry.getResponse();
		 }
	 }

	 @POST
	 @Path("/insert")
	 @ApiOperation(value = "Adds a new question with answer and distractors to the cache",
	    response = QuestionResponseDto.class,
	    notes = "The inserted question with computed id is returned by this method")	 
	 @ApiImplicitParams(
		{@ApiImplicitParam(name = "question", value = "Specifies the properties of the question to be added", required = true, dataType = "QuestionDto", paramType = "body")})
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public QuestionResponseDto insert(QuestionDto question) {
		 
		 try(ResponseLogEntry<QuestionResponseDto> logEntry = 
				 new ResponseLogEntry<QuestionResponseDto>("QuestionService.insert")) {
	
			 try {
				 QuestionDto result = Question.insertObjectDto(question);
				 logEntry.setResponse(new QuestionResponseDto(true, result));
				 
			 } catch(Exception e) {
				 logEntry.setResponse(new QuestionResponseDto(e));
				 logEntry.setException(e);
			 }

			 return logEntry.getResponse();
		 }
	 }

	 @POST
	 @Path("/update")
	 @ApiOperation(value = "Updates the properties, answer and distractors of the specified question",
	    response = QuestionResponseDto.class,
	    notes = "The updated question is returned by this method")	 
	 @ApiImplicitParams(
		{@ApiImplicitParam(name = "question", value = "Specifies the properties of the question to be updated", required = true, dataType = "QuestionDto", paramType = "body")})
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public QuestionResponseDto update(QuestionDto question) {
		 
		 try(ResponseLogEntry<QuestionResponseDto> logEntry = 
				 new ResponseLogEntry<QuestionResponseDto>("QuestionService.update")) {
	
			 try {
				 QuestionDto result = Question.updateObjectDto(question);
				 logEntry.setResponse(new QuestionResponseDto(true, result));
				 
			 } catch(Exception e) {
				 logEntry.setResponse(new QuestionResponseDto(e));
				 logEntry.setException(e);
			 }

			 return logEntry.getResponse();
		 }
	 }


}
