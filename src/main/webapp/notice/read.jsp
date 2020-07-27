<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"  %>
<%
pageContext.setAttribute("br", "<br/>");
pageContext.setAttribute("cn", "\n");
pageContext.setAttribute("<", "&lt");
pageContext.setAttribute(">", "&gt");
pageContext.setAttribute("\'", "&quot");
pageContext.setAttribute("'", "&apos");
%>
<section>
<div class="container">

<br>
<div class="nevi">
<h5><a href="../main/index.do">HOME</a>><a href="javascript:history.back()">NOTICE</a><a href="#">>상세보기</a></h5>
</div>
<br>
<br>
<br>
<h2 class="login_form_h2">NOTICE 상세</h2>
<br>
<br>
<div class="bbs_read">
<hr>

	<h2 class="bbs_read_h2">${dto.noticesub }</h2>
	<div class="bbs_read_cnt">
	 <c:set var="noticedate" value="${dto.noticedate }"></c:set>
	<c:set var="ndatedate" value="${fn:substring(dto.noticedate,0,16)}"></c:set>
	<h4>작성일</h4>
	</div>
	<div class="bbs_read_cnt_1">
	 <h4> | ${noticedate}</h4> 
	</div>
	<br>
	<div class="bbs_read_cnt">
	<h4>조회수</h4>
	</div>
	<div class="bbs_read_cnt_1">
	 <h4> | ${dto.noticecnt }</h4> 
	</div>
	<br>
	<br>
	<br>
	<div class="read_class_table">

		<div class="table_con">
		<br>
		<c:if test="${!empty dto.noticeimg }">
		<img src="../images/notice/${dto.noticeimg}">
		</c:if>
		<div class="table_cont">
		<br>
		${fn:replace(dto.noticecon, cn, br)}
		<br>
		</div>
		</div>
	</div>
	<br>
	<div class="bbs_read_btn_js">
	<div class="bbs_read_btn">
	<br><br>
	<c:if test="${empty col }">
	<input type="button" class="bbs_list_btn" value="글목록"  onclick="location.href='list.do?nowPage=${nowPage}&cntPerPage=${cntPerPage}';">
	</c:if>
	<c:if test="${!empty col }">
	<input type="button" class="bbs_list_btn" value="글목록"  onclick="location.href='listpage.do?nowPage=${nowPage}&cntPerPage=${cntPerPage}&col=${col }&word=${word }';">
	</c:if>
	<br><br>
	</div>
	</div> 
	</div>
</div>
</section>

<%@ include file="../footer.jsp"  %>


