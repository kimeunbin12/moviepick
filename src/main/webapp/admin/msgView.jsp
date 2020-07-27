<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<!-- 본문시작 -->

<div class="container">
<div class="login_form_jr1" style="min-height: 700px;">
  <div class="content2">  
   <input type="hidden" value=${col }>
  <input type="hidden" value=${word }>
  	<dl>
		<dd>${msg1 != null ? img : "" } ${msg1 }</dd>
		<dd>${msg2 != null ? img : "" } ${msg2 }</dd>
		<dd>${msg3 != null ? img : "" } ${msg3 }</dd>
	</dl>
	<p>
		${link1 } ${link2 } ${link3 }
	</p>
  </div>
</div>
</div>

 <!-- 본문끝 -->
<%@ include file="../footer.jsp"  %>