<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!-- 본문시작 -->
<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="bbsList?nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
</script>
<section>
<div class="container">

<br>
<div class="nevi">
<h5><a href="../main/index.do">HOME</a>><a href=#>USERPICK</a></h5>
</div>
<br>
<br>
<br>
<h2 class="login_form_h2">USERPICK</h2>
<br>
<br>
	<div class="bbs_list">
	<c:if test="${paging.total == 0 }">
				<h2 class="login_form_h2">검색 결과가 없습니다.</h2>
				</c:if>	
	<c:if test="${!empty uid }">
	<input type="button" value="글쓰기" style="float: right;" onclick="location.href='./create.do'"><br>
	</c:if>
		<c:forEach var="dto" items="${list }">
		<div class="section_bbs">
			<div class="bbs_box_img">
			<c:if test="${empty dto.nimg}">
			<a href='read.do?nno=${dto.nno}&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}'><img src="../images/netizen/noimg.gif" style="width:200px; height: 270px;"></a>
			</c:if>
			<c:if test="${!empty dto.nimg }">
			<a href='read.do?nno=${dto.nno}&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}'><img src="../images/netizen/${dto.nimg}" onerror="this.src='/images/noimg.gif'" style="width:200px; height: 270px;"></a>
			</c:if>
			</div>	
			<div class="bbs_content">
			<div class="bbs_content_area">
				<dl>
					<dt class="dt_nsub"><a href='read.do?nno=${dto.nno}&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}'>${dto.nsub }</a></dt>
					<br>
					<c:set var="ndate" value="${dto.ndate }"></c:set>
					<c:set var="ndate" value="${fn:substring(dto.ndate,0,16)}"></c:set>
					<dt>조회수 | ${dto.ncnt}	　작성일 | ${ndate}</dt>
					<br>
					<dt>작성자 | ${dto.uid }</dt>
				</dl>
				
			</div>	
				
				</div>
				</div>
		</c:forEach>
		</div>

	
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
		<input type="radio" name="col" value="uid" checked style="">작성자
		<input type="radio" name="col" value="nsub" >제목
		<input type="radio" name="col" value="ncon">내용
		<input type="radio" name="col" value="nsub_ncon">제목+내용
		<input type="text" name="word">
		<input type="submit" value="검색">
		</form>	
		</td>
		</tr>
		</table>
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