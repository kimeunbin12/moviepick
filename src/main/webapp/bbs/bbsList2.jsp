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

<section  style="background-color:white;">
<h2>게시판</h2>
<div>
	
	<table border="1" width: 100%;>
		<tr>
			<th>번호</th>
			
			<th>영화제목</th>
			<th width="50%">제목</th>
			<th>작성자</th>
			<th>등록일</th>
			
			
			<th>조회수</th>		
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.nno }</td>
				
				<td>${dto.ntitle }</td>
				<td><a href='read.do?nno=${dto.nno}&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}'>${dto.nsub}</a></td>
				<td>${dto.uid}</td>
				<td>${dto.ndate}</td>
	
				<td>${dto.ncnt}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${!empty uid }">
	<input type="button" value="글쓰기" style="float: right;" onclick="location.href='./create.do'"><br>
	</c:if>
	<div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1 }">
			<a href="list.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<a href="list.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="list.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&gt;</a>
		</c:if>
	</div>
	</div>
<table>
		<tr>
		<td colspan="4"
			style="text-align: center; height: 50px">
		<form action="list.do" method="post"
			  onsubmit="return searchCheck(this)">
		<input type="radio" name="col" value="uid">작성자
		<input type="radio" name="col" value="nsub" checked>제목
		<input type="radio" name="col" value="ncon">내용
		<input type="radio" name="col" value="nsub_ncon">제목+내용
		<input type="text" name="word">
		
		<input type="submit" value="검색">
		</form>	
		</td>
		</tr>
</table>

</section>

 <!-- 본문끝 -->
<%@ include file="../footer.jsp"  %>