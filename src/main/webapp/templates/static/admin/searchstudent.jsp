<%--
  Created by IntelliJ IDEA.
  User: kolyv
  Date: 18/8/2023
  Time: 6:56 μ.μ.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<! DOCTYPE html>
<html>
<head>
    <title>Student Search</title>
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
                    <th> Όνομα</th>
                    <th> Επώνυμο</th>
                    <th> Φύλο</th>
                    <th> Ημερομηνία Γέννησης</th>
                    <th> Πόλη</th>
                    <th> Username</th>
                    <th>Ενέργειες</th>
                    <th>Συναντήσεις</th>
                </tr>
                <c:forEach var="student" items="${requestScope.students}">
                    <tr>
                        <td> ${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td>${student.gender}</td>
                        <td>${student.birthDate}</td>
                        <td>${student.city}</td
                        <td>${student.username}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/studentupdate?id=${student.id}&criteria=${requestScope.criteria}"><i class="fa-solid fa-pen-to-square"></i></a>
                            <a href="${pageContext.request.contextPath}/admin/studentdelete?id=${student.id}&criteria=${requestScope.criteria}"><i class="fa-solid fa-trash"></i></a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/meetingsearch?studentid=${student.id}"><i class="fa-solid fa-calendar-check"></i></a>
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

