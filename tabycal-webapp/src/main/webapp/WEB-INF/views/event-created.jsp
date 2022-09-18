<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Successfully Created Event</title>
</head>
<body>
<h1>Successfully Created Event</h1>

	<p>${event.eventName}</p>
	<p>${event.description}</p>
	<p>${event.getReadableStartTime()}</p>
	<p>${event.getReadableEndTime()}</p>
	<p>${event.duration}</p>
	<p><c:forEach var= "user" items="${event.users}">
	 | ${user} |  
	</c:forEach></p>
	 <a href="/month-calendar">Back To Month View?</a>
</body>
</html>
