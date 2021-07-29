<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" referrerpolicy="no-referrer" />
	<link rel="stylesheet" href="/shoefly/resources/asset/css/common/manager_header.css">
	<link rel="stylesheet" href="/shoefly/resources/asset/css/manager/noticeInfo.css">
	<title>${notice.title}</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="/shoefly/resources/asset/js/manager/noticeInfo.js" charset="utf-8"></script>
</head>
<body>
	<form id="f" method="post">
		<c:if test="${empty notice}">
		<h3>공지사항 작성</h3>
		</c:if>
		<c:if test="${not empty notice}">
		<h3>공지사항 수정</h3>
		</c:if>
		<div class="title">
			<input type="text" hidden="hidden"/>
			<input type="hidden" id="noticeNo" name="noticeNo" value="${notice.noticeNo}"/>
			<input type="text" id="title" name="title" value="${notice.title}" placeholder="제목" maxlength="50"/>
		</div>
		<div class="content">
			<textarea id="content" name="content">${notice.content}</textarea>
		</div>
		<div class="preview" style="display: none;">
			<div id="preview"></div>
		</div>
		<div class="button_area">
			<input type="button" value="미리보기" onclick="preview_btn();"/>
			<c:if test="${empty notice}">
			<input type="button" value="등록하기" onclick="edit_btn();"/>
			</c:if>
			<c:if test="${not empty notice}">
			<input type="button" value="수정하기" onclick="edit_btn();"/>
			<input type="button" value="삭제하기" onclick="del_btn();"/>
			</c:if>
		</div>
	</form>
</body>
</html>