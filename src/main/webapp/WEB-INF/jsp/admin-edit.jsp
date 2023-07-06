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

    <h3> Edit Quiz Questions </h3>
    <form method="post" action="/admin-edit">
        <div>
            <label>Choose Category:</label>
            <input type="text" name="category">
        </div>
        <div>
            <label>Your New Question Description:</label>
            <input type="text" name="description">
        </div>
        <div>
            <label>Choice 1:</label>
            <input type="text" name="choice1">
        </div>
        <div>
            <label>Choice 2:</label>
            <input type="text" name="choice2">
        </div>
        <div>
            <label>Choice 3:</label>
            <input type="text" name="choice3">
        </div>
        <div>
            <label>Choice 4:</label>
            <input type="text" name="choice4">
        </div>
        <div>
            <label> Right Answer's #:</label>
            <input type="text" name="rightAnswer">
        </div>
        <button type="submit">Submit</button>
    </form>


</body>
</html>
