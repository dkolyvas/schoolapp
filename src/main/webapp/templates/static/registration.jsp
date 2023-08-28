<%--
  Created by IntelliJ IDEA.
  User: kolyv
  Date: 12/8/2023
  Time: 2:10 μ.μ.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<! DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templates/css/style.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <p>Παρακαλούμε εισάγετε τον επιθυμητό username και password</p>
        </div>
        <div class="main">
            <form method="post" action="${pageContext.request.contextPath}/register">
                <div>
                    <label for="username">Username</label>
                    <input type="text" id = "username" name="username">
                </div>
                <div>
                    <label for="password1">Password</label>
                    <input type="password" id = "password1" name="password1">
                </div>
                <div>
                    <label for="password2">Επαναλάβετε το password</label>
                    <input type="password" id="password2" name="password2">
                </div>
                <div>
                    <button type="submit">Υποβολή</button>
                </div>



            </form>
        </div>
            <div class="error">
                <c:if test="${requestScope.isError}">
                    <p> ${requestScope.message}</p>
                </c:if>
            </div>

    </div>
</body>
</html>
