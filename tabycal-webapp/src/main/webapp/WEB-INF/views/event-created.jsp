<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
			<!DOCTYPE html>
			<html>

			<head>
				<link rel="stylesheet" href="/style.css">
                <meta name="viewport" content="width=device-width, initial-scale=1" />
				<title>Successfully Created Event</title>
			</head>

			<body>

				<div style="text-align:center" class="main-account">
					<p class="sign">Successfully Created Event!</p>

					<br><label class="create" for="username">Event Name</label><br>
					<p class="un">${event.eventName}</p>
					<br><label class="create" for="firstname">Event Description</label><br>
					<p class="un">${event.description}</p>
					<br><label class="create" for="lstname">Start Time </label><br>
					<p class="un">${event.startTime}</p>
					<br><label class="create" for="lstname">End Time </label><br>
					<p class="un">${event.endTime}</p>
					<br><label class="create" for="lstname">Event Duration</label><br>
					<p class="un">${event.duration}</p>
					<br><label class="create" for="lstname">Event Attendees</label><br>
					<p class="un">${event.users}</p>
					<p class="create"><a href="/month-calendar">Back To Month View?</p>
				</div>
			</body>

			</html>