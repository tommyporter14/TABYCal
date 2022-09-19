<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Availability</title>
</head>
<body>
<h1>Availability</h1>
<h3>Free From:</h3>
<c:forEach items="${available}" var="entry">
    <fmt:parseDate  value="${entry.key}"  type="date" pattern="yyyy-MM-dd'T'HH:mm" var="parsedKey" />
	<fmt:formatDate value="${parsedKey}" type="date" pattern="EEE, MM/dd/yyyy, h:mm a" var="newKey" />
    <fmt:parseDate  value="${entry.value}"  type="date" pattern="yyyy-MM-dd'T'HH:mm" var="parsedValue" />
	<fmt:formatDate value="${parsedValue}" type="date" pattern="EEE, MM/dd/yyyy, h:mm a" var="newValue" />
    
    <p>${newKey} to ${newValue}</p><br>


</c:forEach>
<p>${message}</p>
<a href="/create-event">Create Event?</a>	
<a href="/month-calendar">Back To Month View?</a>	

</body>
</html>

