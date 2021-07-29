/**
 * @see WEB-INF/views/manager/faqList.jsp
 */
$(document).ready(function(){
	pageLoadEvnet();
	$('#column').change(function(){
		if($('#column').val() == 'POSTDATE'){
			$('#default_search').hide();
			$('#date_search').show();
		} else {
			$('#default_search').show();
			$('#date_search').hide();
		}
	})
});

function fn_show(no) {
	if(no == null) {
		no = '';
	}
	window.open("faqInfoPage.do?no=" + no,"noticeInfo","width=720,height=600");
}

function pageLoadEvnet() {
	if($('#paramColumn').val() != '') {
		$('#column').val($('#paramColumn').val())
	}
	if($('#paramQuery').val() != '') {
		$('#query').val($('#paramQuery').val())
	}
	if($('#paramStartDate').val() != '') {
		$('#startDate').val($('#paramStartDate').val())
	}
	if($('#paramEndDate').val() != '') {
		$('#endDate').val($('#paramEndDate').val())
	}
}