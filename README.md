# My School API Backend

## Background
Administrators can perform administrative functions for teachers and students. Teachers and students are identified by their email addresses.

## Assumptions
1. Login and access control have already been handled.

## How to run locally
1. Download/Clone source code from below link - https://github.com/ishanul/myschool_admin
2. It is by default connecting to the Postgres DB, hosted locally. If we need to change db related config, pls change in "src/main/resources/application.properties".
   spring.datasource.url=jdbc:postgresql://localhost:5432/school
   spring.datasource.username=*****
   spring.datasource.password=*****
3. Swagger URL to access the API end points
   http://localhost:8080/swagger-ui/

## User stories

### 1. As an administrator, I want to add new teachers and students to the system.

* To add a student
    * Endpoint: `POST /api/students`
    * Headers: `Content-Type: application/json`
    * Success response status: HTTP 201
    * Request body example:
```
{
  "email" : "studentmary@gmail.com"
  "name" : "Mary"
}
```

* Swagger UI end point: http://localhost:8080/swagger-ui/#/teacher-controller/addUsingPOST_1
* API end point: http://localhost:8080/api/students

* To add a teacher
    * Endpoint: `POST /api/teachers`
    * Headers: `Content-Type: application/json`
    * Success response status: HTTP 201
    * Request body example:
```
{
  "email" : "teachermary@gmail.com"
  "name" : "Mary"
}
```
* Swagger UI end point: http://localhost:8080/swagger-ui/#/student-controller/addUsingPOST
* API end point: http://localhost:8080/api/teachers

### 2. As an administrator, I want to register one or more students to a specified teacher.
A teacher can register multiple students. A student can also be registered to multiple teachers.

* Endpoint: `POST /api/register`
* Headers: `Content-Type: application/json`
* Success response status: HTTP 204
* Request body example:
```
{
  "teacher": "teacherken@gmail.com"
  "students":
    [
      "studentjon@gmail.com",
      "studenthon@gmail.com"
    ]
}
```
* Swagger UI end point: http://localhost:8080/swagger-ui/#/registration-controller/registerUsingPOST
* API end point: http://localhost:8080/api/register

### 3. As an administrator, I want to retrieve a list of students common to a given list of teachers (i.e. retrieve students who are registered to ALL of the given teachers).

* Endpoint: `GET /api/commonstudents`
* Success response status: HTTP 200
* Request example 1: `GET /api/commonstudents?teacher=teacherken%40gmail.com`
* Success response body 1:
```
{
  "students" :
    [
      "commonstudent1@gmail.com", 
      "commonstudent2@gmail.com",
      "student_only_under_teacher_ken@gmail.com"
    ]
}
```
* Request example 2: `GET /api/commonstudents?teacher=teacherken%40gmail.com&teacher=teacherjoe%40gmail.com`
* Success response body 2:
```
{
  "students" :
    [
      "commonstudent1@gmail.com", 
      "commonstudent2@gmail.com"
    ]
}
```
* Swagger UI end point: http://localhost:8080/swagger-ui/#/query-controller/commonStudentsUsingGET
* API end point: http://localhost:8080/api/commonstudents

### 4. As a teacher, I want to suspend a specified student.

* Endpoint: `POST /api/suspend`
* Headers: `Content-Type: application/json`
* Success response status: HTTP 204
* Request body example:
```
{
  "student" : "studentmary@gmail.com"
}
```
* Swagger UI end point: http://localhost:8080/swagger-ui/#/suspension-controller/registerUsingPOST_1
* API end point: http://localhost:8080/api/suspend

## Other Notes

1. I did not use lombok to avoid extra dependency, but in real project, its better to use it to avoid lots of boilerplate code.
2. l10N was not handled to simplify the use cases. All the messages are in english.
3. java 11+ is required...

