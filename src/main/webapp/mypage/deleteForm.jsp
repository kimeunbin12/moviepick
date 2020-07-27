<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<!-- 본문시작 -->

<div class="container">
	<br>
	<div class="nevi">
		<h5>
			<a href="../main/index.do">HOME</a>><a href="mypage.do">MYPAGE</a>><a href="#">개인정보변경</a>
		</h5>
	</div>
	<br> <br> <br>
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
					<td><a href="downs.do">나의 구매내역</a></td>
				</tr>
				<tr>
					<td><a href="bbslist.do">내가 쓴 글</a></td>
				</tr>
				<tr>
					<td><a href="infchange.do">개인정보변경</a></td>
				</tr>
			</table>
		</div>
		
		<div class="menu" style="float: left; margin-left: 5%; width: 70%; ">
			<div>
				<h3 style="margin-bottom: 2px;">정말 탈퇴하시겠습니까?</h3>
			</div>

			<div class="pscheck_div">
				<p class="info-com">모든 정보가 지워지며 복구할 수 없습니다.</p>
				<form id="form1" method="post" action="delete.do">
					<input type="hidden" name="uid" id="uid" value="${dto.uid }">
					<br>

					<div class="set-btn">
						<input type="hidden" name="uid" id="uid" value="${dto.uid }">
						<input type="submit" value="탈퇴하기" style="align: center;">
						<input type="button" value="뒤로가기" onclick="javascript:history.back()">
					</div>

				</form>
			</div>
		</div>

</div>
</div>
<script>
	function idCheck() {
		//아이디 중복확인

		//부트스트랩 모달창
		//->부모창과 자식창이 한몸으로 구성되어 있음

		//새창만들기
		//->부모창과 자식창이 별개로 구성되어 있음
		//->모바일에 기반을 둔 frontend단에서는 사용하지 말것!!
		//window.open("파일명","새창이름","다양한옵션들")

		window.open("idCheck.do", "idwin", "width=400, height=350");
	}//idcheck() end

	function emailCheck() {
		//이메일 중복확인

		//부트스트랩 모달창
		//->부모창과 자식창이 한몸으로 구성되어 있음

		//새창만들기
		//->부모창과 자식창이 별개로 구성되어 있음
		//->모바일에 기반을 둔 frontend단에서는 사용하지 말것!!
		//window.open("파일명","새창이름","다양한옵션들")

		window.open("emailCheck.do", "emailwin", "width=400, height=350");
	}//idcheck() end

	function memberCheck(f) {
		//회원가입 유효성 검사

		//1)아이디 5~15글자 인지
		var uid = f.uid.value;
		uid = uid.trim();

		if (uid.length<5 || uid.length>15) {
			alert("아이디를 5~15글자 이내로 입력해주세요");
			f.uid.focus();
			return false;
		}//if end
		//2)비밀번호 5~15글자 인지
		var ups = f.ups.value;
		ups = ups.trim();
		if (ups.length<5 || ups.length>15) {
			alert("비밀번호 5~15글자 이내로 입력해 주세요");
			f.ups.focus();
			return false;
		}//if end

		//3)비밀번호와 비밀번호 확인이 서로 일치하는지
		var reps = f.reps.value;
		reps = reps.trim();
		if (reps != ups) {
			alert("비밀번호가 틀렸습니다. 다시 입력해주세요 ");
			return false;
		}

		//4)이름 1~20글자 이내인지
		var uname = f.uname.value;
		uname = uname.trim();
		if (uname.length<1 || uname.length>20) {
			alert("이름을 1~20자 이내로 입력해 주세요");
			f.uname.focus();
			return false;
		}//if end

		//정규표현식(regular expression)
		//공백지우기(trim()함수와 동일)
		//return x.replace(/^\s+|\s+$/gm,'');

		//5)이메일 주소가 유효한지?
		//<input type="email"> 요소도 있음

		var email = f.email.value;
		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		if (email.match(regExp) == null) {
			alert("이메일을 제대로 입력해주세요");
			return false;
		}

		return true;

	}//memberCheck() end

	function next() {
		if (confirm(" 정말 탈퇴하시겠습니까? ")) {
			alert('탈퇴 되었습니다.');
			location.href = "main/index.do";
		} else {
			alert('아니오를 누르셨습니다');
			location.href = "mypage/update.do";
		}
	}
</script>

<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>