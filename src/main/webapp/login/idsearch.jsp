<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>



<div class="container">
	<div class="login_form_jr1">
		<h2 class="login_form_h2">아이디 찾기</h2>
		<div class="login_form_jr2">
		<div class="login_form">
			<form method="post" action="idsearch.do">
				<input type="text" name="uname" value="" id="uname" placeholder="이름"required> 
				<br> 
				<input type="email" name="uemail" id="uemail" placeholder="이메일" required>
				<br> 
				<input type="submit" name="searchbtn" value="찾기">
				<br>
				<input type="button" value="뒤로가기"  onclick="javascript:history.back(); return false;">
			</form>
			</div>
		</div>
	</div>
</div>

<%@ include file="../footer.jsp"%>