<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>

<div class=container>

 <h2 style="text-align: center; margin-top: 5%; margin-bottom: 5%;">인물 관리</h2>
 <br>
<div style="text-align: center;">
<input type="button" value="인물등록" style="width: 70%;" class="notice_admin_upde" onclick="location.href='people_create.do'">  
 </div>   
 <br>  <br>
  <table class="type06">
  <tr>
	<th>번호</th>
	<th>이름</th>
	<th>국적</th>
	<th>생년월일</th>
	<th>성별</th>
	<th>이미지</th>
	<th>수정</th>
	<th>삭제</th>
  </tr>
  <c:forEach var="dto" items="${list}">
    <tr>
      <td>${dto.pno}</td>
      <td>${dto.pname}</td>
      <td>${dto.country }</td>
      <td>${dto.pbirth }</td>
       <td>${dto.pgender }</td>
       <td>${dto.ppic }</td>
       <td><input type="button" value="수정" class="updehtn" onclick="location.href='people_update.do?pno=${dto.pno}'"></td>
       <td><input type="button" value="삭제" class="updehtn"  onclick="location.href='people_delete.do?pno=${dto.pno}'"></td>
    </tr>
  </c:forEach>
  </table> 
  <br>  <br>
    <div class="paging_jr">
	<div class="paging">

		<c:if test="${empty col }">		
		<c:if test="${paging.startPage != 1 }">
			<ul class=""> 
			<li><a href="people.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a></li>
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
   				<li><a href="people.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a></li>  
   				</ul>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
			<li><a href="people.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a></li>
		</ul>	 
		</c:if>
		</c:if>
			
		<c:if test="${!empty col}">
		<c:if test="${paging.startPage != 1 }">
		<ul>
		<li><a href="peoplepage.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&lt;</a></li>
			
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
				<li><a href="peoplepage.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">${p }</a><li>
				</ul>	
					
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
		<li><a href="peoplepage.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&gt;</a></li>
			</ul>
		</c:if>
		</c:if>
	</div>
			</div>

<div class="search_form">
<table style="width: 100%;">
		<tr>
		<td colspan="4"
			style="text-align: center; height: 50px">
		<form action="peoplepage.do?&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}" method="get"
			  onsubmit="return searchCheck(this)">
		<input type="radio" name="col" value="pname" checked>이름
		<input type="radio" name="col" value="country" >국적
		<input type="radio" name="col" value="pname_country">이름+국적
		<input type="text" name="word">
		<input type="submit" value="검색">
		</form>	
		</td>
		</tr>
		
</table>
  </div>
  <br><br><a href="../admin/index.do"><input type="button"  class="notice_admin_upde" value="관리자 메인페이지로"></a> <br> <br>
</div>
 <br>


