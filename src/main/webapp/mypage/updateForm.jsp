<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


		<div class="menu" style="float: left; width:80%; margin-left: 5%">
		<div class="member_form_jr2" style=" width: 100%;">
				<form name="regForm" method="post" action="update.do"onsubmit="return memberCheck(this)">
					<p>아이디</p>
					<input type="text" name="uid" id="uid" size="15"  value="${dto.uid }" readonly>
					<p>비밀번호</p>
					<input type="password" name="ups" id="ups" size="15" value="${dto.ups }" required>
					<p>비밀번호확인</p>
					<input type="password" name="reps" id="reps" size="15" required>
					<p>이름</p>
					<input type="text" name="uname" id="uname" size="15" value="${dto.uname }" required>
					<p>생년월일</p>
					<input type="date" name="ubirth" id="ubirth" size="15" value="${dto.ubirth }"  required>
					<p>이메일</p>
						<input type="text" name="uemail" id="uemail" size="30" value="${dto.uemail }" readonly>
					<p>전화번호</p>
					<input type="text" name="uphone" id="uphone" size="15" value="${dto.uphone }" required>
					<p>성별</p>
					<input type="radio" name="ugender" value="F" <c:if test="${dto.ugender eq 'F'}">checked="checked"</c:if>/>
						<label for="F">여자</label> 
						<input type="radio" name="ugender" value="M" <c:if test="${dto.ugender eq 'M'}">checked="checked"</c:if>/>
						<label for="M">남자</label> 
						<br> <br> <br>
				<input type="submit" value="수정하기" style="align: center; width: 20%; margin-bottom: 10%;" > 
				<input type="button" value="탈퇴하기" onclick="location.href='delete.do'" style="width: 20%;">
				</form>
				<input type="hidden" name="uid" id="uid" value="${dto.uid }">
				
			</div>
			
			
		</div>
			</div>
	</div>

<script>


function memberCheck(f) {
    //회원가입 유효성 검사

    //1)아이디 5~15글자 인지
    var uid=f.uid.value;
    uid= uid.trim();
    
    if(uid.length<5 || uid.length>15){
        alert("아이디를 5~15글자 이내로 입력해주세요");
        f.uid.focus();
        return false;
    }//if end
    //2)비밀번호 5~15글자 인지
    var ups=f.ups.value;
    ups=ups.trim();
    if(ups.length<5 || ups.length>15){
        alert("비밀번호 5~15글자 이내로 입력해 주세요");
        f.ups.focus();
        return false;
    }//if end
    
    //3)비밀번호와 비밀번호 확인이 서로 일치하는지
    var reps=f.reps.value;
    reps=reps.trim();
    if(reps != ups ){
        alert("비밀번호가 틀렸습니다. 다시 입력해주세요 ");
        return false;
    }
    
    //4)이름 1~20글자 이내인지
    var uname=f.uname.value;
    uname=uname.trim();
    if(uname.length<1 || uname.length>20){
        alert("이름을 1~20자 이내로 입력해 주세요");
        f.uname.focus();
        return false;
    }//if end
    
    
    //정규표현식(regular expression)
    //공백지우기(trim()함수와 동일)
    //return x.replace(/^\s+|\s+$/gm,'');
    
    //5)이메일 주소가 유효한지?
    //<input type="email"> 요소도 있음
    
    var email=f.email.value;
    var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    if (email.match(regExp) == null) {
            alert("이메일을 제대로 입력해주세요");
            return false;
          }
    


    return true;
}//memberCheck() end

onload = function() {                                            // 페이지가 로드되면 자동으로 실행

    var frm = document.form(0);                    // 처음 폼을 frm으로 정의

    var ugender ="${dto.ugender}";

if( ugender =="F" ) {

frm.gender(0).checked = true;          // 배열로 표시가능

} else {

frm.gender(1).checked = true;

}
return false;
}


</script>
<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>