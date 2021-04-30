<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>아이디별 정보 가져오기 및 update</title>
</head>
<body>
	<h3>아이디 : ${vo.id }</h3>
	<br>
	<h3>비밀번호 : ${vo.pw }</h3>
	<br>
	<h3>이름 : ${vo.name }</h3>
	<br>
	<h3>핸드폰번호 : ${vo.phone }</h3>
	<br>
	<h3>이메일 : ${vo.email }</h3>
	<br>
	<h3>생년월일 : ${vo.birth }</h3>
	<br>
	<h3>리뷰평점 : ${vo.star }</h3>

	<hr>



	<a href="mypageupdate?id=${userid }"><input type="button" value="개인정보 수정"></a>
	<a href="mypagedelete?id=${vo.id }"><input type="button" value="회원정보삭제"></a>

	<input id="updateAlert" type="hidden" value="${update_result }">

	<script type="text/javascript">
		$(document).ready(function() {
			confirmInsertResult();
			function confirmInsertResult() {
				var result = $('#updateAlert').val();
				if (result == 'success') {
					alert('업데이트성공');
				} else if (result == 'fail') {
					alert('업데이트실패');
				}
			}
		});
	</script>

</body>
</html>