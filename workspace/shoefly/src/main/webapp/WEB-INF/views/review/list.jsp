<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="/shoefly/resources/asset/css/common/header.css">
	<link rel="stylesheet" href="/shoefly/resources/asset/css/common/footer.css">
	<link rel="stylesheet" href="/shoefly/resources/asset/css/review/list.css">
	<title>리뷰 리스트</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_search();
			fn_loginCheck();
			
		
		});
		
		function fn_enter() {
			fn_search();	
		}	
		
		// 검색 이벤트
		function fn_search(){
			$('#search_btn').click(function(){
				if ( $('#column').val() == '' || $('#query').val() == '') {
					location.href = 'listPage.do';
				}else {
					$('#f').attr('action', 'listPage.do' );
					$('#f').submit();
				}
			});
		}
		
		// 로그인 상태 체크
		function fn_loginCheck() {
			$('#insertReview_btn').click(function(){
				if (${loginMember.memberId == null}) {
					location.href='/shoefly/member/loginPage.do';
				}else {
					location.href = 'insertPage.do';
				}
			});
		}
		
		function fn_enter() {
			if(event.keyCode == 13) {
				if ( $('#column').val() == '' || $('#query').val() == '') {
					location.href = 'listPage.do';
				}else {
					$('#f').attr('action', 'listPage.do' );
					$('#f').submit();
				}
					event.preventDefault();
			}
		}
		
		
	</script>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
		<div class="reviewList_container">
			<div class="search_box">
				<form method="get" autocomplete="off" id='f'>
					<select class="selectBox" id="column" name="column">
						<option class="option" value="">선택</option>
						<option class="option" value="MEMBER_ID" <c:if test="${column == 'MEMBER_ID'}">selected="selected"</c:if>>작성자</option>
						<option class="option" value="TITLE" <c:if test="${column == 'TITLE'}">selected="selected"</c:if>>제목</option>
					</select>
					<input class="queryBox" type="text" id="query" name="query" onkeypress="fn_enter();">
					<input class="button" type="button" value="검색" id="search_btn">
					<input class="button" type="button" value="후기 작성" id="insertReview_btn">  <!-- onclick="location.href='/shoefly/member/loginPage.do'" -->
				</form>
			</div>
			<table class="reviewList_table">
				<thead class="thead">
					<tr>
						<th>No</th>
						<th>작성자</th>
						<th>제목</th>
						<th>제품명</th>
						<th>조회수</th>
						<th>작성일</th>
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
							<td><a href="select.do?reviewNo=${review.reviewNo}&page=1">${review.title}</a></td>
							<td>${review.productName}</td>
							<td>${review.hit}</td>
							<td>${review.postdate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="paging">
				${paging}
			</div>
		</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>