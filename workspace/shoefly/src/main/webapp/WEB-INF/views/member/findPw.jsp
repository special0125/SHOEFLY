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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/member/findPw.css">
	<title>비밀번호 찾기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_idCheck();
			fn_emailCheck();
			fn_findPw();
		})

		// 아이디 존재여부
		var email;
		var idPass = false;
		function fn_idCheck(){
			$('#idCheck_btn').click(function(){
				if($('#memberId').val() == '') {
					$('#memberId').addClass('redBorder');
					$('#memberId_result').text('아이디를 입력해주세요.').css('color', 'red');
					$('#memberId').focus();
					idPass = false;
				}
				$.ajax({
					url: 'idExists.do',
					type: 'get',
					data: 'memberId=' + $('#memberId').val(),
					dataType: 'json',
					success: function(result){
						if(result.email != null) {
							email = result.email;
							$('#memberId').removeClass('redBorder');
							$('#memberId_result').text('확인되었습니다.').css('color', 'blue');							
							idPass = true;
						} else {
							$('#memberId_result').text('없는 아이디입니다.').css('color', 'red');
							$('#memberId').addClass('redBorder');
							$('#memberId').focus();
							idPass = false;
						}
					}
				});
			})
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
				if(emailPass == true) {
					fn_email_verifyCodeSend();
				}
			})			
		}
		
		// 이메일 인증코드발송
		function fn_email_verifyCodeSend(){
			if(email == $('#email').val()) {
				$.ajax({
					url: 'verifyCode.do',
					type: 'get',
					data: 'email=' + $('#email').val(),
					dataType: 'json',
					success: function(result){
						alert('인증코드가 발송되었습니다.');
						$('#email_result').text('');
						fn_verify(result.authCode);
					},
					error: function(xhr, textStatus, errorThorwn) {
						
					}				
				});				
			} else {
				alert('입력하신 이메일이 일치하지 않습니다.');
				$('#email').focus();
			}	
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
					$('#verify_code').focus();
					authPass = false;
				}
			});
		}
		
		// 비밀번호 찾기
		function fn_findPw(){
			$('#findPw_btn').click(function(){
				if($('#memberId').val() == '') {
					alert('아이디를 입력해주세요.');
					$('#memberId').focus();
					return false;
				} else if(!idPass){
					alert('아이디를 확인해주세요.');
					return false;
				} else if($('#email').val() == '') {
					alert('이메일을 입력해주세요.');
					$('#email').focus();
					return false;
				} else if(!authPass){
					alert('이메일 인증을 받아주세요.');
					$('#email').focus();
					return false;
				} else {
					$('#f').attr('action', 'findPw.do');
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
				<span>비밀번호찾기</span>
			</div>
			
			<div class="find">
				<form id="f" method="post">
					<div class="find_title">
				     	<label for="memberId">아이디(ID)</label>					
					</div>
					<div class="findPw_info">
				     	<input type="text" id="memberId" name="memberId">	
				     	<input type="button" value="아이디체크" id="idCheck_btn" class="btn_primary">	
					</div>
					<div class="result_box">
				     	<span id="memberId_result"></span>		
					</div>
					<div class="find_title">
				     	<label for="email">이메일(E-MAIL)</label>
					</div>
					<div class="findPw_info">
				     	<input type="text" id="email" name="email">
				     	<input type="button" value="인증코드발송" id="verify_code_btn" class="btn_primary">
			     	</div>
			     	<div class="result_box">
				    	<span id="email_result"></span>
					</div>
					<div class="find_title">
				    	<label for="memberId">인증코드(CODE)</label>					
					</div>
			     	<div class="findPw_info">
				     	<input type="text" id="verify_code" name="verify_code">
				     	<input type="button" value="인증" id="verify_btn" class="btn_primary">
			     	</div>
			     	<input type="button" value="비밀번호찾기" id="findPw_btn" class="findPw_btn btn_primary">
				</form>		
			</div>		
		</div>		
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>