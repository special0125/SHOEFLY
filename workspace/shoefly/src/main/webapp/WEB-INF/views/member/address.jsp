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
	<link rel="stylesheet" href="/shoefly/resources/asset/css/member/address.css">
	<title>주소록</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<c:if test="${empty loginMember}">
	<script>
		alert('로그인을 해주세요.');
		location.href="loginPage.do";
	</script>
	</c:if>
	<script>
		$(document).ready(function(){
			fn_selectAddressList();
			fn_updateAddress();
			fn_deleteAddress();
			fn_insertAddr();
			fn_return();
		})
		
		// 주소록 리스트 가져오기
		function fn_selectAddressList(){
			$.ajax({
				url: 'selectAddressList.do',
				type: 'post',
				data: 'memberNo=' + $('#memberNo').val(),
				dataType: 'json',
				success: function(result){
					$('#address_list').empty();
					if(result.exists) {
						$.each(result.list, function(i, address){
							$('<tr>')
							.append( $('<td>').text(address.name) )
							.append( $('<td>').text(address.addr1) )
							.append( $('<td>').text(address.addr2) )
							.append($('<td>').html('<input type="hidden" name="memberAddressNo" id="memberAddressNo" value="' + address.memberAddressNo + '"><input type="button" value="수정" id="update_address_btn" class="btn_primary2">'))
							.append( $('<td>').html('<input type="hidden" name="memberAddressNo" id="memberAddressNo" value="' + address.memberAddressNo + '"><input type="button" id="delete_address_btn" value="삭제" class="btn_primary2">') )
							.appendTo('#address_list')
						})
					} else {
						$('<tr>')
						.append( $('<td colspan="3">등록된 주소가 없습니다.</td>') )
						.appendTo('#address_list')
					}
				}
			});
		} 
		
		// 주소 정보 변경
		function fn_updateAddress(){
			$('body').on('click', '#update_address_btn', function(){
				var memberAddressNo = $(this).prev().val();
				location.href = 'updateAddressList.do?memberAddressNo=' + memberAddressNo;
			})
		}
		
		function fn_deleteAddress(){
			$('body').on('click', '#delete_address_btn', function(){
				var memberAddressNo = $(this).prev().val();/*  $('#memberAddressNo').val(); */
				if(confirm('선택하신 주소지를 삭제하시겠습니까?')) {
					location.href = 'deleteAddress.do?memberAddressNo=' + memberAddressNo;									
				}
			})
		}
<<<<<<< HEAD
		// 주소추가
		function fn_insertAddr(){
			$('#insert_btn').click(function(){
				location.href = 'insertAddressPage.do';
			})
		}
=======
		
>>>>>>> 6b460b5854011829b2bdae50810a5ef830266df8
		// 돌아가기
		function fn_return(){
			$('#return_btn').click(function(){
				history.back();
			})
		}
	</script>	
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<section>
		<div class="container">
		<input type="hidden" id="memberNo" value="${loginMember.memberNo}"/>
		<div class="logo">
			<span>주소록</span>
		</div>	
		<table>
			<thead class="headTitle">
				<tr>
					<td>배송지명</td>
					<td>주소</td>
					<td>상세주소</td>
					<td colspan="2">
						
					</td>
				</tr>
			</thead>
			<tbody id="address_list" class="body">				
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6"><input type="button" id="insert_btn" value="주소추가" class="insert_address_btn btn_primary"><input type="button" value="돌아가기" id="return_btn" class="returnPage_btn btn_primary"></td>
				</tr>
			</tfoot>
		</table>
		</div>
	</section>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>