<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" referrerpolicy="no-referrer" />
	<link rel="stylesheet" href="/shoefly/resources/asset/css/common/manager_header.css">
	<link rel="stylesheet" href="/shoefly/resources/asset/css/manager/productInfo.css">
	<title>SHOEFLY : 관리자</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="/shoefly/resources/asset/js/manager/productInfo.js" charset="utf-8"></script>
</head>
<body>
	<jsp:include page="../common/manager_header.jsp"></jsp:include>
	<section>
		<div class="left-container">
			<img alt="${product.image}" src="/shoefly/resources/archive/product/${product.image}">
		</div>
		<div class="right-container">
			<h3>
				<span>${product.productNo}</span>
			</h3>
			<ul>
				<li>
					<span>상품번호</span>
					<input type="text" id="productNo" value="${product.productNo}" readOnly/>
				</li>
				<li>
					<span>상품이름</span>
					<input type="text" value="${product.productName}" readOnly/>
				</li>
				<li>
					<span>브랜드</span>
					<input type="text" value="${product.brand}" readOnly/>
				</li>
				<li>
					<span>정가</span>
					<input type="text" value="${product.price}" readOnly/>
				</li>
				<li>
					<span>사이즈</span>
					<span>${sizes}</span>
				</li>
				<li>
					<span>상태</span> 
					<c:if test="${product.state == 0}">
					<span style="color:#539bf5;">거래중</span>
					<input type="button" value="정지" onclick="setState(-1);"/>
					</c:if>
					<c:if test="${product.state == -1}">
					<span style="color:red;">정지</span>
					<input type="button" value="거래전환" onclick="setState(0);"/>
					</c:if>
				</li>
			</ul>
		</div>
	</section>
</body>
</html>