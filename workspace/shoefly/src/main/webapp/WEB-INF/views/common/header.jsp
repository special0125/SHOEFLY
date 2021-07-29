<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<header>
	<div class="logo_container">
		<a href="/shoefly/">
			<img alt="SHOEFLY" src="/shoefly/resources/asset/image/logo.png">
		</a>
	</div>
	<div class="login_container">
		<c:if test="${empty loginMember}">
			<a href="/shoefly/member/loginPage.do">LOGIN</a>
			<a href="/shoefly/member/joinPage.do">JOIN</a>
		</c:if>
		<c:if test="${not empty loginMember}">
			<span>Welcome! '${loginMember.memberId}'</span>
			<a href="/shoefly/member/logout.do">LOGOUT</a>
			<a href="/shoefly/member/myPage.do">MYPAGE</a>
		</c:if>
	</div>
	<div class="nav_container">
		<a href="/shoefly/product/productListPage.do">PRODUCT</a>
		<a href="/shoefly/review/listPage.do">REVIEW</a>
		<a href="/shoefly/notice/noticeListPage.do">NOTICE</a>
		<a href="/shoefly/notice/faqListPage.do">FAQ</a>
	</div>
</header>