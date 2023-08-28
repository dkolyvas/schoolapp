<%--
  Created by IntelliJ IDEA.
  User: kolyv
  Date: 12/8/2023
  Time: 2:09 μ.μ.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<! DOCTYPE html>
<html>
<head>
    <title>Admin Menu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templates/css/mystyle.css">
</head>
<body>
    <div class="container">
        <div class="nav">
            <div class = "header">
                <p> Μενού διαχειριστή </p>
            </div>
            <div class="menu">
                <p> admin <a href="${pageContext.request.contextPath}/admin/adminmenu"><i class="fa-solid fa-house"></i></a>
                    <a href="${pageContext.request.contextPath}/logout"> Logout</a></p>
            </div>
        </div>
        <div class="main">
            <div class="emphasis">
                <p> Καθηγητές</p>
            </div>
            <div class="form-division">
                <form method="post"  action="${pageContext.request.contextPath}/admin/teachersearch">
                    <label for="teachername"> Επώνυμο </label>
                    <input type="text" name="teachername" id="teachername">
                    <button type="submit"> <i class="fa-solid fa-magnifying-glass"></i>Αναζήτηση</button>
                </form>
                <a href="${pageContext.request.contextPath}/admin/teacherinsert"><i class="fa-sharp fa-solid fa-plus"></i> Προσθήκη Νέου</a>
            </div>
                <div class="emphasis">
                 <p> Μαθητές</p>
                </div>
            <div class="form-division">
                <form method="post"  action="${pageContext.request.contextPath}/admin/studentsearch">
                    <label for="studentname"> Επώνυμο </label>
                    <input type="text" name="studentname" id="studentname">
                    <button type="submit"><i class="fa-solid fa-magnifying-glass"></i>Αναζήτηση</button> 
                </form>
                <a href="${pageContext.request.contextPath}/admin/studentinsert"><i class="fa-sharp fa-solid fa-plus"></i> Προσθήκη Νέου</a>
            </div>
                <div class="emphasis">
                    <p>Συναντήσεις</p>
                </div>
            <div class="form-division">

                <div>
                    <a href="${pageContext.request.contextPath}/admin/meetinginsert"><i class="fa-sharp fa-solid fa-plus"></i>Ορισμός Νέας</a>
                </div>
            </div>
        </div>
        <div class="error">
            <p>"${requestScope.message}</p>
        </div>

    </div>
</body>
</html>
