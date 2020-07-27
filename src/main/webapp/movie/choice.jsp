<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<!-- 본문시작 -->

 <script>
 $(function(){ 
	 var mno="";
		$('li').click(function() {
			$('li').removeClass()
			$(this).addClass('on')
			$('.zza').hide();
		})
	    $('li').click(function(){
	    	var mno = $(this).attr('id');
	    	
			//alert(mno)
			$.get("choice2.do"		//요청명령어
				,"mno="+mno //전달값
				,responseProc 			//callback함수
			);//get() end		
			
	    });//click end
	    
	    
		function responseProc(result){
			//result
			//->서버가 응답해준 메세지를 받는 변수
			$("#sdto").empty();
			$("#sdto").html(result);
			$("#sdto").show();
		}//responseProc() end
		

	    $(document).on('click', 'dt', function(){ 
	    	$('dt').removeClass()
			$(this).addClass('dtclasson')
	    	var arr=[];
	    	var mno = $('#ee').val();
	    	
	    	var scrdate = $(this).attr('id');
	    	//alert(mno)
	    	arr.push(mno);
	    	arr.push(scrdate);

			jQuery.ajaxSettings.traditional = true; 
			$.get("choice3.do"//요청명령어
					, {
				        'main' : arr
				    }
					,responseProc2 			//callback함수
			);//get() end		
	    });
	    
		function responseProc2(result){
			//result
			//->서버가 응답해준 메세지를 받는 변수
	 		$("#sdto2").empty();
			$("#sdto2").html(result);
			$("#sdto2").show();
		}//responseProc() end

	 });

 
 $(document).on('click', '#goseat', function(){  
	 if(confirm("좌석선택창으로 이동 하시겠습니까?")) {
         $(this).parent().click();
     } else {
         return false;
     }

 
 });


</script>

<input type="hidden" value="${scrno }">
<div class="container">
<br>
<div class="nevi">
			<h5>
				<a href="../main/index.do">HOME</a>><a
					href="list.do">MOVIEPICK</a>><a href="javascript:history.back()">상세보기</a>><a href="#">예매하기</a>
			</h5>
		</div>
		<br><br><br>
		
		<h2 class="login_form_h2">예매하기</h2>
		
		<br><br>
		
<div class="movie_total">
<div>
<div class="movie_select">

<div class="movie_select_top" style="">
<h4 class="movie_select_top_h4">영화</h4>
</div>

 <div class="choice_scroll">
		<ul>
			<c:forEach var="mdto" items="${mdto}"> 
		
				<li class="aa" id="${mdto.mno }">

					<div class="movie_select_jr">
						<c:if test="${mdto.mgrade == 0 }">
					<span class="ic_grade gr_all">
					전체
					</span>
					</c:if>
					<c:if test="${mdto.mgrade == 12 }">
					<span class="ic_grade gr_12">
					15
					</span>
					</c:if>
					<c:if test="${mdto.mgrade == 15 }">
					<span class="ic_grade gr_15">
					15
					</span>
					</c:if>
					<c:if test="${mdto.mgrade == 19 }">
					<span class="ic_grade gr_19">
					청불
					</span>
					</c:if>
					 <a class="" href="#"  onclick="return false;" title="${mdto.msub }" style="vertical-align:middle; " >${mdto.msub }</a>
					 </div>
					 
			
				</li>
			
		
			</c:forEach>
			
				
			
		</ul>
		

	</div>
			

</div>

<div class="movie_select" >
<div class="movie_select_top">
<h4 class="movie_select_top_h4">날짜</h4>

</div>
	<div class="choice_scroll2" id="sdto">
		
		
			</div>
			
			
	
	</div>
	<div class="movie_select">
<div class="movie_select_top">
<h4 class="movie_select_top_h4">시간</h4>

</div>
		<div class="choice_scroll" id="sdto2">
		
		
			</div>
	
	</div>
	</div>
	</div>
	</div>

 <!-- 본문끝 -->
<%@ include file="../footer.jsp"  %>