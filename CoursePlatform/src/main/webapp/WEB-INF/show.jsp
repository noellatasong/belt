<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${ singleCourse.name }</h1>
<h3>Instructor: ${singleCourse.instructor }</h3>
<h3>Sign Ups:${singleCourse.users.size()}</h3>

<table border="1">
		<thead>
			<tr>
				<th>Name</th>
				<th>Signup Date</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
	<c:forEach var="signup" items="${ signups }">
			<tr> 
				<td>${signup.user.name }</td>
				<td>${signup.createdAt } </td>
				<td>
				<c:if test="${sessionScope.user == signup.user.id}">
				<p>remove</p>
				</c:if>
				
				 </td>
	
			</tr>
	</c:forEach> 

		</tbody>
	</table>
	<br>
	<br>
	
	<a href="/course/edit/${singleCourse.id}"><button>Edit</button></a>
	
	<a href="/course/remove/${singleCourse.id}"><button>Delete</button></a>
</body>
</html>