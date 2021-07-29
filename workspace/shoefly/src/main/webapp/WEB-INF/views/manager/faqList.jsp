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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/manager/faqList.css">
	<title>SHOEFLY : 관리자</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="/shoefly/resources/asset/js/manager/faqList.js" charset="utf-8"></script>
</head>
<body>
	<jsp:include page="../common/manager_header.jsp"></jsp:include>
	<input type="hidden" id="paramColumn" value="${param.column}"/>
	<input type="hidden" id="paramQuery" value="${param.query}"/>
	<input type="hidden" id="paramStartDate" value="${param.startDate}"/>
	<input type="hidden" id="paramEndDate" value="${param.endDate}"/>
	<section>
		<h2 class="title">
			<a href="faqListPage.do">FAQ</a>
		</h2>
		<div class="search">
			<form method="get" autocomplete="off">
				<select id="column" name="column">
					<option value="ALL">제목/내용</option>
					<option value="QUESTION">제목</option>
					<option value="ANSWER">내용</option>
					<option value="POSTDATE">날짜</option>
				</select>
				<span id="default_search">
					<input type="text" id="query" name="query" placeholder="검색내용입력"/>
					<button><i class="fas fa-search"></i></button>
				</span>
				<span id="date_search" style="display: none;">
					<input type="date" id="startDate" name="startDate" value="2021-01-01"/>
					<input type="date" id="endDate" name="endDate" value="${nowDate}"/>
					<button><i class="fas fa-search"></i></button>
				</span>
			</form>
		</div>
		<div class="write">
			<input type="button" value="새글 추가" onclick="fn_show()"/>
		</div>
		<table class="content">
			<thead>
				<tr>
					<th>번호</th>
					<th>질문</th>
					<th>작성일자</th>
					<th>수정</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty list}">
				<tr>
					<td colspan="4">찾으시는 FAQ가 없습니다.</td>
				</tr>
				</c:if>
				<c:forEach items="${list}" var="faq">
				<tr>
					<td>${faq.faqNo}</td>
					<td><div>${faq.question}</div></td>
					<td>${faq.postdate}</td>
					<td>
						<input type="button" value="EDIT" onclick="fn_show(${faq.faqNo});"/>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="paging">${paging}</div>
	</section>
</body>
</html>