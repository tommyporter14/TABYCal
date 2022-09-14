<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Event Overview</title>
</head>
<body>

	<h1>${event.eventName}</h1>
	<p>${event.getReadableStartTime()}<p>
	<p>${event.getReadableEndTime()}<p>
	<p>${event.description}<p>
	<p>${event.duration} hrs<p>
	<p><c:forEach var= "user" items="${event.users}">
	 | ${user} |  
	</c:forEach></p>
	
	<a href="/successfully-deleted/?id=${event.id}">Delete Event?</a>
	<a href="/month-calendar">Back To Month View?</a>

</body>
</html>