<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">

                <!-- <link rel="stylesheet" href="/availability.css"> -->
                <link rel="stylesheet" href="/style.css" />
                <meta name="viewport" content="width=device-width, initial-scale=1" />
                <title>Availability</title>
            </head>

            <body>
                <div style="text-align:center" class="main-account">
                    <p class="sign">Availability</p>
                    <p class="sign">Free From:</p>
                    <c:forEach items="${available}" var="entry">
                        <fmt:parseDate value="${entry.key}" type="date" pattern="yyyy-MM-dd'T'HH:mm" var="parsedKey" />
                        <fmt:formatDate value="${parsedKey}" type="date" pattern="EEE, MM/dd/yyyy, h:mm a"
                            var="newKey" />
                        <fmt:parseDate value="${entry.value}" type="date" pattern="yyyy-MM-dd'T'HH:mm"
                            var="parsedValue" />
                        <fmt:formatDate value="${parsedValue}" type="date" pattern="EEE, MM/dd/yyyy, h:mm a"
                            var="newValue" />
                        <p class="un">${newKey} to ${newValue}</p>
                    </c:forEach>
                    <p class="un">${message}</p>
                    <a class="create" id="create-event" href="/create-event">Create Event?</a><br>
                    <a class="create" id="month-view" href="/month-calendar">Back To Month View?</a>
                </div>

            </body>

            </html>