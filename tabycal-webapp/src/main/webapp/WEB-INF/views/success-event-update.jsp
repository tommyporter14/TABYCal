<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <link rel="stylesheet" href="/style.css">
                <meta name="viewport" content="width=device-width, initial-scale=1" />
                <title> Successfully updated event </title>
            </head>

            <body>

                <div style="text-align:center" class="main-account">
                    <p class="sign">Successfully Updated Event!</p>

                    <br><label class="create" >Event Name</label><br>
                    <p class="un">${updatedEvent.eventName}</p>
                    <br><label class="create" >Event Description</label><br>
                    <p class="un">${updatedEvent.description}</p>
                    <br><label class="create" >Start Time </label><br>
                    <p class="un">${updatedEvent.getReadableStartTime()}</p>
                    <br><label class="create" >End Time </label><br>
                    <p class="un">${updatedEvent.getReadableEndTime()}</p>
                    <br><label class="create" >Event Duration</label><br>
                    <p class="un">${updatedEvent.duration}</p>
                    <br><label class="create" >Event Attendees</label><br>
                    <p class="un">
                    <c:forEach var="user" items="${updatedEvent.users}">
						| ${user} | <br>
					</c:forEach>
                    </p>
                    <p class="create"><a href="/month-calendar">Back To Month View?</p>
                </div>
            </body>

            </html>