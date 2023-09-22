<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>XSS Form</title>
</head>
<body>
<form action="/submit.do" method="post">
    User Input: <input type="text" name="userInput">
    <input type="submit" value="Submit">
</form>
</body>
</html>
