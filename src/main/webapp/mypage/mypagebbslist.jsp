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
	
	<h3 style="margin-bottom: 2px; font-weight: bold;">USERPICK</h3>	
	<table class="type04" >
	<tr>
	<th>번호</th>
	<th>제목</th>
	<th>영화제목</th>
	<th>등록일</th>
  	</tr>
	 <c:forEach var="dto" items="${list}">
    <tr>
     <td>${dto.nno}</td>
    <td style="text-align: left;"><a href='../bbs/read2.do?nno=${dto.nno}'>${dto.nsub }</a></td>
    <td>${dto.ntitle }</td>
    <c:set var="ndate" value="${dto.ndate }"></c:set>
	<c:set var="ndate" value="${fn:substring(dto.ndate,0,16)}"></c:set>
	<td>${ndate}</td>
    </tr>
		</c:forEach>
		</table>
		
		<br>
		<br>
		<div class="paging_jr">
	<div class="paging">
	
		<c:if test="${paging.startPage != 1 }">
			<ul class=""> 
			<li><a href="bbslist.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a></li>
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
   				<li><a href="bbslist.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a></li>  
   				</ul>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
			<li><a href="bbslist.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a></li>
		</ul>	 
		</c:if>

	</div>
			</div>
		<br>
	<h3 style="margin-bottom: 2px; font-weight: bold;">REVIEW</h3>	
	<table class="type04" >
	<tr>
	<th>번호</th>
	<th>별점</th>
	<th>영화제목</th>
	<th>한줄평</th>
  	</tr>
	 <c:forEach var="redto" items="${relist}">
    <tr>
     <td>${redto.rno}</td>
      <td><img src="../img/moviepickstar.png">${redto.rstar }</td>
      <td><a href='../movie/read2.do?mno=${redto.mno}'>${redto.msub }</a></td>
    <td style="text-align: left;">${redto.rcom }</td>
    </tr>
		</c:forEach>
		</table>	
	<br>
	<div class="paging_jr" style="margin-bottom: 3%; ">
	<div class="paging">
		<c:if test="${paging2.startPage2 != 1 }">
		<ul>
		<li><a href="bbslist.do?nowPage=${nowpage }&cntPerPage=${cntPerPage}&nowPage2=${paging2.startPage2 - 1 }&cntPerPage2=${paging2.cntPerPage2}">&lt;</a></li>
			
			</ul>
		</c:if>
		<c:forEach begin="${paging2.startPage2 }" end="${paging2.endPage2 }" var="p2">
			<c:choose>
				<c:when test="${p2 == paging2.nowPage2 }">
				<ul>
				<li><a>${p2 }</a></li>
				</ul>
				</c:when>
				<c:when test="${p2 != paging2.nowPage2 }">
				<ul>
				<li><a href="bbslist.do?nowPage=${nowpage }&cntPerPage=${cntPerPage}&nowPage2=${p2 }&cntPerPage2=${paging2.cntPerPage2}">${p2 }</a><li>
				</ul>	
					
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging2.endPage2 != paging2.lastPage2}">
		<ul>
		<li><a href="bbslist.do?nowPage=${nowpage }&cntPerPage=${cntPerPage}&nowPage2=${paging2.endPage2+1 }&cntPerPage2=${paging2.cntPerPage2}">&gt;</a></li>
			</ul>
		</c:if>
		
		</div>
		</div>
	<div>
	</div>
	<br>
	
</div>

<div class="menu" style="float: left; margin-left: 5%; margin-bottom: 5%; ">
	
	
		

	<div>
	</div>
	<br>
	
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