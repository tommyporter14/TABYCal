<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class= "top">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/day-and-week.css">

<title>Week Schedule</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Ubuntu"
	rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">

<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="path/to/font-awesome/css/font-awesome.min.css">
</head>
<body>
	<div class= "outer-container">
	<div class="week-heading">
		<a class ="btn btn-light" title="Previous Week" href="/week/${previousWeek}">
		 <i class="fas fa-angle-left prev"></i>
		 </a>
		<div class= date-and-nav-buttons">
		<div class= "text-pointers">	
		Week of
		<c:out value="${weekList[0].getSpecificDayString()}" />
		Schedule
		</div>
		<div class= "nav-buttons">
                 <a class="btn btn-secondary" href="/create-event">Create Event</a>
                 <a class="btn btn-secondary" href="/check-availability">Check Availability</a>
                 <a class="btn btn-secondary" href="/month-calendar" >Back to Month</a>
         </div>			
		</div>
		<a class ="btn btn-light" title="Next Week" href="/week/${nextWeek}"> 
			<i class="fas fa-angle-right next"></i>
		 </a>
	</div>
	<div class="table">
		<table class=table>
			<thead class="sticky-head">
				<th class="time-week"></th>
				<c:forEach var="date" items="${weekList}">
					<th> <a href= "/day/${date.dateToString()}">
						<span class= "day-of-week">
							<c:out value="${date.getDayOfWeek()}" /> 
						</span>
						<span class= "day-string">
						<br>
							<c:out value="${date.getSpecificDayString()}" />
						</span>
						</a> 
							<c:if test="${holidayHelper.isThisAHoliday(date)}">
							<br>
							<span class= "holiday">
							<c:out value="${holidayHelper.showActualHoliday(date).name}" />
							</span>
						</c:if>
					</th>

				</c:forEach>
			</thead>


			<tbody class="scroll-body">
				<c:forEach begin="${startTime.getHour()}" end="${endTime.getHour()}" varStatus="loop">
					<tr>
						<td class= "time-week"> ${eventsHelper.printHourForWeek(loop.index)}</td>
						<c:forEach var="date" items="${weekList}">
							<td><c:forEach var="eventforday"
									items="${eventsHelper.eventsForDayAtTime(loop.index, date)}">
									<a href="/event-overview/?id=${eventforday.id}">${eventforday.eventName}</a>
									<!--  	<c:forEach var="user" items="${eventforday.users}">
								<br>${user}<br>
								</c:forEach>-->

									<br>
								</c:forEach></td>
						</c:forEach>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	
	</div>
   </div>
</body>
</html>