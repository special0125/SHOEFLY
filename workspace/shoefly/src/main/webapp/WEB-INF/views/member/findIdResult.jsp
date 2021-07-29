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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/member/findIdResult.css">
	<title>아이디 찾기 결과</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
		<div class="container">
			<div class="pageTitle">
				<span>아이디찾기 결과</span>
			</div>
			
			<c:if test="${findMember == null}">
				${message}<br><br>
				<a href="joinPage.do">회원가입</a>
			</c:if>
			<c:if test="${findMember != null}">
				<div>
					<span>아이디 : ${findMember.memberId}</span><br>
					<span>가입일 : ${findMember.regdate}</span>
				</div>
				<div class="btn-box">
					<input type="button" value="로그인" onclick="location.href='loginPage.do'" class="btn_type">&nbsp;
					<input type="button" value="비밀번호찾기" onclick="location.href='findPwPage.do'" class="btn_type">
				</div>
			</c:if>
		</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>