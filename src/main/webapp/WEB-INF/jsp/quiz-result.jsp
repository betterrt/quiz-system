<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz Result</title>
</head>

<body>
<div>
    <h3> Quiz Result </h3>
    <c:if test="${quiz.score > 3}">
        <p style="color: green">Congratulations! You have passed the quiz!</p>
    </c:if>
    <c:if test="${quiz.score <= 3}">
        <p style="color: red">You failed the quiz</p>
    </c:if>
    <p>Your Score: ${quiz.score}</p>
        <table>
            <thead>
            <tr>
                <th>Quiz Name</th>
                <th>User First Name</th>
                <th>User Last Name</th>
                <th>Start Time</th>
                <th>End Time</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${quiz.name} </td>
                    <td>${user.firstname} </td>
                    <td>${user.lastname} </td>
                    <td>${quiz.startTime}</td>
                    <td>${quiz.endTime}</td>
                </tr>
            </tbody>
        </table>

    <c:forEach items="${questions}" var="question" varStatus="loop">
        <p> -----Question No.${loop.index + 1} -----</p>
        <p> Description: ${question.description} </p>

        <table>
            <thead>
            <tr>
                <th>Correct Answer</th>
                <th>User selected Answer</th>
                <th>Choice Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${mapOfAllChoices.get(loop.index)}" var="choice">
                <tr>
                    <td>
                        <c:if test="${choice.isCorrect}">
                            <p style="color: green"> √ </p>
                        </c:if>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${mapOfUserChoice.get(loop.index) == null}">
                                <p></p>
                            </c:when>
                            <c:when test="${choice.isCorrect &&
                                choice.choiceId == mapOfUserChoice.get(loop.index).choiceId}">
                                <p style="color:green"> correct </p>
                            </c:when>
                            <c:when test="${!choice.isCorrect &&
                                choice.choiceId == mapOfUserChoice.get(loop.index).choiceId}">
                                <p style="color:red"> wrong </p>
                            </c:when>
                        </c:choose>
                    </td>
                    <td>${choice.description} </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </c:forEach>

    <li><a href="${pageContext.request.contextPath}/home">Take another quiz</a></li>

</div>
</body>

</html>