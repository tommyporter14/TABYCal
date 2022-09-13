<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <link rel="stylesheet" href="style.css">
                <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
                <meta name="viewport" content="width=device-width, initial-scale=1" />
                <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
                <title>Sign in</title>
            </head>

            <body>
                <div class="main">
                    Event Count<p>${myEvents}</p></br>
                    <c:forEach var="event" items="${myEvents}">
                        Event Name<p>${event.eventName}</p></br>
                        Start Time<p>${event.startTime}</p></br>
                        End Time<p>${event.endTime}</p></br>
                        Attendees<p>${event.users}</p></br>
                    </c:forEach>
                </div>

            </body>

            </html>