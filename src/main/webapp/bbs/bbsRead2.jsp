<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<%
pageContext.setAttribute("br", "<br/>");
pageContext.setAttribute("cn", "\n");
pageContext.setAttribute("<", "&lt");
pageContext.setAttribute(">", "&gt");
pageContext.setAttribute("\'", "&quot");
pageContext.setAttribute("'", "&apos");
%>
<!-- 본문시작 -->

		
<section>
<div class="container">

<br>
<div class="nevi">
<h5><a href="../main/index.do">HOME</a>><a href="javascript:history.back()">USERPICK</a><a href="#">>상세보기</a></h5>
</div>
<br>
<br>
<br>
<h2 class="login_form_h2">USERPICK 상세</h2>
<br>
<br>
<div class="bbs_read">
<hr>

	<h2 class="bbs_read_h2">${dto.nsub }</h2>
	<div class="bbs_read_sub">
	<h4>영화제목</h4>
	</div>
	<div class="bbs_read_sub_1">
	 <h4> | ${dto.ntitle }</h4> 
	</div>
	<div class="bbs_read_cnt">
	<c:set var="ndate" value="${dto.ndate }"></c:set>
	<c:set var="ndate" value="${fn:substring(dto.ndate,0,16)}"></c:set>
	<h4>작성일</h4>
	</div>
	<div class="bbs_read_cnt_1">
	 <h4> | ${ndate }</h4> 
	</div>
	<br>

	<div class="bbs_read_sub">
	<h4>작성자</h4>
	</div>
	<div class="bbs_read_sub_1">
	 <h4> | ${dto.uid }</h4> 
	</div>
	<div class="bbs_read_cnt">
	<h4>조회수</h4>
	</div>
	<div class="bbs_read_cnt_1">
	 <h4> | ${dto.ncnt }</h4> 
	</div>
	<br>
	<br>
	<br>
	<div class="read_class_table">

		<div class="table_con">
		<c:if test="${!empty dto.nimg }">
		
		<img src="../images/netizen/${dto.nimg}">
		<br>
		</c:if>
		<div class="table_cont">
		${fn:replace(dto.ncon, cn, br)}
		</div>
		</div>
	</div>
	<br>
	<div class="bbs_read_btn_js">
	<div class="bbs_read_btn">
	<c:if test="${dto.uid == uid }">
		<input type="button" class="bbs_up_btn" value="수정" onclick="location.href='update.do?nno=${dto.nno}';">
		<input type="button" class="bbs_del_btn" value="삭제" onclick="location.href='delete.do?nno=${dto.nno}';">
	</c:if>
	<br><br>
	
	<input type="button" class="bbs_list_btn" value="글목록"  onclick="location.href='list.do?';">
	
	<br><br>
	</div>
	</div> 
	</div>
</div>
</section>
 <!-- 본문끝 -->
<%@ include file="../footer.jsp"  %>