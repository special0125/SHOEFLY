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
	<title>상품상세페이지</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" referrerpolicy="no-referrer" />
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
	$(document).ready(function(){
		sizeSelect();
		applicationInfoPopUp();
		buyApplication();
		sellApplication();
		buyNow();
		sellNow();
	});
	
	function sizeSelect(){
		$('#productSize').change(function(){
			var option = $("#productSize").val();
			//사이즈 선택 안했을 때
			if(option == ""){
				$('#fastDeal').css("display", "none");
				$('#buyNow').html('즉시구매가<br>(사이즈를 선택하세요.)');
				$('#sellNow').html('즉시판매가<br>(사이즈를 선택하세요.)');
				$('#buyNow').attr('disabled', 'false');
				$('#sellNow').attr('disabled', 'false');
			}
			//사이즈가 선택 했을 때
			else if(option != ""){
				var productSize = $(this).find(":selected").val();
				var productName = $('#productName').val();
				//제품명과 사이즈를 통해 즉시구매가, 즉시판매가 조회
				//조회 후 등록된 상품이 없을 경우 즉시구매가, 즉시판매가는 클릭 불가
				//조회 후 즉시 거래 해도 되고 그 아래에 빠른거래를 위한 신청서 작성 버튼 띄우기
				$.ajax({
					url:'selectPriceBySize.do',
					type: 'get',
					data : 'productSize=' + productSize + '&productName=' + productName,
					dataType: 'json',
					success : function(resultMap){
						if (resultMap.buyPrice != null){
							$('#buyNow').html('즉시구매가<br>' + resultMap.buyPrice + "원");
							$('#buyNow').removeAttr('disabled');
							$('#fastDeal').show();
						}if (resultMap.sellPrice != null){
							$('#sellNow').html('즉시판매가<br>' + resultMap.sellPrice + "원");
							$('#sellNow').removeAttr('disabled');
							$('#fastDeal').show();
						}if (resultMap.buyPrice == null){
							$('#buyNow').html('즉시 구매 상품이<br>존재하지않습니다.');
							$('#buyNow').attr('disabled', 'true');
							$('#fastDeal').show();
						}if (resultMap.sellPrice == null){
							$('#sellNow').html('즉시 판매 상품이<br>존재하지않습니다.');
							$('#sellNow').attr('disabled', 'true');
							$('#fastDeal').show();
						}
					}
				});	//end ajax
				
			}
		});
	}
	
	//구매신청 판매신청 팝업
	function applicationInfoPopUp(){
		$('.faseDealInfo').click(function(){
			window.open('applicationInfoPopUp.do', '빠른거래하기 소개', 'width=800, height=520, left=500, top=200');
		});	
	}
	
	//구매신청하기 클릭
	function buyApplication(){
		$('#buyApplication').click(function(){
			if($('#loginMember').val() == ""){
				if(confirm('로그인이 필요한 기능입니다. 로그인창으로 이동할까요?')){
					location.href='/shoefly/member/loginPage.do';
					return false;
				}else{
					return false;
				}
			} else{
				$('#f').serialize();
				$('#f').attr('action','buyApplication.do?');		
			}
		});
	}
		
	//구매신청하기 클릭
	function sellApplication(){
		$('#sellApplication').click(function(){
			if($('#loginMember').val() == ""){
				if(confirm('로그인이 필요한 기능입니다. 로그인창으로 이동할까요?')){
					location.href='/shoefly/member/loginPage.do';
					return false;
				}else{
					return false;
				}
			}else{
				$('#f').serialize();
				$('#f').attr('action','sellApplication.do?');
			}
		});
	}
	
 	//즉시구매가 클릭
	function buyNow(){
		$('#buyNow').click(function(){
			if($('#productSize').val() == ""){
				alert('사이즈를 선택하세요.');
				return false;
			} else if($('#loginMember').val() == ""){
				if(confirm('로그인이 필요한 기능입니다. 로그인창으로 이동할까요?')){
					location.href='/shoefly/member/loginPage.do';
					return false;
				}else{
					return false;
				}
			} else{
				$('#f').serialize();
				$('#f').attr('action', 'buyNow.do');
			}
		});	
	}
		
	//즉시판매가 클릭
	function sellNow(){
		$('#sellNow').click(function(){
			if($('#productSize').val() == ""){
				alert('사이즈를 선택하세요.');
				return false;
			} else if($('#loginMember').val() == ""){
				if(confirm('로그인이 필요한 기능입니다. 로그인창으로 이동할까요?')){
					location.href='/shoefly/member/loginPage.do';
					return false;
				}else{
					return false;
				}
			}else{
				$('#f').serialize();
				$('#f').attr('action', 'sellNow.do');
			}
		});
	}

	</script>
	<style>
		*{
			box-sizing: border-box;
		}
		section{
			width: 1080px;
			margin: auto;
		}
		.showList{
			display: inline-block;
			margin-top: 20px;
			text-align: center;
			font-weight: border;
			width: 100px;
			height: 35px;
			line-height: 35px;
			text-decoration: none;
			color: white;
			font-weight: border;
			border-radius: 3px;
			background-color: #D5C2EE;
		}
		.container{
			margin: auto;
			width: 900px;
			min-height : 700px;
		}
		.product_container{
			width: 100%;
			display: flex;
			margin: auto;
		}
		.imgBox{
			width: 50%;
			margin-left: 10px;
			margin-top: 10px;
		}
		.imgBox img{
			width: 100%;
		}
		.textBox{
			width: 50%;
			margin-top: 50px;
		}
		.productName{
			font-size: 25px;
			font-weight: bolder;
		}
		.productName, p, .fastDeal{
			width: 100%;
			text-align: center;
		}
		.applicationBtn{
			display: inline;
		}
		.btn{
			width: 150px;
			height: 50px;
			border: none;
			border-radius: 5px;
			padding: 10px;
		}
		.btn:hover {
			cursor: pointer;
		}
		.productSizeList{
			width: 150px;
			height: 30px;
			padding-left: 5px;
			font-size: 14px;
			outline: none;
			border: 1px solid #FFBEBE;
			border-radius: 3px;
		}
		#buyNow{
			background-color: #D5C2EE;
		}
		#sellNow{
			background-color: #FFBEBE;
		}
		.fastBuySell{
			margin: 10px 0;
		}
		.fastDealTitle{
			display: inline-block;
			width: 100px;
			margin-top: 20px;
			text-align: center;
		}
		@keyframes color_ani {
            25% { color: rgb(241, 171, 185); font-size: 14px;}
            50% { color: rgb(235, 146, 169); font-size: 16px;}
            75% { color: rgb(226, 112, 146); font-size: 18px;}
            100% { color: rgb(223, 93, 134); font-size: 16px;}
        }
        @keyframes color_ani1 {
            25% { font-size: 14px;}
            50% { font-size: 16px;}
            75% { font-size: 18px;}
            100% { font-size: 16px;}
        }
		.faseDealInfo{
			width: 100%;
			height: 20px;
			font-size: 14px;
			cursor: pointer;
			color: #FFBEBE;
			animation-name: color_ani;
            animation-duration: 1s;
            animation-iteration-count: infinite;
		}
		.faseDealInfo1{
			width: 100%;
			height: 20px;
			font-size: 14px;
			color: white;
			animation-name: color_ani1;
            animation-duration: 1s;
            animation-iteration-count: infinite;
		}
		#buyApplication{
			border: 1px solid #D5C2EE;
			background-clip: white;
		}
		#sellApplication{
			border: 1px solid #FFBEBE;
			background-clip: white;
		}
		.productInfo{
			margin-top: 30px;
			text-align: center;
		}
		table{
			width: 300px;
			margin-left: 75px;
			text-align: left;
			border-collapse: collapse;
		}
		td{
			padding: 5px;
			border-bottom: 1px solid #FFBEBE;
		}
		td:nth-of-type(1) {
			text-align: center;
			width: 50%;
		}
	</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
	<a href="productListPage.do" class="showList"><i class="fas fa-bars"></i>&nbsp; 상품목록</a>
	<div class="container">
		<form id="f" method="post">
		<input type="hidden" id="loginMember" value="${loginMember}">
		<input type="hidden" id="productNo" value="${productproductNo}">
			<div class="product_container">
				<c:if test="${empty product}">제품이 없습니다.</c:if>
				<c:if test="${not empty product}">
					<div class="imgBox">
						<img alt="${product.image}"src="/shoefly/resources/archive/product/${product.image}" /><br>
					</div>
					<div class="textBox">
						<div class="productName">${product.productName}</div>
						<input type="hidden" name ="productName" id="productName" value="${product.productName}">
						<p>
							사이즈&nbsp;&nbsp;<select name="productSize" id="productSize" class="productSizeList">
								<option value="">:::::::: SIZE ::::::::</option>
								<option value="240">240mm</option>
								<option value="250">250mm</option>
								<option value="260">260mm</option>
								<option value="270">270mm</option>
								<option value="280">280mm</option>
								<option value="290">290mm</option>
							</select><br>
							<br>
							<button id="buyNow" class="btn">
								즉시구매가<br>(사이즈를 선택하세요.)
							</button>
							<button id="sellNow" class="btn">
								즉시판매가<br>(사이즈를 선택하세요.)
							</button><br><br>
						</p>
						<div id="fastDeal" class="fastDeal" style="display: none">
							<a class="faseDealInfo1"><i class="far fa-question-circle"></i></a>
							<div class="fastDealTitle">빠른거래하기</div>
							<a class="faseDealInfo"><i class="far fa-question-circle"></i></a>
							<div class="fastBuySell">
								<button id="buyApplication" class="btn">구매신청하기</button>
								<button id="sellApplication" class="btn">판매신청하기</button>
							</div>
						</div>
						<div class="productInfo">
							<table>
								<tr>
									<td>브랜드</td>
									<td>${product.brand}</td>
								</tr>
								<tr>
									<td>일련번호</td>
									<td>${product.productNo}</td>
								</tr>
								<tr>
									<td>발매가</td>
									<td>${product.price}</td>
								</tr>
							</table>
							
						</div>
					</div>
				</c:if>
			</div>
		</form>
		</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>