<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<!-- 본문시작 -->
<div class="container">
	<br> <br> <br>
 <h2 class="login_form_h2">상영 정보 수정 </h2><br><br>

<div class="bbs_update_form_jr">
<div class="bbs_create_form">
<form method="post" action="screen_update.do"  enctype="multipart/form-data" > 
      <input type='hidden' name='scrno' value='${dto.scrno }'>
        <p class="bbs_sub_up_p">상영일</p>  
        <input type="date" name="scrdate" value="${dto.scrdate }" style="width: 50%;"><br><br>
       <p class="bbs_sub_up_p">상영시간</p>
        <input type="time" name="scrstart" value="${dto.scrstart }" style="width: 50%;"><br> <br> 
       <p class="bbs_sub_up_p">상영관</p>       <!-- <input type="text" name="hno" style="width: 40%;" />-->
        <select name="hno" id="sel" style="width: 50%;">
            <option value="" <c:if test="${dto.hno == null}">selected</c:if>>선택</option>
            <option value="1" <c:if test="${dto.hno == 1}">selected</c:if>>1관</option>
            <option value="2" <c:if test="${dto.hno == 2}">selected</c:if>>2관</option>
            <option value="3" <c:if test="${dto.hno == 3}">selected</c:if>>3관</option>
        </select><br><br> 
         <p class="bbs_sub_up_p">영화</p>  
          <input type="text" name="mno" id="sm" value="${dto.mno }" style=" width: 40%;" >
    <input type="button" name="screen-movie" value="영화목록보기" onclick="mlist()" ><br><br>
        
        <br><br>
        <p>
        <input id="back" type="button" value="뒤로가기" style="float: left;" onclick="location.href='javascript:history.back();'"/>         
        <input id="subBtn" type="submit" value="저장" style="float: right;" />
        </p>
        <br><br><br>
        
    </form>
		</div>
		</div>
</div>


<script>

function mlist() {
    window.open("screen-movie.do","mlist","width=600, height=525");
}

function goWrite(frm){ 
	
	frm.submit();
}
</script>
 <!-- 본문끝 -->
