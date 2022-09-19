<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/day-and-week.css"> 

<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
<title>Day</title>
</head>
<body>
<div class="day-heading">
		<div class = "text-pointers">
		<a class ="btn btn-light" title="Previous Day" href="/day/${dayBefore}"> << </a>
		Schedule for ${dayOfWeek}, ${month} ${day}
	<c:if test="${holidayHelper.isThisAHoliday(dateTime)}">
		<br><c:out value= "${holidayHelper.showActualHoliday(dateTime).name}"/>
	</c:if>		
		<a class ="btn btn-light" title="Next Day" href="/day/${nextDay}"> >> </a>
	</div>
		<div class= "nav-buttons">
			  <a class="btn btn-secondary" href="/month-calendar" >Back to Calendar</a>
              <a class="btn btn-secondary" href="/create-event">Create Event</a>
              <a class="btn btn-secondary" href="/check-availability">Check Availability</a>			
		</div>
</div>
	<div class="table">
		<table class=table>
			<thead class="sticky-head">
				<th> </th>
					<th>${dayOfWeek}, ${month} ${day}</th>			
			</thead>
			
			<tbody>
				<c:forEach var= "event" items="${listOfDayEvents}">
				<tr>
					<td class= "day-time">${event.getReadableStartHour()} -
					 <br> ${event.getReadableEndHour()}</td>
					<td><a href="/event-overview/?id=${event.id}">${event.eventName}</a>
					<br> 
						<c:forEach var="user" items="${event.users}">
					<br> ${user}
					</c:forEach>
					</td>
				</tr>
				</c:forEach>
			</tbody>

		</table>
		<a href="/month-calendar" class="button">Go Back to Calendar</a>

	</div>




</body>
</html>