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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/review/insertReviewPage.css">
	<title>리뷰 작성</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_selectProduct();
			fn_insertReview();
		});
		
		// placeholder 컬러 바꾸기 함수 (class, color)
		function change_placeholder_color(target_class, color_choice) {
		    $("body").append("<style>" + target_class + "::placeholder{color:" +  color_choice + "}</style>")
		}
		
		// alert창 디자인
		function fn_sweetAlert() {
			Swal.fire('제목을 입력하세요.');
		}
		
		// 상품리스트 가져오기
		function fn_selectProduct() {
			$.ajax({
				url: 'selectProduct.do',
				type: 'post',
				dataType: 'json',
				success: function(resultMap) {
					$.each(resultMap.productName, function(i, product) {
						$('<option value="' + product.productName + '">').text(product.productName)
						.appendTo('#productList');
					});
				}
			});
		}
		
		// 리뷰 등록 이벤트
		function fn_insertReview() {
			$('#insert_btn').click(function(){
				if( $('#title').val() == '' ) {
					alert('제목을 입력하세요.');
					$('#title').focus();
					return false;
				}else if ( $('#productList').val() == '' ) {
					alert('상품명을 선택하세요.');
					return false;
				}
				else if ( $('#content'). val() == '' ) {
					alert('내용을 입력하세요.');
					$('#content').focus();
					return false;
				}
				
				if(confirm('작성 하시겠습니까?')) {
					$('#f').submit();
				}
			});
		}
		
		// 파일 등록시 이미지 파일 확인
		function fileCheck(obj) {
			
			var file_kind = obj.value.lastIndexOf('.');
			var file_name = obj.value.substring(file_kind+1,obj.length);
			var file_type = file_name.toLowerCase();

			var check_file_type=['jpg','png','jpeg'];

			if(check_file_type.indexOf(file_type)==-1){
				alert('이미지 파일만 선택할 수 있습니다.');
			  	var parent_Obj=obj.parentNode
			  	var node=parent_Obj.replaceChild(obj.cloneNode(true),obj);
			  	$('#file').val('');
			  	return false;
			}else {
				if (obj.files && obj.files[0]) {
					var reader = new FileReader();
				    reader.onload = function (e) {
				    	$('.img').attr('src', e.target.result)
				        .width(400)
				        .height(500);
				        $('.content').width(669);
					}
				reader.readAsDataURL(obj.files[0]);
				}
			 }
		}
                                
        
		
	</script>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
		<div class="insert_container">
			<form  id="f" action="insert.do" method="post" enctype="multipart/form-data">
				<div class="title_line">
					<input type="hidden" name="loginId" value="${loginMember.memberId}">
					<input type="text" class="title" id="title" name="title" placeholder="제목을 입력하세요.">
					<select class="productList" name="productName" id="productList" >
						<option value="">상품명 선택</option>
					</select>
					<div class="filebox">
						<label class="file_label" for="file">사진 첨부</label>
						<input class="file" type="file" id="file" name="file" accept=".jpg, .png, .jpeg" onchange="fileCheck(this)"><br><br>
					</div>
				</div>
				<div class="content_box">
					<img class="img" src="">
					<textarea class="content" id="content" name="content"  placeholder="내용을 입력하세요."></textarea>
				</div>
				<div class="button_box">
					<input class="button" type="button" value="등록" id="insert_btn">
					<input class="button" type="button" value="취소" id="cancel_btn"onclick="location.href='listPage.do'">
				</div>
			</form>
		</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>