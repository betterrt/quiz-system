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

    <h3> Enable/Disable Questions </h3>

  <table>
    <thead>
    <tr>
      <th>Question ID</th>
      <th>Description</th>
      <th>Status </th>
      <th> </th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${questions}" var="question">
      <tr>
        <td>${question.questionId}</td>
        <td>${question.description}</td>
        <td>
          <c:if test="${question.isActive == true}">
            <p> Active </p>
          </c:if>

          <c:if test="${question.isActive != true}">
            <p> Not Active </p>
          </c:if>
        </td>

        <td>
          <c:if test="${question.isActive == true}">
            <form method="post" action="/admin-enable-question">
              <input type="hidden" name="questionStatus" value="0">
              <input type="hidden" name="questionId" value="${question.questionId}">
              <button type="submit"> Disable </button>
            </form>
          </c:if>
          <c:if test="${question.isActive != true}">
            <form method="post" action="/admin-enable-question">
              <input type="hidden" name="questionStatus" value="1">
              <input type="hidden" name="questionId" value="${question.questionId}">
              <button type="submit"> Enable </button>
            </form>
          </c:if>
        </td>
      </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
