<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="/shoefly/resources/asset/css/common/header.css">
	<link rel="stylesheet" href="/shoefly/resources/asset/css/common/footer.css">
	<link rel="stylesheet" href="/shoefly/resources/asset/css/member/login.css">
	
	<title>로그인</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>		
		$(document).ready(function(){
			
			let memberId = $('#memberId');
			let pw = $('#pw');
			let loginBtn = $('#login_btn');
				$(loginBtn).on('click', function(){
					if($(memberId).val() == '') {
						$(memberId).next('label').addClass('warning');
						setTimeout(function(){
							$('label').removeClass('warning');
						}, 1500);
					} 
					else if ($(pw).val() == '') {
						$(pw).next('label').addClass('warning');
						setTimeout(function(){
							$('label').removeClass('warning');
						}, 1500);
					}
					
				})		
		})
	</script>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
		<div class="login-form">
			<div class="logo">	
				<a href="/shoefly/">
			       	<img alt="SHOEFLY" src="/shoefly/resources/asset/image/logo.png">
			    </a>
	      	</div>
	    <form action="login.do" id="f" method="post">
	    	<input type="hidden" name="referer" value="<%=request.getHeader("Referer")%>"/>
	       	<div class="login_box">
	       	      <input type="text" id="memberId" name="memberId" autocomplete="off" required>
	          	  <label for="memberId">USER ID</label><br>
	       	</div>
	       	<div class="login_box">
	           	  <input type="password" id="pw" name="pw" autocomplete="off" required>
	           	  <label for="pw">PASSWORD</label><br>
	       	</div>
	       	<div class="btn_box">
	       		<button id="login_btn">Login</button>
	       	</div>	
	    </form>
      	<div class="caption">
	    	<a href="joinPage.do">회원가입</a>
	    	<a href="findIdPage.do">아이디찾기</a>
	    	<a href="findPwPage.do">비밀번호찾기</a>
      	</div>
      	</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>