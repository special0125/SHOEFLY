<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" referrerpolicy="no-referrer" />
	<link rel="stylesheet" href="/shoefly/resources/asset/css/common/manager_header.css">
	<link rel="stylesheet" href="/shoefly/resources/asset/css/manager/memberAddress.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="/shoefly/resources/asset/js/manager/memberAddress.js" charset="utf-8"></script>
</head>
<body>
	<h3>'<strong>${param.memberId}</strong>'님의 주소록</h3>
	<input type="hidden" id="memberNo" value="${param.memberNo}"/>
	<input type="hidden" id="empty_list" value="${empty list}"/>
	<div class="search_div">
		<select onchange="select_change(this);">
		<c:forEach items="${list}" var="address">
			<option value="${address.memberAddressNo}">${address.name}</option>
		</c:forEach>
		</select>
	</div>
	<c:forEach items="${list}" var="address">
	<div id="addr_${address.memberAddressNo}" class="addr_div" style="display: none;">
		<span>주소</span>
		<input type="text" value="${address.addr1}" readonly/><br>
		<span>상세주소</span>
		<input type="text" value="${address.addr2}" readonly/><br>
	</div>
	</c:forEach>
	<div class="bottom_div">
		<input type="button" value="이전" onclick="history.back();"/>
	</div>
</body>
</html>