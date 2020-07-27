<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>

    <div class="container" style="width: 90%;">
    <div class="movie_admin">
    <h2 style="text-align: center; margin-top: 5%; margin-bottom: 5%;">무비픽관리</h2>
    <br><a href="../admin/movie_create.do"><input type="button" value="등록"></a> <br> <br> <br>
    
    <table class="type06">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>포스터</th>
            <th>장르</th>
            <th>연령제한</th>
            <th>상영시간</th>
            <th>개봉일</th>
            <th>마감일</th>
            <th>감독</th>
            <th>배우</th>
            <th>줄거리</th>
            <th>예고편</th>
            <th>추천이유</th>
            <th>영화파일</th>
            <th>파일크기</th>
            <th>현재상영여부</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
        <c:forEach var="dto" items="${list}">
	        <tr>
	            <td>${dto.mno }</td>
	            <td>${dto.msub}</td>
	            <td>${dto.mposter }</td>
	            <td>${dto.mgenre }</td>
	            <td>${dto.mgrade }</td>
	            <td>${dto.mrun }</td>
	            <td>${dto.mopen }</td>
	            <td>${dto.mend }</td>
	            <td>${dto.mdir }</td>
	            <td>${dto.mact }</td>
	            <td>${dto.msum }</td>
	            <td>${dto.mtrail }</td>
	            <td>${dto.mwhy }</td>
	            <td>${dto.mfile }</td>
	            <td>${dto.msize }G</td>
	            <td>${dto.msta }</td>
	            <td><a href="../admin/movie_update.do?mno=${dto.mno}"><input type="button" class="updehtn" value="수정"></a></td>
	            <td><a href="../admin/movie_delete.do?mno=${dto.mno}"><input type="button" class="updehtn" value="삭제"></a></td>
	        </tr>
        </c:forEach>
    </table>
    <br>
    <br>
    
      <br>
        <br>
    </div>
<div class="paging_jr">
	<div class="paging">

		<c:if test="${empty col }">		
		<c:if test="${paging.startPage != 1 }">
			<ul class=""> 
			<li><a href="movie.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a></li>
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
   				<li><a href="movie.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a></li>  
   				</ul>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
			<li><a href="movie.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a></li>
		</ul>	 
		</c:if>
		</c:if>
			
		<c:if test="${!empty col}">
		<c:if test="${paging.startPage != 1 }">
		<ul>
		<li><a href="moviepage.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&lt;</a></li>
			
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
				<li><a href="moviepage.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">${p }</a><li>
				</ul>	
					
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
		<li><a href="moviepage.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&gt;</a></li>
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
		<form action="moviepage.do?&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}" method="get"
			  onsubmit="return searchCheck(this)">
		<input type="radio" name="col" value="msub" checked>제목
		<input type="radio" name="col" value="msum">줄거리
		<input type="radio" name="col" value="msub_msum">제목+줄거리
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