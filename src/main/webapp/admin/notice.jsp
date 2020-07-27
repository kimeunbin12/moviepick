<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../header.jsp"%>


  <div class="container">
   <h2 style="text-align: center; margin-top: 5%; margin-bottom: 5%;">공지사항관리</h2>
   <br>
   <div style="text-align: center;">
       <input type='button' value='공지사항등록' class="notice_admin_create_btn"   onclick="location.href='../admin/notice_create.do'">
    </div> 
     <br><br>      
  <table class="type06">
  <tr>
	<th>게시글번호</th>
	<th>제목</th>
	<th>내용</th>
	<th>조회수</th>
	<th>등록일</th>
	<th>이미지</th>
	<th>수정</th>
	<th>삭제</th>
  </tr>
  <c:forEach var="dto" items="${list}">
    <tr>
      <td>${dto.noticeno}</td>
      <td>${dto.noticesub}</td>
      <td>${dto.noticecon }</td>
      <td>${dto.noticecnt }</td>
      <td>${dto.noticedate}</td>
      <td>${dto.noticeimg}</td>
      
      	<td><input class="notice_admin_upde" type="button" value="수정" onclick="location.href='../admin/notice_update.do?noticeno=${dto.noticeno}'"></td>
		<td><input class="notice_admin_upde" type="button" value="삭제" onclick="location.href='../admin/notice_delete.do?noticeno=${dto.noticeno}'"></td>
	
    </tr>
  </c:forEach>
  </table>
  <br>
  <br>
  
 
 <br>

  <div class="paging_jr">
	<div class="paging">

		<c:if test="${empty col }">		
		<c:if test="${paging.startPage != 1 }">
			<ul class=""> 
			<li><a href="notice.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a></li>
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
   				<li><a href="notice.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a></li>  
   				</ul>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
			<li><a href="notice.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a></li>
		</ul>	 
		</c:if>
		</c:if>
			
		<c:if test="${!empty col}">
		<c:if test="${paging.startPage != 1 }">
		<ul>
		<li><a href="noticepage.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&lt;</a></li>
			
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
				<li><a href="noticepage.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">${p }</a><li>
				</ul>	
					
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
		<li><a href="noticepage.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&gt;</a></li>
			</ul>
		</c:if>
		</c:if>
	</div>
			</div>

            <br><br><br>
            <div style="text-align: center;">
           
            </div>
            <br>

<div class="search_form">
		<table style="width: 100%">
		<tr>
		<td colspan="4"
			style="text-align: center; height: 50px">
		<form action="noticepage.do?&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}" method="get"
			  onsubmit="return searchCheck(this)">
		<input type="radio" name="col" value="noticesub" checked>제목
		<input type="radio" name="col" value="noticecon">내용
				<input type="radio" name="col" value="noticesub_noticecon">제목+줄거리
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

  

