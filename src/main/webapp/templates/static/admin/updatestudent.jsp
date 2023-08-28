<%--
  Created by IntelliJ IDEA.
  User: kolyv
  Date: 19/8/2023
  Time: 2:45 μ.μ.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<! DOCTYPE html>
<html>
<head>
    <title>Update Student</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templates/css/mystyle.css">
</head>
<body>
    <div class="container">
        <div class="nav">
            <div class = "header">
                <p> Τροποίηση στοιχείων μαθητή</p>
            </div>
            <div class="menu">
                <p> admin <a href="${pageContext.request.contextPath}/admin/adminmenu"><i class="fa-solid fa-house"></i></a>
                    <a href="${pageContext.request.contextPath}/logout"> Logout</a></p>
            </div>
        </div>
        <div class="main">
            <form method="post" action="${pageContext.request.contextPath}/admin/studentupdate?id=${requestScope.student.id}&criteria=${requestScope.criteria}">
                <div class="form-division">
                    <label for="firstname"> Όνομα</label>
                    <input type="text" name="firstname" id="firstname" value=${requestScope.student.firstName}>
                    <label for="lastname"> Επώνυμο</label>
                    <input type="text" name="lastname" id="lastname" value="${requestScope.student.lastName}">
                </div>
                <div class="form-division">
                    <span>Φύλο: </span>
                    <label for="male">Άνδρας</label>
                    <<input type="radio" id="male" value="M" name="gender">
                    <label for="female">Γυναίκα</label>
                    <<input type="radio" id="female" value="F" name="gender">
                </div>
                <div class="form-division">
                    <label for="birthdate"> Ημερομηνία Γέννησης</label>
                    <input type="text" id="birthdate" name="birthdate"  value="${requestScope.student.birthDate}">
                    <label for="username"> Όνομα χρήστη</label>
                    <select name="username" id="username">
                        <c:forEach var= "user" items="${requestScope.users}">
                            <c:choose>
                                <c:when test="${requestScope.student.username} == ${user}">
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
                    <label for="city">Πόλη</label>
                    <select name="city" id="city">}
                        <c:forEach var="city" items="${requestScope.cities}">
                            <c:choose>
                                <c:when test="${requestScope.teacher.city} == ${city}">
                                    <option value="${city}" selected>${city}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${city}">${city}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-division">
                    <button type="submit"><i class="fa-solid fa-floppy-disk"></i> Ενημέρωση></button>
                </div>
            </form>
        </div>
        <div class="error">
            <p>${requestScope.message}</p>
        </div>
    </div>
</body>
</html>