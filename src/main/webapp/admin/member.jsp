<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<div class="container">
 <h2 style="text-align: center; margin-top: 5%; margin-bottom: 5%;">회원관리</h2>
<input type="hidden" name="uid" value="${dto.uid }">
    
       
    <table class="type06">
        <tr>
            <th>아이디</th>
            <th>비밀번호</th>
            <th>회원이름</th>
            <th>생일</th>
            <th>이메일주소</th>
            <th>성별</th>
            <th>가입일</th>
            <th>휴대전화번호</th>
            <th>삭제</th>
        </tr>
        <c:forEach var="dto" items="${list}">
            <tr>
                <td>${dto.uid }</td>
                <td>${dto.ups }</td>
                <td>${dto.uname }</td>
                <td>${dto.ubirth }</td>
                <td>${dto.uemail }</td>
                <td>${dto.ugender }</td>
                <td>${dto.udate }</td>
                <td>${dto.uphone }</td>
                <td><a href="../admin/login_delete.do?uid=${dto.uid}"><input type="button"  class="updehtn" value="삭제"></a></td>
                
            </tr>
        </c:forEach>
    </table>
    <br> <br>
        <div class="paging_jr">
	<div class="paging">

		<c:if test="${empty col }">		
		<c:if test="${paging.startPage != 1 }">
			<ul class=""> 
			<li><a href="login.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a></li>
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
   				<li><a href="login.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a></li>  
   				</ul>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
			<li><a href="login.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a></li>
		</ul>	 
		</c:if>
		</c:if>
			
		<c:if test="${!empty col}">
		<c:if test="${paging.startPage != 1 }">
		<ul>
		<li><a href="loginpage.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&lt;</a></li>
			
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
				<li><a href="loginpage.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">${p }</a><li>
				</ul>	
					
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
		<li><a href="loginpage.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&gt;</a></li>
			</ul>
		</c:if>
		</c:if>
	</div>
			</div>
			<br>
			<div class="search_form">
		<table style="width: 100%">
		<tr>
		<td colspan="4"
			style="text-align: center; height: 50px">
		<form action="loginpage.do?&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}" method="get"
			  onsubmit="return searchCheck(this)">
		<input type="radio" name="col" value="uname" checked>이름
		<input type="radio" name="col" value="uemail">이메일	
		<input type="radio" name="col" value="uphone">핸드폰번호	
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