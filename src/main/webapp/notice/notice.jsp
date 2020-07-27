<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<style type="text/css"> 
*{ 
  font-family: BinggraeMelona;
  font-size: 24px; 
} 
</style> 
<link href="../css/style.css" rel="stylesheet" type="text/css">


  <div class="title" style="background-color: white;">공지사항
  <table border="1">
  <tr>
	<th>게시글번호</th>
	<th>제목</th>
	<th>내용</th>
	<th>조회수</th>
	<th>등록일</th>
	<th>공개여부</th>
	<th>이미지</th>
  </tr>
  <c:forEach var="dto" items="${list}">
    <tr>
      <td>${dto.noticeno}</td>
      <td><a href="../notice/read.do?noticeno=${dto.noticeno}">${dto.noticesub}</a></td>
      <td>${dto.noticecon }</td>
      <td>${dto.noticecnt }</td>
      <td>${dto.noticedate}</td>
      <td>${dto.whether} </td>
      <td>${dto.noticeimg}</td>
      
      	<td><input type="button" value="수정" onclick="location.href='../admin/notice_update.do?noticeno=${dto.noticeno}'"></td>
		<td><input type="button" value="삭제" onclick="location.href='../admin/notice_delete.do?noticeno=${dto.noticeno}'"></td>
	
    </tr>
  </c:forEach>
  </table>
  </div>
  <div class='bottom'>
    <input type='button' value='공지사항등록'
           onclick="location.href='../admin/notice_create.do'">
  </div>


  

