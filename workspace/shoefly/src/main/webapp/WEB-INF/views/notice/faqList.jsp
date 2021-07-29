<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" referrerpolicy="no-referrer" />
	<link rel="stylesheet" href="/shoefly/resources/asset/css/common/header.css">
	<link rel="stylesheet" href="/shoefly/resources/asset/css/common/footer.css">
	<link rel="stylesheet" href="/shoefly/resources/asset/css/notice/faqList.css">
	<title>리스트</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="/shoefly/resources/asset/js/notice/faqList.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
		<h2 class="title">
			<a href="faqListPage.do">자주묻는 질문</a>
		</h2>
		<div class="search">
			<form method="get" autocomplete="off">
				<button><i class="fas fa-search"></i></button>
				<input type="text" name="query"/>
			</form>
		</div>
		<table class="content">
			<thead>
				<tr>
					<th>NO</th>
					<th>제목</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty list}">
				<tr>
					<td colspan="3">검색결과가 없습니다.</td>
				</tr>
				</c:if>
				<c:forEach items="${list}" var="notice" varStatus="status">
				<tr>
					<td>${page.beginRecord + status.index}</td>
					<td>
						<a href="javascript:fn_table_toggle(${status.index});">${notice.question}</a>
						<div class="content-${status.index} table-content"style="display:none">${notice.answer}</div>
					</td>
					<td>${notice.postdate}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<c:if test="${not empty list}">
				${paging}
			</c:if>
		</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>