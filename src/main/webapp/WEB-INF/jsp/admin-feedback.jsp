<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
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

    <h3> User Feedback </h3>

    <thead>
    <tr>
        <th>Date</th>
        <th>Rating</th>
        <th>Message</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${feedbacks}" var="feedback">
        <tr>
            <td>${feedback.date} </td>
            <td>${feedback.rating} </td>
            <td>${feedback.message} </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
