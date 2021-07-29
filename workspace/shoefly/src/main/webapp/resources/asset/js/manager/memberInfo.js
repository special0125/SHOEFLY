/**
 * @see WEB-INF/views/manager/memeberInfo.jsp
 */
function show_address() {
	location.href="memberAddressPage.do?memberNo=" + $('#memberNo').val()  + "&memberId=" + $('#memberId').val();
}
function del_btn() {
	if(confirm('정말 해당 회원의 이용/탈퇴 정보를 변경하시겠습니까?')){
		$.ajax({
			url: 'deleteMember.do',
			type: 'GET',
			data: 'memberNo=' + $('#memberNo').val() + "&state=" + $('#state').val(), 
			dataType: 'json',
			success: function(data) {
				opener.parent.location.reload();
				location.reload();
			}
		})
	}
}
function change_pw_btn() {
	if(confirm('해당 회원의 임시비밀번호를 발급하시겠습니까?')){
		location.href='updateMemberPw.do?memberNo=' + $('#memberNo').val();
	}
}