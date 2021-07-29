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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/index/index.css">
	<title>제목</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_recommand();
			fn_mostPopular();
		});
		
		
		// mostPopular_list
		function fn_mostPopular() {
			$.ajax({
				url: 'selectMostPopular.do',
				type: 'POST',
				dataType: 'json',
				success: function(resultMap) {
					$.each(resultMap.mostProduct, function(i, product) {
						$('<div class="product_item">')
						.html('<a class="item_inner" href="product/viewProductPage.do?productNo=' + product.productNo + '"> <div class="product" style="background-color: #D5C2EE;"> <img class="product_img" alt="' + product.image +
						'" src="/shoefly/resources/archive/product/' + product.image + '"> </div><div class="info_box"> <p class="product_name">' + product.productName)
						.appendTo('.most_list');
					});
				}
			});
		}
		
		// recommand_list 관리자가 추천하는 제품 index.xml에서 변경 가능(브랜드)
		function fn_recommand() {
			$.ajax({
				url: 'selectRecommand.do',
				type: 'POST',
				dataType: 'json',
				success: function(resultMap) {
					$.each(resultMap.recommandProduct, function(i, product) {
						$('<div class="product_item">')
						.html('<a class="item_inner" href="product/viewProductPage.do?productNo=' + product.productNo + '"> <div class="product" style="background-color: #FFBEBE;"> <img class="product_img" alt="' + product.image +
						'" src="/shoefly/resources/archive/product/' + product.image + '"> </div><div class="info_box"> <p class="product_name">' + product.productName)
						.appendTo('.recommand_list');
						
						
					});
				}
			});
		}
		
	</script>
</head>
<body>
	<jsp:include page="./common/header.jsp"/>
	<section>
		<div class="index_container">
			<div class="product_title">
				<div class="title">Most Popular</div>
				<div class="sub_title">인기 상품</div>
			</div>
			<div class="most_list">
			</div>
			<div class="product_title">
				<div class="title">Recomanded Product</div>
				<div class="sub_title">추천 상품</div>
			</div>
			<div class="recommand_list">
			</div>
		</div>
	</section>
	<jsp:include page="./common/footer.jsp"/>
</body>
</html>