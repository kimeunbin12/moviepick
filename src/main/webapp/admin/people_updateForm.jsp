<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<!-- 본문시작 -->
	<div class="container">
	<form name="regForm"
      method="post"
      action="people_update.do"
      enctype="multipart/form-data">


		<div class="tit-heading-wrap">
			<h3>인물 수정</h3>
		</div>
		<div class="wrap-people" style="padding-top: 20px;">
			<div class="sect-base-people">
				<div class="sect-base">
				<input type='hidden' name='pno' value='${dto.pno }'>
					<div class="box-image">
						<img src="../images/people/${dto.ppic }" style="min-width:120px; min-height: 150px; max-width:120px; max-height: 150px;"><br>
						<input type="file" name='posterMF' size='50'>
					</div>
					<div class="box-contents">
						<div class="title">
							<input type="text" name="pname" id="pname" value='${dto.pname }'>
						</div>
						<div class="spec">
							<dl>
								<dt>생년월일</dt>
								<dd>
									<input type="date" name="pbirth" id="pbirth" value='${dto.pbirth }'>
								</dd>
								<dt>국적</dt>
								<dd>
									<input type="text" name="country" id="country" value='${dto.country }'>
								</dd>
								<dt>성별</dt>
								<dd>
									<input type="radio" id="W" name="pgender" <c:if test="${dto.pgender eq 'W'}">checked="checked"</c:if>> 
									<label for="W">여자</label>
									 <input type="radio" id="M" name="pgender" <c:if test="${dto.pgender eq 'M'}">checked="checked"</c:if>> 
									<label for="M">남자</label>
									<br>
									<input type="submit" value="수정하기"  class="notice_admin_upde" style="align: center;">
								</dd>
							</dl>
						</div>
					</div>
				</div>
			</div>
		
		</div>
		</form>
	</div>



<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>