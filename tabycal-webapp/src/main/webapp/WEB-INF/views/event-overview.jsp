<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <link rel="stylesheet" href="/style.css" />
                <meta name="viewport" content="width=device-width, initial-scale=1" />
                <title>Event Overview</title>
            </head>


			<body>
				<div style="text-align:center" class="main-account">
				<p class="sign">Event Overview</p>
				<br><label class="create" for="username">Event Name</label><br>
				<p class="un">${event.eventName}</p>
				<br><label class="create" for="username">Event Start</label><br>
				<p class="un">${event.getReadableStartTime()}</p>
				<br><label class="create" for="username">Event End</label><br>
				<p class="un">${event.getReadableEndTime()}</p>
				<br><label class="create" for="username">Event Description</label><br>
				<p class="un">${event.description}</p>
				<br><label class="create" for="username">Event Duration</label><br>
				<p class="un">${event.duration} hrs</p>
				<br><label class="create" for="username">Event Attendees</label><br>
				<p class="un">
					<c:forEach var="user" items="${event.users}">
						| ${user} | <br>
					</c:forEach>
				</p>
				<p class="create"><a href="/update-event/?id=${event.id}">Update Event?</a></p>
				<p class="create"><a href="/successfully-deleted/?id=${event.id}">Delete Event?</a></p>
				<p class="create"><a href="/month-calendar">Back To Month View?</a></p>
				
				</div>

			</body>

			</html>