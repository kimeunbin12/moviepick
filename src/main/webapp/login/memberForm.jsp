<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 memberForm.jsp-->

<div class="container">
	<div class="login_form_jr1">
		<h2 class="login_form_h2">회원가입</h2>
		<div class="member_form_jr2">
			<div class="login_form">
				<form name="regForm" method="post" action="member.do"
					onsubmit="return memberCheck(this)">
					<br>
					<br>
					<p>아이디</p>
					<input type="text" name="uid" id="uid" size="15" readonly>
					<input type="button" value="중복확인" onclick="idCheck()"> <br>
					<p>비밀번호</p>

					<input type="password" name="ups" id="ups" size="15" required>
					<p>비밀번호 확인</p>
					<input type="password" name="reps" id="reps" size="15" required>
					<p>이름</p>
					<input type="text" name="uname" id="uname" size="15" required>
					<p>생년월일</p>
					<input type="date" class="date_text" name="ubirth" id="ubirth" size="15" required>
					<p>이메일</p>
						<input type="text" name="uemail" id="uemail" size="30" readonly>
						<input type="button" value="중복확인" onclick="emailCheck()">
					<p>전화번호</p>
					<input type="text" name="uphone" id="uphone" size="15" placeholder="01012345678" required>
					<p>성별</p>
					<input type="radio" id="F" name="ugender" value="F"
						checked="checked"> <label for="F">여자</label> <input
						type="radio" id="M" name="ugender" value="M"> <label
						for="M">남자</label> <br> <br> <br>
					<div class="button">
						<input type="submit" value="가입하기" class="btn btn-primary"
							style="align: center;">
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

		window.open("idcheck.do", "idwin", "width=400, height=500");
	}//idcheck() end

	function emailCheck() {
		//이메일 중복확인

		//부트스트랩 모달창
		//->부모창과 자식창이 한몸으로 구성되어 있음

		//새창만들기
		//->부모창과 자식창이 별개로 구성되어 있음
		//->모바일에 기반을 둔 frontend단에서는 사용하지 말것!!
		//window.open("파일명","새창이름","다양한옵션들")

		window.open("emailCheck.do", "emailwin", "width=400, height=500");
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
</script>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>