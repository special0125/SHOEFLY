/**
 * @see WEB-INF/views/manager/productInfo.jsp
 */
function setState(state) {
	if(confirm('거래 상태를 전환하시겠습니까?'))
		location.href='updateProductState.do?no=' + $('#productNo').val() + '&state=' + state;
}
function delProduct() {
	if(!confirm('정말로 해당 상품의 모든 정보를 삭제하시겠습니까?\n(복구하실 수 없습니다!!)'))
		return;
	var no = $('#productNo').val();
	$.ajax({
		url: 'deleteProduct.do',
		type: 'post',
		data: 'no=' + no,
		dataType: 'json',
		success: function(data){
			if(data.result){
				location.href='productListPage.do';
			} else {
				alert(data.message);
			}
		}
	})
}