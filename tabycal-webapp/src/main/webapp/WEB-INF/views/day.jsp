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
	Schedule for ${dayOfWeek}, ${month} ${day}
	<c:if test="${holidayHelper.isThisAHoliday(dateTime)}">
		<br><c:out value= "${holidayHelper.showActualHoliday(dateTime).name}"/>
</c:if>
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
					<td>${event.startTime.getHour()}:${event.startTime.getMinute()} -
					 <br> ${event.endTime.getHour()}:${event.endTime.getMinute()}</td>
					<td>${event.eventName} 
					<br> ${event.description}
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