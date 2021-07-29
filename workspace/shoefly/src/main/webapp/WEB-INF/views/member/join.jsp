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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/member/join.css">
	<title>회원가입</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_idCheck();
			fn_pwCheck();
			fn_pwCheck2();
			fn_emailCheck();
			fn_join();
		})
		
		// 아이디 검사 (정규식검사/중복검사)
		var idPass = false;
		function fn_idCheck(){
			$('#memberId').keyup(function(){
				var regID = /^[a-z0-9]{5,10}$/;
				if(!regID.test($('#memberId').val())) {
					$('#memberId').addClass('redBorder');
					$('#memberId_result').text('아이디는 영어소문자와 숫자를 조합하여 5자 이상 10자이하로 입력해주세요.').css('color', 'red');
					return false;
				}
				$.ajax({
					url: 'idCheck.do',
					type: 'get',
					data: 'memberId=' + $('#memberId').val(),
					dataType: 'json',
					success: function(result){
						if(result.count == 0) {
							$('#memberId').removeClass('redBorder');
							$('#memberId_result').text('사용 가능한 아이디입니다.').css('color', 'blue');
							idPass = true;
						} else {
							$('#memberId').addClass('redBorder');
							$('#memberId_result').text('이미 사용중인 아이디입니다.').css('color', 'red');
							idPass = false;
						}
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				});
			})
		}
		
		// 비밀번호 검사 (정규식검사)
		var pwPass = false;
		function fn_pwCheck(){
			$('#pw').keyup(function(){
				 var regPW = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d!@#$%^&*]{5,15}$/;
			 	if(regPW.test($('#pw').val())){
					$('#pw').removeClass('redBorder');
					$('#pw_result').text('사용가능한 비밀번호입니다.').css('color', 'blue');	
					pwPass = true;
				} else if(!regPW.test($('#pw').val())) {
					$('#pw').addClass('redBorder');
					$('#pw_result').text('필수 영어 1이상, 숫자 1이상, 5글자 이상 15글자 이하(특수문자 !@#$%^&* 사용가능)').css('color', 'red');
					pwPass = false; 
				}
			});
		}
		
		// 비밀번호2 검사 (비밀번호 = 비밀번호2)
		var pwPass2 = false;
		function fn_pwCheck2(){
			$('#pw2').keyup(function(){
				if($('#pw').val() == $('#pw2').val()) {
					$('#pw2').removeClass('redBorder');
					$('#pw2_result').text('비밀번호가 일치합니다.').css('color', 'blue');
					pwPass2 = true;
				} else if($('#pw2').val() == ''){
					$('#pw2').removeClass('redBorder');
					$('#pw2_result').text('');
				} else {
					$('#pw2').addClass('redBorder');
					$('#pw2_result').text('비밀번호를 확인해주세요.').css('color', 'red');
					pwPass2 = false;
				}
			});
		}
		
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
					if(result.count == 0) {
						$('#email').removeClass('redBorder');
						$('#email_result').text('사용 가능한 이메일입니다.').css('color', 'blue');
						fn_email_verifyCodeSend();
					} else {
						$('#email').addClass('redBorder');
						$('#email_result').text('이미 사용중인 이메일입니다.').css('color', 'red');
					}
				},
				error: function(xhr, textStatus, errorThrown) {
					
				}
			});
		}
		
		// 이메일 인증코드발송
		function fn_email_verifyCodeSend(){
			$('#email').attr('readonly',true);
			$('#verify_result').text('잠시만 기다려주세요.').css('color', 'red');
			$.ajax({
				url: 'verifyCode.do',
				type: 'get',
				data: 'email=' + $('#email').val(),
				dataType: 'json',
				success: function(result){
					$('#verify_result').text('이메일에 인증코드가 발송되었습니다.').css('color', 'blue');
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
					$('#verify_code').removeClass('redBorder');
					$('#verify_result').text('인증에 성공했습니다.').css('color','blue');
					authPass = true;
					$('#email').prop('readonly', true);
					$('#verify_code').prop('readonly', true);
					$('#join_btn').attr('disabled',false);
				} else {
					$('#verify_code').addClass('redBorder');
					$('#verify_result').text('인증코드가 올바르지 않습니다.').css('color','red');
					authPass = false;
				}
			});
		}
		
		// 회원가입
		function fn_join(){
			$('#join_btn').click(function(){
				if(!idPass) {
					alert('아이디를 확인하세요');
					return false;
				} else if(!pwPass || !pwPass2) {
					alert('비밀번호를 확인하세요.');
					return false;
				} else if(!authPass) {
					alert('이메일 인증을 받아주세요.');
					return false;
				} else {
					$('#f').attr('action', 'join.do');
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
		<div class="join-form">
			<div class="logo">
		        <img onclick="location.href='/shoefly/'" alt="SHOEFLY" src="/shoefly/resources/asset/image/logo.png">
		     	<span>회원가입</span>
	     	</div>
	     	
	     	<div class="join_box">
	     		<form id="f" method="post">		     			
	     			<div class="join_info">
	     				<div class="join_title">	
		     				<label for="memberId">아이디(ID)</label>
		     			</div>
		     			<div class="info_box">
			     			<input type="text" id="memberId" name="memberId"><br>
		     			</div>
		     			<div class="result_box">
			     			<span id="memberId_result"></span>		     			
		     			</div>
	     			</div>	     			
	     			<div class="join_info">
	     				<div class="join_title">	
		     			<label for="pw">비밀번호(PASSWORD)</label>
		     			</div>	
		     			<div class="info_box">
			     			<input type="password" id="pw" name="pw"><br>
			     		</div>
			     		<div class="result_box">			     		
			     			<span id="pw_result"></span>
			     		</div>	
	     			</div>	     			
	     			<div class="join_info">
	     				<div class="join_title">	
		     			<label for="pw2">비밀번호 재확인(REPEAT PASSWORD)</label>
		     			</div>
		     			<div class="info_box">
			     			<input type="password" id="pw2"><br>
			     		</div>
						<div class="result_box">
			     			<span id="pw2_result"></span>
						</div>
	     			</div>	     			
	     			<div class="join_info">
	     				<div class="join_title">	
		     			<label for="name">이름(NAME)</label>
		     			</div>
		     			<div class="info_box">
		     				<input type="text" id="name" name="name">	
		     			</div>	
		     			<div class="result_box">
			     		</div>	     			
	     			</div>	     			
	     			<div class="join_info">
		     			<div class="join_title">	
			     			<label for="email">이메일(E-MAIL)</label>
			     		</div>
			     		<div class="info_box withBtn">
		     				<input type="text" id="email" name="email">
		     				<input type="button" value="인증코드전송" id="verify_code_btn" class="btn_verify btn_primary"><br>
		     			</div>
						<div class="result_box">
			     			<span id="email_result"></span>
			     		</div>	
	     			</div>	     			
	     			<div class="join_info">
	     				<div class="join_title">	
		     				<label for="verify_code">인증코드(CODE)</label>
		     			</div>
		     			<div class="info_box withBtn">
		     				<input type="text" id="verify_code" name="verify_code">
		     				<input type="button" value="확인" id="verify_btn" class="btn_verify btn_primary">	
		     			</div>
						<div class="result_box">
			     			<span id="verify_result"></span>
			     		</div>			
		     			<br>
	     			</div>
	     			<div class="btn_area">
	     			<input type="button" value="회원가입" id="join_btn" class="btn_type btn_primary" disabled>
	     			</div>
	     		</form>
	     	</div>
		</div>
		</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>