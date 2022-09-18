<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="/event-success-overview-delete.css"> 
<title>Create An Event</title>
</head>
<body>
<h1>Create An Event</h1>
 	
 	<h3>${message}</h3>
                    
                    <form class="eventform" action="/event-created" method="post">
                        <input type="text" name="eventName" required/><span>Name</span><br>
                        <input type="text" name="description"/><span>Description</span><br>
                        <input type="datetime-local" name="start" required/><span>Start Time</span><br>
                        <input type="datetime-local" name="end" required/><span>End Time</span><br>
                      	<select name="users" multiple>
		    				<c:forEach var="user" items="${users}">
								<option value="${user.userName}">${user.userName}</option>
							</c:forEach>
  						</select>
                        <input type="submit" class="submit" value="Submit">

                    </form>
             <a id="month-view" href="/month-calendar">Back To Month View?</a>
</body>
</html>