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

    <div>
        <form method="post" action="/feedback">
            <div>
                <label>Your Star Rating: </label>
                    <c:forTokens items="1,2,3,4,5" delims="," var="number">
                        <div>
                            <input type="radio"
                                name="starRating"
                                value="${number}"/>
                                ${number} star
                        </div>
                    </c:forTokens>
            </div>

            <div>
                <label>Your Feedback: </label>
                <textarea name="feedbackText"> </textarea>
            </div>
            <button type="submit">Submit</button>
        </form>
    </div>

</body>
</html>
