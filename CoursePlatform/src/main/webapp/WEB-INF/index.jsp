<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration and Login</title>
<link rel="stylesheet" type="text/css" href="css/datestyle.css">
<script type="text/javascript" src="js/dateapp.js"></script>
</head>
<body>
<h1>Registration</h1>

	<c:if test="${ exists != null}">
    		<p>${ exists }</p>
    	</c:if>
	    
    <c:if test="${userError != null}">
        <p class="error"> ${userError}</p> 
    </c:if>

    <form:form action="/registration" method="POST" modelAttribute="user">
    

        <p><form:label path="name"> Name: </form:label></p>
        <p><form:errors path="name"></form:errors> </p>
        <p><form:input path="name"></form:input></p>

        <p><form:label path="email"> Email: </form:label></p>
        <p><form:errors path="email"></form:errors> </p>
        <p><form:input path="email" type="email" ></form:input></p>

        <p><form:label path="password"> Password: </form:label></p>
        <p><form:errors path="password"></form:errors></p> 
        <p><form:input path="password" type="password" ></form:input></p>

        <p><form:label path="confirm"> Password Confirmation: </form:label></p>
        <p><form:errors path="confirm"></form:errors> </p>
        <p><form:input path="confirm" type="password"></form:input></p>
        
        <p><input type="submit" value="Register" /></p>
        
    </form:form>
	
	<h1> Login </h1>
	
    <form:form action="/login" method="POST">
        <c:if test="${loginError != null}">
        	<p class="error"> ${loginError}</p> 
   	 	</c:if>
    	<p>Email:
		<input type="text" name="email"  /></p>
        
        <p>Password:
        <input type="password" name="password" /></p>
        
        <p><input type="submit" value="Login" /></p>
        
    </form:form>
    
    
</body>
</html>