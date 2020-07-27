<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<link href="../css/style.css" rel="stylesheet">

<div class="container">
		<div class="id_check_jr2">
			<div class="id_check_img">
				<br> <img src="../img/logo2.png" alt="logo"> <br>
			</div>
			<div style="text-align: center">

				<h2 class="login_form_h2">중복확인 결과</h2>
				<div class="content1">
					<dl>
						<dd>${msg1 != null ? img : "" }${msg1 }</dd>
						<dd>${msg2 != null ? img : "" }${msg2 }</dd>
						<dd>${msg3 != null ? img : "" }${msg3 }</dd>
					</dl>
					<p>${link1 } ${link2 } ${link3 }</p>
					<div class="btn">
			<input type="button"  class="idcheck_re" value="다시조회" onclick="javascript:history.back()">
			<input type="button"  class="idcheck_close" value="창닫기" onclick="javascript:window.close()">
				</div>
				</div>
			</div>
			
	</div>
	</div>



<script>
	function apply(uid) {
		//alert(id);
		//중복이 확인된 uid를 부모창(opener)에 적용
		opener.document.regForm.uid.value = uid;
		window.close();
	}//apply() end
</script>





