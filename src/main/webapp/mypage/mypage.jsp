<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ include file="../header.jsp"%>
<!-- 본문시작 -->


	<div class="container">
	<br>
	<div class="nevi">
	<h5><a href="../main/index.do">HOME</a>><a href=#>MYPAGE</a></h5>
	</div>
	<br><br><br>
	<h2 class="login_form_h2"><a href="mypage.do">마이페이지</a></h2>
	
	<br><br>
	<h3 style="text-align: center;">${uid }님 환영합니다.</h3>
	<div class="mypage_div">
	<div class="menu" style="float: left; height:1000px;  margin-top: 2.5%;">
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
			<div class="menu" style="float: left; margin-left: 5%; margin-bottom: 5%; width: 80%; ">
	
	<h3 style="margin-bottom: 2px; font-weight: bold;"><a href="bookinglist.do">TIKETING+</a></h3>	
	<table class="type04" >
	<tr>
	<th>예매번호</th>
	<th>영화제목</th>
	<th>상영일</th>
	<th>시작시간</th>
	<th>관/좌석번호</th>
	<th>예매날짜</th>
	<th>금액</th>
  	</tr>
	 <c:forEach var="dto" items="${blist}">
    <tr>
     <td>${dto.bno}</td>
    <td style="text-align: left;"><a href='../movie/read2.do?mno=${dto.mno}'>${dto.msub }</a></td>
    <c:set var="ddate" value="${dto.scrdate }"></c:set>
	<c:set var="ddate" value="${fn:substring(dto.scrdate,0,16)}"></c:set>
	<td>${ddate}</td>
	<td>${dto.scrstart }</td>
	<td>${dto.hno }관/${dto.bloc }</td>
	<td>${dto.bdate }</td>
	<fmt:parseNumber value="${fn:substring(dto.btype,0,1)}" var="num1"/>
	<fmt:parseNumber value="${fn:substring(dto.btype,2,3)}" var="num2"/>
	<fmt:parseNumber value="${fn:substring(dto.btype,4,5)}" var="num3"/>
	<fmt:parseNumber value="${fn:substring(dto.btype,6,7)}" var="num4"/>
	<td><c:out value="${num1 * 10000 + num2 * 8000 + num3 * 6000 + num4 * 5000}" /></td>
    </tr>
		</c:forEach>
		</table>
		
		<br>
		<br>
		
	</div>
	<div class="menu" style="float: left; margin-left: 5%; margin-bottom: 5%; width: 80%; ">
	
	<h3 style="margin-bottom: 2px; font-weight: bold;"><a href="down.do">DOWNLOAD+</a></h3>	
	<table class="type04" >
	<tr>
	<th>구매번호</th>
	<th>영화제목</th>
	<th>구매일</th>
	<th>다운로드</th>
  	</tr>
	 <c:forEach var="dto" items="${dlist}">
    <tr>
     <td>${dto.dno}</td>
    <td style="text-align: left;"><a href='../movie/read2.do?mno=${dto.mno}'>${dto.msub }</a></td>
    <c:set var="ddate" value="${dto.ddate }"></c:set>
	<c:set var="ddate" value="${fn:substring(dto.ddate,0,16)}"></c:set>
	<td>${ddate}</td>
	<td><input type="button" class="download_movie" value="다운로드" onclick="location.href='${root}/download?dir=/mp4/mfile&mfile=${dto.mfile}'"></td>
    </tr>
		</c:forEach>
		</table>
		
		<br>
		<br>
		
	</div>
		<div class="menu" style="float: left; margin-left: 5%; margin-bottom: 5%; width: 80%; ">
	
	<h3 style="margin-bottom: 2px; font-weight: bold;"><a href="bbslist.do">USERPICK+</a></h3>	
	<table class="type04" >
	<tr>
	<th>번호</th>
	<th>제목</th>
	<th>영화제목</th>
	<th>등록일</th>
  	</tr>
	 <c:forEach var="dto" items="${bbslist}">
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
		
		<br>
	<h3 style="margin-bottom: 2px; font-weight: bold;"><a href="bbslist.do">REVIEW+</a></h3>	
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
	
	<div>
	</div>
	<br>
	
</div>
	</div>
	
	</div>

<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>