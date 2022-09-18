<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="style.css" />
    <link
      href="https://fonts.googleapis.com/css?family=Ubuntu"
      rel="stylesheet"
    />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      rel="stylesheet"
      href="path/to/font-awesome/css/font-awesome.min.css"
    />
    <title>New Account</title>
  </head>

  <body>
    <c:if test="${not empty errorMsg}">
      <script>
        alert("${errorMsg}");
      </script>
    </c:if>
    <div class="main-account">
      <p class="sign">New Account Details</p>

      <form class="form1" action="/createuser" method="post">
        <input
          class="un"
          type="text"
          placeholder="Email"
          name="userName"
          required
        />
        <input
          class="un"
          type="text"
          placeholder="First Name"
          name="firstName"
          required
        />
        <input
          class="un"
          type="text"
          placeholder="Last Name"
          name="lastName"
          required
        />

        <!--work on aligning this later -->
        <p class="create">Date of Birth</p>
        <input
          class="un"
          id="dateOfBirth"
          type="date"
          name="dateOfBirth"
          required
        />
        <!-- //need to make true and false  -->
        <p hidden class="create">Check for Admin</p>
        <input hidden
          class="un"
          id="adminStatus"
          type="checkbox"
          name="adminStatus"
        /><br />

        <input type="submit" class="submit" value="Submit" />
      </form>
    </div>
  </body>
</html>
