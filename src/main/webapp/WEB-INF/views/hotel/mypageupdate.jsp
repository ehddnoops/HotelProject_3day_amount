<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<title>마이페이지 개인정보 수정</title>
</head>
<body>
		  <form action="mypageupdate" method="post">
  
  <input type="text" name="id" placeholder="아이디" required="required" autofocus="autofocus" value="${vo.id }">
  <br>
  <input type="password" name="pw" placeholder="비밀번호" required="required" value="${vo.pw }">
  <br>
  <input type="text" name="name" placeholder="이름" required="required" value="${vo.name }">
  <br>
  <input type="text" name="phone" placeholder="핸드폰번호" required="required" value="${vo.phone }">
  <br>
  <input type="email" name="email" placeholder="email" required="required" value="${vo.email }">
  <br>
  <input type="number" name="birth" placeholder="birth" required="required" value="${vo.birth }">
  <br>
  <input type="submit" value="개인정보수정확인">
  </form>
	
</body>
</html>