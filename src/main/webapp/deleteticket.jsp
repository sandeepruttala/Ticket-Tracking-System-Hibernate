<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Ticket" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="helper.FactoryProvider" %>
<html>
<head>
    <title>Delete Ticket</title>
    <link rel="stylesheet" href="global.css">
</head>
<body>
<h1>Tickets</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Description</th>
        <th>Priority</th>
        <th>Status</th>
    </tr>
    <%
        Session session_display_tickets = FactoryProvider.getFactory().openSession();

        try {
            session_display_tickets.beginTransaction();
            List<Ticket> tickets = session_display_tickets.createQuery("from Ticket").getResultList();

            if (tickets.isEmpty()) {
    %>
    <tr>
        <td colspan="5">No tickets found</td>
    </tr>
    <%
    } else {
        for (Ticket ticket : tickets) {
    %>
    <tr>
        <td><%= ticket.getId() %>
        </td>
        <td><%= ticket.getTitle() %>
        </td>
        <td><%= ticket.getDescription() %>
        </td>
        <td><%= ticket.getPriority() %>
        </td>
        <td><%= ticket.getStatus() %>
        </td>
    </tr>
    <%
                }
            }
        } finally {
            session_display_tickets.close();
        }
    %>

</table>
<br>
<h1>Delete Ticket</h1>
<form action="DeleteTicket" method="post">
    <label for="id">Ticket ID:</label>
    <input type="number" id="id" name="id" required/>
    <input type="submit" value="Delete"/>
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
<a href="index.jsp">
    <button>Back to Home</button>
</a>
</body>
</html>
