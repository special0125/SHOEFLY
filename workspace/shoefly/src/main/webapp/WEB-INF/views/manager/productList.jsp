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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/manager/productList.css">
	<title>SHOEFLY : 관리자</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="/shoefly/resources/asset/js/manager/productList.js" charset="utf-8"></script>
</head>
<body>
	<jsp:include page="../common/manager_header.jsp"></jsp:include>
	<section>
		<h2>
			<a href="productListPage.do">PRODUCT</a>
		</h2>
		<form id="f" class="search" >
			<div class="search_container">
				<input type="hidden" id="page" name="page" value="1"/>
				<input type="hidden" id="order" name="order" value="PRODUCT_NO"/>
				<input type="hidden" id="isDesc" name="isDesc" value="ASC"/>
				<input type="text" name="productNo" placeholder="상품번호"/><br>
				<input type="text" name="productName" placeholder="상품명"/><br>
				<div class="checkbox_container">
					<label><input type="checkbox" name="brand" value="Nike"/><span>Nike</span></label>
					<label><input type="checkbox" name="brand" value="Jordan"/><span>Jordan</span></label>
					<label><input type="checkbox" name="brand" value="New Balance"/><span>New Balance</span></label>
					<label><input type="checkbox" name="brand" value="Adidas"/><span>Adidas</span></label>
				</div>
			</div>
			<input type="button" value="검색" onclick="search();"/>
		</form>
		<div class="list_container">
			<table>
				<thead>
					<tr>
						<th onclick="setOrder('PRODUCT_NO');">
							<span>상품번호</span>
							<span id="PRODUCT_NO_STATE" class="up-down"></span>
						</th>
						<th onclick="setOrder('PRODUCT_NAME');">
							<span>상품명</span>
							<span id="PRODUCT_NAME_STATE" class="up-down"></span>
						</th>
						<th onclick="setOrder('BRAND');">
							<span>브랜드</span>
							<span id="BRAND_STATE" class="up-down"></span>
						</th>
						<th onclick="setOrder('PRICE');">
							<span>정가</span>
							<span id="PRICE_STATE" class="up-down"></span>
						</th>
						<th>이미지</th>
						<th onclick="setOrder('STATE');">
							<span>상태</span>
							<span id="STATE_STATE" class="up-down"></span>
						</th>
					</tr>
				</thead>
				<tbody id="tbody"></tbody>
				<tfoot id="tfoot"></tfoot>
			</table>
		</div>
	</section>
</body>
</html>