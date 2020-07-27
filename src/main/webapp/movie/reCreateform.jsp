<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"  %>
<!-- 본문시작 -->
 <form action="reCreate.do">
			<span class="star-input">
				<span class="input">
			    	<input type="radio" name="rstar" value="1.0" id="p1">
			    	<label for="p1">1</label>
			    	<input type="radio" name="rstar" value="2.0" id="p2">
			    	<label for="p2">2</label>
			    	<input type="radio" name="rstar" value="3.0" id="p3">
			    	<label for="p3">3</label>
			    	<input type="radio" name="rstar" value="4.0" id="p4">
			    	<label for="p4">4</label>
			    	<input type="radio" name="rstar" value="5.0" id="p5">
			    	<label for="p5">5</label>
		  		</span>
	  		</span>
	  		<textarea id="rcom" name="rcom"></textarea>
	  		<input type="text" name="uid" style="width: 40%;" value="<c:out value='${uid }'/>" readonly="readonly">	
	  		<input type="hidden" name="mno" style="width: 40%;" value="<c:out value='${mno }'/>" readonly="readonly">
	  		
	  		<input type="submit" value="등록하기">
  		
  		</form>	
 <!-- 본문끝 -->
<%@ include file="../footer.jsp"  %>