<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!-- 본문시작 -->
<link rel="stylesheet" href="../css/style.css">
<section>
<div class="container" style="text-align: center;">
 <h2 style="text-align: center; margin-top: 5%; margin-bottom: 5%;">상영-영화</h2>
<form action="screen-movie.do" name="frm" method="post">
<div class="search_form">
영화: <input type="text" value="" name="mno" id="movie" size="10" style="margin-bottom: 5px;">

<input type="submit" value="선택완료" onclick='apply("m");'>
</div>
<br><br>
<div style="display: block; text-align: center;">
<div style="width: 90%; margin-left:5%; text-align: center;">   
    <table class="type06">
    
        <tr>
            <th>선택</th>
            <th>번호</th>
            <th>영화제목</th>
            <th>연령제한</th>
            <th>마감일</th> 
       </tr>
       <c:forEach var="dto" items="${list }">
       <tr>
            <td><input type="checkbox" onClick="writeBox('${dto.mno -1}')" name="m" value="${dto.mno }"></td>
            <td>${dto.mno }</td>
            <td><a href="../movie/read.do?mno=${dto.mno}&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">${dto.msub}</a></td>
            <td>${dto.mgrade }</td>
            <td>${dto.mend }</td>
      </tr>
      </c:forEach>
    </table>
        </div>
        </div>
    </form>
	<br><br>
	 <div class="paging_jr">
	<div class="paging">

		<c:if test="${empty col }">		
		<c:if test="${paging.startPage != 1 }">
			<ul class=""> 
			<li><a href="screen-movie.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a></li>
			</ul>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
				<ul>
				<li><a>${p }</a></li>
				</ul>
				</c:when>
				<c:when test="${p != paging.nowPage }">
			 	<ul>
   				<li><a href="screen-movie.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a></li>  
   				</ul>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
			<li><a href="screen-movie.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a></li>
		</ul>	 
		</c:if>
		</c:if>
			
		<c:if test="${!empty col}">
		<c:if test="${paging.startPage != 1 }">
		<ul>
		<li><a href="paging.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&lt;</a></li>
			
			</ul>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
				<ul>
				<li><a>${p }</a></li>
				</ul>
				</c:when>
				<c:when test="${p != paging.nowPage }">
				<ul>
				<li><a href="paging.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">${p }</a><li>
				</ul>	
					
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
		<li><a href="paging.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&gt;</a></li>
			</ul>
		</c:if>
		</c:if>
	</div>
			</div>
			<br><br><br>
<div class="search_form">
		<table style="width: 100%">
        <tr>
        <td colspan="4"
            style="text-align: center; height: 50px">
        <form action="paging.do?&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}" method="get" onsubmit="return searchCheck(this)">
        <input type="radio" name="col" value="msub" checked>영화제목
        <input type="radio" name="col" value="mgrade" >연령제한
        <input type="text" name="word">
        <input type="submit" value="검색">
        </form> 
        </td>
        </tr>
</table>
</div>
</div>
</section>
<script>
function searchCheck(f) {
    var word=f.word.value;
    word=word.trim();
    if(word.length==0){
        alert("검색어를 입력하세요");
        return false;//서버전송불가
    }//if end
    return true; // 서버로 전송
}//searchCheck() end

function writeBox(ck){
    var theform = document.frm;
    var quote = theform.mno.value
    var quote1 = theform.m[ck].value;
    var quechk = theform.m[ck].checked;
    
    if(quechk == true){
        if(quote == ""){
            theform.mno.value = quote1
        }else{
            theform.mno.value = quote + ',' + quote1
        }
    } else{
    	if(quote1 == quote.substr(quote.length-1,quote.length)){
    		theform.mno.value = quote.replace(','+quote1,"");
    	}else{
    		theform.mno.value = quote.replace(quote1+',',"");
    	}
    }
}

function apply(m){
    opener.document.getElementById("sm").value = document.getElementById("movie").value;
    window.close();
}//apply() end

</script>

<!-- 본문 끝 -->           
