<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<!-- 본문시작 -->
<section>
<div class="container">
 <br>
 <br>
  <br>
 <br>
 <h2 class="login_form_h2">무비픽 수정</h2>
 <br>
 <br>
  <br>
 <br>
<div class="bbs_update_form_jr">
<div class="bbs_create_form">
<form method="post" action="movie_update.do"  enctype="multipart/form-data" >
<input type='hidden' name='mno'      value='${dto.mno }'>	
      <p class="bbs_sub_up_p">영화제목</p>
       <input type="text" name="msub" value="${dto.msub }"> <br>
       <p class="bbs_sub_up_p">포스터</p>
       <div class="bbs_file_div">
			<input class="upload-name" value="${dto.mposter }" disabled="disabled">
			<label for="file_img" class="file_img">이미지첨부</label> 
			<input type="file" name='posterMF' size='50' id="file_img" class="upload-hidden">
					</div>
       			<br>
       	<p class="bbs_sub_up_p">감독</p>
        <input type="text" name="mdir" value="${dto.mdir }"><br>
        <p class="bbs_sub_up_p">배우</p>
        <input type="text" name="mact" value="${dto.mact }" ><br>
    <input type="button" name="movie-people" value="인물목록보기" onclick="plist()"/><br><br>
        <p class="bbs_sub_up_p">장르</p>
        <br>
         판타지, 로맨스, 범죄, 액션, 다큐, 코미디, 애니, 뮤지컬, 공포  <br>
    (fan, rom, cri, act, doc, com, ani, mus, thr) <br>
        <input type="text" name="mgenre" value="${dto.mgenre }"><br>
        <p class="bbs_sub_up_p">러닝타임</p>
        <input type="text" name="mrun" value="${dto.mrun }"><br>
        <p class="bbs_sub_up_p">연령제한</p>
        <select name="mgrade">
            <option value="">선택</option>
            <option value="0" <c:if test="${dto.mgrade eq '0'}">selected="selected"</c:if>>전체</option>
            <option value="12" <c:if test="${dto.mgrade eq '12'}">selected="selected"</c:if>>12세</option>
            <option value="15" <c:if test="${dto.mgrade eq '15'}">selected="selected"</c:if>>15세</option>
            <option value="19" <c:if test="${dto.mgrade eq '19'}">selected="selected"</c:if>>청불</option>
        </select>
        <br><br>
        <p class="bbs_sub_up_p">개봉일</p>
        <input type="date" name="mopen" value="${dto.mopen }"><br><br>
       <p class="bbs_sub_up_p">마감일</p>
        <input type="date" name="mend" value="${dto.mend }"><br><br>
        <p class="bbs_sub_up_p">상영여부</p>
        <input type="radio" id="상영" name="msta" value="1" <c:if test="${dto.msta eq '1'}">checked="checked"</c:if>>
        <label for="상영">Y</label>
       <input type="radio" id="종료" name="msta" value="0" <c:if test="${dto.msta eq '0'}">checked="checked"</c:if>>
       <label for="종료">N</label><br><br>
        <p class="bbs_sub_up_p">영화파일</p>
        <div class="movie_file_div">
			<input class="upload-name-movie" value="${dto.mfile }" disabled="disabled">
			<label for="movie_file" class="movie_file">파일첨부</label> 
			<input type="file" name='filenameMF' size='50' id="movie_file" class="upload-hidden-2">
			<c:set var="filesize" value="${fn:substringBefore(msize,'.') }"></c:set>(${dto.msize } MB)<br><br>
			</div>
			<br><br>
			<p class="bbs_sub_up_p">줄거리</p><br><br>
       <textarea class="bbs_update_textarea"  name="msum" id="msum" rows="10" cols="80">${dto.msum }</textarea><br><br>
        <p class="bbs_sub_up_p">예고편</p>     
         <div class="movietrailer_file_div">
			<input class="upload-name-movietrailer" value="${dto.mtrail }" disabled="disabled">
			<label for="movietrailer_file" class="movietrailer_file">파일첨부</label> 
			<input type="file" name='trailMF' size='50' id="movietrailer_file" class="upload-hidden-3">
			</div> <br>
        <p class="bbs_sub_up_p">추천이유</p>     
        <br><textarea  class="bbs_update_textarea" name="mwhy" id="mwhy" rows="10" cols="50">${dto.mwhy }</textarea><br>
        
        <br><br>
        <p>
        <input id="back" type="button" value="뒤로가기" style="float: left;" onclick="location.href='javascript:history.back();'"/>         
        <input id="subBtn" type="submit" value="수정하기" style="float: right;" />
        </p>
        <br><br><br>
        
    </form>

</div>
</div>
</div>
</section>


<script>

function plist() {
    //부트스트랩 모달창
    //->부모창과 자식창이 한몸으로 구성되어 있음
    
    //새창만들기
    //->부모창과 자식창이 별개로 구성되어 있음
    //->모바일에 기반을 둔 frontend단에서는 사용하지 말것!!
    //window.open("파일명","새창이름","다양한옵션들")
    
    window.open("movie-people.do","plist","width=600, height=525");
}//plist() end
function goWrite(frm){ 
	
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
	
$(document).ready(function(){ 
	var fileTarget = $('.movie_file_div .upload-hidden-2'); 
	fileTarget.on('change', function(){ // 값이 변경되면 
		if(window.FileReader){ // modern browser 
			var filename = $(this)[0].files[0].name; 
		} else { // old IE 
			var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출
			} // 추출한 파일명 삽입 
			$(this).siblings('.upload-name-movie').val(filename);
			}); 
	});
	
$(document).ready(function(){ 
	var fileTarget = $('.movietrailer_file_div .upload-hidden-3'); 
	fileTarget.on('change', function(){ // 값이 변경되면 
		if(window.FileReader){ // modern browser 
			var filename = $(this)[0].files[0].name; 
		} else { // old IE 
			var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출
			} // 추출한 파일명 삽입 
			$(this).siblings('.upload-name-movietrailer').val(filename);
			}); 
	});
	
</script>
 <!-- 본문끝 -->
