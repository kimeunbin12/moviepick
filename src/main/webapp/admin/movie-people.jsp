<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!-- 본문시작 -->
<link rel="stylesheet" href="../css/style.css">
<section>

<div class="container">

<h2 style="text-align: center; margin-top: 5%; margin-bottom: 5%;">무비픽-인물정보</h2>
<form method="post" action="movie-people.do" name="form1">
<div style="display: block; text-align: center;">
<div style="width: 90%; margin-left:5%; text-align: center;"> 
    <table class="type06">
        <tr>
            <th>번호</th>
            <th>이름</th>
            <th>국적</th>
            <th>생년월일</th>
            <th>성별</th>
            <th>이미지</th>      
        </tr>
        <c:forEach var="dto" items="${list }">
            <tr>
                
               
                <td>${dto.pno }</td>
                <td><a href='../people/read.do?pno=${dto.pno}&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}'>${dto.pname}</a></td>
                <td>${dto.country }</td>
                <td>${dto.pbirth }</td>
                <td>${dto.pgender }</td>
                <td><img src="../images/people/${dto.ppic }"  style="min-width:100px; min-height: 
						150px; max-width:100px; max-height: 150px;"></td>
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
			<li><a href="movie-people.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a></li>
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
   				<li><a href="movie-people.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a></li>  
   				</ul>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
			<li><a href="movie-people.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a></li>
		</ul>	 
		</c:if>
		</c:if>
			
		<c:if test="${!empty col}">
		<c:if test="${paging.startPage != 1 }">
		<ul>
		<li><a href="paged.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&lt;</a></li>
			
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
				<li><a href="paged.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">${p }</a><li>
				</ul>	
					
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
		<ul>
		<li><a href="paged.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&col=${col}&word=${word}">&gt;</a></li>
			</ul>
		</c:if>
		</c:if>
	</div>
			</div>
			<br><br><br>
<div class="search_form">
<table style="width: 100%;">
        <tr>
        <td colspan="4"
            style="text-align: center; height: 50px">
        <form action="paged.do?&nowPage=${paging.nowPage}&cntPerPage=${paging.cntPerPage}" method="get"
              onsubmit="return searchCheck(this)">
        <input type="radio" name="col" value="pname" checked>이름
        <input type="radio" name="col" value="country" >국가
        <input type="radio" name="col" value="pgender">성별
        <input type="radio" name="col" value="pname_country">이름+국가
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

//배열 선언
//var arrayParam = new Array();
//each로 loop를 돌면서 checkbox의 check된 값을 가져와 담아준다.
//$("input:checkbox[name=select]:checked").each(function(){
//    arrayParam.push($(this).val());
//});

function writeBox(checkvalue){
   var theform = document.form1;
   var quote = theform.box.value
   var quote1 = theform.mdir[checkvalue].value;
   var quechk = theform.mdir[checkvalue].checked;

   if(quechk == true){
      theform.box.value = quote + quote
   } else{
      theform.box.value = quote.replace(quote1,"");
   }
}



/*
$(document).ready(function() {
    // 체크 되어 있는 값 추출
    $("#mdir").click(function() {
    	var test = new Array();
        $("input[name=select]:checked").each(function() {
            var test = $(this).val();
            for(var i=0; i<test.length; i++){
            	alert("확인"+test);
            }
            
            console.log(test);
        });
    });

    checkbox();

});


function checkbox() {
    var cnt = $("input:checkbox").size();
    console.log("checkboxSize=" + cnt);
    $("input[name=box]:checkbox").each(function() {
        var checkboxValue = $(this).val();
        console.log("checkboxValue=" + checkboxValue);
    });
    console.log("----------------------------------------------");

    $("#checkboxArea").children().each(function() {
        var checkboxValue = $(this).children(":checkbox").val();
        var text = $(this).children().eq(1).text();
        console.log(text + "=" + checkboxValue);
    });
}
*/

function apply(mdir, mact){
    //중복이 확인된 uid를 부모창(opener)에 적용
    opener.document.Form1.mdir.value=mdir;
    opener.document.Form1.mact.value=mact;
    window.close();
}//apply() end

</script>
