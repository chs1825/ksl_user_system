<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>XSS Multipart</title>
</head>
<body>
<form action="/submit-multipart" method="post" enctype="multipart/form-data">
<%--    <label for="name">이름:</label>--%>
<%--    <input type="text" id="name" name="name" required><br>--%>
    File: <input type="file" name="file">
    <input type="submit" value="Upload">
</form>
</body>
</html>
