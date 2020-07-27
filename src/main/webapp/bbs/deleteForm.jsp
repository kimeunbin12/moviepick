<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<!-- 본문시작 -->

 <div class="container">
 <br>
 <div class="nevi">
<h5><a href="../main/index.do">HOME</a>><a href="javascript:history.back()">USERPICK</a><a href="#">>삭제</a></h5>
</div>
<div class="bbs_del_form">
  <div class="bbs_del_form_jr">  
  	<form method="post" action="./delete.do">
	
	<input type="hidden" name="nno" value="${dto.nno}">
  <div>
    <p class="bbs_del_p">삭제하시겠습니까?</p>
  </div>

  <input class="bbs_del_back" id="subBtn" type="button" value="뒤로가기" onclick="location.href='javascript:history.back();'"/>
   <input type="submit" value="삭제진행">
	
  </form>
  </div>
</div>
</div>
  
 <!-- 본문끝 -->
<%@ include file="../footer.jsp"  %>