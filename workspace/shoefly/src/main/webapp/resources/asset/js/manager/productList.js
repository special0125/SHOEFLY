/**
 * @see WEB-INF/views/manager/productList.jsp
 */
$(document).ready(function(){
	updateList();
})

function search() {
	$('#page').val('1');
	updateList();
}

function updateList() {
	$('#tbody').empty();
	$('#tfoot').empty();
	$.ajax({
		url: 'selectListProduct.do',
		type: 'GET',
		data: $('#f').serialize(),
		dataType: 'json',
		success: function(data){
			if(data.result){
				var state_idName = "#" + $('#order').val() + "_STATE";
				$('.up-down').empty();
				if($('#isDesc').val() == 'ASC')
					$(state_idName).html('<i class="fas fa-caret-down"></i>');
				else
					$(state_idName).html('<i class="fas fa-caret-up"></i>');
				
				var list = data.list;
				$.each(list,function(index, product){
					var tr = $('<tr>').appendTo('#tbody');
					$('<td>').text(product.productNo).appendTo(tr);
					$('<td>').append($('<a>').text(product.productName).attr('href','productInfoPage.do?no=' + product.productNo)).appendTo(tr);
					$('<td>').text(product.brand).appendTo(tr);
					$('<td>').text(product.price).appendTo(tr);
					$('<td>').append($('<span>').attr('onclick','show_img(\'' + product.image + '\');').html('<i class="fas fa-image"></i>')).appendTo(tr);
					if(product.state == 0)
						$('<td style="color:#539bf5;">').text('거래중').appendTo(tr);
					else
						$('<td style="color:red;">').text('정지').appendTo(tr);
				});
				
				var page = data.page;
				var td = $('<td colspan="6">').appendTo($('<tr>').appendTo('#tfoot'));
				if(data.page.page == 1){
					$('<span>').html('<i class="fas fa-caret-left"></i>이전').appendTo(td);
				} else {
					$('<a>').html('<i class="fas fa-caret-left"></i>이전').attr('href','javascript:setPage(' + (data.page.page - 1) + ')').appendTo(td);
				}
				if(data.page.page == data.page.totalPage){
					$('<span>').html('다음<i class="fas fa-caret-right"></i>').appendTo(td);
				} else {
					$('<a>').html('다음<i class="fas fa-caret-right"></i>').attr('href','javascript:setPage(' + (data.page.page + 1) + ')').appendTo(td);
				}
			} else {
				$('<td colspan="6">').text('검색된 상품이 없습니다.').appendTo(tr);
			}
		}
	})
	
}

function setOrder(data) {
	var order = $('#order');
	var isDesc = $('#isDesc');
	if(order.val() == data){
		if(isDesc.val() == 'DESC')
			isDesc.val('ASC');
		else
			isDesc.val('DESC');
		search();
	} else {
		order.val(data);
		isDesc.val('ASC');
		search();
	}
}

function setPage(data) {
	$('#page').val(data);
	updateList();
}

function show_img(data) {
	window.open("/shoefly/resources/archive/product/" + data,"noticeInfo");
}
