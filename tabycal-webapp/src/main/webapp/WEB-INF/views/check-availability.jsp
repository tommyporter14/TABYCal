<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
			<!DOCTYPE html>
			<html>

			<head>
				<link rel="stylesheet" href="/style.css">
                <meta name="viewport" content="width=device-width, initial-scale=1" />
				<title>Check Availability</title>
			</head>

			<body>
				<div class="main-account">
					<p class="sign">Find Open Times For Scheduling</p>
					<form class="form1" action="/availability" method="post">
						<p class="create">Enter Start Time</p>
						<input class="un"  type="datetime-local" placeholder="Start Time" name="start" /><br>
						<p class="create">Enter End Time</p>
						<input class="un" type="datetime-local" placeholder="End Time" name="end" /><br>
						<p class="create">Select Employees To Check Availability With:</p>
						<p class="create">Hold control/command to select multiple employees</p>
						<select class="un" name="users" multiple>
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
