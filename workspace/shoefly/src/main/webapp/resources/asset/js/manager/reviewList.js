
// @see WEB-INF/views/manager/reivewList.jsp


/**
 * PageLoadEvent
 */
$(document).ready(function(){
	// Event
	column_onchange();
	delete_btn_onclick();
	restore_btn_onclick();
})

/**
 * id="column"의 value에 따라 검색 창이 바뀝니다.
 */
function column_onchange() {
	$('#column').change(function(){
		if($('#column').val() == 'POSTDATE'){
			date_search_toggle(true);
			default_search_toggle(false);
		} else {
			date_search_toggle(false);
			default_search_toggle(true);
		}
	})
}

/**
 * id="default_search"를 보이거나 숨깁니다.<br>
 * isAble == true : .show();<br>
 * isAble == false : .hide();
 * 
 * @param isAble
 */
function default_search_toggle(isAble) {
	if(isAble) {
		$('#default_search').show();
		$('#query').attr('disabled',false);
	} else {
		$('#default_search').hide();
		$('#query').attr('disabled',true);
	}
}

/**
 * id="date_search"를 보이거나 숨깁니다.<br>
 * isAble == true : .show();<br>
 * isAble == false : .hide();
 * 
 * @param isAble
 */
function date_search_toggle(isAble) {
	if(isAble) {
		$('#date_search').show();
		$('#startDate').attr('disabled',false);
		$('#endDate').attr('disabled',false);
	} else {
		$('#date_search').hide();
		$('#startDate').attr('disabled',true);
		$('#endDate').attr('disabled',true);
	}
}

/**
 * class="delete_btn"의 클릭 이벤트<br>
 * 해당 개시글을 삭제처리 합니다.
 */
function delete_btn_onclick() {
	$('.delete_btn').click(function(){
		if(confirm('삭제하시겠습니까?'))
			location.href='deleteReview.do?reviewNo=' + $(this).data('no');
	})
}

/**
 *  class="restore_btn"의 클릭 이벤트<br>
 *  해당 개시글을 복구합니다.
 */
function restore_btn_onclick() {
	$('.restore_btn').click(function(){
		if(confirm('복구하시겠습니까?'))
			location.href='restoreReview.do?reviewNo=' + $(this).data('no');
	})
}
