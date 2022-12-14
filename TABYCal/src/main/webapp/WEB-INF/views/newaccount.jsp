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
                <title>New Account</title>
            </head>

            <body>
                <div class="main-account">
                    <p class="sign">New Account Details</p>
                    <c:if test="${not empty userName}">
                        <script>
                            function myFunction() {
                                alert("${userName} is not available");
                            }
                        </script>
                    </c:if>
                    
                    <form class="form1" action="/createuser" method="post">
                        <input class="un" type="text" placeholder="Email" name="userName" required>
                        <input class="un" type="text" placeholder="First Name" name="firstName" required>
                        <input class="un" type="text" placeholder="Last Name" name="lastName" required>

                        <!--work on aligning this later -->
                        <br><label class="sign" for="dateOfBirth">Birth Day:</label><br>

                        <input class="un" id="dateOfBirth" type="date" name="dateOfBirth" required>
                        <!-- //need to make true and false  -->
                        <label class="sign" for="adminStatus">Enable Admin:</label><input id="adminStatus"
                            type="checkbox" name="adminStatus">

                        <input type="submit" class="submit" value="Submit">

                    </form>

                </div>

            </body>

            </html>