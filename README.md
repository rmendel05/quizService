# quizService
##A RESTful service for creating, updating and querying quiz questions

[Codebase]()

To install the service, download and place the contents of the following target folder to an empty folder on your local machine.  

  https://github.com/rmendel05/quizService/tree/master/quizServer/target

Please note that the service requires Version 1.8 of the Java Runtime Environment.  Make sure your local folder contains a file called QuestionData.csv.  This file contains all the questions to be loaded during service startup and should be in the following format:

  question|answer|distractor1,distractor2,..,distractorN

Then start the service by executing the following command:

```
java -jar quizServer-0.0-SNAPSHOT-jar-with-dependencies.jar
```

After starting up the service, open a web browser and navigate to the REST endpoint.  The service is hosted on localhost, port 8080 at this address:

http://localhost:8080/api/question

For example, to query the question having object id=1, navigate to:

http://localhost:8080/api/question/getObject/1

Please see the section on Further Documentation below for a convenient way to explore the API using Swagger.

##Codebase ## 
When reviewing the code, it might help to look at the following areas first:

Endpoint definition: https://github.com/rmendel05/quizService/blob/master/quizServer/src/main/java/com/rmendel/quizManager/service/QuestionService.java

API Data Transfer Objects (DTOs): https://github.com/rmendel05/quizService/tree/master/quizServer/src/main/java/com/rmendel/quizManager/api

In addition, unit tests can be found in the following location: https://github.com/rmendel05/quizService/tree/master/quizServer/src/test/java/com/rmendel

##Testing the Service  
To test the service, you can use a tool such as Postman, a Google Chrome browser plugin.  Here are some examples of calls to the methods:

###Lookup a Single Question by Internal ID
          |                                                                   |
----------|-------------------------------------------------------------------
Url|http://localhost:8080/api/question/getObject/25
Method|GET

Response:
```JSON
{
  "results": [
      {
          "objectNotion": "question",
          "questionText": "What is 812 * 2069?",
          "answer": {
              "objectNotion": "answer",
              "responseText": "1680028",
              "questionID": "25",
              "id": "84"
          },
          "wordCount": 9,
          "distractors": [
              {
                  "objectNotion": "answer",
                  "responseText": "6397",
                  "questionID": "25",
                  "id": "85"
              },
              {
                  "objectNotion": "answer",
                  "responseText": " 85",
                  "questionID": "25",
                  "id": "86"
              }
          ],
          "id": "25"
      }
  ],
  "errorMessage": null,
  "objectNotion": "questionResponse",
  "success": true,
  "rowCount": 1,
  "id": "b7d741a2-3751-4e96-bf67-0a896745e7c0"
}
```

###Query by Distractor Count
          |                                                                   |
----------|-------------------------------------------------------------------
Url|http://localhost:8080/api/question/query
Method|POST
Headers|Content-Type: application/json
Body:
```JSON
{
"filter": {
  "filterType": "distractorCount",
  "minValue": 2,
  "maxValue": 2
},
"sort": {
  "sortByAttribute": "wordCount"
},
"page": {
  "rowsPerPage": 2,
  "pageOffset": 2
}
}
```
Note that the Content-Type header is required for all POST methods.

Response:
```JSON
{
  "results": [
      {
          "objectNotion": "question",
          "questionText": "What is 2368 * 5998?",
          "answer": {
              "objectNotion": "answer",
              "responseText": "14203264",
              "questionID": "3631",
              "id": "12673"
          },
          "wordCount": 9,
          "distractors": [
              {
                  "objectNotion": "answer",
                  "responseText": "5456",
                  "questionID": "3631",
                  "id": "12674"
              },
              {
                  "objectNotion": "answer",
                  "responseText": " 4930",
                  "questionID": "3631",
                  "id": "12675"
              }
          ],
          "id": "3631"
      },
      {
          "objectNotion": "question",
          "questionText": "What is 3804 - 5903?",
          "answer": {
              "objectNotion": "answer",
              "responseText": "-2099",
              "questionID": "3632",
              "id": "12676"
          },
          "wordCount": 9,
          "distractors": [
              {
                  "objectNotion": "answer",
                  "responseText": "9208",
                  "questionID": "3632",
                  "id": "12677"
              },
              {
                  "objectNotion": "answer",
                  "responseText": " 8868",
                  "questionID": "3632",
                  "id": "12678"
              }
          ],
          "id": "3632"
      }
  ],
  "errorMessage": null,
  "objectNotion": "questionResponse",
  "success": true,
  "rowCount": 2,
  "id": "81eaf89a-9d20-4e9d-ba30-b88340c66ce1"
}
```

