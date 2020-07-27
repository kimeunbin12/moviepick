<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 본문시작 -->

<!--Start of slider section-->
<section id="slider">
	<div id="carousel-example-generic" class="carousel slide carousel-fade"
		data-ride="carousel" data-interval="3000">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0"
				class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			<li data-target="#carousel-example-generic" data-slide-to="3"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<div class="slider_overlay">
					<img src="../img/반도.jpg" alt="...">
					<div class="carousel-caption">
						<div class="slider_text"></div>
					</div>
				</div>
			</div>
			<!--End of item With Active-->
			<div class="item">
				<div class="slider_overlay">
					<img src="../img/알라딘.jpg" alt="...">
					<div class="carousel-caption">
						<div class="slider_text"></div>
					</div>
				</div>
			</div>
			<!--End of Item-->
			<div class="item">
				<div class="slider_overlay">
					<img src="../img/원데이.jpg" alt="...">
					<div class="carousel-caption">
						<div class="slider_text"></div>
					</div>
				</div>
			</div>
					<div class="item">
				<div class="slider_overlay">
					<img src="../img/미녀와야수.jpg" alt="...">
					<div class="carousel-caption">
						<div class="slider_text"></div>
					</div>
				</div>
			</div>
			<!--End of item-->
		</div>
		<!--End of Carousel Inner-->
		<a class="left carousel-control" href="#carousel-example-generic"
			role="button" data-slide="prev"> <!-- 왼쪽 화살표 --> <span
			class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		</a>
		<!-- 오른쪽 화살표 버튼 -->
		<!-- href는 carousel의 id를 가르킨다. -->
		<a class="right carousel-control" href="#carousel-example-generic"
			role="button" data-slide="next"> <!-- 오른쪽 화살표 --> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		</a>
	</div>
</section>
<!--end of slider section-->



<!--Start of welcome section-->
<section id="welcome">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="wel_header">
					<h2>현재 상영작</h2>
					<hr>
				</div>
			</div>
		</div>

		<!--End of row-->
		<!--ranking1 start-->

		<div class="mainmoviepick_section">

				<c:forEach var="dto" items="${mlist}">
					<div class="mainmoviepick_cont">
						<div class="mainmoviepick_img">
							<a href="../movie/read2.do?mno=${dto.mno}"><img
								src="../images/poster/${dto.mposter }"
								style="min-height: 294px;"></a>
						</div>
						<div class="moviepick_content ">
							<div class="main_content" >
								<dl>
									<dt class="maindt_msub">
										<a href="../movie/read.do?mno=${dto.mno}" style="color: #f2cb05;" title="${dto.msub}">${dto.msub}</a>
									</dt>
									<dt class="maindt_msum" style="color: white;">${dto.msum }</dt>

								</dl>
							</div>
						</div>

					</div>
				</c:forEach>
			</div>
		<!--ranking5 end-->
	</div>
	<!--End of container-->

</section>

<!--end of welcome section-->
<section id="etc">
	<div class="container">
		<div class="mp4">
			<h3 style="color: #f2cb05;">예고편</h3>
			<iframe width="90%" height="400px"
				src="https://www.youtube.com/embed/1K6uSMB_3DM?controls=0"
				frameborder="0"></iframe>
		</div>
		<div class="mp4">

			<h3>
				<a href="../notice/list.do" style="color: #f2cb05">공지사항</a>
			</h3>
			<br>
			<table class="type05">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>등록일</th>
				</tr>
				<c:forEach var="dto" items="${list}">
					<tr>
						<td>${dto.noticeno}</td>
						<td style="text-align: left;">
						<a href='../notice/read2.do?noticeno=${dto.noticeno}' style="color: white;">${dto.noticesub }</a></td>
						<c:set var="noticedate" value="${dto.noticedate }"></c:set>
						<c:set var="ndatedate"
							value="${fn:substring(dto.noticedate,0,16)}"></c:set>
						<td>${noticedate}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</section>

<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>
