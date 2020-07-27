<%@ page contentType="text/html; charset=UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<!-- 본문시작 -->

	<div class="container">
	<br>
	<div class="nevi">
	<h5><a href="../main/index.do">HOME</a>><a href="mypage.do">MYPAGE</a>><a href="#">개인정보변경</a></h5>
	</div>
	<br><br><br>
	<h2 class="login_form_h2"><a href="mypage.do">마이페이지</a></h2>
	<div class="mypage_div">
	<div class="menu" style="float: left;">
	<table class="type04" style="width: 100%; margin-right: 3%;">
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
		
				
	<div class="menu" style="float: left; margin-left: 5%; ">
	<div class="tit-mycgv">
    <h3 style="margin-bottom: 2px;">비밀번호 확인</h3>
	</div>

<div class="pscheck_div">
<p class="info-com">고객님의 개인정보 보호를 위한 절차이오니, Moviepick 로그인 시 사용하는 비밀번호를 입력해 주세요.</p>
<form id="form1" method="post" action="infchange.do">
<input type="hidden" name="uid" id="uid" value="${dto.uid }">
<br>
    <fieldset class="confirm">
     
        <div class="info-confirm">
            <p style="margin-bottom: 5px;">
                <strong>아이디</strong> 
                <span><c:out value='${uid }'/></span>
                
            </p>
                <p>
                    <strong><label for="txtPassword">비밀번호</label></strong> 
                    <input type="password" id="ups" name="ups" title="비밀번호" required="required" maxlength="20" class="small" />
                </p>
        </div>
    </fieldset>
		<div class="set-btn">
        <input type="submit" id="save" value="확인">
		</div>

</form>
</div>
	</div>
	</div>

    </div>

<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>