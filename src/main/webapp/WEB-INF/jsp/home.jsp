<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
            <li><a href="${pageContext.request.contextPath}/register">Register</a></li>
            <li><a href="${pageContext.request.contextPath}/feedback">Feedback</a></li>
            <li><a href="${pageContext.request.contextPath}/contact">Contact Us</a></li>
        </ul>
    </nav>

    <h3> Categories </h3>
    <p> choose one to start the quiz</p>

    <c:forEach items="${categories}" var="category">
        <div>
<%--            pass the selected categoryId to controller--%>
            <a href="${pageContext.request.contextPath}/quiz?categoryId=${category.id}">
                    ${category.name}
            </a>
            <img src="${category.imageUrl}" width="120" height="100">
        </div>
    </c:forEach>

    <h3> Quizes you have taken </h3>
    <table>
        <thead>
        <tr>
            <th>Quiz Name</th>
            <th>Date taken</th>
            <th>Link to the quiz result</th>
        </tr>

        </thead>
        <tbody>
        <c:forEach items="${takenQuizes}" var="takenQuiz">
            <tr>
                <td>${takenQuiz.name} </td>
                <td>${takenQuiz.startTime} </td>
                <td>
                    <a href="${pageContext.request.contextPath}/quiz-result?quizIdFromLink=${takenQuiz.quizId}">
                           Link to quiz result
                    </a>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>


</body>
</html>
