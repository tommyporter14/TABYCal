<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/day-and-week.css"> 

<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
<title>Day</title>
</head>
<body>
<div class="day-heading">
	Schedule for ${dayOfWeek}, ${month} ${day}
</div>
	<div class="table">
		<table class=table>
			<thead class="sticky-head">
				<th> </th>
					<th>${dayOfWeek}, ${month} ${day}</th>			
			</thead>
			
			<tbody>
				<tr>
					<td>6:00</td>
				</tr>
				<tr>
					<td>7:00</td>
				</tr>
				<tr>
					<td>8:00</td>
				</tr>
				<tr>
					<td>9:00</td>
				</tr>
				<tr>
					<td>10:00</td>
				</tr>
				<tr>
					<td>11:00</td>
				</tr>
				<tr>
					<td>12:00</td>
				</tr>
				<tr>
					<td>1:00</td>
				</tr>
				<tr>
					<td>2:00</td>
				</tr>
				<tr>
					<td>3:00</td>
				</tr>
				<tr>
					<td>4:00</td>
				</tr>
				<tr>
					<td>5:00</td>
				</tr>
				<tr>
					<td>6:00</td>
				</tr>
				<tr>
					<td>7:00</td>
				</tr>
				<tr>
					<td>8:00</td>
				</tr>
			</tbody>

		</table>
		<a href="/month-calendar" class="button">Go Back to Calendar</a>

	</div>




</body>
</html>