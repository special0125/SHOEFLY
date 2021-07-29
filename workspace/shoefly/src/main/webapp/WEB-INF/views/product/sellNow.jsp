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
		fn_findAddress();
		goBackPage();
		clickRadio();
		checkbox();
		submitCheck();
	});
	
	//주소api사용
	//주소api사용
	function fn_findAddress() {
		$('#addr_search_btn').click(function(){
			$('#memberAddressNo').val(0);
			goPopup();
		})
	}
	function goPopup(){
		var pop = window.open("jusoPopup.do","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	}
	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		$('#addr1').val(roadAddrPart1);
		$('#addr2').val(addrDetail);
	}
	
	//뒤로가기
	function goBackPage(){
		$('#backBtn').click(function(){
			location.href='viewProductPage.do?productNo=' + $('#productNo').val();
		});
	}
	
	//저장된 주소 radio 클릭시 주소창에 보여주기
	function clickRadio(){
		$('input:radio[name=addressName]').click(function(){
			$('#addrName').val($('input:radio[name=addressName]:checked').val());
			$('#memberAddressNo').val($(this).next().val());
			$('#addr1').val($(this).next().next().val());
			$('#addr2').val($(this).next().next().next().val());
		});
	}
	function checkbox() {
		//전체동의 클릭, 해제
		$('#check0').click(function(){
			if($("#check0").prop("checked")) { 
				$(".check").prop("checked",true);
			} else if(!$("#check0").prop("checked")) {
				$(".check").prop("checked",false);
			} 
		});
		//개별체크클릭시 전체체크, 전체해지
		$('.check').click(function(){
			if($("input[class='check']:checked").length == 3){
	            $("#check0").prop("checked", true);
	        }else{
	            $("#check0").prop("checked", false);
	        }
		});
	}
	function submitCheck(){
		$('#sellNow_btn').click(function(){
			if($('#addrname').val() == "" || $('#addr1').val() == ""){
				alert('반송지를 입력해주세요.');
			} else if($('#check1').is(":checked") == false){
				alert("필수 이용약관을 읽고 동의해주세요.");
				return false;
			} else if($('#check2').is(":checked") == false){
				alert("필수 이용약관을 읽고 동의해주세요.");
				return false;
			} else{
				//판매완료하기
				$('#f').attr('action', 'sell.do');
				//sellCommand제작해야함
				$('#f').serialize();
				$('#f').submit();
			}
		});
	}
	</script>
	<style>
		*{
			box-sizing: border-box;
		}
		.container{
			margin:auto;
			width: 1080px;
		}
		#backBtn{
			display: inline-block;
			margin-top: 20px;
			width: 100px;
			height: 35px;
			line-height: 35px;
			text-align: center;
			color: white;
			font-weight: border;
			border-radius: 3px;
			background-color: #D5C2EE;
		}
		#backBtn:hover{
			cursor: pointer;
		}
		h3{
			text-align: center;
		}
		table{
			width: 100%;
			border: 1px solid lightgrey;
			border-collapse: collapse;
		}
		td{
			padding: 5px;
		}
		.firstTr > td:nth-child(1){width: 50%; test-align: center;}
		.firstTr > td:nth-child(2){width: 15%;}
		.firstTr > td:nth-child(3){width: 35%;}
		tr >td:nth-last-child(2){text-align: center;}
		input[type="text"]{
			width: 100%;
			outline: none;
			border: 1px solid #FFBEBE;
			padding: 5px;
		}
		#addr1{
			width: 340px;
		}
		.addr_search_btn{
			width: 70px;
			height: 28px;
			line-height: 28px;
			outline: none;
			border: none;
			color: white;
			background-color: #FFBEBE;
		}
		.addr_search_btn:hover{
			cursor: pointer;
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
		.redText{
			color: red;
		}
		.sellNow_btn{
			display: inline-block;
			float: right;
			margin-top: 20px;
			border: none;
			width: 200px;
			padding: 10px;
			font-size: 16px;
			color: white;
			border-radius: 3px;
			background-color: #FFBEBE;
		}
		.sellNow_btn:hover {
			cursor: pointer;
			font-weight: border;
		}
	</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
	<div class="container">
		<a id = "backBtn"><i class="fas fa-angle-double-left"></i>&nbsp;뒤로가기</a>
		<h3>판매하기</h3>
		<form id="f" method="post">
			<table border="1">
				<tbody>
					<tr class="firstTr">
						<td rowspan="9">
							<img alt="${product.image}"
							src="/shoefly/resources/archive/product/${product.image}" />
						</td>
					</tr>
					<tr>
						<td>판매 신청자 ID</td>
						<td>${loginMember.memberId}
						<input type="hidden" name="memberId" value="${loginMember.memberId}"></td>			
					</tr>
					<tr>
						<td>판매 상품명</td>
						<td>${product.productName}
						<input type="hidden" name="productName" value="${product.productName}"></td>
					</tr>
					<tr>
						<td>사이즈</td>
						<td>${param.productSize}mm
						<input type="hidden" name="productSize" value="${param.productSize}"></td>
					</tr>
					<tr>
						<td>브랜드</td>
						<td>${product.brand}</td>
					</tr>
					<tr>
						<td>판매수익</td>
						<td>${highPrice}원
						<input type="hidden" name="price" value="${highPrice}"></td>
					</tr>

					<tr>
						<td rowspan="2">반송지<br>주소</td>
						<td>
							<c:if test="${empty addressList}">
								저장된 주소가 존재하지 않습니다.<br>
								직접 입력해주세요.
							</c:if>
							<c:if test="${not empty addressList}">
								저장된 주소에서 선택 가능합니다.<br>
								<c:forEach var="address" items="${addressList}">
									<input type="radio" name="addressName" value="${address.name}">${address.name}
									<input type="hidden" value="${address.memberAddressNo}">
									<input type="hidden" value="${address.addr1}">
									<input type="hidden" value="${address.addr2}">
								</c:forEach>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>
							반송지명<br>
							<input type="hidden" id="memberAddressNo" name="memberAddressNo" value="0">
							<input type="text" id="addrName" name="addrName" placeholder="ex&#41; 집, 회사"><br>
							주소<br>
							<input type="text" name="addr1" id="addr1" readonly>
							<input type="button" id="addr_search_btn" class="addr_search_btn" value="주소찾기"><br>
							상세주소<br>
							<input type="text" name="addr2" id="addr2" readonly>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="checkbox" class="check0" id="check0"><label for="check0"> 전체 동의</label><br>
							<input type="checkbox" class="check" id="check1">
							<label for="check1"> 개인정보 이용약관에 동의</label>
							<label for="check1" class="redText">(필수)</label><br>
							<input type="checkbox" class="check" id="check2">
							<label for="check2"> 위치정보 이용약관에 동의</label>
							<label for="check2" class="redText">(필수)</label><br>
							<input type="checkbox" class="check" id="check3">
							<label for="check3"> 마케팅 수신 동의(선택)</label>
						</td>
					</tr>		
				</tbody>
			</table>
		</form>
		<input type="hidden" id ="productNo" value="${product.productNo}">
		<input type="button" value="판매하기" id="sellNow_btn" class="sellNow_btn">
	</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>