<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../header.jsp"%>
<%
	pageContext.setAttribute("br", "<br/>");
pageContext.setAttribute("cn", "\n");
pageContext.setAttribute("<", "&lt");
pageContext.setAttribute(">", "&gt");
pageContext.setAttribute("\'", "&quot");
pageContext.setAttribute("'", "&apos");
%>
<!-- 본문시작 -->
<style>
.star-input>.input, .star-input>.input>label:hover, .star-input>.input>input:focus+label,
	.star-input>.input>input:checked+label {
	display: inline-block;
	vertical-align: middle;
	background: url('../img/grade_img.png') no-repeat;
}

.star-input {
	display: inline-block;
	white-space: nowrap;
	width: 225px;
	height: 40px;
	padding: 0px;
	line-height: 30px;
}

.star-input>.input {
	display: inline-block;
	width: 150px;
	background-size: 150px;
	height: 28px;
	white-space: nowrap;
	overflow: hidden;
	position: relative;
}

.star-input>.input>input {
	position: relative;
	width: 1px;
	height: 1px;
	opacity: 0;
}

.star-input>.input.focus {
	outline: 1px dotted #ddd;
}

.star-input>.input>label {
	width: 30px;
	height: 0;
	padding: 28px 0 0 0;
	overflow: hidden;
	float: left;
	cursor: pointer;
	position: absolute;
	top: 0;
	left: 0;
}

.star-input>.input>label:hover, .star-input>.input>input:focus+label,
	.star-input>.input>input:checked+label {
	background-size: 150px;
	background-position: 0 bottom;
}

.star-input>.input>label:hover ~label{
	background-image: none;
}

.star-input>.input>label[for="p1"] {
	width: 30px;
	z-index: 5;
}

.star-input>.input>label[for="p2"] {
	width: 60px;
	z-index: 4;
}

.star-input>.input>label[for="p3"] {
	width: 90px;
	z-index: 3;
}

.star-input>.input>label[for="p4"] {
	width: 120px;
	z-index: 2;
}

.star-input>.input>label[for="p5"] {
	width: 150px;
	z-index: 1;
}

.star-input>output {
	display: inline-block;
	width: 60px;
	font-size: 18px;
	text-align: right;
	vertical-align: middle;
}
</style>
<script src="../js/star.js"></script>
<script src="//unpkg.com/jscroll/dist/jquery.jscroll.min.js"></script>

