<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="../header.jsp" %>

<div class="container">
<div class="login_form_jr1">
  <div class="content">  
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

<%@ include file="../footer.jsp"%>
