<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>메인페이지 입니다.</title>
</head>
<body>

	<c:if test="${empty sessionScope.userid }">
		<!-- sessionScopre.id가 없으면 -->
		<a href="login"><input type="button" value="로그인"></a>
		<br />
		<a href="register"><input type="button" value="회원가입"></a>
	</c:if>

	<c:if test="${not empty sessionScope.userid }">
		<!-- sessionScopre.id가 있으면 -->

	${userid} 님 어서오세요<br />

		<a href="mypage?id=${userid }"><input type="button" value="마이페이지"></a>
		<a href="logout"><input type="button" value="로그아웃"></a>
		<br />

	</c:if>


	<input id="deleteAlert" type="hidden" value="${delete_result }">
	<script type="text/javascript">
		$(document).ready(function() {
			confirmInsertResult();
			function confirmInsertResult() {
				var result = $('#deleteAlert').val();
				if (result == 'success') {
					alert('정보삭제성공');
				} else if (result == 'fail') {
					alert('정보삭제실패');
				}
			}
		});
	</script>

</body>
</html>