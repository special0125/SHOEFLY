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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/member/changePw.css">
	<title>비밀번호 변경하기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_updatePw();
			fn_pwCheck();
			fn_pwCheck2();
			fn_return();
		})
	
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
					$('#pw').focus();
					pwPass = false; 
				}
			});
		}
		
		// 비밀번호2 검사 (비밀번호 = 비밀번호2)
		var pwPass2 = false;
		function fn_pwCheck2(){
			$('#pw2').keyup(function(){
				if($('#pw').val() != $('#pw2').val()) {
					$('#pw2').addClass('redBorder');
					$('#pw2_result').text('비밀번호를 확인해주세요.').css('color', 'red');
					pwPass2 = false;
				} else {
					$('#pw2_result').text('비밀번호가 일치합니다.').css('color', 'blue');
					pwPass2 = true;
				}
			});
		}
		
		// 비밀번호 변경
		function fn_updatePw(){
			$('#updatePw_btn').click(function(){
				if(!pwPass || !pwPass2) {
					alert('비밀번호를 확인하세요.');
					return false;
				} else {
					$('#f').attr('action', 'updatePw.do');
					$('#f').submit();
				}
			});
		}	
		
		// 돌아가기
		function fn_return(){
			$('#return_btn').click(function(){
				history.back();
			})
		}
	</script>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
		<div class="container">
			<div class="pageTitle">
				<span>비밀번호 변경</span>
			</div>
			
			<div class="change">
				<c:if test="${findMember == null}">
					<span>일치하는 회원정보가 없습니다.</span><br><br>
					<a href="joinPage.do">회원가입</a>
				</c:if>
				<c:if test="${findMember != null}">
					<form id="f" method="post">
						<input type="hidden" name="memberNo" value="${findMember.memberNo}">
						<div class="update_title">
			     			<label for="pw">새 비밀번호(NEW PASSWORD)</label>						
						</div>
						<div class="updatePw_info">
			     			<input type="password" id="pw" name="pw">
		     			</div>
		     			<div class="result_box">		     			
			     			<span id="pw_result"></span>
		     			</div>
		     			<div class="update_title">
			     			<label for="pw2">비밀번호 재확인(REPEAT PASSWORD)</label>		     			
		     			</div>
		     			<div class="updatePw_info">
			     			<input type="password" id="pw2">
		     			</div>
		     			<div class="result_box">
			     			<span id="pw2_result"></span>		     			
		     			</div>
		     			<div class="btn-box">
							<input type="button" value="비밀번호변경" id="updatePw_btn" class="btn changePw_btn btn_primary">
							<input type="button" value="돌아가기" id="return_btn" class="btn returnPage_btn btn_primary">
						</div>
					</form>
				</c:if>	
			</div>
		</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>