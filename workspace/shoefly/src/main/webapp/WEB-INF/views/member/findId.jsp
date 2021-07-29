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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/member/findId.css">
	<title>아이디 찾기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_emailCheck();
			fn_findId();
		})
		
		// 이메일 검사 (정규식검사/인증코드발송)
		var emailPass = false;
		function fn_emailCheck(){
			$('#verify_code_btn').click(function(){
				var regEMAIL = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
				if($('#email').val() == '') {
					$('#email').addClass('redBorder');
					$('#email_result').text('이메일을 입력하세요.').css('color', 'red');
					$('#email').focus();
					emailPass = false;
				} 
				if(!regEMAIL.test($('#email').val())) {
					$('#email').addClass('redBorder');
					$('#email_result').text('이메일을 형식에 맞춰 입력해주세요.').css('color', 'red');
					$('#email').focus();
					emailPass =  false;
				} else {
					$('#email').removeClass('redBorder');
					emailPass = true;
				}
				if(emailPass == true){
					fn_email_orCheck();
				}
			})			
		}
		
		// 이메일 중복검사
		function fn_email_orCheck(){
			$.ajax({
				url: 'emailOrCheck.do',
				type: 'get',
				data: 'email=' + $('#email').val(),
				dataType: 'json',
				success: function(result){
					if(result.count == 1) {
						fn_email_verifyCodeSend();
					} else {
						$('#email_result').text('입력하신 이메일 정보가 없습니다.').css('color', 'red');
					}
				},
				error: function(xhr, textStatus, errorThrown) {
					
				}
			});
		}
		
		// 이메일 인증코드발송
		function fn_email_verifyCodeSend(){
			$.ajax({
				url: 'verifyCode.do',
				type: 'get',
				data: 'email=' + $('#email').val(),
				dataType: 'json',
				success: function(result){
					alert('인증코드가 발송되었습니다.');
					fn_verify(result.authCode);
				},
				error: function(xhr, textStatus, errorThorwn) {
					
				}				
			});	
		}
		
		// 인증코드 검사
		var authPass = false;
		function fn_verify(authCode) {
			$('#verify_btn').click(function(){
				if(authCode == $('#verify_code').val()) {
					alert('인증에 성공했습니다.');
					authPass = true;
				} else {
					alert('인증에 실패했습니다.');
					authPass = false;
				}
			});
		}
		
		// 아이디 찾기
		function fn_findId(){
			$('#findId_btn').click(function(){
				if ($('#email').val() == '') {
					alert('이메일을 입력하세요.');
					$('#email').focus();
					return false;
				} else if(!authPass) {
					alert('이메일 인증을 받아주세요.');
					return false; 
				} else {
					$('#f').attr('action', 'findId.do');
					$('#f').submit();
				}
			});
		}
	</script>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
		<div class="container">	
			<div class="pageTitle">
				<span>아이디찾기</span>
			</div>
			
			<div class="find">
				<form id="f" method="post">
					<div class="find_title">
				     	<label for="email">이메일(E-MAIL)</label><br>			
					</div>
					<div class="findId_info">
				     	<input type="text" id="email" name="email">
				     	<input type="button" value="인증코드발송" id="verify_code_btn" class="btn_verify btn_primary"><br>
			     	</div>
			     	<div class="result_box">
				    	<span id="email_result"></span>
				    </div>	
				     <div class="find_title">
				    	<label for="memberId">인증코드(CODE)</label><br>		     
				     </div>
			     	<div class="findId_info">
				     	<input type="text" id="verify_code" name="verify_code">
				     	<input type="button" value="확인" id="verify_btn" class="btn_verify btn_primary"><br>
			     	</div>	
			     	<input type="button" value="아이디찾기" id="findId_btn" class="find_btn btn_primary">
				</form>	
			</div>
		</div>	
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>