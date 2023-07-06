<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:if test="${isPasswordInvalid}">
        <p> ${"<script>alert('Passwords Do not match!')</script>"} </p>
    </c:if>
    <c:if test="${isEmailInvalid}">
        <p> ${"<script>alert('This Email address has already been registerd!')</script>"} </p>
    </c:if>

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
        <form method="post" action="/register">
            <div>
                <label>First Name</label>
                <input type="text" name="firstname">
            </div>
            <div>
                <label>Last Name</label>
                <input type="text" name="lastname">
            </div>
            <div>
                <label>Email</label>
                <input type="text" name="email">
            </div>
            <div>
                <label>Password</label>
                <input type="password" name="password">
            </div>
            <div>
                <label>Confirm Password</label>
                <input type="password" name="confirmPassword">
            </div>
            <div>
                <label>Phone</label>
                <input type="tel" name="phone" placeholder="123-123-1234"
                       pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" required>
            </div>
            <div>
                <label>Address</label>
                <input type="text" name="address">
            </div>
            <button type="submit">Submit</button>
            <input type="reset">
        </form>
    </div>

</body>
</html>
