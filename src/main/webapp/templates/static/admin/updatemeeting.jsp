<%--
  Created by IntelliJ IDEA.
  User: kolyv
  Date: 21/8/2023
  Time: 7:43 μ.μ.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<! DOCTYPE html>
<html>
<head>
    <title>Update Meeting</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templates/css/mystyle.css">
</head>
<body>
    <div class="container">
        <div class="nav">
            <div class = "header">
                <p> Τροποποίση συνάντησης</p>
            </div>
            <div class="menu">
                <p> admin <a href="${pageContext.request.contextPath}/admin/adminmenu"><i class="fa-solid fa-house"></i></a>
                    <a href="${pageContext.request.contextPath}/logout"> Logout</a></p>
            </div>
        </div>
        <div class="main">

            <div class="emphasis">
                <p>Καθηγητής: ${sessionScope.meeting.teacherName}  </p>
                <p>Εκπαιδευόμενος: ${sessionScope.meeting.studentName} </p>
            </div>
            <div class="form-division">
                <form action="${pageContext.request.contextPath}/admin/meetingupdate" method="post">
                    <label for="room">Αίθουσα συνάντησης</label>
                    <input type="text" name="room" id="room" value="${sessionScope.meeting.classroom}">
                    <label for="date">Ημερομηνία συνάντησης</label>
                    <input type="text" name="date" id="date" value="${sessionScope.meeting.date}">
                    <button type="submit"><i class="fa-solid fa-floppy-disk"></i></i> Τροποποίηση συνάντησης</button>
                </form>

            </div>

        </div>
        <div class="error">
            <p>${requestScope.message}</p>
        </div>
    </div>
</body>
</html>
