/**
 * @see WEB-INF/views/manager/faqInfo.jsp
 */

$(document).ready(function(){
	textarea_tab();
})

/**
 * textarea에서 tab키를 누를 수 있게 해 줍니다.
 */
function textarea_tab() {
	$('textarea').keydown(function(e){
		if(event.keyCode == 9){
		     var start = this.selectionStart;
		     var end = this.selectionEnd;

		     var $this = $(this);
		     var value = $this.val();
		     $this.val(value.substring(0, start)
		                    + "\t"
		                    + value.substring(end));

		     this.selectionStart = this.selectionEnd = start + 1;

		     e.preventDefault();
		}
	})
}

function preview_btn() {
	$('#preview').html($('#answer').val());
	$('.content').toggle();
	$('.preview').toggle();
}

function edit_btn() {
	if($('#title').val() == '') {
		alert('제목을 입력해 주세요.')
		$('#title').focus();
		return;
	}
	if($('#content').val() == '') {
		alert('내용을 입력해 주세요.')
		$('#content').focus();
		return;
	}
	$.ajax({
		url: 'insertFaq.do',
		type: 'POST',
		data: $('#f').serialize(),
		dataType: 'json',
		success: function(data) {
			console.log("yes");
			console.log(data);
			if (data.result) {
				alert(data.message);
				opener.parent.location.reload();
				window.close();
			}
		}
	})
}

function del_btn() {
	if(!confirm('정말로 삭제하겠습니까?\n (복구가 되지 않습니다)')){
		return;
	}
	$.ajax({
		url: 'deleteFaq.do',
		type: 'POST',
		data: 'faqNo=' + $('#faqNo').val(),
		dataType: 'json',
		success: function(data) {
			console.log("yes");
			console.log(data);
			if (data.result) {
				alert(data.message);
				opener.parent.location.reload();
				window.close();
			}
		}
	})
}
