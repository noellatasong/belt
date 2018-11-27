<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<h1>Welcome!, ${ user.name }</h1>
	<a href="">Low Sign Up</a>
	<a href="">High Sign Up</a>
	<h1>Courses</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Course</th>
				<th>Instructor</th>
				<th>Signups</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="course" items="${ courses }" >
			<tr> 
				<td><a href="/course/${course.id }">${ course.name }</a></td>
				<td>${  course.instructor } </td>
				<td>${course.users.size()} / ${course.capacity } </td>
				<td>
				<c:if test="${course.users.size() == course.capacity}">
				<p>Full</p>
				</c:if>
								
				<c:if test="${course.user != course.user}">
				<p>Already Added</p>
				</c:if>
								 
				
				<form action="/course/addcourse/${user.id}/${course.id}" method="post">
				<input type="submit" value="ADD">
				</form>
				
				 
				
				
				</td>
			</tr>
		</c:forEach>

		</tbody>
	</table>
	<br>
	<br>
	
	<a href="/course/add">ADD a Course</a>
</body>
</html>