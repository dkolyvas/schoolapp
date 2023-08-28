<%--
  Created by IntelliJ IDEA.
  User: kolyv
  Date: 21/8/2023
  Time: 5:44 μ.μ.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<! DOCTYPE html>
<html>
<head>
    <title>Αναζήτηση Συναντήσεων</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templates/css/mystyle.css">
</head>
<body>
    <div class="container">
        <div class="nav">
            <div class = "header">
                <p> Αποτελέσματα αναζήτησης</p>
            </div>
            <div class="menu">
                <p> admin <a href="${pageContext.request.contextPath}/admin/adminmenu"><i class="fa-solid fa-house"></i></a>
                    <a href="${pageContext.request.contextPath}/logout"> Logout</a></p>
            </div>
        </div>
        <div class="main">
            <table>
                <tr>
                    <th>Καθηγητής</th>
                    <th>Μαθητής</th>
                    <th>Αίθουσα</th>
                    <th>Ημερομηνία</th>
                    <th>Ενέργειες</th>
                </tr>
                <c:forEach var="meeting" items="${requestScope.meetings}">
                   <tr>
                    <td>${meeting.teacherName}</td>
                    <td>${meeting.studentName}</td>
                    <td>${meeting.classroom}</td>
                    <td>${meeting.date}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/meetingdelete?student=${meeting.studentId}&teacher=${meeting.teacherId}&searchtype=${requestScope.searchtype}&searchvalue=${requestScope.searchvalue}"><i class="fa-solid fa-trash"></i></a>
                        <a href="${pageContext.request.contextPath}/admin/meetingupdate?teacherid=${meeting.teacherId}&teachername=${meeting.teacherName}&studentid=${meeting.studentId}&studentname=${meeting.studentName}&room=${meeting.classroom}&date=${meeting.date}&searchtype=${requestScope.searchtype}"><i class="fa-solid fa-pen-to-square"></i></a>
                    </td>
                   </tr>
                </c:forEach>
            </table>
        </div>
        <div class="error">
            <p>${requestScope.message}</p>
        </div>
    </div>

</body>
</html>
