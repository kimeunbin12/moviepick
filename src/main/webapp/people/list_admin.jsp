<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<style>
.backback {
	background-color: #ffffff !important
}

.container {
	background-color: transparent !important
}
</style>
<div class=backback>
 <div class="title">사람 그룹 목록</div>
  <table border=1>
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
      <td><a href="read.do?pno=${dto.pno }">${dto.pname}</a></td>
      <td>${dto.country }</td>
      <td>${dto.pbirth }</td>
       <td>${dto.pgender }</td>
       <td><img src="../images/people/${dto.ppic }" width="200"></td>
       <td><input type="button" value="수정" onclick="location.href='update.do'"></td>
       <td><input type="button" value="삭제" onclick="location.href='delete.do'"></td>
    </tr>
  </c:forEach>
  </table> 
<input type="button" value="인물등록" onclick="location.href='create.do'">  
</div>
<%@ include file="../footer.jsp"%> 
