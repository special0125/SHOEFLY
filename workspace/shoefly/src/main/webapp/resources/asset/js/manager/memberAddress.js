/**
 * @see WEB-INF/views/manager/memberAddress.jsp
 */
$(document).ready(function() {
	if($('#empty_list').val() == 'true') {
		alert('이 회원은 주소를 등록하지 않았습니다.'); 
		history.back(); 
	}
	
	$($('.addr_div')[0]).show();
}) 

function select_change(obj) {
	var addr_id = '#addr_' + $(obj).val();
	$('.addr_div').hide();
	$(addr_id).show();
}