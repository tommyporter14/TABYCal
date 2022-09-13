<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Holidays</title>
            </head>

            <body>
                <p>Holiday</p>
                     
                <c:forEach var="holiday" items="${holidays}">
                    <div>${holiday.date}</div>
                    <div>${holiday.localName}</div>
                    <div>${holiday.name}</div>
                    <div>${holiday.countryCode}</div>
                </c:forEach>
            </body>

            </html>