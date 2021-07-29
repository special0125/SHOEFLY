<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty loginMember || loginMember.manager != 1}">
<script>
	location.href="/shoefly/";
</script>
</c:if>
<header>
	<a href="/shoefly/manager/"><img alt="SHOEFLY" src="/shoefly/resources/asset/image/logo_white.png"></a>
	<a href="/shoefly/">메인 페이지로 이동</a>
</header>
<nav>
	<div class="nav-container">
		<ul>
			<li><a href="memberListPage.do"><i class="fas fa-users"></i><span>회원관리</span></a></li>
			<li><a href="productListPage.do"><i class="fas fa-gift"></i><span>상품관리</span></a></li>
			<li><a href="productBuyListPage.do"><i class="fas fa-shopping-cart"></i><span>구입신청</span></a></li>
			<li><a href="productSellListPage.do"><i class="fas fa-money-bill"></i><span>판매신청</span></a></li>
		</ul>
		<ul>
			<li><a href="reviewListPage.do"><i class="fas fa-sticky-note"></i><span>REVIEW</span></a></li>	
		</ul>
		<ul>
			<li><a href="noticeListPage.do"><i class="fas fa-exclamation-circle"></i><span>공지사항</span></a></li>
			<li><a href="faqListPage.do"><i class="fas fa-question-circle"></i><span>FAQ</span></a></li>
		</ul>
	</div>
</nav>