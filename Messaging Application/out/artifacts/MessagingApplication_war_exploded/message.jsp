<%--
  Created by IntelliJ IDEA.
  User: Ryan Falzon
  Date: 30-Dec-17
  Time: 5:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Messaging Page</title>
    <style type="text/css">
        .container {
            width: 500px;
            clear: both;
            text-align: center;
        }
        .container input {
            width: 75%;
            clear: both;
        }
    </style>
</head>

<body>
<div class="container">
    <h2>Messaging Page</h2>
    <h3>Enter a message below and press send.</h3>
    <form action="/message" method="post">
        <label>Target Agent ID</label>
        <input type="text" name="targetagent"><br /><br />
        <textarea rows="4" cols="50" name="message"></textarea><br /><br />
        <input type="submit" value="Submit Message">
    </form>
    <form action="/mailbox" method="post">
        <input type="submit" value="Check If You Have Messages" name="count">
        <input type="submit" value="Get Next Message" name="next">
    </form>
    <form action="/login" method="post">
        <input type="submit" value="Logout"><br /><br />
    </form>
</div>
</body>

</html>