###Query by Word Count
          |                                                                   |
----------|-------------------------------------------------------------------
Url|http://localhost:8080/api/question/query
Method|POST
Headers|Content-Type: application/json
Body:
```JSON
{
"filter": {
  "filterType": "wordCount",
  "minValue": 0,
  "maxValue": 10
},
"sort": {
  "sortByAttribute": "wordCount"
},
"page": {
  "rowsPerPage": 2,
  "pageOffset": 2
}
}
```

Response:
```
{
  "results": [
      {
          "objectNotion": "question",
          "questionText": "What is 6190 + 9800?",
          "answer": {
              "objectNotion": "answer",
              "responseText": "15990",
              "questionID": "2307",
              "id": "8005"
          },
          "wordCount": 7,
          "distractors": [
              {
                  "objectNotion": "answer",
                  "responseText": "8456",
                  "questionID": "2307",
                  "id": "8006"
              }
          ],
          "id": "2307"
      },
      {
          "objectNotion": "question",
          "questionText": "What is 7696 * 896?",
          "answer": {
              "objectNotion": "answer",
              "responseText": "6895616",
              "questionID": "2316",
              "id": "8037"
          },
          "wordCount": 7,
          "distractors": [
              {
                  "objectNotion": "answer",
                  "responseText": "3590",
                  "questionID": "2316",
                  "id": "8038"
              }
          ],
          "id": "2316"
      }
  ],
  "errorMessage": null,
  "objectNotion": "questionResponse",
  "success": true,
  "rowCount": 2,
  "id": "29863210-472b-45c8-a6d8-47b1e2ba3c3e"
}
```

###Query for All Questions
          |                                                                   |
----------|-------------------------------------------------------------------
Url|http://localhost:8080/api/question/query
Method|POST
Headers|Content-Type: application/json
Body:
```JSON
{ }
```

Response:
```
[A JSON collection of all questions is returned.]
```

###Insert a Question
Url|http://localhost:8080/api/question/insert
Method|POST
Headers|Content-Type: application/json
Body:
```JSON
{
"questionText": "Who was the first person to land on the moon?",
"distractors": [
  {
    "responseText": "Alan Shepard"
  },
  {
    "responseText": "John Glenn"
  },
  {
    "responseText": "Buzz Aldrin"
  }
],
"answer": {
  "responseText": "Neil Armstrong"
}
}
```
Response:
```JSON
{
  "results": [
      {
          "objectNotion": "question",
          "questionText": "Who was the first person to land on the moon?",
          "answer": {
              "objectNotion": "answer",
              "responseText": "Neil Armstrong",
              "questionID": "4000",
              "id": "13965"
          },
          "wordCount": 18,
          "distractors": [
              {
                  "objectNotion": "answer",
                  "responseText": "Alan Shepard",
                  "questionID": "4000",
                  "id": "13966"
              },
              {
                  "objectNotion": "answer",
                  "responseText": "John Glenn",
                  "questionID": "4000",
                  "id": "13967"
              },
              {
                  "objectNotion": "answer",
                  "responseText": "Buzz Aldrin",
                  "questionID": "4000",
                  "id": "13968"
              }
          ],
          "id": "4000"
      }
  ],
  "errorMessage": null,
  "objectNotion": "questionResponse",
  "success": true,
  "rowCount": 1,
  "id": "2d0e0d30-59ff-409e-a523-161918d4adf0"
}
```
Note the id of 4000 for the new question is returned above.  In addition, a unique id of the response for       tracking and logging purposes is also returned.

