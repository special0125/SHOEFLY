/**
 * @see WEB-INF/views/manager/productSellList.jsp
 */
var t0_page = 1;
var t1_page = 1;
var t2_page = 1;
var t3_page = 1;
var t_page = 1;
var order = 'POSTDATE';
var isDesc = 'DESC';
$(document).ready(function(){
	allRefreshList();
})
function allRefreshList() {
	refreshList(-1, t_page);
	refreshList(0, t0_page);
	refreshList(1, t1_page);
	refreshList(2, t2_page);
	refreshList(3, t3_page);
}
function refreshList(state, page) {
	var tbody = $('#tbody' + state);
	var tfoot = $('#tfoot' + state);
	$.ajax({
		url: 'selectListProductSell.do',
		type: 'get',
		data: 'page=' + page + '&' + $('#option').val() + '=' + $('#query').val() + '&order=' + order + '&isDesc=' + isDesc + '&state=' + state,
		dataType: 'json',
		success: function(data){
			console.log(data.list);
			tbody.empty();
			tfoot.empty();
			if(data.result) {
				
				$.each(data.list, function(index, productSell){
					var tr = $('<tr>').appendTo(tbody);
					$('<td>').text(productSell.productSellNo).appendTo(tr);
					$('<td>').text(productSell.memberId).appendTo(tr);
					$('<td>').text(productSell.productName).appendTo(tr);
					$('<td>').text(productSell.productSize).appendTo(tr);
					$('<td>').text(productSell.price).appendTo(tr);
					$('<td>').append($('<button>').text('확인').attr('onclick','check_addr(' + productSell.memberAddressNo + ')')).appendTo(tr);
					$('<td>').text(productSell.postdate).appendTo(tr);
					$('<td>').text(productSell.selldate).appendTo(tr);
					if(productSell.state == 2){
						$('<td>').append($('<button>').text('결산완료').attr('onclick','change_state(3,' + productSell.productSellNo + ');')).appendTo(tr);
					} else if(productSell.state == 0) {
						$('<td>').append($('<button>').text('취소').attr('onclick','change_state(-1,' + productSell.productSellNo + ');'))
						.append($('<button>').text('수령').attr('onclick','change_state(1,' + productSell.productSellNo + ');')).appendTo(tr)
					} else {
						$('<td>').appendTo(tr);
					}
				});

				var td = $('<td colspan="9">').appendTo($('<tr>').appendTo(tfoot));
				if(data.page.page == 1){
					$('<span>').html('<i class="fas fa-caret-left"></i>이전').appendTo(td);
				} else {
					$('<a>').html('<i class="fas fa-caret-left"></i>이전').attr('href','javascript:setPage(' + state + ',' + (data.page.page - 1) + ')').appendTo(td);
				}
				if(data.page.page == data.page.totalPage){
					$('<span>').html('다음<i class="fas fa-caret-right"></i>').appendTo(td);
				} else {
					$('<a>').html('다음<i class="fas fa-caret-right"></i>').attr('href','javascript:setPage(' + state + ',' + (data.page.page + 1) + ')').appendTo(td);
				}
				
			} else { 
				$('<td colspan="9">').text('검색내역이 없습니다.').appendTo($('<tr>').appendTo(tbody));
			}
		}
	})
}

function setPage(state, nowpage) {
	refreshList(state, nowpage);
}

function change_option(){
	t0_page = 1;
	t1_page = 1;
	t2_page = 1;
	t3_page = 1;
	t_page = 1;
	order = 'POSTDATE';
	isDesc = 'DESC';
	allRefreshList();
}
function change_query(){
	t0_page = 1;
	t1_page = 1;
	t2_page = 1;
	t3_page = 1;
	t_page = 1;
	order = 'POSTDATE';
	isDesc = 'DESC';
	allRefreshList();
}
function check_addr(addrNo){
	$.ajax({
		url: 'selectOneMemberAddress.do',
		type: 'get',
		data: 'no=' + addrNo,
		dataType: 'json',
		success: function(data) {
			if(data.result){
				var addr1 = data.addr.addr1;
				var addr2 = data.addr.addr2;
				alert('주소: ' + addr1 +'\n'
						+ '상제주소: ' + addr2);
			}
		}
	})
}
function change_state(state, no){
	var message;
	if(state == 3)
		message = '해당 상품에 대한 입금을 완료했으면 확인을 눌러주세요.';
	else if(state == -1)
		message = '해당 판매신청을 취소하시겠습니까?';
	else 
		message = '해당 상품을 수령하셨습니까?'; 
			
	if(confirm(message))
		$.ajax({
			url: 'updateProductSellState.do',
			type: 'get',
			data: 'no=' + no + "&state=" + state,
			dataType: 'json',
			success: function(data) {
				if(data.result){
					allRefreshList();
				} else {
					console.log('ProductBuy table\'s state is not changed');
				}
			}
		})
}