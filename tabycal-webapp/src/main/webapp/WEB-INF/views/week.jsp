<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="path/to/font-awesome/css/font-awesome.min.css">
</head>
<body>
	<div class="week-heading">
		Week of
		<c:out value="${weekList[0].getSpecificDayString()}" />
		Schedule

	</div>
	<div class="table">
		<table class=table>
			<thead class="sticky-head">
				<th></th>
				<c:forEach var="date" items="${weekList}">
					<th><c:out value="${date.getDayOfWeek()}" /> <c:out
							value="${date.getSpecificDayString()}" /> 
							<c:if test="${holidayHelper.isThisAHoliday(date)}">
							<br>
							<c:out value="${holidayHelper.showActualHoliday(date).name}" />
						</c:if></th>

				</c:forEach>
			</thead>


			<tbody class="scroll-body">
				<c:forEach begin="6" end="23" varStatus="loop">
					<tr>
						<td>${loop.index}:00</td>
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
		<a href="/month-calendar" class="button">Go Back to Calendar</a>
	</div>
</body>
</html>