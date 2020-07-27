<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<!-- 본문시작 -->
<div class="container">
	<br> <br> <br>
 <h2 class="login_form_h2">영화상영 등록</h2><br><br>

<div class="bbs_update_form_jr">
<div class="bbs_create_form">
<form method="post" action="screen_create.do"  enctype="multipart/form-data" > 
      
        <p class="bbs_sub_up_p">상영일</p>  
        <input type="date" name="scrdate" style="width: 50%;"><br><br>
       <p class="bbs_sub_up_p">상영시간</p>
        <input type="time" name="scrstart" style="width: 50%;"><br> <br> 
       <p class="bbs_sub_up_p">상영관</p>       <!-- <input type="text" name="hno" style="width: 40%;" />-->
        <select name="hno" style="width: 50%;">
            <option value="">선택</option>
            <option value="1">1관</option>
            <option value="2">2관</option>
            <option value="3">3관</option>
        </select><br><br> 
         <p class="bbs_sub_up_p">영화</p>  
          <input type="text" name="mno" id="sm" style="width: 40%;">
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
}//plist() end

function goWrite(frm){ 
    var scrdate = frm.scrdate.value;
    var scrstart = frm.scrstart.value;
    var hno = frm.hno.value;
    var mno = frm.mno.value;
    
    if (scrdate.trim() == ''){
        alert("상영날짜를 입력해주세요");
        return false;
    }
    if (scrstart.trim() == ''){
        alert("상영시간을 등록해주세요");
        return false;
    }
    if (hno.trim() == ''){
        alert("상영관를 입력해주세요");
        return false;
    } 
    if (mno.trim() == ''){
        alert("상영영화를 입력해주세요");
        return false;
    }
    
    frm.submit();
}
</script>
 <!-- 본문끝 -->
