<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="../header.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>notice/deleteForm.jsp</title>
  <style type="text/css">
  *{
    font-family: gulim;
    font-size: 24px;
  }
  </style>
  <link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
  <div class="title">삭제</div>
  <form method="post" action="./delete.do">
  <input type="hidden" name="noticeno" value="${dto.noticeno }">
    <div class="content">
        <p>공지를 삭제하시겠습니까?</p>
        <p>※ 관련 파일도 전부 삭제됩니다</p>
    </div>
    <div class="bottom">
        <input type="submit" value="삭제진행">
        <input type="button" value="목록" onclick="location.href='./list.do'">
    </div>
  </form>

</body>
</html>