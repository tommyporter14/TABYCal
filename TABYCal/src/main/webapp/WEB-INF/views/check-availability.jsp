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
			<input type="datetime-local" name="end" /><span>Start Time</span><br>
			<!-- need to figure out how to select users to check between with Yaksh -->
			<input type="submit" value="Submit"/><br>
	</form>
	<a href="/">Home</a>			
</body>
</html>