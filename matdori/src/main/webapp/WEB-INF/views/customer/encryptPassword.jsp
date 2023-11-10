<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 암호화</title>
</head>
<body>
    <h1>비밀번호 암호화</h1>
    
    <form action="/customer/encrypt" method="post">
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="customerPw" required>
        <br><br>
        <input type="submit" value="암호화">
    </form>
</body>
</html>
