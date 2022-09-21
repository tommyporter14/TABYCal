<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Month</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css?family=Ubuntu"
	rel="stylesheet">
</head>
<body class ="body">
	<div class="outer-container">
        <div class="calendar">
            <div class="month">
                <i class="fas fa-angle-left
                prev"></i> 
                <div class="date">
                    <h1>
                    <span class= "month-heading-top"></span>
                    <span class= "year-heading-top"></span>
                    
                    </h1>
                    
                    <p></p>
                  <div class= "nav-buttons">
                  	<a class="btn btn-secondary" href="/create-event">Create Event</a>
                  	<a class="btn btn-secondary" href="/check-availability">Check Availability</a>
                  	<a class="btn btn-secondary" href="/login">Log Out</a>
                 </div>
                </div>
                <i class="fas fa-angle-right next"></i>
            </div>
            <div class="weekdays">
                <div>Sun</div>
                <div>Mon</div>
                <div>Tue</div>
                <div>Wed</div>
                <div>Thu</div>
                <div>Fri</div>
                <div>Sat</div>
                <div><i class="icon-sign-blank"></i>‎</div>
            </div>
            <div class="days">
       
            </div>
        </div>
 <div class="events">
     <ul>
        <c:forEach var="events" items="${events}">
            <li hidden>
                <a hidden id="eventId">${events.id}</a>
                <a hidden id="eventName">${events.eventName}</a>
                <a hidden id="startTime">${events.startTime}</a>
                <a hidden id="endTime">${events.endTime}</a>
            </li>
        </c:forEach>
    </ul>
 </div>
 <div class="holiday">
  	<ul>
       <c:forEach var="holiday" items="${holidays}">
            <li hidden>
                <a hidden id="holidayName">${holiday.name}</a>
                <a hidden id="holidayDate">${holiday.date}</a>
            </li>
       </c:forEach>
    </ul>
</div>
   
    </div>
<script src="script.js"></script>
</body>
</html>