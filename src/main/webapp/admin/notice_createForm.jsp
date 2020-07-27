<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ include file="../header.jsp"  %>
<!-- 본문시작 -->
 
<section>
	<div class="container">

<br>
		<br> <br> <br>
		<h2 class="login_form_h2">공지사항 작성</h2>
		<br> <br><br>
		<div class="bbs_update_form_jr">
			<div class="bbs_create_form">
				<form method="post" action="notice_create.do"  enctype="multipart/form-data" >	
					<p class="bbs_sub_up_p">공지제목</p>
					<input type="text" name="noticesub" id="noticesub" placeholder="글제목" /><br>
					<p class="bbs_sub_up_p">내용</p>
					<textarea class="bbs_update_textarea"name="noticecon" id="noticecon" 
						rows="10" cols="100" placeholder="내용입력" onkeyup="characterCheck()"
						onkeydown="characterCheck()"></textarea>
					<br>
					<div class="bbs_file_div">
						<input class="upload-name" value="파일선택" disabled="disabled">
						<label for="file_img" class="file_img">이미지첨부</label> 
						<input type="file" name='posterMF' size='50' id="file_img" class="upload-hidden">
					</div>
					<br>
					<br>
					<p>
						<input id="subBtn" type="submit" value="글작성"
							onclick="goWrite(this.form)" />
							 <input id="subBtn" type="button"value="뒤로가기" onclick="location.href='javascript:history.back();'" />
					</p>
				</form>
				<br> <br> <br>
			</div>
		</div>
	</div>
</section>


 <!-- 본문끝 -->
 

<script>	
function characterCheck() {
    var RegExp = /[ \{\}\[\]\/|\)<>\'\"\\\(\=]/gi;//정규식 구문
    var obj = document.getElementsByName("ncon")[0]
    if (RegExp.test(obj.value)) {
        alert("특수문자는 입력하실 수 없습니다.");
        obj.value = obj.value.substring(0, obj.value.length - 1);//특수문자를 지우는 구문
    }
}



function goWrite(frm){ 
	var noticesub = frm.noticesub.value;
	var noticecon = frm.noticecon.value;
	

	if (noticesub.trim() == ''){
		alert("글제목을 입력해주세요");
		return false;
	}
	if (noticecon.trim() == ''){
		alert("내용을 입력해주세요");
		return false;
	} 
	frm.submit();
}

$(document).ready(function(){ 
	var fileTarget = $('.bbs_create_form .upload-hidden'); 
	fileTarget.on('change', function(){ // 값이 변경되면 
		if(window.FileReader){ // modern browser 
			var filename = $(this)[0].files[0].name; 
		} else { // old IE 
			var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출
			} // 추출한 파일명 삽입 
			$(this).siblings('.upload-name').val(filename);
			}); 
	});
</script>

<br>
<br>
<br>

<%@ include file="../footer.jsp"  %>