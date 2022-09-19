<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <link rel="stylesheet" href= "/style.css" />
                <meta name="viewport" content="width=device-width, initial-scale=1" />
                <title>Event Overview</title>
            </head>

            <body>
                <div class="main-account">
                    <p class="sign">Availability</p>
                    <p class="un">${available}</p>
                    <p class="un">${message}</p>
                    <p class="create"><a href="/create-event">Create Event?</a> </p>
                    <p class="create"><a href="/month-calendar">Back To Month View?</a> </p>
                </div>
            </body>

            </html>