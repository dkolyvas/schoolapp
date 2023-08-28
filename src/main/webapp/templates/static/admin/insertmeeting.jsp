<%--
  Created by IntelliJ IDEA.
  User: kolyv
  Date: 20/8/2023
  Time: 1:06 μ.μ.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<! DOCTYPE html>
<html>
<head>
    <title>Insert Meeting</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templates/css/mystyle.css">
</head>
<body>
    <div class="container">
        <div class="nav">
            <div class = "header">
                <p> Ορισμός Συνάντησης </p>
            </div>
            <div class="menu">
                <p> admin <a href="${pageContext.request.contextPath}/admin/adminmenu"><i class="fa-solid fa-house"></i></a>
                    <a href="${pageContext.request.contextPath}/logout"> Logout</a></p>
            </div>
        </div>
        <div class="main">
            <div class="form-division">
                <form method="post" action="${pageContext.request.contextPath}/admin/meetinginsert?action=searchteacher">
                    <label for="teachersearch">Αναζήτηση Καθηγητή</label>
                    <input type="text" id="teachersearch" name="strteachersearch">
                    <button type="submit"><i class="fa-solid fa-magnifying-glass"> </i> Αναζήτηση</button>
                </form>
                <form method="post" action="${pageContext.request.contextPath}/admin/meetinginsert?action=setteacher">
                    <select name="teacherid" id="teacherid">
                        <c:forEach var="teacher" items="${sessionScope.teacherlist}">
                            <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
                        </c:forEach>
                    </select>
                    <button type="submit"><i class="fa-solid fa-check"></i> Ορισμός</button>
                </form>
            </div>
            <div class="emphasis">
                <p>Ορίστηκε: ${sessionScope.meetingteachername}  </p>
            </div>
            <div class="form-division">
                <form action="${pageContext.request.contextPath}/admin/meetinginsert?action=searchstudent" method="post">
                    <label for="studentsearch">Αναζήτηση Μαθητή</label>
                    <input type="text" id="studentsearch" name="strstudentsearch">
                    <button type="submit"><i class="fa-solid fa-magnifying-glass"> </i> Αναζήτηση</button>
                </form>
                <form action="${pageContext.request.contextPath}/admin/meetinginsert?action=setstudent" method="post">
                    <select name="studentid" id="studentid">
                        <c:forEach var="student" items="${sessionScope.studentlist}">
                            <option value="${student.id}">${student.firstName} ${student.lastName}</option>
                        </c:forEach>
                    </select>
                    <button type="submit"><i class="fa-solid fa-check"></i> Ορισμός</button>
                </form>
            </div>
            <div class="emphasis">
                <p>Ορίστηκε: ${sessionScope.meetingstudentname} </p>
            </div>
            <div class="form-division">
                <form action="${pageContext.request.contextPath}/admin/meetinginsert?action=insert" method="post">
                    <label for="room">Αίθουσα συνάντησης</label>
                    <input type="text" name="room" id="room" value="${requestScope.room}">
                    <label for="date">Ημερομηνία συνάντησης</label>
                    <input type="text" name="date" id="date" value="${requestScope.date}">
                    <button type="submit"><i class="fa-sharp fa-solid fa-plus"></i> Ορισμός συνάντησης</button>
                </form>

            </div>

        </div>
        <div class="error">
            <p>${requestScope.message}</p>
        </div>
    </div>
</body>
</html>
