<%--
  Created by IntelliJ IDEA.
  User: kolyv
  Date: 12/8/2023
  Time: 2:10 μ.μ.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<! DOCTYPE html>
<html>
<head>
    <title>User Menu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templates/css/mystyle.css">
</head>
<body>
    <div class="container">
        <div class="nav">
            <div class = "header">
                <p> Καλωσήρθατε, ${requestScope.name}</p>
            </div>
            <div class="menu">
                <p> ${sessionScope.username} <a href="${pageContext.request.contextPath}/logout"> Logout</a></p>
            </div>
        </div>
        <div class="main">
            <div class ="emphasis">
                <p>Οι συναντήσεις μου</p>
            </div>
            <div>
                <table>
                    <tr>
                        <th> Καθηγητής</th>
                        <th> Μαθητής</th>
                        <th> Αίθουσα</th>
                        <th> Ημερομηνία</th>
                    </tr>
                    <c:forEach var="meeting" items="${requestScope.meetings}">
                        <tr>
                            <td>${meeting.teacherName}</td>
                            <td>${meeting.studentName}</td>
                            <td>${meeting.classroom}</td>
                            <td>${meeting.date}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="error">
            <p>${requestScope.message}</p>
        </div>
    </div>
</body>
</html>
