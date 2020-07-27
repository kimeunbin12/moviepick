<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<script type="text/javascript" src="..js/jquery.cookie.js"></script>


<div class="container">
	<div class="login_form_jr1">
		<h2 class="login_form_h2">로 그 인</h2>
		<div class="login_form_jr2">
		<div class="login_form">
			<form method="post" action="login.do">
				<input type="text" name="uid" value="" id="uid" placeholder="아이디"required> 
				<br> 
				<input type="password" name="ups" id="ups" placeholder="비밀번호" required> 
				<br> 
				<input type="submit" name="loginbtn" value="로그인">
				<br>
				<a href="agreement.do">회원가입</a> &nbsp;&nbsp;
				<a href="idsearch.do">아이디찾기</a>/
				<a href="pssearch.do">비밀번호찾기</a> <br>
			</form>
			</div>
		</div>
	</div>
</div>

<script>
	
</script>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>