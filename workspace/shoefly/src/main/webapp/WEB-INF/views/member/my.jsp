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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/member/my.css">
	
	<title>마이페이지</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<c:if test="${loginMember.manager == 1}">
	<script>
		location.href="/shoefly/manager/";
	</script>
	</c:if>
	<c:if test="${empty loginMember}">
	<script>
		alert('로그인을 해주세요.');
		location.href="loginPage.do";
	</script>
	</c:if>
	<script>
		$(document).ready(function(){
			fn_delete_member();
			fn_updatePw();
			fn_updateName();
		})
		
		function fn_delete_member(){
			$('#delete_member_btn').click(function(){
				if(confirm('정말 탈퇴하시겠습니까?')) {
					location.href = 'deleteMember.do';
				}
			})
		}
		
		function fn_updatePw(){
			$('#update_pw_btn').click(function(){
				location.href = 'updatePwPage.do';
			})
		}
		
		function fn_updateName(){
			$('#update_name_btn').click(function(){
				alert('이름이 변경되었습니다.');
				$('#f').attr('action', 'updateName.do');
				$('#f').submit();
			})
		}
	</script>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
		<div class="container">
		<div class="logo">
			<img onclick="location.href='/shoefly/'" alt="SHOEFLY" src="/shoefly/resources/asset/image/logo.png">
			<span>마이페이지</span>
		</div>	
		<div class="myPage">	
		<form id="f" method="post">
			<div class="my_title">
	     		<label for="memberId">아이디(ID)</label>
	     	</div>
			<div class="my_info">
	     		<input type="text" id="memberId" name="memberId" value="${loginMember.memberId}" disabled>
     			<input type="button" value="회원탈퇴" id="delete_member_btn" class="btn_primary">
     		</div>
     		<div class="my_title">
	     		<label for="pw">비밀번호(PASSWORD)</label><br>
			</div>
     		<div class="my_info">
	     		<input type="password" id="pw" name="pw" value="****" disabled>
     			<input type="button" value="변경" id="update_pw_btn" class="btn_primary">
     		</div>
     		<div class="my_title">
	     		<label for="name">이름(NAME)</label><br>
			</div>
     		<div class="my_info">
	     		<input type="text" id="name" name="name" value="${loginMember.name}">
     			<input type="button" value="변경" id="update_name_btn" class="btn_primary">
     		</div>
     		<div class="my_title">
	     		<label for="pw">이메일(E-MAIL)</label><br>
			</div>
     		<div class="my_info">
	     		<input type="text" id="email" name="email" value="${loginMember.email}" disabled>
     		</div>
		</form>
		</div>
		<div class=caption>
   		<a href="addressPage.do">주소록</a>
   		<a href="productBuyPage.do">구매내역</a>
   		<a href="productSellPage.do">판매내역</a>
   		</div>
   		</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>