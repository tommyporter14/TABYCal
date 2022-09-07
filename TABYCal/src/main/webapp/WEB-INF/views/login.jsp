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
                <title>Sign in</title>
            </head>

            <body>
                <div class="main">
                    <p class="sign" align="center">Sign in</p>
                    <form class="form1" action="/verifyaccount" method="get">
                        <input class="un " type="text" align="center" placeholder="Enter Email">
                        <!-- <input class="pass" type="password" align="center" placeholder="Password"> -->
                        <input type="submit" class="submit" align="center" value="Submit">
                        <p class="create" align="center"><a href="/newaccount">Create Account?</p>
                    </form>

                </div>

            </body>

            </html>