###Update a Question
          |                                                                   |
----------|-------------------------------------------------------------------
Url|http://localhost:8080/api/question/update
Method|POST
Headers|Content-Type: application/json
Body:
```JSON
{
"id": "4000",
"questionText": "Who was the first person to land on the moon?",
"distractors": [
  {
    "responseText": "Michael Collins"
  },
  {
    "responseText": "John Glenn"
  },
  {
    "responseText": "Buzz Aldrin"
  }
],
"answer": {
  "responseText": "Neil Armstrong"
}
}
```
Note that the id (4000) of the question (but not answers or distractors) is required in the update above.

Response:
```JSON
{
  "results": [
      {
          "objectNotion": "question",
          "questionText": "Who was the first person to land on the moon?",
          "answer": {
              "objectNotion": "answer",
              "responseText": "Neil Armstrong",
              "questionID": "4000",
              "id": "13969"
          },
          "wordCount": 18,
          "distractors": [
              {
                  "objectNotion": "answer",
                  "responseText": "Michael Collins",
                  "questionID": "4000",
                  "id": "13970"
              },
              {
                  "objectNotion": "answer",
                  "responseText": "John Glenn",
                  "questionID": "4000",
                  "id": "13971"
              },
              {
                  "objectNotion": "answer",
                  "responseText": "Buzz Aldrin",
                  "questionID": "4000",
                  "id": "13972"
              }
          ],
          "id": "4000"
      }
  ],
  "errorMessage": null,
  "objectNotion": "questionResponse",
  "success": true,
  "rowCount": 1,
  "id": "ccd9e546-061b-4ebf-bd44-fb6176f3f539"
}
```

###Erroneous Update
          |                                                                   |
----------|-------------------------------------------------------------------
Url|http://localhost:8080/api/question/update
Method|POST
Headers|Content-Type: application/json
Body:
```
{
"questionText": "Who was the first person to land on the moon?",
"distractors": [
  {
    "responseText": "Michael Collins"
  },
  {
    "responseText": "John Glenn"
  },
  {
    "responseText": "Buzz Aldrin"
  }
],
"answer": {
  "responseText": "Neil Armstrong"
}
}
```
(Above request is missing id.)

Response:
```JSON
{
  "results": [],
  "errorMessage": "Must specify a non-trivial value for parameter lookupID.",
  "objectNotion": "questionResponse",
  "success": false,
  "rowCount": 0,
  "id": "59446bb0-c8af-4356-9229-0b2c52e042d1"
}
```

##Further Documentation
Descriptions of objects and properties can be found by looking at the source code classes found here

https://github.com/rmendel05/quizService/tree/master/quizServer/src/main/java/com/rmendel/quizManager/api

The Java classes and their properties are marked with @ApiModel and @ApiModelProperty annotations containing notes about each element.  This application produces swagger.json documents based on these annotations.  Once you have the Quiz Service running as specified above, you can explore this documentation in a browser from this link:

http://petstore.swagger.io/?url=http%3A%2F%2Flocalhost%3A8080%2Fapi%2Fswagger.json#!/question

Please note that the Swagger site above will also allow execution of GET methods.  However, this will not support POST methods because they require the ```Content-Type: application/json``` header to be specified.

##Things to Improve
* Handle when incoming JSON objects are formatted incorrectly, such as missing comma from array spec.  Add better error messages in responses for these cases.
* Return an indication of whether the specified page is the last one containing data
* Add more business rules to enforce data integrity, such as required values and uniqueness constraints
* Add more ways to filter
* Add more ways to sort
* Add more query tests
* Create indexes for supported search keys and key words
* Synchronize access to table data
* Move QuestionTable cache to a real database
* For distractor updates, support merging of existing items with new items by id
* Handle quoted separators in CSV parser
* Test Question table and business objects more directly
* Use generics to convert between server objects and DTOs
* Add class hierarchy to support logging plugins.
