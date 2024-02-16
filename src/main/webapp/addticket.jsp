<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Ticket</title>
    <link rel="stylesheet" href="global.css">
</head>
<body>
<h1>Add Ticket</h1>
<form action="CreateTicket" method="post">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required><br>
    <label for="description">Description:</label>
    <textarea id="description" name="description" rows="4" cols="50" style="padding: 5px;" required></textarea><br>
    <label for="priority">Priority</label>
    <select id="priority" name="priority">
        <option value="low">Low</option>
        <option value="medium">Medium</option>
        <option value="high">High</option>
    </select><br>
    <label for="status">Status:</label>
    <select id="status" name="status">
        <option value="open">Open</option>
        <option value="in progress">In Progress</option>
        <option value="resolved">Resolved</option>
    </select><br>
    <input type="submit" value="Submit">
</form>
<br>
<%
    String message = (String) request.getAttribute("message");
    if (message != null && !message.isEmpty()) {
%>
<div class="alert" style="width:fit-content;padding: 5px;border:1px solid grey;">
    Alert: <%= message %> !
</div>
<%
    }
%>
<br>
<a href="displaytickets.jsp">
    <button>View Tickets</button>
</a>
<a href="index.jsp">
    <button>Back to Home</button>
</a>
</body>
</html>
