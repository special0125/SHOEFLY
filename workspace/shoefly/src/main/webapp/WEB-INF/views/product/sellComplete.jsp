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
	<title>판매완료</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" referrerpolicy="no-referrer" />
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
	$(document).ready(function(){
		$('#listBtn').click(function(){
			location.href='productListPage.do';
		});
	});
	</script>
	<style>
		*{
			box-sizing: border-box;
		}
		.container{
			margin: auto;
			width: 1080px;
		}
		.infoBox{
			margin-top: 100px;
			width: 100%;
			height: 200px;
			border: 1px solid lightgray;
			text-align: center;
		}
		i{
			margin-top: 50px;
			margin-bottom: 30px;
			font-size: 30px;
		}
		table{
			margin-top: 30px;
			width: 100%;
			border-collapse: collapse;
			border: 1px solid lightgray;
		}
		td{
			padding: 5px;
		}
		.tableName > td:nth-child(1){width: 15%; text-align: center;}
		.tableName > td:nth-child(2){width: 15%; text-align: center;}
		.tableName > td:nth-child(3){width: 70%;}
		tr >td:nth-last-child(2){text-align: center;}
		.listBtn{
			float: right;
			margin-top: 20px;
			border: none;
			padding: 10px;
			color: white;
			border-radius: 3px;
			background-color: #FFBEBE;
		}
		.listBtn:hover {
			cursor: pointer;
			font-weight: border;
		}
	</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
		<div class="container">
			<div class="infoBox">
				<i class="fas fa-check"></i><br>
				상품 판매가 완료되었습니다.
			</div>
			<form id="f" method="post">
				<table border="1">
					<tbody>
						<tr class="tableName">
							<td rowspan="5">상품/결제정보</td>
							<td>판매 고객</td>
							<td>${memberId}</td>
						</tr>
						<tr>
							<td>상품명</td>
							<td>${productName}</td>
						</tr>
						<tr>
							<td>사이즈</td>
							<td>${productSize}mm</td>
						</tr>
						<tr>
							<td>판매금액</td>
							<td>${price}원</td>
						</tr>
						<tr>
							<td>반송지</td>
							<td>${memberAddress.name}&nbsp;/&nbsp;${memberAddress.addr1}&nbsp;
							<c:if test="not empty ${memberAddres.addr2}">
								${memberAddress.addr2}
							</c:if>
							</td>
						</tr>
					</tbody>
				</table>
				<input type="button" value="쇼핑 이어하기" id="listBtn" class="listBtn">
			</form>
		</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>