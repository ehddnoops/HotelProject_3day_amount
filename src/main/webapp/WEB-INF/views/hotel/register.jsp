<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<title>회원가입 페이지</title>
</head>
<body>
  <form action="register" method="post">
  
  <input type="text" name="id" placeholder="아이디" required="required" autofocus="autofocus">
  <br>
  <input type="password" name="pw" placeholder="비밀번호" required="required">
  <br>
  <input type="text" name="name" placeholder="이름" required="required">
  <br>
  <input type="text" name="phone" placeholder="핸드폰번호" required="required">
  <br>
  <input type="email" name="email" placeholder="email" required="required">
  <br>
  <input type="number" name="birth" placeholder="birth" required="required">
  <br>
  <input type="submit" value="로그인">
  </form>

<input id="insertAlert" type="hidden" value="${insert_result }">
	
	<script type="text/javascript">
		$(document).ready(function(){
			confirmInsertResult();
			function confirmInsertResult(){
				var result = $('#insertAlert').val();
				if(result == 'success'){
					alert('새 글 작성 성공');
				} else if(result == 'fail'){
					alert('새 글 작성 실패');
				}
			}
		});
	</script>


</body>
</html>