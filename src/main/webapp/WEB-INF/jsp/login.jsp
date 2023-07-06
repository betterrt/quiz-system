<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>


<body>

    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/login">Log in</a></li>
            <li><a href="${pageContext.request.contextPath}/register">Register</a></li>
            <li><a href="${pageContext.request.contextPath}/feedback">Feedback</a></li>
            <li><a href="${pageContext.request.contextPath}/contact">Contact Us</a></li>
        </ul>
    </nav>

    <div>
        <form method="post" action="/login">
            <div>
                <label>email</label>
                <input type="text" name="email">
            </div>
            <div>
                <label>Password</label>
                <input type="password" name="password">
            </div>
            <button type="submit">Submit</button>
        </form>
    </div>

</body>

</html>
