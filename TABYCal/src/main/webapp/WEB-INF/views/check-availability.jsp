<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check Availability</title>
</head>
<body>
<h1>Fill Out The Form Below To Find Open Times For Scheduling</h1>
	<form action="/availability" method="POST">
			<input type="datetime-local" name="start"/><span>Start Time</span><br>
			<input type="datetime-local" name="end" /><span>End Time</span><br>
			<h3>Select Employees To Check Availability With:</h3>
			<p>Hold control/command to select multiple employees</p>
			<select name="users" multiple>
    				<c:forEach var="user" items="${users}">
						<option value="${user.userName}">${user.userName}</option>
					</c:forEach>
  			</select>
  			<input type="submit" value="Submit"/><br>

	</form>
	<a href="/">Home</a>			
</body>
</html>