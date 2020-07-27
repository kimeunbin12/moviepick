<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<!-- 본문시작 -->
<section  style="background-color:white;">
  
  <div>  
  	<dl>
		<dd>${msg1 }</dd>
		</dl>
  </div>
</section>
<script type="text/javascript">
<!--
if (msg==0){
alert("실패");
location href='histroy.back()';
} else{
	alert("등록되었습니다.");
	location href='/read.do?mno=${mno}'
}
-->
</script>
 <!-- 본문끝 -->
<%@ include file="../footer.jsp"  %>