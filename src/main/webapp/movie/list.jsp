<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<!-- 본문시작 -->


<section>
	<div class="container">

		<br>
		<div class="nevi">
			<h5>
				<a href="../main/index.do">HOME</a>><a href=#>MOVIEPICK</a>
			</h5>
		</div>
		<br> <br> <br>
		<h2 class="login_form_h2">MOVIEPICK</h2>
		
		<br> <br>
		<div class="bbs_list">
			<div class="moviepick_section">
				<c:if test="${paging.total == 0 }">
				<h2 class="login_form_h2">검색 결과가 없습니다.</h2>
				</c:if>
				<c:forEach var="dto" items="${list}">
					<div class="moviepick_cont" style="max-height: 460px; margin-bottom: 3px;">
						<div class="moviepick_img">
							<a href="../movie/read.do?mno=${dto.mno}&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}&col=${col }&word=${word }"><img
								src="../images/poster/${dto.mposter }"
								style="min-height: 375px; max-height: 375px; min-width: 262px; max-width: 262px" ></a>
						</div>
						
						<div class="moviepick_content" >
									
							<div class="bbs_content_area">
								<dl>
									<dt class="dt_msub2">
										<a href="../movie/read.do?mno=${dto.mno}&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}&col=${col }&word=${word }" title="${dto.msub }">${dto.msub}</a>
									</dt>

									<dt class="list_dtdt" style="float: left;">연령등급 | ${dto.mgrade}세 상영여부 |&nbsp;
									
									 </dt> 
									<dt class="list_dtdt" style="float: left; color: red;">
									<c:if test="${dto.msta eq 1}">
									 상영중
									</c:if>
									</dt>
									<dt class="list_dtdt" style="float: left; color: blue;">
									<c:if test="${dto.msta eq 0}">
									 상영종료
									</c:if>
		
									</dt>
									

								</dl>
							</div>
						</div>
	
					</div>
				
				</c:forEach>
			</div>
		</div>
	


		<div class="paging_jr" style="margin-bottom: 5%;">
			<div class="paging">

				<c:if test="${empty col  }">
					<c:if test="${paging.startPage != 1 }">
						<ul class="">
							<li><a
								href="list.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&lt;</a></li>
						</ul>
					</c:if>
					<c:forEach begin="${paging.startPage }" end="${paging.endPage }"
						var="p">
						<c:choose>
							<c:when test="${p == paging.nowPage }">
								<ul>
									<li><a>${p }</a></li>
								</ul>
							</c:when>
							<c:when test="${p != paging.nowPage }">
								<ul>
									<li><a
										href="list.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">${p }</a></li>
								</ul>
							</c:when>
						</c:choose>
					</c:forEach>
					<c:if test="${paging.endPage != paging.lastPage}">
						<ul>
							<li><a
								href="list.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&gt;</a></li>
						</ul>
					</c:if>
				</c:if>

				<c:if test="${!empty col}">
					<c:if test="${paging.startPage != 1 }">
						<ul>
							<li><a
								href="listpage.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&lt;</a></li>

						</ul>
					</c:if>
					<c:forEach begin="${paging.startPage }" end="${paging.endPage }"
						var="p">
						<c:choose>
							<c:when test="${p == paging.nowPage }">
								<ul>
									<li><a>${p }</a></li>
								</ul>
							</c:when>
							<c:when test="${p != paging.nowPage }">
								<ul>
									<li><a
										href="listpage.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">${p }</a>
									<li>
								</ul>

							</c:when>
						</c:choose>
					</c:forEach>
					<c:if test="${paging.endPage != paging.lastPage}">
						<ul>
							<li><a
								href="listpage.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&gt;</a></li>
						</ul>
					</c:if>
				</c:if>
			</div>
		</div>
		<br>

			<div class="search_form">
				<table style="width: 100%">
					<tr>
						<td colspan="4" style="text-align: center; height: 50px">
							<form
								action="listpage.do?&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}"
								method="get" onsubmit="return searchCheck(this)">
								<p style="font-size: 15px; margin-bottom: 1%; font-weight: bold;">장르검색</p>
								<input type="radio" name="col" value="fan">판타지 
								<input type="radio" name="col" value="rom">로맨스 
									<input type="radio" name="col" value="cri">범죄 
									<input type="radio" name="col" value="act">액션
									 <input type="radio" name="col" value="doc">다큐 
									<input type="radio" name="col" value="com">코미디 
									<input type="radio" name="col" value="ani">애니메이션 
									<input type="radio" name="col" value="mus">뮤지컬
									 <input type="radio" name="col" value="thr">공포
									 <br> 
									 <p style="font-size: 15px; margin-bottom: 1%;margin-top: 1%; font-weight: bold;">일반검색</p>
									<input type="radio" name="col" value="msub">제목 
									<input type="radio" name="col" value="msum">줄거리 
									<input type="radio" name="col" value="msub_msum">제목+줄거리
									<br><br>
									<input type="text" name="word"> <input type="submit"value="검색">
							</form>
						</td>
					</tr>
				</table>
			</div>
	</div>
</section>







<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>