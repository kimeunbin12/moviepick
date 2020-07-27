<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<!-- 본문시작 -->

<section>
<div class="container">

<br>
<div class="nevi">
<h5><a href="../main/index.do">HOME</a>><a href="javascript:history.back()">USERPICK</a><a href="#">>수정</a></h5>
</div>
<br>
<br>
<br>
<h2 class="login_form_h2">USERPICK 수정</h2>
<br>
<br>
<div class="bbs_update_form_jr">
<div class="bbs_update_form">
	<form method="post" action="update.do"  enctype="multipart/form-data" >	
		<input type='hidden' name='nno'      value='${dto.nno }'>
		<input type="hidden" value=${col }>
		<input type="hidden" value=${word }>
		<p class="bbs_sub_up_p">글제목</p><input type="text" name="nsub"  value="${dto.nsub }"/><br>
		<p class="bbs_sub_up_p">영화제목</p><input type="text" name="ntitle" value='${dto.ntitle }'/><br>
		<p class="bbs_sub_up_p">내용</p>
		<textarea class="bbs_update_textarea" name="ncon" id="ncon" rows="10" cols="100">${dto.ncon }</textarea>
		<br>
		<div class="bbs_file_div">
						<input class="upload-name" value="${dto.nimg}" disabled="disabled">
						<label for="file_img" class="file_img">이미지첨부</label> 
						<input type="file" name='posterMF' size='50' id="file_img" class="upload-hidden">
					</div>
					<br>
					<br>
		<p>
		<input id="subBtn" type="submit" value="글 수정"  onclick="goWrite(this.form)"/>
		<input id="subBtn" type="button" value="뒤로가기" onclick="location.href='javascript:history.back();'"/>			
		</p>
	</form>
	</div>
	<br>
	<br>
	<br>
	</div>

	</section>
<script>	
function goWrite(frm){ 
	var nsub = frm.nsub.value;
	var ntitle = frm.ntitle.value;
	var ncon = frm.ncon.value;
	
	if (ntitle.trim() == ''){
		alert(" 영화제목을 입력해주세요");
		return false;
		}
	if (nsub.trim() == ''){
		alert("글제목을 입력해주세요");
		return false;
	}
	if (ncon.trim() == ''){
		alert("내용을 입력해주세요");
		return false;
	} 
	frm.submit();
}
$(document).ready(function(){ 
	var fileTarget = $('.bbs_update_form .upload-hidden'); 
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
 <!-- 본문끝 -->
<%@ include file="../footer.jsp"  %>