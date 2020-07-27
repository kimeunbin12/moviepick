<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!-- 본문시작 -->

<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="list?nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
</script>

<section>
<div class="container">

<br>
<div class="nevi">
<h5><a href="../main/index.do">HOME</a>><a href=#>NOTICE</a></h5>
</div>
<br>
<br>
<br>
<br>
<br>
<h2 class="login_form_h2">공지사항</h2>
<br>
<br>
	<div class="notice_list">
		
	<table class="type04" >
		<tr>
	<th>번호</th>
	<th>제목</th>
	<th>등록일</th>
  	</tr>
	 <c:forEach var="dto" items="${list}">
    <tr>
     <td>${dto.noticeno}</td>
    <td style="text-align: left;"><a href='../notice/read.do?noticeno=${dto.noticeno}&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}'>${dto.noticesub }</a></td>
    <c:set var="noticedate" value="${dto.noticedate }"></c:set>
	<c:set var="ndatedate" value="${fn:substring(dto.noticedate,0,16)}"></c:set>
	<td>${noticedate}</td>
    </tr>
		</c:forEach>
		</table>
		

	<div>
	</div>
	<br>
	
<div class="paging_jr">
	<div class="paging">

		<c:if test="${empty col }">		
		<c:if test="${paging.startPage != 1 }">
			<ul class=""> 
			<li><a href="list.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a></li>
			</ul>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
				<ul>
				<li><a>${p }</a></li>
				</ul>
				</c:when>
				<c:when test="${p != paging.nowPage }">
			 	<ul>
   				<li><a href="list.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a></li>  
   				</ul>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
			<li><a href="list.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a></li>
		</ul>	 
		</c:if>
		</c:if>
			
		<c:if test="${!empty col}">
		<c:if test="${paging.startPage != 1 }">
		<ul>
		<li><a href="listpage.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&lt;</a></li>
			
			</ul>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
				<ul>
				<li><a>${p }</a></li>
				</ul>
				</c:when>
				<c:when test="${p != paging.nowPage }">
				<ul>
				<li><a href="listpage.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">${p }</a><li>
				</ul>	
					
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
		<li><a href="listpage.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&gt;</a></li>
			</ul>
		</c:if>
		</c:if>
	</div>
			</div>
			
<div class="search_form">		
<table style="width: 100%">
		<tr>
		<td colspan="4"
			style="text-align: center; height: 50px">
		<form action="listpage.do?&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}" method="get"
			  onsubmit="return searchCheck(this)">
		<input type="radio" name="col" value="noticesub" checked >제목
		<input type="radio" name="col" value="ncoticeon">내용
		<input type="radio" name="col" value="noticesub_noticecon">제목+내용
		<input type="text" name="word">
		<input type="submit" value="검색">
		</form>	
		</td>
		</tr>
</table>
</div>
</div>
</div>
</section>
<script>
function searchCheck(f) {
	
	var word=f.word.value;
	word=word.trim();
	if(word.length==0){
		alert("검색어를 입력하세요");
		return false;//서버전송불가
	}//if end
	
	return true; // 서버로 전송
	
}//searchCheck() end
</script>
 <!-- 본문끝 -->
<%@ include file="../footer.jsp"  %>