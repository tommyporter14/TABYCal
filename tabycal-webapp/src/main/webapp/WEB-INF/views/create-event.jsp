<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <link rel="stylesheet" href="/style.css">
                <meta name="viewport" content="width=device-width, initial-scale=1" />
                <title>Create An Event</title>
            </head>

            <body>

                <div class="main-account">
                    <p class="sign">Create An Event!</p>
                    <!-- <p class="sign">${message}</p> -->
                    <form class="form1" action="/event-created" method="post">
                        <input class="un" type="text" placeholder="Event Name" name="eventName" required /><br>
                        <input class="un" type="text" placeholder="Event Description" name="description" /> <br>
                        <p class="create">Start Time</p>
                        <input class="un" type="datetime-local" placeholder="" name="start" required /><br>
                        <p class="create">End Time</p>
                        <input class="un" type="datetime-local" placeholder="" name="end" required /><br>
                        <p class="create">Add Attendees</p>
                        <select class="un" name="users" multiple>
                            <c:forEach var="user" items="${users}">
                                <option value="${user.userName}">${user.userName}</option>
                            </c:forEach>
                        </select>
                        <input class="submit" type="submit" class="submit" value="Submit">
                        <p class="create"><a href="/month-calendar">Back To Month View?</p>
                    </form>

                </div>
            </body>

            </html>
