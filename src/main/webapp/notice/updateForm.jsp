<%@ page contentType="text/html; charset=UTF-8" %> 
<jsp:include page="../header.jsp"></jsp:include>

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title>notice/updateForm.jsp</title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 24px; 
} 
</style> 
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head> 
<body>
<div class="title">수정</div>
<form name='frm' method='POST' action='update.do'>
<input type="hidden"
	   name="noticeno"
	   value="${dto.noticeno }">
  <table class='table'>
    <tr>
      <th>제목</th>
      <td><input type="text" name="title" size="20" maxlength="20" value='${dto.noticesub}'></td>
    </tr>
  </table>
  
  <table class='table'>
    <tr>
      <th>내용</th>
      <td><input type='text' name='title' textarea rows="5" cols="30" maxlength="300" value='${dto.noticecon}'></td>
    </tr>
  </table>

  <div class='bottom'>
    <input type='submit' value='수정'>
    <input type='button' value='목록' onclick="window.location.href='list.do'">
  </div>
</form>
</body> 
</html> 
