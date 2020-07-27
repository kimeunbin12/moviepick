/*myscript.js*/
/* charset=utf-8*/

function bbsCheck(f) {
	//게시판의 유효성 검사
	//this 자기자신
	//f-> <form name=bbsfrm><form>
	
	//1)작성자는 2글자이상 입력
	
	var wname=f.wname.value;
	wname=wname.trim();
	if(wname.length<2){
		alert("작성자 2글자 이상 입력해 주세요");
		f.wname.focus();
		return false;
	}//if end
	
	
	//2)제목은 2글자이상 입력
	var subject=f.subject.value;
	subject = subject.trim();
	if(subject.length<2){
		alert("제목을 2글자 이상 입력해 주세요");
		f.subject.focus();
		return false;
	}//if end
	//3)내용은 2글자이상 입력
	var content=f.content.value;
	content = content.trim();
	if(content.length<2){
		alert("내용을 2글자 이상 입력해 주세요");
		f.content.focus();
		return false;
	}//if end
	//4)비밀번호는 4글자이상 입력
	var passwd=f.passwd.value;
	passwd = passwd.trim();
	if(passwd.length<4){
		alert("비밀번호를 4글자 이상 입력해 주세요");
		f.passwd.focus();
		return false;
	}//if end
	
	//onsubmit이벤트에서 서버로 전송
	
	return true;

}//bbsCheck() end

function pwCheck(f){
	
	//비밀번호가  4글자 이상 입력되었는지 검사
	var passwd=f.passwd.value;
	passwd = passwd.trim();
	if(passwd.length<4){
		alert("비밀번호를 4글자 이상 입력해 주세요");
		f.passwd.focus();
		return false;
	}
	var message="진행된 내용은 복구되지 않습니다.\n 계속 진행할까요?"
		if(confirm(message)){
			
			return true; //서버로 전송
		
		}else{
			
			return false;
		}
	
	
}//pwCheck() end

function searchCheck(f) {
	
	var word=f.word.value;
	word=word.trim();
	if(word.length==0){
		alert("검색어를 입력하세요");
		return false;//서버전송불가
	}//if end
	
	return true; // 서버로 전송
	
}//searchCheck() end


function loginCheck(f) {
	
	//1)아이디 5~10글자이내
	var id=f.id.value;
	id= id.trim();
	
	if(id.length<5 || id.length>10){
		alert("아이디를 5~10글자 이내로 입력해주세요");
		f.id.focus();
		return false;
	}//if end
	//2)비밀번호 5~10글자 이내
	var passwd=f.passwd.value;
	passwd=passwd.trim();
	if(passwd.length<5 || passwd.length>10){
		alert("비밀번호 5~10글자 이내 입력해 주세요");
		f.passwd.focus();
		return false;
	}//if end
	
	return true;
}//logincheck() end
function idCheck() {
	//아이디 중복확인
	
	//부트스트랩 모달창
	//->부모창과 자식창이 한몸으로 구성되어 있음
	
	//새창만들기
	//->부모창과 자식창이 별개로 구성되어 있음
	//->모바일에 기반을 둔 frontend단에서는 사용하지 말것!!
	//window.open("파일명","새창이름","다양한옵션들")
	
	window.open("idCheckForm.do","idwin","width=400, height=350");
}//idcheck() end

function emailCheck(){
	//이메일 중복확인
	
	//부트스트랩 모달창
	//->부모창과 자식창이 한몸으로 구성되어 있음
	
	//새창만들기
	//->부모창과 자식창이 별개로 구성되어 있음
	//->모바일에 기반을 둔 frontend단에서는 사용하지 말것!!
	//window.open("파일명","새창이름","다양한옵션들")
	
	window.open("emailCheckForm.jsp","emailwin","width=400, height=350");
}//idcheck() end


