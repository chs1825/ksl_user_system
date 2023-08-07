<%--
  Created by IntelliJ IDEA.
  User: chs
  Date: 2023/08/02
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring Security Custom Page </title>
</head>
<body>

<h2>Spring Security Custom Page</h2>


<form action="/loginPage.do" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
</form>
</body>
</html>
