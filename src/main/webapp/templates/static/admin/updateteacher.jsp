<%--
  Created by IntelliJ IDEA.
  User: kolyv
  Date: 17/8/2023
  Time: 6:49 μ.μ.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<! DOCTYPE html>
<html>
<head>
    <title>Update Teacher</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templates/css/mystyle.css">
</head>
<body>
    <div class="container">
        <div class="nav">
            <div class = "header">
                <p> Τροποποίηση στοιχείων καθηγητή</p>
            </div>
            <div class="menu">
                <p> admin <a href="${pageContext.request.contextPath}/admin/adminmenu"><i class="fa-solid fa-house"></i></a>
                    <a href="${pageContext.request.contextPath}/logout"> Logout</a></p>
            </div>
        </div>
        <div class="main">
            <form method="post" action="${pageContext.request.contextPath}/admin/teacherupdate?criteria=${requestScope.criteria}&id=${requestScope.teacher.id}">
                <div class="form-division">
                    <label for="firstname"> Όνομα</label>
                    <input type="text" name="firstname" id="firstname" value=${requestScope.teacher.firstName}>
                    <label for="lastname"> Επώνυμο</label>
                    <input type="text" name="lastname" id="lastname" value="${requestScope.teacher.lastName}">
                </div>
                <div class="form-division">
                    <label for="ssn"> ΑΦΜ</label>
                    <input type="text" id="ssn" name="ssn"  value="${requestScope.teacher.ssn}">
                    <label for="username"> Όνομα χρήστη</label>
                    <select name="username" id="username">
                        <c:forEach var= "user" items="${requestScope.users}">
                            <c:choose>
                                <c:when test="${requestScope.teacher.userName} eq ${user}">
                                    <option value="${user}" selected> ${user}   </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${user}"> ${user}   </option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-division">
                    <label for="speciality">Ειδικότητα</label>
                    <select name="speciality" id="speciality">}
                        <c:forEach var="speciality" items="${requestScope.specialities}">
                            <c:choose>
                                <c:when test="${requestScope.teacher.speciality} eq ${speciality}">
                                    <option value="${speciality}" selected>${speciality}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${speciality}">${speciality}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-division">
                    <button type="submit"><i class="fa-solid fa-floppy-disk"></i>  Ενημέρωση</button>>
                </div>
            </form>
        </div>
        <div>
            <p>${requestScope.message}</p>
        </div>
</body>
</html>
