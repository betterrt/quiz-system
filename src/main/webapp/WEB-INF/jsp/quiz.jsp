<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz</title>
</head>

<body>
                <%--Question description--%>
                <c:choose>
                    <c:when test="${answerList[curIndex] != -1}">
                        <p style="color:green"> Already answered </p>
                    </c:when>
                    <c:otherwise>
                        <p style="color:red"> Not answered yet </p>
                    </c:otherwise>
                </c:choose>

                <p> Question No. ${curIndex + 1} </p>
                <p>${question.description}</p>

                <form method="post" action="/quiz">
                    <%--4 choices--%>
                    <c:forEach items="${choices}" var="choice">
                        <div>
                            <c:choose>
                                <c:when test="${choice.choiceId == answerList[curIndex]}">
                                    <input type="radio"
                                           name="selectedChoiceId"
                                           value="${choice.choiceId}" checked/>
                                    ${choice.description}
                                </c:when>
                                <c:otherwise>
                                    <input type="radio"
                                           name="selectedChoiceId"
                                           value="${choice.choiceId}"/>
                                    ${choice.description}
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div>
                            <input type="hidden" name="nowIndex" value="${curIndex}">
                        </div>

                    </c:forEach>
                    <button type="submit"> save </button>

                </form>

                    <%--prev button--%>
                    <c:if test="${curIndex > 0}">
                        <form method="post" action="/quiz">
                            <input type="hidden" name="nextIndex" value="${curIndex - 1}">
                            <button type="submit"> prev </button>
                        </form>
                    </c:if>

                    <%--next button--%>
                    <c:if test="${curIndex < 4}">
                        <form method="post" action="/quiz">
                            <input type="hidden" name="nextIndex" value="${curIndex + 1}">
                            <button type="submit"> next </button>
                        </form>
                    </c:if>

                    <%--submit button, only shown on last page--%>
                     <c:if test="${curIndex == 4}">
                         <form method="post" action="/quiz">
                            <input type="hidden" name="nextIndex" value="-1">
                            <button type="submit"> submit </button>
                         </form>
                    </c:if>


</body>
</html>
