<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<script type="text/javascript">

	function gobook(frm){
			var adu = $("#adu option:selected").attr('name');
			var stu = $("#stu option:selected").attr('name');
			var sen = $("#sen option:selected").attr('name');
			var kid = $("#kid option:selected").attr('name');
			var sum = parseInt(adu,0)+parseInt(stu,0)+parseInt(sen,0)+parseInt(kid,0);
			var cnt = $("input:checkbox[class=bloc]:checked").length
			var a = new Array();
			//var a = new Array($("input:checkbox[id=seat]:checked").val());
			$("input:checkbox[id=seat]:checked").each(function(){
				a.push($(this).val());
				});
			
			//for(var i=0; i<cnt; i++) {
			//	alert("선택된좌석:"+a[i]);
			//}
			//alert("cnt"+cnt);
			//0명일때 막아라!!!!!!!!!!!!!!!!!!!!!!!!!!
			if(sum==0){
				alert("인원수를 선택해 주세요");
			} else if(sum>=6) {
				alert("최대 인원 선택은 5명까지 입니다.");
			} else if(cnt>=6){
				alert("최대 좌석 선택은 5석까지 입니다.")
			} else{
				if( cnt != sum){
					alert("인원수와 좌석수를 일치시켜주세요");
				}else{
					//alert("예매완료 했습니다.");
					//submit 되게	
					
					frm.submit();				
				}
				//alert("합"+sum);
			}
		}
	
	
</script>
<!-- 본문시작 -->
<div class="container">
<br>
<div class="nevi">
			<h5>
				<a href="../main/index.do">HOME</a>><a href="javascript:history.back()">TIKETING</a>><a href="javascript:history.back()">예매하기</a>><a href="javascript:history.back()">좌석선택</a>
			</h5>
		</div>
		<br>	<br>	<br>
		<h2 class="login_form_h2">좌석 선택</h2>
		<br><br>
	<form method="post" action="seat.do">
	<div style="text-align: center;">
<h3>최대 선택인원은 5명 입니다.</h3>
<br><br>
<input type="hidden" name="uid" value="${uid }">

<input type="hidden" name="scrno" value="${param.scrno}">
<div class="btype_select">
성인
<select id="adu" name="btype">
	<option name="0" value="0">0</option>
	<option name="1" value="1">1</option>
	<option name="2" value="2">2</option>
	<option name="3" value="3">3</option>
	<option name="4" value="4">4</option>
	<option name="5" value="5">5</option>
</select>
&nbsp&nbsp&nbsp청소년
<select id="stu" name="btype">
	<option name="0" value="0">0</option>
	<option name="1" value="1">1</option>
	<option name="2" value="2">2</option>
	<option name="3" value="3">3</option>
	<option name="4" value="4">4</option>
	<option name="5" value="5">5</option>
</select>
&nbsp&nbsp&nbsp경로우대
<select id="sen" name="btype">
	<option name="0" value="0">0</option>
	<option name="1" value="1">1</option>
	<option name="2" value="2">2</option>
	<option name="3" value="3">3</option>
	<option name="4" value="4">4</option>
	<option name="5" value="5">5</option>
</select>
&nbsp&nbsp&nbsp어린이(만8세 미만)
<select id="kid" name="btype">
	<option name="0" value="0">0</option>
	<option name="1" value="1">1</option>
	<option name="2" value="2">2</option>
	<option name="3" value="3">3</option>
	<option name="4" value="4">4</option>
	<option name="5" value="5">5</option>
</select>
</div>
</div>
<br><br>
<div class="bloc_jr" style="text-align: center;">

${bloc }
</div>
<div style="text-align: center;">
<div class="seatsubmit">
<input type="button" value="예매하기" id="button" onclick="gobook(this.form)">
</div>
</div>
</form>
</div>

 <!-- 본문끝 -->
<%@ include file="../footer.jsp"  %>