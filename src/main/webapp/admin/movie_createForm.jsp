<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<!-- 본문시작 -->
<section>
<div class="container">
 <br>
 <br>
  <br>
 <br>
 <h2 class="login_form_h2">무비픽 등록</h2>
 <br>
 <br>
  <br>
 <br>
<div class="bbs_update_form_jr">
<div class="bbs_create_form">
<form method="post" action="movie_create.do"  enctype="multipart/form-data" > 
      <p class="bbs_sub_up_p">영화제목</p>
       <input type="text" name="msub"> <br>
       <p class="bbs_sub_up_p">포스터</p>
       <div class="bbs_file_div">
			<input class="upload-name" value="파일선택" disabled="disabled">
			<label for="file_img" class="file_img">이미지첨부</label> 
			<input type="file" name='posterMF' size='50' id="file_img" class="upload-hidden">
					</div>
       			<br>
       	<p class="bbs_sub_up_p">감독</p>
        <input type="text" name="mdir"><br>
        <p class="bbs_sub_up_p">배우</p>
        <input type="text" name="mact" ><br>
    <input type="button" name="movie-people" value="인물목록보기" onclick="plist()"/><br><br>
    <br>
        <p class="bbs_sub_up_p">장르</p>
          판타지, 로맨스, 범죄, 액션, 다큐, 코미디, 애니, 뮤지컬, 공포  <br>
    (fan, rom, cri, act, doc, com, ani, mus, thr) <br>
        <input type="text" name="mgenre"><br>
        <p class="bbs_sub_up_p">러닝타임</p>
        <input type="text" name="mrun"><br>
        <p class="bbs_sub_up_p">연령제한</p>
        <select name="mgrade" style="width: 70%;">
            <option value="">선택</option>
            <option value="0">전체</option>
            <option value="12">12세</option>
            <option value="15">15세</option>
            <option value="19">청불</option>
        </select>
        <br><br>
        <p class="bbs_sub_up_p">개봉일</p>
        <input type="date" name="mopen" style="width: 70%;"><br><br>
       <p class="bbs_sub_up_p">마감일</p>
        <input type="date" name="mend" style="width: 70%;"><br><br>
        <p class="bbs_sub_up_p">상영여부</p>
        <input type="radio" id="상영" name="msta" value="1"><label for="상영">Y</label>
       <input type="radio" id="종료" name="msta" value="0"><label for="종료">N</label><br><br>
        <p class="bbs_sub_up_p">영화파일</p>
        <div class="movie_file_div">
			<input class="upload-name-movie" value="파일선택" disabled="disabled">
			<label for="movie_file" class="movie_file">파일첨부</label> 
			<input type="file" name='filenameMF' size='50' id="movie_file" class="upload-hidden-2">
			</div>
			<br><br>
			<p class="bbs_sub_up_p">줄거리</p><br><br>
       <textarea class="bbs_update_textarea"  name="msum" id="msum" rows="10" cols="80"></textarea><br><br>
        <p class="bbs_sub_up_p">예고편</p>     
         <div class="movietrailer_file_div">
			<input class="upload-name-movietrailer" value="파일선택" disabled="disabled">
			<label for="movietrailer_file" class="movietrailer_file">파일첨부</label> 
			<input type="file" name='trailMF' size='50' id="movietrailer_file" class="upload-hidden-3">
			</div> <br>
        <p class="bbs_sub_up_p">추천이유</p>     
        <br><textarea  class="bbs_update_textarea" name="mwhy" id="mwhy" rows="10" cols="50"></textarea><br>
        
        <br><br>
        <p>
        <input id="back" type="button" value="뒤로가기" style="float: left;" onclick="location.href='javascript:history.back();'"/>         
        <input id="subBtn" type="submit" value="등록하기" style="float: right;" />
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
    var msub = frm.msub.value;
    var mposter = frm.mposter.value;
    var mgenre = frm.mgenre.value;
    var mgrade = frm.mgrade.value;
    var mrun = frm.mrun.value;
    var mopen = frm.mopen.value;
    var mend = frm.mend.value;
    var mdir = frm.mdir.value;
    var mact = frm.mact.value;
    var msum = frm.msum.value;
    var mtrail = frm.mtrail.value;
    var mwhy = frm.mwhy.value;
    
    if (msub.trim() == ''){
        alert("영화제목을 입력해주세요");
        return false;
    }
    if (mposter.trim() == ''){
        alert("포스터를 등록해주세요");
        return false;
    }
    if (mgenre.trim() == ''){
        alert("장르를 입력해주세요");
        return false;
    } 
    if (mgrade.trim() == ''){
        alert("연령제한을 입력해주세요");
        return false;
    }
    if (mrun.trim() == ''){
        alert("러닝시간을 입력해주세요");
        return false;
    }
    if(mopen.trim() == ''){
        alert("개봉일을 입력해주세요");
        return false;
    }
    if(mend.trim() == ''){
        alert("마감(예정)일을 입력해주세요");
        return false;
    }
    if(mdir.trim() == ''){
        alert("감독정보를 등록해주세요");
        return false;
    }
    if(mact.trim() == ''){
        alert("배우정보를 등록해주세요");
        return false;
    }
    if(msum.trim() == ''){
        alert("줄거리를 입력해주세요");
        return false;
    }
    if(mtrail.trim() == ''){
        alert("예고편을 등록해주세요");
        return false;
    }
    if(mwhy.trim() == ''){
        alert("추천이유를 등록해주세요");
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
