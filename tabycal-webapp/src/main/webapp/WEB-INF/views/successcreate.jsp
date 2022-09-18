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
                <title> SuccessPage </title>
            </head>

            <body>
                <div style="text-align:center" class="main-account">
                    <p class="sign" >Success!</p>
                    <p class="sign" >Account Details</p>

                    <br><label class="sign" for="username">User Name</label><br>
                    <p class="un" id="username" >${addedUser.userName}</p>
                    <br><label class="sign" for="firstname">First Name</label><br>
                    <p class="un" id= "firstname">${addedUser.firstName}</p>
                    <br><label class="sign" for="lstname">Last Name</label><br>
                    <p class="un" id= "lastname">${addedUser.lastName}</p>
                    
                    <p class="create" ><a href="/">login</p>
                 

                </div>

            </body>

            </html>