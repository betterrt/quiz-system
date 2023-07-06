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
        <form method="post" action="/contact">
            <div>
                <label> Subject: </label>
                <textarea name="subject"> </textarea>
            </div>
            <div>
                <label> Your message: </label>
                <textarea name="message"> </textarea>
            </div>
            <button type="submit">Submit</button>
        </form>
    </div>

</body>
</html>
