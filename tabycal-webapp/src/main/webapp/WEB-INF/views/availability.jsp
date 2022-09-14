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
<p>${available}</p>
<p>${message}</p>
<a href="/create-event">Create Event?</a>	
<a href="/month-calendar">Back To Month View?</a>	

</body>
</html>