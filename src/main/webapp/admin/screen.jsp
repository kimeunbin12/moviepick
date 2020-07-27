<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    <div class="container">
     <div class="movie_admin">
     
        <h2  style=" text-align:center; margin-top:5%; margin-bottom: 5%;">상영 관리</h2>
    <br><a href="../admin/screen_create.do"><input type="button" value="등록"></a> <br>
    <br>
    <table class="type06">
        <tr>
            <th>상영번호</th>
            <th>상영일</th>
            <th>상영시간</th>
            <th>상영관</th>
            <th>영화번호</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
        <c:forEach var="dto" items="${list}">
	        <tr>
	            <td>${dto.scrno }</td>
	            <td>${dto.scrdate }</td>
	            <td>${dto.scrstart }</td>
	            <td>${dto.hno }</td>
	            <td><a href="../movie/read.do?mno=${dto.mno}">${dto.mno}</a></td>
	            <td><a href="../admin/screen_update.do?scrno=${dto.scrno}"><input type="button" value="수정"></a></td>
	            <td><a href="../admin/screen_delete.do?scrno=${dto.scrno}"><input type="button" value="삭제"></a></td>
	        </tr>
        </c:forEach>
    </table>
     <br> <br>
        </div>
    <div class="paging_jr">
	<div class="paging">

		<c:if test="${empty col }">		
		<c:if test="${paging.startPage != 1 }">
			<ul class=""> 
			<li><a href="screen.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a></li>
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
   				<li><a href="screen.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a></li>  
   				</ul>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
			<li><a href="screen.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a></li>
		</ul>	 
		</c:if>
		</c:if>
			
		<c:if test="${!empty col}">
		<c:if test="${paging.startPage != 1 }">
		<ul>
		<li><a href="screenpage.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&lt;</a></li>
			
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
				<li><a href="screenpage.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">${p }</a><li>
				</ul>	
					
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
		<li><a href="screenpage.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&gt;</a></li>
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
		<form action="screenpage.do?&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}"
		 method="get" onsubmit="return searchCheck(this)">
		<input type="radio" name="col" value="scrdate" checked>상영일
		<input type="radio" name="col" value="hno" >상영관
		<input type="radio" name="col" value="mno" >영화
		<input type="text" name="word">
		<input type="submit" value="검색">
		</form>	
		</td>
		</tr>
		</table>
		</div>
		 <br>
		   <br><a href="../admin/index.do"><input type="button"  class="notice_admin_upde" value="관리자 메인페이지로"></a> <br> <br>
    
</div>

</body>
</html>