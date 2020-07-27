<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<!-- 본문시작 -->

<div class="container">
	<form name="regForm"
      method="post"
      action="people_create.do"
      enctype="multipart/form-data">
	
		<div class="tit-heading-wrap">
			<h3>인물 등록</h3>
		</div>
		<div class="wrap-people" style="padding-top: 20px;">
			<div class="sect-base-people">
				<div class="sect-base">
					<div class="box-image">
						<input type="file" name='posterMF' size='50'>
					</div>
					<div class="box-contents">
						<div class="title">
							<input type="text" name="pname" id="pname" placeholder="이름">
						</div>
						<div class="spec">
							<dl>
								<dt>생년월일</dt>
								<dd>
									<input type="date" name="pbirth" id="pbirth" placeholder="생년월일">
								</dd>
								<dt>국적</dt>
								<dd>
									<input type="text" name="country" id="country" placeholder="국적">
								</dd>
								<dt>성별</dt>
								<dd>
									<input type="radio" id="W" name="pgender" value="W"> 
									<label for="W">여자</label>
									 <input type="radio" id="M" name="pgender" value="M"> 
									<label for="M">남자</label>
									<br><br>
									<input type="submit" value="등록하기"   class="notice_admin_upde" style="align: center;">
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