<section>
	<div class="container">

		<br>
		<div class="nevi">
			<h5>
				<a href="../main/index.do">HOME</a>><a href="list.do">MOVIEPICK</a>><a href="#">상세보기</a>
			</h5>
		</div>
		<br> <br> <br>
		<h2 class="login_form_h2">MOVIEPICK 상세</h2>
		<br> <br>
		<hr class="moviepick_hr_">
		<div class="moviepick_list">

			<div class="section_bbs">
				<div class="bbs_box_img">
					<img src="../images/poster/${dto.mposter }"
						style="width: 250px; height: 350px;">
				</div>
				<div class="bbs_content">
					<div class="bbs_content_area">
						<dl>
							<dt class="dt_msub">${dto.msub }
								<c:if test="${dto.msta == 1}">
									<em class="round lightblue"><span>현재상영중</span> </em>
								</c:if>
							</dt>
							<br>
							<dt class="dt_mpeople">
								감독 |
								<c:forEach var="peodto" items="${peolist }">
									<a href="../people/read.do?pno=${peodto.pno }">${peodto.pname }</a>
								</c:forEach>
							</dt>
							<dt class="dt_mpeople">
								배우 |
								<c:forEach var="peodto" items="${peolist2 }">
									<a href="../people/read.do?pno=${peodto.pno }">${peodto.pname }</a>
								</c:forEach>
							</dt>

							<dt class="dt_mpeople">
								장르 |
								<c:set var="textvalue" value="${dto.mgenre }" />
								<!-- ${fn:substring(textvalue,0,3)} -->
								<!-- act  -->
								<!-- ${fn:substring(textvalue,4,7)} -->
								<!-- fan  -->
								<c:if test='${fn:substring(textvalue,0,3) eq "fan"}'>
			판타지
		</c:if>
								<c:if test='${fn:substring(textvalue,0,3) eq "rom"}'>
			로맨스
		</c:if>
								<c:if test='${fn:substring(textvalue,0,3) eq "cri"}'>
			범죄
		</c:if>
								<c:if test='${fn:substring(textvalue,0,3) eq "act"}'>
			액션
		</c:if>
								<c:if test='${fn:substring(textvalue,0,3) eq "doc"}'>
			다큐
		</c:if>
								<c:if test='${fn:substring(textvalue,0,3) eq "com"}'>
			코미디
		</c:if>
								<c:if test='${fn:substring(textvalue,0,3) eq "ani"}'>
			애니메이션
		</c:if>
								<c:if test='${fn:substring(textvalue,0,3) eq "mus"}'>
			뮤지컬
		</c:if>
								<c:if test='${fn:substring(textvalue,0,3) eq "thr"}'>
			공포
		</c:if>
								<c:if test='${fn:substring(textvalue,4,7) eq "fan"}'>
			판타지
		</c:if>
								<c:if test='${fn:substring(textvalue,4,7) eq "rom"}'>
			로맨스
		</c:if>
								<c:if test='${fn:substring(textvalue,4,7) eq "cri"}'>
			범죄
		</c:if>
								<c:if test='${fn:substring(textvalue,4,7) eq "act"}'>
			액션
		</c:if>
								<c:if test='${fn:substring(textvalue,4,7) eq "doc"}'>
			다큐
		</c:if>
								<c:if test='${fn:substring(textvalue,4,7) eq "com"}'>
			코미디
		</c:if>
								<c:if test='${fn:substring(textvalue,4,7) eq "ani"}'>
			애니메이션
		</c:if>
								<c:if test='${fn:substring(textvalue,4,7) eq "mus"}'>
			뮤지컬
		</c:if>
								<c:if test='${fn:substring(textvalue,4,7) eq "thr"}'>
			공포
		</c:if>
							</dt>
							<dt class="dt_mpeople">
								기본 | ${dto.mrun }분,
								<c:choose>
									<c:when test='${dto.mgrade == 0 }'>
				전체이용가
			</c:when>
									<c:when test='${dto.mgrade == 12 }'>
				12세
			</c:when>
									<c:when test='${dto.mgrade == 15 }'>
				15세
			</c:when>
									<c:when test='${dto.mgrade == 19 }'>
				청소년관람불가
			</c:when>
								</c:choose>
							</dt>
							<dt class="dt_mpeople">개봉 | ${dto.mopen }</dt>
							<dt class="dt_mpeople">
								평점 | <img src="../img/moviepickstar.png"> ${aver }
							</dt>
							<br>
							<dt class="">
							<c:if test="${!empty uid }">
								<c:if test="${dto.msta == 0}">
								<form action="../movie/down.do" method="post" >
								<input type="hidden" name="uid" style="width: 40%;"
								value="<c:out value='${uid }'/>" readonly="readonly"> <input
								type="hidden" name="mno" style="width: 40%;"
								value="<c:out value='${mno }'/>" readonly="readonly">
									<input class="gmbtn" type="submit" value="구매하기" >
								</form>
								</c:if>
								<c:if test="${dto.msta == 1}">
									<input class="ymbtn" type="button" value="예매하기" onclick="location.href='choice.do' ">
								</c:if>
								</c:if>
							</dt>
						</dl>
					</div>
				</div>
			</div>
			<br>
			<div class="julguri">
				<h2 class="julguri_h2">줄거리</h2>
				<hr class="moviepick_hr_">
				<p class="juguri_a">${fn:replace(dto.msum, cn, br) }</p>
				<br>
			</div>
			<br> <br> <br>
			<div class="yego">
				<h2 class="yego_h2">예고편</h2>
				<hr class="moviepick_hr_">
				<div class="yegomp4">
					<video src="../mp4/trailer/${dto.mtrail }" controls autoplay
						style="width: 80%;"></video>
					<br>
				</div>
			</div>
			<div class="julguri">
				<h2 class="julguri_h2">추천이유</h2>
				<hr class="moviepick_hr_">
				<div class="moviepick_jul_content">
					<p class="juguri_a">${fn:replace(dto.mwhy, cn, br) }</p>
					<br>
				</div>
			</div>
			<div class="julguri">
				<h2 class="julguri_h2">평점</h2>
				<hr class="moviepick_rehr">
				<c:if test="${!empty uid }">
					<div class="review_pj">
						<form action="reCreate.do">
							<div class="star_jr">
								<span class="star-input"> <span class="input"> <input
										type="radio" name="rstar" value="1.0" id="p1"> <label
										for="p1">1</label> <input type="radio" name="rstar"
										value="2.0" id="p2"> <label for="p2">2</label> <input
										type="radio" name="rstar" value="3.0" id="p3"> <label
										for="p3">3</label> <input type="radio" name="rstar"
										value="4.0" id="p4"> <label for="p4">4</label> <input
										type="radio" name="rstar" value="5.0" id="p5"> <label
										for="p5">5</label>
								</span>
								</span>
							</div>
							<div class="review_textarea">
								<textarea id="rcom" name="rcom"
									style="resize: none; width: 100%;"></textarea>
							</div>
							<input type="hidden" name="uid" style="width: 40%;"
								value="<c:out value='${uid }'/>" readonly="readonly"> <input
								type="hidden" name="mno" style="width: 40%;"
								value="<c:out value='${mno }'/>" readonly="readonly">
							<div class="redr_brn_div">
								<input class="redr_btn" type="button" value="등록하기"
									onclick="goWrite(this.form)">
							</div>
						</form>
					</div>
				</c:if>
				<br>
				
			</div>
		</div>
		
		<div class="review_jaksung">
					<div class="review_jaksung_jr" id="rv_list">
						<c:forEach var="redto" items="${review }">
							<dl>
								<dt>${redto.uid}
									<img src="../img/moviepickstar.png">${redto.rstar }</dt>
								<dt ><c:if test="${redto.uid == uid }">
								<input type="button" value="삭제" style="float: right;"onclick="location.href='reviewdelete.do?mno=${dto.mno }&rno=${redto.rno}'">

								</c:if></dt>	
								<dt style="width: 80%;">${redto.rcom} 	</dt>
								
								
							</dl>
							<hr>
						</c:forEach>
	<div class="paging_jr" style="margin-bottom: 3%; ">
	<div class="paging">
	<c:if test="${!empty col }">
		<c:if test="${paging2.startPage2 != 1 }">
		<ul>
		<li><a href="read.do?mno=${dto.mno}&nowPage=${nowpage }&cntPerPage=${cntPerPage}&col=${col}&word=${word}&nowPage2=${paging2.startPage2 - 1 }&cntPerPage2=${paging2.cntPerPage2}">&lt;</a></li>
			
			</ul>
		</c:if>
		<c:forEach begin="${paging2.startPage2 }" end="${paging2.endPage2 }" var="p2">
			<c:choose>
				<c:when test="${p2 == paging2.nowPage2 }">
				<ul>
				<li><a>${p2 }</a></li>
				</ul>
				</c:when>
				<c:when test="${p2 != paging2.nowPage2 }">
				<ul>
				<li><a href="read.do?mno=${dto.mno}&nowPage=${nowpage }&cntPerPage=${cntPerPage}&col=${col}&word=${word}&nowPage2=${p2 }&cntPerPage2=${paging2.cntPerPage2}">${p2 }</a><li>
				</ul>	
					
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging2.endPage2 != paging2.lastPage2}">
		<ul>
		<li><a href="read.do?mno=${dto.mno}&nowPage=${nowpage }&cntPerPage=${cntPerPage}&col=${col}&word=${word}&nowPage2=${paging2.endPage2+1 }&cntPerPage2=${paging2.cntPerPage2}">&gt;</a></li>
			</ul>
		</c:if>
		</c:if>
			<c:if test="${empty col }">
		<c:if test="${paging2.startPage2 != 1 }">
		<ul>
		<li><a href="read.do?mno=${dto.mno}&nowPage=${nowpage }&cntPerPage=${cntPerPage}&nowPage2=${paging2.startPage2 - 1 }&cntPerPage2=${paging2.cntPerPage2}">&lt;</a></li>
			
			</ul>
		</c:if>
		<c:forEach begin="${paging2.startPage2 }" end="${paging2.endPage2 }" var="p2">
			<c:choose>
				<c:when test="${p2 == paging2.nowPage2 }">
				<ul>
				<li><a>${p2 }</a></li>
				</ul>
				</c:when>
				<c:when test="${p2 != paging2.nowPage2 }">
				<ul>
				<li><a href="read.do?mno=${dto.mno}&nowPage=${nowpage }&cntPerPage=${cntPerPage}&nowPage2=${p2 }&cntPerPage2=${paging2.cntPerPage2}">${p2 }</a><li>
				</ul>	
					
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging2.endPage2 != paging2.lastPage2}">
		<ul>
		<li><a href="read.do?mno=${dto.mno}&nowPage=${nowpage }&cntPerPage=${cntPerPage}&nowPage2=${paging2.endPage2+1 }&cntPerPage2=${paging2.cntPerPage2}">&gt;</a></li>
			</ul>
		</c:if>
		</c:if>
		</div>
		</div>
		

					</div>
				</div>
		<br> <br> <br>

		<c:if test="${empty col }">
		<input class="moklok_btn" type="button" value="목록으로" onclick=" location.href='../movie/list.do?nowPage=${nowpage }&cntPerPage=${cntPerPage}'">
		</c:if>
			<c:if test="${!empty col }">
		<input class="moklok_btn" type="button" value="목록으로" onclick=" location.href='../movie/list.do?nowPage=${nowpage }&cntPerPage=${cntPerPage}&col=${col }&word=${word }'">
		</c:if>
	</div>
</section>
<script>
	function goWrite(frm) {
		var rstar = frm.rstar.value;
		var rcom = frm.rcom.value;

		if (rstar.trim() == '') {
			alert(" 평점을 선택해주세요");

			return false;
		} else if (rcom.trim() == '') {
			alert("내용을 입력해주세요");
			return false;
		} else

			frm.submit();
	}


	  </script>

<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>