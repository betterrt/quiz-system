<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Title</title>
</head>
<body>

    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/quizes">User Quiz Result</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/profile">User Profile</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/edit">Edit Questions</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/enable-question">
                Enable/Disable Questions</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/feedback">Admin Feedback</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/contact">Admin Contact</a></li>
        </ul>
    </nav>

    <h3> User Profile </h3>

    <table>
        <thead>
        <tr>
            <th>FullName</th>
            <th>Primary Address</th>
            <th>Primary Email</th>
            <th>Primary Phone number</th>
            <th>Status </th>
            <th> </th>
        </tr>
        </thead>

        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${mapOfFullName.get(user.id)}</td>
                    <td>${user.address} </td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>
                        <c:if test="${user.isActive == true}">
                            <p> Active </p>
                        </c:if>

                        <c:if test="${user.isActive != true}">
                            <p> Suspended </p>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${user.isActive == true}">
                            <form method="post" action="/admin-profile">
                                <input type="hidden" name="userStatus" value="0">
                                <input type="hidden" name="userId" value="${user.id}">
                                <button type="submit"> Suspend </button>
                            </form>
                        </c:if>
                        <c:if test="${user.isActive != true}">
                            <form method="post" action="/admin-profile">
                                <input type="hidden" name="userStatus" value="1">
                                <input type="hidden" name="userId" value="${user.id}">
                                <button type="submit"> Activate </button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
