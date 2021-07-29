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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/manager/reviewList.css">
	<title>SHOEFLY : 관리자</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="/shoefly/resources/asset/js/manager/reviewList.js" charset="utf-8"></script>
</head>
<body>
	<jsp:include page="../common/manager_header.jsp"></jsp:include>
	<section>
		<h2>
			<a href="reviewListPage.do">REVIEW</a>
		</h2>
		<form method="get" class="search">
			<select id="column" name="column">
				<option value="ALL">제목/내용</option>
				<option value="TITLE">제목</option>
				<option value="CONTENT">내용</option>
				<option value="POSTDATE">날짜</option>
			</select>
			<span id="default_search">
				<input type="text" id="query" name="query" placeholder="검색내용입력"/>
				<button><i class="fas fa-search"></i></button>
			</span>
			<span id="date_search" style="display: none;">
				<input type="date" id="startDate" name="startDate" value="2021-01-01" disabled/>
				<input type="date" id="endDate" name="endDate" value="${nowDate}" disabled/>
				<button><i class="fas fa-search"></i></button>
			</span>
		</form>
		<table class="content">
			<thead>
				<tr>
					<th>No</th>
					<th>작성자</th>
					<th>제목</th>
					<th>제품명</th>
					<th>조회수</th>
					<th>작성일</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty review}">
					<tr>
						<td colspan="6">검색결과가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach items="${review}" var="review" varStatus="status">
					<tr>
						<td>${review.reviewNo}</td>
						<td>${review.memberId}</td>
						<td><div><a href="/shoefly/review/select.do?reviewNo=${review.reviewNo}&page=1">${review.title}</a></div></td>
						<td><div>${review.productName}</div></td>
						<td>${review.hit}</td>
						<td>${review.postdate}</td>
						<c:if test="${review.state == 0}">
							<td><input type="button" class="delete_btn" value="삭제" data-no="${review.reviewNo}"></td>
						</c:if>
						<c:if test="${review.state == -1}">
							<td><input type="button" class="restore_btn" value="복구" data-no="${review.reviewNo}"></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			${paging}
		</div>
	</section>
</body>
</html>