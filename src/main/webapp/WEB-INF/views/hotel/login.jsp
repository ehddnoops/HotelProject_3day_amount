<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
  <form action="login" method="post">
  <input type="text" name="id" placeholder="아이디" required="required" autofocus="autofocus">
  <br>
  <input type="password" name="pw" placeholder="비밀번호" required="required">
  <br>
  <input type="submit" value="로그인">
  </form>

	<a href="pwfind"><input type="button" value="비밀번호찾기 페이지"></a>

</body>
</html>
