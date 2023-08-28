<%--
  Created by IntelliJ IDEA.
  User: kolyv
  Date: 11/8/2023
  Time: 1:10 μ.μ.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<! DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templates/css/mystyle.css">
</head>
<body>
    <div class="container">
        <div class="nav">
            <div class ="header">
                <h1> Καλωσήρθατε</h1>
                <p> Παρακαλούμε εισάγετε τα στοιχεία σας για να συνδεθείτε</p>
            </div>
        </div>
        <div class="main">
            <div class="form">
                <form method="post" action="${pageContext.request.contextPath}/login">
                    <div>
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username">
                    </div>
                    <div>
                        <label for="password"> Password</label>
                        <input type="password" id="password" name="password">
                    </div>
                    <button type="submit" >Login</button>
                </form>
            </div>
            <div class="form">
                    <p> Αν δεν είστε χρήστης της υπηρεσίας πατήστε <a href="${pageContext.request.contextPath}/register">εδώ</a>
                    για να εγγραφείτε</p>
            </div>

            <div class="error">
                <c:if test="${requestScope.isError eq 'true'}">
                    <p> ${requestScope.errors}</p>
                </c:if>
                <c:if test="${requestScope.newUser eq 'true'}">
                    <p>Επιτυχής εισαγωγή χρήστη. Παρακαλώ συνδεθείτε</p>
                </c:if>

            </div>
        </div>
    </div>
</body>
</html>
