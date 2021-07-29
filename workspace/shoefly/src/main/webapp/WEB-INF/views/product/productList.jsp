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
	<title>리스트</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
	$(document).ready(function(){
		selectCondition();
		
		//전체클릭시 모두 선택, 모두해제
		$('#AllBrand').click(function(){
			if($("#AllBrand").prop("checked")) { 
				$(".brand").prop("checked",true);
			} else if(!$("#AllBrand").prop("checked")) {
				$(".brand").prop("checked",false);
			} 
		});
		//개별체크클릭시 전체체크, 전체해지
		$('.brand').click(function(){
			if($("input[name='brand']:checked").length == 4){
	            $("#AllBrand").prop("checked", true);
	        }else{
	            $("#AllBrand").prop("checked", false);
	        }
		});
	});
	
	//검색버튼으로 모든 선택사항 조회
	function selectCondition(){
		var reg = /[0-9]+$/;
		$('#search_btn').click(function(){
			if (!reg.test($('.input_price').val()) && $('.input_price').val() != '') {
				alert('가격은 숫자만 입력 가능합니다.');
				return false;
			}
			$('#f').attr('action', 'selectCondition.do');
			$('#f').submit();
		});
	}
	</script>
	<style>
		*{
			box-sizing: border-box;
		}
		.productList_container{
			width: 1080px;
			display: flex;
			margin: auto;
		}
		.selectCondition{
			width: 300px;
			height: 430px;
			padding: 15px;
			float: left;
			margin-top: 15px;
			border-radius: 10px;
			border: 1px solid #D5C2EE;
		}
		.conditionListBox{
			width: 100%;
			margin-bottom: 15px;
			padding: 5px;
		}
		.search_btn{
			width: 100%;
			height: 40px;
			border: 1px solid #D5C2EE;			
			background-color: #D5C2EE;
			border-radius: 5px;
			background-color: #D5C2EE;
			color: white;
			font-size: 16px;
			outline: none;
			border:none;
			font-weight: bolder;
			text-shadow: 2px 2px 2px grey;
		}
		.search_btn:hover{
			cursor: pointer;
			text-shadow: 2px 2px 2px #FFBEBE;
		}
		.productName, .input_price{
			width: 100%;
		 	height: 40px;
		 	font-size: 14px;
		 	border-radius: 5px;
		 	border: 1px solid #D5C2EE;
		 	padding: 10px;
		 	outline: none;
		 }
		 .input_price {
		 	width: 107px;
		 }
		 .brand_box {
		 	margin-bottom: 20px;
		 }
		 input[type="checkbox"] {
			display: none;
			color: #D5C2EE;
		 }
		 input[type="checkbox"] + label {
	 		cursor: pointer;
	   	 }
		 input[type="checkbox"] + label:before {
			content:"";
			display:inline-block;
			width:16px;
			height:16px;
			line-height:16px;
			border:1px solid #cbcbcb;
			vertical-align:middle;
			color: #D5C2EE;
		 }
		 input[type="checkbox"]:checked + label:before {
			content:"\f00c";/*폰트어썸 유니코드*/
		    font-family:"Font Awesome 5 free"; /*폰트어썸 아이콘 사용*/
		    font-weight:900;/*폰트어썸 설정*/
		    color:#fff;
		    background-color:#D5C2EE;
		    border-color:#D5C2EE;
		    font-size:13px;
		    text-align:center;
		    color: white;
		 }
		 .text {
		 	font-weight: 700;
		 	line-height: auto;
		 	
		 }
		.productList_box {
			width: 875px;
		}
		.productList {
			width: 100%;
			height: 610px;
			padding: 15px;
			overflow: hidden;
		    position: relative;
		    margin: 0 auto;
		}
		.conditionTitle{
			font-weight: bolder;
			color: #D5C2EE;
			margin-bottom: 5px;
		}
		.product_item {
			position: relative;
		    float: left;
		    margin: 20px 0;
		    padding: 0 12px;
		    width: 25%;
		}
		.product_item, .item_inner {
			display: block;
    		background-color: #fff;
    		border-radius: 12px;
    		text-decoration: none;
		}
		.product {
			overflow: hidden;
		    position: relative;
		    padding-top: 100%;
		    border-radius: 12px;
		}
		.product_img {
		    width: 81.5%;
		    height: auto;
		    top: 50%;
		    left: 50%;
			position: absolute;
		    transform: translate(-50%,-50%);
			border-radius: 12px;
		}
		.product_name {
			padding-left: 5px;
			font-size: 14px;
			height: 45px;
			color: #000;
		}
		.product_price {
   			padding-left: 5px;
			height: 20px;
			font-size: 12px;
   			color: #000;
		}
		section .paging {
		margin: 30px auto 60px;
		text-align: center;
		font-family: '푸른전남체R', sans-serif;
		}
		section .paging .now-page {
			color: #FFBEBE;
			font-weight: bold;	
		}
		section .paging a {
			color: #909090;
			text-decoration: none;
		}
		section .paging a[href] {
			color: #000;
			text-decoration: none;
		}
		section .paging a[href]:hover {
			text-decoration: underline;
		}
	</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
		<div class="productList_container">
			<div class="selectCondition">
				<form id="f">
				<div class="conditionTitle">상품명</div>	
				<div class="conditionListBox">
					<input type="text" name="productName" id="productName" class="productName" placeholder="상품명">
				</div>
				
				<div class="conditionListBox">
					<div class="conditionTitle">브랜드</div>
					<table>
						<tr>
							<td>
								<input type="checkbox" id="AllBrand" class="brand">
								<label for="AllBrand"> 전체브랜드</label>
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="brand" id="jordan" value="Jordan" class="brand">
								<label for="jordan"> 조던</label>&emsp;&emsp;
							</td>
							<td>
								<input type="checkbox" name="brand" id="nike" value="Nike" class="brand">
								<label for="nike"> 나이키</label>
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="brand" id="adidas" value="Adidas" class="brand">
								<label for="adidas"> 아디다스</label>
							</td>
							<td>
								<input type="checkbox" name="brand" id="newbalance" value="New Balance" class="brand">
					<label for="newbalance"> 뉴발란스</label>
							</td>
						</tr>
					</table>
					
				</div>
				
				<div class="conditionListBox">		
					<div class="conditionTitle">사이즈</div>
					<table>
						<tr>
							<td>
								<input type="checkbox" name="size" id="240" value="240">
								<label for="240"> 240</label>					
								<input type="checkbox" name="size" id="250" value="250">
								<label for="250"> 250</label>					
								<input type="checkbox" name="size" id="260" value="260">
								<label for="260"> 260</label>
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="size" id="270" value="270">
								<label for="270"> 270</label>
								<input type="checkbox" name="size" id="280" value="280">
								<label for="280"> 280</label>
								<input type="checkbox" name="size" id="290" value="290">
								<label for="290"> 290</label>
							</td>
						</tr>
					</table>
				</div>
				
				<div class="conditionListBox">	
					<div class="conditionTitle">가격범위</div>		
					<input type="text" name="minPrice" id="minPrice" class="input_price" placeholder="최소">&nbsp;&#126;
					<input type="text" name="maxPrice" id="maxPrice" class="input_price" placeholder="최대">
				</div>
							
				<input type="button" value="LOOK UP" id="search_btn" class="search_btn">
			
				</form>
			</div>
			<br>
			<div class="productList_box">
				<div class="productList">
					<c:if test="${empty list}">
						<h3 class="empty_text">준비된 상품이 없습니다.</h3>
					</c:if>
					<c:forEach var="product" items="${list}">
						<c:if test="${not empty list}">
							<div class="product_item">
								<a class="item_inner" href="viewProductPage.do?productNo=${product.productNo}">
									<div class="product" style="background-color: #FFD3B0;">
										<img class="product_img" alt="${product.image}" src="/shoefly/resources/archive/product/${product.image}">
									</div>
									<div class="info_box">
										<p class="product_name">${product.productName}</p>
										<p class="product_price">발매가${product.price}원</p>
									</div>
								</a>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<c:if test="${not empty list}">
					<div class="paging">${paging}</div>
				</c:if>
			</div>
		</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>