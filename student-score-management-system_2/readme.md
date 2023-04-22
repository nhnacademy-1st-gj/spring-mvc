**Implement a controller to register, retrieve, and update student information based on the Day 1 assignment.**

# View changes
- Use Thymeleaf view template engine instead of JSP.

# Multilingual support
- All messages should support English and Korean.
- Language change should be possible using cookies, sessions, or resolvers. Any method can be used.

# Add login/logout functionality
- Other functions cannot be used without logging in.
- Create a LoginCheckInterceptor to check login status.
- If the session exists, no need to log in again.
- The login page should not be intercepted by the login interceptor.

# Provide REST API - JSON Request/Response
- Register student information: POST /students
- Retrieve student information: GET /students/{studentId}
- Update student information: PUT /students/{studentId}"
