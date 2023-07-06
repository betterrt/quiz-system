<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <h3> User Quiz Results </h3>

    <form method="post" action="/admin-quizes">
        <div>
            <label>Show only quizes of category:</label>
            <input type="text" name="categoryName">
        </div>
        <button type="submit">Filter1</button>
    </form>

    <form method="post" action="/admin-quizes">
        <div>
            <label>Show only quizes of user_id:</label>
            <input type="text" name="userId">
        </div>
        <button type="submit">Filter2</button>
    </form>

    <table>
        <thead>
        <tr>
            <th>Quiz Taken Time</th>
            <th>Category</th>
            <th>User Full Name</th>
            <th>No. of Questions</th>
            <th>Score</th>
            <th>Link to Quiz Result</th>
        </tr>
        </thead>
        <tbody>
            <c:if test="${showItems == true}">
                <c:forEach items="${quizes}" var="quiz">
                    <tr>
                        <td>${quiz.startTime} </td>
                        <td>${mapOfCategory.get(quiz.quizId)} </td>
                        <td>${mapOfFullName.get(quiz.quizId)} </td>
                        <td> 5 </td>
                        <td>${quiz.score} </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin-quizes-result?quizIdFromLink=${quiz.quizId}">
                                Get quiz result
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>

</body>
</html>
