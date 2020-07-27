<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<!-- 본문시작 -->

<input type="hidden" name='pno' value='${dto.pno}'>
	<div class="container">
		<div class="tit-heading-wrap">

			<h3>인물 정보</h3>
		</div>
		<div class="wrap-people" style="padding-top:20px;">
			<div class="sect-base-people">
				<div class="sect-base">
					<div class="box-image">
						<img src="../images/people/${dto.ppic }" style="min-width:250px; min-height: 
						300px; max-width:250px; max-height: 300px;"><br>
					</div>
					<div class="box-contents">
						<div class="title">
							<strong>${dto.pname}</strong>
						</div>
						<div class="spec">
							<dl>
								<dt>생년월일</dt>
								<dd>${dto.pbirth }</dd>
								<dt>국적</dt>
								<dd>${dto.country }</dd>
								<dt>성별</dt>
								<dd>${dto.pgender }</dd>
							</dl>
						</div>
					</div>
					<input type="button" value="뒤로가기" class="peoeplebackbtn" onclick="javascript:history.back()">
				</div>
			</div>
		</div>
	</div>

<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>