# quizService
A RESTful service for creating, updating and querying quiz questions

To install the service, download and place the contents of the following target folder to an empty folder on your local machine.  

  https://github.com/rmendel05/quizService/tree/master/quizServer/target

Please note that the service requires Version 1.8 of the Java Runtime Environment.  Make sure your local folder contains a file called QuestionData.csv.  This file contains all the questions to be loaded during service startup and should be in the following format:

  question|answer|distractor1,distractor2,..,distractorN

Then start the service by executing the following command:

  java -jar quizServer-0.0.1-SNAPSHOT.jar

After starting up the service, open a web browser and navigate to the REST endpoint.  The service is hosted on localhost, port 8080 at this address:

  http://localhost:8080/api/question

For example, to query the question having object id=1, navigate to:

  http://localhost:8080/api/question/getObject/1
  
When reviewing the code, it might be helpful to look at the following areas first:

  Endpoint definition: https://github.com/rmendel05/quizService/blob/master/quizServer/src/main/java/com/rmendel/quizManager/service/QuestionService.java
  API Data Transfer Objects (DTOs): https://github.com/rmendel05/quizService/tree/master/quizServer/src/main/java/com/rmendel/quizManager/api
