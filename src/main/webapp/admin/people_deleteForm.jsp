<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<!-- 본문시작 -->
  <div class="container">

<div class="bbs_del_form" style="min-height: 700px;">
  <div class="bbs_del_form_jr">  
<form method="post" action="people_delete.do">
	
	<input type="hidden" name="pno" value="${dto.pno}">
  <div>
    <p class="bbs_del_p">인물정보를 삭제하시겠습니까?</p>
    <br>
  </div>
  
  <input class="bbs_del_back" id="subBtn" type="button" value="뒤로가기" onclick="location.href='javascript:history.back();'"/>
   <input type="submit" value="삭제진행">
	
  </form>

  </div>
</div>
</div>
  
 <!-- 본문끝 -->
<%@ include file="../footer.jsp"  %>