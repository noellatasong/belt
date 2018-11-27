<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Course</title>
</head>
<body>

	<h1>Create a New Course</h1>
		<form:form action="/course/create" method="POST" modelAttribute="course">
    
        <p><form:label path="name">  Name: </form:label></p>
		<p><form:errors path="name"></form:errors> </p>
        <p><form:input path="name"></form:input></p>

        <p><form:label path="instructor"> Instructor: </form:label></p>
        <p><form:errors path="instructor"></form:errors> </p>
        <p><form:input path="instructor"></form:input></p>
        
        <p><form:label path="capacity"> Capacity: </form:label></p>
        <p><form:errors path="capacity"></form:errors> </p>
        <p><form:input path="capacity"></form:input></p>

        <p><input type="submit" value="Create Course" /></p>
        
    </form:form>
</body>
</html>