<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Month</title>
                <link rel="stylesheet" href="style.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400;500;600;700&display=swap"
                    rel="stylesheet">
            </head>

            <body>
                <div class="container">
                    <div class="calendar">
                        <div class="month">
                            <i class="fas fa-angle-left
                prev"></i>
                            <div class="date">
                                <h1></h1>
                                <p></p>
                            </div>
                            <i class="fas fa-angle-right next"></i>
                        </div>
                        <div class="weekdays">
                            <div>Sun</div>
                            <div>Mon</div>
                            <div>Tue</div>
                            <div>Wed</div>
                            <div>Thu</div>
                            <div>Fri</div>
                            <div>Sat</div>
                        </div>
                        <div class="days">

                        </div>
                        <div class="events">
                            <h1>Events</h1>
                            <ul>
                                <c:forEach var="events" items="${events}">
                                    <li>
                                        <a>${events.eventName}</a>

                                    </li>
                                </c:forEach>

                            </ul>
                        </div>
                    </div>
                </div>
<<<<<<< HEAD
                <script src="script.js"></script>
            </body>

            </html>
=======
                <i class="fas fa-angle-right next"></i>
            </div>
            <div class="weekdays">
                <div>Sun</div>
                <div>Mon</div>
                <div>Tue</div>
                <div>Wed</div>
                <div>Thu</div>
                <div>Fri</div>
                <div>Sat</div>
            </div>
            <div class="days">
                
            </div>
 <div class="events">
<h1>Events</h1>
	<ul>
			<c:forEach var="events" items="${events}">
			<li>
				<form action="/event-overview" method="Post">
					<input type="hidden" name="id" value="${events.id}"/>
					<input type="submit" value="${events.eventName}"/>
				</form>
				
			</li>
			</c:forEach>
	
	</ul>
	<a href="/check-availability">Check For Availability?</a>
</div>
        </div>
    </div>
<script src="script.js"></script>
</body>
</html>
>>>>>>> TommysBranch
