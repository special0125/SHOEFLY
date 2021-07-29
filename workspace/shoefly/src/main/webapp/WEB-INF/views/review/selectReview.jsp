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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/review/selectReview.css">
	<title>리뷰 보기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_updateReview();
			fn_deleteReview();
			
			fn_insertComment();
			fn_selectCommentList();
			fn_updateCommentPage();
			fn_updateComment();
			fn_deleteComment();
			fn_cancel();
			
			fn_imgExsist();
			
		});	
		// 리뷰 수정 이벤트
		function fn_updateReview() {
			$('#update_btn').click(function(){
				$('#f').attr('action', 'updatePage.do');
				$('#f').submit();
			});
		}
		
		// 리뷰 삭제 이벤트
		function fn_deleteReview() {
			$('#delete_btn').click(function() {
				if (confirm('삭제하시겠습니까?')) {
					$('#f').attr('action', 'delete.do');
					$('#f').submit();
				}
			});
		}
		
		// 댓글 등록 이벤트
		function fn_insertComment() {
			$('#insertComment_btn').click(function(){
				if ( ${loginMember.memberId == null} ) {
					location.href='/shoefly/member/loginPage.do';
				}else if ( $('#context').val() == ''){
					alert('댓글을 입력하세요.');
				}else {
					 $.ajax({
						url: 'insertComment.do',
						type: 'POST',
						data: $('#f2').serialize(),
						dataType: 'json',
						success: function(resultMap){
							location.reload();
						}
					}); 
				}
			});
		} 

		
		// 댓글 리스트, 페이징
		function fn_selectCommentList() {
			var page = 1;
			if (${param.page} != 1) {
				page = ${param.page};
			}
			$.ajax({
				url: 'selectCommentList.do',
				type: 'POST',
				data: 'reviewNo=' + ${review.reviewNo} + '&page=' + page,
				dataType: 'json',
				success: function(resultMap) {
					$('#list').empty();
					if(resultMap.status == 200) {
						$.each(resultMap.commentList, function(i, comment){
							fn_commentList(comment);	
						});
							$('<span>').html(resultMap.paging)
							.appendTo('.paging');
					}else if (resultMap.status == 500) {
						
					}
				}
			}); 
		}
		
		// 댓글 리스트 보여주기
		function fn_commentList(comment) {
		    var form = $('<form>').appendTo('#commentList');
		    if ('${loginMember.memberId}' == comment.memberId) {
		    	var div1 = $('<div class="comment">').html( '<span class="comment_id">' + comment.memberId + '</span><br><input class="comment_content" type="text" value="' + comment.context + '" readonly><input type="hidden" name="reviewCommentNo" value="' + comment.reviewCommentNo + '"> <input type="button" id="updateComment_btn" value="수정"> <input type="button" id="deleteComment_btn" value="삭제">' )
		    	.appendTo(form);
		    	
		    	var div2 = $('<div id="update_comment" style="display:none">').html( '<span class="comment_id">' + comment.memberId + '</span><br> <input type="hidden" name="reviewCommentNo" value="' + comment.reviewCommentNo + '"> <input class="hidden_content" type="text" name="context" value="' + comment.context + '"> <input type="button" id="real_updateComment_btn" value="확인"> <input type="button" id="cancel_btn" value="취소">' )
		    	.appendTo(form);
		    
			}else {
				var span = $('<div class=comment">').html('<span class="comment_id">' + comment.memberId + '</span><br> <input class="comment_content" tyle="text" value="' + comment.context + '" readonly>').appendTo(form);
			}
		}
		
		// 댓글 수정 클릭 이벤트
		function fn_updateCommentPage() {
			$('section').on('click', '#updateComment_btn', function(){
				const len = $(this).parent().next().children('.hidden_content').val().length;
				$(this).parent().hide();
				$(this).parent().next().attr('style', 'false');
				$(this).parent().next().children('.hidden_content').focus();
				$(this).parent().next().children('.hidden_content')[0].setSelectionRange(len, len);
				
			});
		}
		// 댓글 수정 취소 클릭 이벤트
		function fn_cancel() {
			$('section').on('click', '#cancel_btn', function(){
				$(this).parent().hide();				
				$(this).parent().prev().attr('style', 'false');
			})
		}
		
		// 댓글 수정 이벤트
		function fn_updateComment() {
			$('section').on('click', '#real_updateComment_btn', function(){
				var f = $(this).parent().parent();
				$.ajax({
					url: 'updateComment.do',
					type: 'POST',
					data: f.serialize(),
					dataType: 'json',
					success: function(resultMap) {
						location.reload();
					}
				});
			
			});
		}
		
		// 댓글 삭제 이벤트
		function fn_deleteComment() {
			$('section').on('click', '#deleteComment_btn', function(){
				var f = $(this).parent().parent();
				if ( confirm('삭제하시겠습니까?') ) {
					$.ajax({
						url: 'deleteComment.do',
						type: 'POST',
						data: f.serialize(),
						dataType: 'json',
						success: function(resultMap) {
							location.reload();
						}
					});
				}
			});
		}
		
		
		function fn_imgExsist () {
			if (${review.image != null}) {
				$('.content').width(669);
				$('.img').width(400).height(500);
			}
		}
		
		function fn_enter() {
			if(event.keyCode == 13) {
				if ( ${loginMember.memberId == null} ) {
					location.href='/shoefly/member/loginPage.do';
				}else if ( $('#context').val() == ''){
					alert('내용을 입력하세요.');
					$('#context').focus();
				}else {
					 $.ajax({
						url: 'insertComment.do',
						type: 'POST',
						data: $('#f2').serialize(),
						dataType: 'json',
						success: function(resultMap){
							location.reload();
						}
					}); 
				}
				event.preventDefault();
			}
		}
		
		
	</script>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
		<div class="review_container">
			<div class="title_box">
					<h3 class="title_text">${review.title}</h3>
					<span class="memberId">${review.memberId}</span><br>
					<span class="date">${review.postdate}</span>&nbsp;&nbsp;<span class="hit">조회 ${review.hit}</span>
				<div class="button_box">
					<form id="f" method="post">
						<input class="button" type="hidden" name="reviewNo" value="${review.reviewNo}">
						<c:if test="${review.memberId == loginMember.memberId}"> <!--  로그인아이디 == 작성자아이디 (수정,삭제 권한) review.memberId == loginMember.memberid -->
							<input class="button" type="button" value="수정" id="update_btn">
							<input class="button" type="button" value="삭제" id="delete_btn">
						</c:if>
						<input type="button" value="목록" id="list_btn" onclick="history.back()">
					</form>
				</div>
			</div>
			<hr>
			<div class="content_box">
				<img class="img" alt="${review.image}" src="/shoefly/resources/archive/review/${filename}">
				<textarea class="content" readonly>${review.content}</textarea>
			</div>
			<form id="f2">
				<input type="hidden" name="memberId" value="${loginMember.memberId}"> <!-- 로그인아이디 ${loginMember.memberId} -->
				<input type="hidden" name="reviewNo" value="${review.reviewNo}"> <!-- 게시글 번호 -->
				<input class="comment_text" type="text"  id="context" name="context" placeholder="댓글 입력" onkeypress="fn_enter();">  <!-- name= content로 변경 (가능하면) -->
				<input class="comment_btn" type="button" value="댓글 등록" id="insertComment_btn">
			</form>
			<hr>
			<div class="comment_list" id="commentList"></div>
			<div class="paging"></div>
		</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>