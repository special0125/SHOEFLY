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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/manager/memberList.css">
	<title>SHOEFLY : 관리자</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="/shoefly/resources/asset/js/manager/memberList.js" charset="utf-8"></script>
</head>
<body>
	<jsp:include page="../common/manager_header.jsp"></jsp:include>
	<section>
		<h2><a href="memberListPage.do">회원관리</a></h2>
		<div class="search">
			<select id="column" onchange="column_change();">
				<option value="MEMBER_NO">회원번호</option>
				<option value="MEMBER_ID">아이디</option>
				<option value="NAME">이름</option>
				<option value="EMAIL">이메일</option>
				<option value="REGDATE">가입일</option>
			</select>
			<div class="default_search">
				<input type="text" id="query"/>
				<button type="button" onclick="search_btn()"><i class="fas fa-search"></i></button>
			</div>
			<div class="regdate_search" style="display: none;">
				<input type="date" id="startDate"/>
				<input type="date" id="endDate"/>
				<button type="button" onclick="search_btn()"><i class="fas fa-search"></i></button>
			</div>
		</div>
		<div class="member_table">
			<table>
				<thead>
					<tr>
						<th onclick="setOrder('MEMBER_NO');">
							<span>회원번호</span>
							<span id="MEMBER_NO_STATE" class="up-down"></span>
						</th>
						<th onclick="setOrder('MEMBER_ID');">
							<span>아이디</span>
							<span id="MEMBER_ID_STATE" class="up-down"></span>
						</th>
						<th onclick="setOrder('NAME');">
							<span>이름</span>
							<span id="NAME_STATE" class="up-down"></span>
						</th>
						<th onclick="setOrder('EMAIL');">
							<span>이메일</span>
							<span id="EMAIL_STATE" class="up-down"></span>
						</th>
						<th onclick="setOrder('REGDATE');">
							<span>가입일</span>
							<span id="REGDATE_STATE" class="up-down"></span>
						</th>
						<th onclick="setOrder('STATE');">
							<span>가입/탈퇴</span>
							<span id="STATE_STATE" class="up-down"></span>
						</th>
					</tr>
				</thead>
				<tbody id="member_tbody"></tbody>
			</table>
		</div>
		<div id="member_paging"></div>
	</section>
</body>
</html>