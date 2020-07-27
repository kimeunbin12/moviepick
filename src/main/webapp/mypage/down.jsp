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
	<h5><a href="../main/index.do">HOME</a>><a href="mypage.do">MYPAGE</a>><a href="#">내가 쓴 글</a></h5>
	</div>
	<br><br><br>
	<h2 class="login_form_h2"><a href="mypage.do">마이페이지</a></h2>
	<div class="mypage_div" style="margin-top: 5%;">
	<div class="menu" style="float: left; margin-top: 6%;">
	<table class="type04" style="width: 100%;">
	<tr>
	<th>MY PAGE</th>
	<input type="hidden" name="uid" id="uid" value="${dto.uid }">
	</tr>
	<tr>
	<td><a href="bookinglist.do">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;나의 예매내역&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
	</tr>
	<tr>
	<td><a href="down.do">나의 구매내역</a></td>
	</tr>
	<tr>
	<td><a href="bbslist.do">내가 쓴 글</a></td>
	</tr>
	<tr>
	<td><a href="infchange.do">개인정보변경</a></td>
	</tr>
	</table>
		</div>
	
<br>
<br>
	<div class="menu" style="float: left; margin-left: 5%; margin-bottom: 5%; width: 80%; ">
	
	<h3 style="margin-bottom: 2px; font-weight: bold;">DOWNLOAD</h3>	
	<table class="type04" >
	<tr>
	<th>구매번호</th>
	<th>영화제목</th>
	<th>구매일</th>
	<th>다운로드</th>
  	</tr>
	 <c:forEach var="dto" items="${list}">
    <tr>
     <td>${dto.dno}</td>
    <td style="text-align: left;"><a href='../movie/read2.do?mno=${dto.mno}'>${dto.msub }</a></td>
    <c:set var="ddate" value="${dto.ddate }"></c:set>
	<c:set var="ddate" value="${fn:substring(dto.ddate,0,16)}"></c:set>
	<td>${ddate}</td>
	<td><input type="button"  class="download_movie" value="다운로드" onclick="location.href='${root}/download?dir=/mp4/mfile&mfile=${dto.mfile}'"></td>
    </tr>
		</c:forEach>
		</table>
		
		<br>
		<br>
		<div class="paging_jr">
	    <div class="paging">
	
		<c:if test="${paging.startPage != 1 }">
			<ul class=""> 
			<li><a href="down.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a></li>
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
   				<li><a href="down.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a></li>  
   				</ul>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
			<li><a href="down.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a></li>
		</ul>	 
		</c:if>
	</div>
	</div>
	</div></div>

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