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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/manager/productSellList.css">
	<title>SHOEFLY : 관리자</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="/shoefly/resources/asset/js/manager/productSellList.js" charset="utf-8"></script>
</head>
<body>
	<jsp:include page="../common/manager_header.jsp"></jsp:include>
	<section>
		<h2 class="title_container">
			<a href="productSellListPage.do">판매신청</a>
		</h2>
		<div class="search_container">
			<select id="option" onchange="change_option();">
				<option value="memberId">아이디</option>
				<option value="productName">상품명</option>
			</select>
			<input type="text" id="query" oninput="change_query();">
		</div>
		<div class="list_container">
			<h3>신청목록</h3>
			<table>
				<thead>
					<tr>
						<th>주문번호</th>
						<th>아이디</th>
						<th>상품명</th>
						<th>사이즈</th>
						<th>가격</th>
						<th>주소</th>
						<th>구입신청일</th>
						<th>거래확정일</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="tbody0" class="tbody">
				</tbody>
				<tfoot id="tfoot0">
				</tfoot>
			</table>
		</div>
		<div class="list_container">
			<h3>상품수령</h3>
			<table>
				<thead>
					<tr>
						<th>주문번호</th>
						<th>아이디</th>
						<th>상품명</th>
						<th>사이즈</th>
						<th>가격</th>
						<th>주소</th>
						<th>구입신청일</th>
						<th>거래확정일</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="tbody1" class="tbody">
				</tbody>
				<tfoot id="tfoot1">
				</tfoot>
			</table>
		</div>
		<div class="list_container">
			<h3>판매결정</h3>
			<table>
				<thead>
					<tr>
						<th>주문번호</th>
						<th>아이디</th>
						<th>상품명</th>
						<th>사이즈</th>
						<th>가격</th>
						<th>주소</th>
						<th>구입신청일</th>
						<th>거래확정일</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="tbody2" class="tbody">
				</tbody>
				<tfoot id="tfoot2">
				</tfoot>
			</table>
		</div>
		<div class="list_container">
			<h3>결산완료</h3>
			<table>
				<thead>
					<tr>
						<th>주문번호</th>
						<th>아이디</th>
						<th>상품명</th>
						<th>사이즈</th>
						<th>가격</th>
						<th>주소</th>
						<th>구입신청일</th>
						<th>거래확정일</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="tbody3" class="tbody">
				</tbody>
				<tfoot id="tfoot3">
				</tfoot>
			</table>
		</div>
		<div class="list_container">
			<h3>취소/삭제</h3>
			<table>
				<thead>
					<tr>
						<th>주문번호</th>
						<th>아이디</th>
						<th>상품명</th>
						<th>사이즈</th>
						<th>가격</th>
						<th>주소</th>
						<th>구입신청일</th>
						<th>거래확정일</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="tbody-1" class="tbody">
				</tbody>
				<tfoot id="tfoot-1">
				</tfoot>
			</table>
		</div>
	</section>
</body>
</html>