function memberCheck(f) {
	//회원가입 유효성 검사

	//1)아이디 5~10글자 인지
	var id=f.id.value;
	id= id.trim();
	
	if(id.length<5 || id.length>10){
		alert("아이디를 5~10글자 이내로 입력해주세요");
		f.id.focus();
		return false;
	}//if end
	//2)비밀번호 5~10글자 인지
	var passwd=f.passwd.value;
	passwd=passwd.trim();
	if(passwd.length<5 || passwd.length>10){
		alert("비밀번호 5~10글자 이내로 입력해 주세요");
		f.passwd.focus();
		return false;
	}//if end
	
	//3)비밀번호와 비밀번호 확인이 서로 일치하는지
	var repasswd=f.repasswd.value;
	repasswd=repasswd.trim();
	if(repasswd != passwd ){
		alert("비밀번호가 틀렸습니다. 다시 입력해주세요 ");
		return false;
	}
	
	//4)이름 1~20글자 이내인지
	var mname=f.mname.value;
	mname=mname.trim();
	if(mname.length<1 || mname.length>20){
		alert("이름을 1~20자 이내로 입력해 주세요");
		f.mname.focus();
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

	//6)직업을 선택했는지?
		
	var job=f.job.value;
	if(job=="0"){
		alert("직업을 선택해 주세요");
		return false;
	}
	
	return true;
}//memberCheck() end

function pdsCheck(f) {
	
	//1)이름 1글자 이상인지
	var wname=f.wname.value;
	wname=wname.trim();
	if(wname.length<2){
		alert("이름을 2자 이상 입력해 주세요");
		f.wname.focus();
		return false;
	}//if end
	//2)제목 1글자 이상인지
	var subject=f.subject.value;
	subject=subject.trim();
	if(subject.length<1 ){
		alert("제목을 1자 이상 입력해 주세요");
		f.subject.focus();
		return false;
	}//if end
	//3)비밀번호 4~15글자 이내인지
	var passwd=f.passwd.value;
	passwd=passwd.trim();
	if(passwd.length<4 || passwd.length>15){
		alert("비밀번호 4~15글자 이내로 입력해 주세요");
		f.passwd.focus();
		return false;
	}//if end
	
	//4)첨부파일
	//이미지 파일만 가능(png, gif, jpg)
	
	var filename = f.filename.value;
	filename=filename.trim();
	if(filename.length==0){
		alert("첨부파일을 선택해 주세요");
		return false;
	}else{
		//이미지 파일만 가능
		
		//마지막 . 의 위치를 확인
		
		var dot=filename.lastIndexOf(".");
		if(dot==-1){// . 기호가 없다면
			alert("첨부파일을 다시 선택해 주세요");
			return false;
		}else{
			//마지막 . 기호 이후 문자열 자르기
			var ext=filename.substr(dot+1);
			//전부 소문자로 치환
			ext= ext.toLowerCase();
			if(ext=="png"||ext=="jpg"||ext=="gif") {
				return true;
			}else {
				alert("이미지 파일만 가능합니다.");
				return false;
			}
		}	

	}
	
	  
}//pdsCheck() end

function noticeCheck(f) {
	
	var subject=f.subject.value;
	subject = subject.trim();
	if(subject.length<2){
		alert("제목을 2글자 이상 입력해 주세요");
		f.subject.focus();
		return false;
	}//if end
	//3)내용은 2글자이상 입력
	var content=f.content.value;
	content = content.trim();
	if(content.length<2){
		alert("내용을 2글자 이상 입력해 주세요");
		f.content.focus();
		return false;
	}//if end
}

function idemailCheck(f) {
	//회원가입 유효성 검사

	//1)아이디 5~10글자 인지
	var id=f.id.value;
	id= id.trim();
	
	if(id.length<5 || id.length>10){
		alert("아이디를 5~10글자 이내로 입력해주세요");
		f.id.focus();
		return false;
	}//if end

	var email=f.email.value;
	var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	if (email.match(regExp) == null) {
			alert("이메일을 제대로 입력해주세요");
			return false;
		  }


	return true;
}//idCheck() end