<h1>Implement controllers for registering, retrieving, and updating student information. </>

## Project Configuration
- Maven project
- Packaging: war
- Use Spring MVC
- Use WebApplicationInitializer to eliminate the web.xml file.
- Class Implementation

## Must implement the following 3 classes:
- StudentRegisterController
- StudentController
- StudentRepositoryImpl

Additional classes can be created if necessary. <br>
Should not include any code that is not related to the assignment.


## Model Related
- Use both 2 ways of using @ModelAttribute: 
  1. For a specific attribute, 
  2. For common attributes 
- Use Model, ModelMap, and ModelAndView at least once each.

## GET /student/{studentId}?hideScore=yes
- In this case, do not display the score and evaluation items (use the params attribute).
- Implement as a separate controller method from GET /student/{studentId}.

## Input form validation for registration and modification
- Name: Remove whitespace and the length of the resulting string must be greater than 0.
- Email: Must be in email format.
- Score: Must be between 0 and 100.
-Evaluation: Remove whitespace and the length of the resulting string must be greater than 0 and less than or equal to 200.

## Error handling
- If accessing a non-existent resource when accessing the following URLs, use Controller-based exception handling to respond with Http Status Code 404:
  - GET /student/{studentId}
  - GET /student/{studentId}/modify
- All other exceptions must be handled using @ControllerAdvice.
