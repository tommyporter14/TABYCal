<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <link rel="stylesheet" href="/style.css">
                <meta name="viewport" content="width=device-width, initial-scale=1" />
                <title>Update An Event</title>
            </head>

            <body>

                <div class="main-account">
                    <p class="sign">Update An Event!</p>
                    <!-- <p class="sign">${message}</p> -->
                    <form class="form1" action="/event-updated" method="post">
                        
                        <input hidden type="text"  name="id" value=${event.id} required/>
                        <p class="create">Update Event Name</p>
                        <input class="un" type="text" value="${event.eventName}" name="eventName" required /><br>
                        <p class="create">Update Event Description</p>
                        <input class="un" type="text" value="${event.description}" name="description" /> <br>
                        <p class="create">Update Start Time</p>
                        <input class="un" type="datetime-local" value="${event.startTime}" name="start" required /><br>
                        <p class="create">Update End Time</p>
                        <input class="un" type="datetime-local" value="${event.endTime}" name="end" required /><br>
                        
                        <p class="create">Current Attendees</p>
                        <p class="un">
                            <c:forEach var="user" items="${event.users}">
                                | ${user} | <br>
                            </c:forEach>
                        </p>

                        <p class="create">Update Attendees</p>
                        <select class="un" name="users" multiple required>
                            <c:forEach var="user" items="${users}">
                                <option value="${user.userName}">${user.userName}</option>
                            </c:forEach>
                        </select>
                        <input class="submit" type="submit" class="submit" value="Submit">
                    </form>
                    <p class="create"><a href="/month-calendar">Back To Month View?</p>

                </div>
            </body>

            </html>
