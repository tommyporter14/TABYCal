<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Week</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
</head>
<body>
<div class= "week-heading">
	Week of <c:out value="${weekList[0].getSpecificDayString()}" />

</div>
	<div class="table">
		<table class=table>
			<thead>
				<th> </th>
				<c:forEach var="date" items="${weekList}">
					<th><c:out value="${date.getDayOfWeek()}" /> 
						<c:out value="${date.getSpecificDayString()}"/>
					</th>	 	

				</c:forEach>
			</thead>
		</table>
	</div>
</body>
</html>