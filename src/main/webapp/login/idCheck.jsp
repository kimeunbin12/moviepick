<%@ page contentType="text/html; charset=UTF-8"%>
<link href="../css/style.css" rel="stylesheet">

<div class="container">

		<div class="id_check_jr2">
			<div class="id_check_img">
			<br> 
			<img src="../img/logo2.png" alt="logo">
			 <br>
			 </div>
			<div style="text-align: center">

				<h2 class="login_form_h2">아이디 중복확인</h2>
				<form method="post" action="idCheck.do"
					onsubmit="return blankCheck(this)">
					<p>아이디 입력</p>
					<br>
					<input type="text" name="uid" maxlength="15" autofocus>
					<input type="submit" value="중복확인">
				</form>
			</div>
		</div>
	</div>
<script>
	function blankCheck(f) {
		var uid = f.uid.value;
		uid = uid.trim();
		if (uid.length<5 || uid.length>15) {
			alert("아이디는 5~15글자 이내로 입력해 주세요.");
			return false;
		}//if end
		return true;
	}//end
</script>

<!-- 본문 끝 -->
