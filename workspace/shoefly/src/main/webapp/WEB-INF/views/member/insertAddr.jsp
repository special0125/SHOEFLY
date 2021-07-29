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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/member/insertAddr.css">	
	<title>제목</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<c:if test="${empty loginMember}">
	<script>
		alert('로그인을 해주세요.');
		location.href="loginPage.do";
	</script>
	</c:if>
	<script>
		$(document).ready(function(){
			fn_findAddress();
			fn_insertAddress();
		})

		function fn_findAddress() {
			$('#addr_search_btn').click(function(){
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
		
		function fn_insertAddress(){
			$('#insert_addr_btn').click(function(){
				if($('#name').val() == '') {
					alert('배송지를 입력해주세요.');
					return false;
				} else if($('#addr1').val() == '') {
					alert('주소를 입력해주세요.');
					return false;
				} 
				$('#form').attr('action', 'insertAddress.do');
				$('#form').submit();
			})
		}
	</script>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
		<div class="container">
			<div class="pageTitle">
				<span>신규 주소등록</span>
			</div>	
			
			<div class="insert_addr">
				<form name="form" id="form" method="post">		
					<div class="addr_title">
				     	<label for="name">배송지</label>			
					</div>
					<div class="addr_info">
						<input type="text" id="name" name="name" placeholder="배송지를 입력해주세요.">
					</div>
					<div class="addr_title">
				     	<label for="addr1">주소</label>	
					</div>
					<div class="addr_info">
						<input type="text" id="addr1"  name="addr1" readonly/>
						<input type="button" id="addr_search_btn" value="주소찾기" class="btn_primary">			
					</div>
					<div class="addr_title">
				     	<label for="addr2">상세주소</label>		
					</div>
					<div class="addr_info">
						<input type="text" id="addr2"  name="addr2" readonly/>				
					</div>
					<input type="button" id="insert_addr_btn" value="추가" class="insert_addr_btn btn_primary">
				</form>
			</div>
		</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>