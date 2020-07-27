<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>

 <div class="title">사람 그룹 목록</div>
  <table>
  <tr>
	<th>번호</th>
	<th>이름</th>
	<th>국적</th>
  </tr>
  <c:forEach var="dto" items="${list}">
    <tr>
      <td>${dto.pno}</td>
      <td><a href="read.do?pno=${dto.pno }">${dto.pname}</a></td>
      <td>${dto.country }</td>
    </tr>
  </c:forEach>
  </table> 
  

<%@ include file="../footer.jsp"%> 
