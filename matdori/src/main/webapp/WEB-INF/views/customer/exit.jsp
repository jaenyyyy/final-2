<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
.container {
	max-width: 500px; /* 변경 가능한 폭 설정 */
	margin: 0 auto; /* 가운데 정렬 */
}

.tag-none {
	text-decoration: none;
	color: black;
}

.line {
	border-top: 1px solid #ffb416;
}

.tag-none:hover {
	color: #FF3300; /* 원하는 호버 시 색상으로 변경 */
}
.container {
    border: 3px solid #ffb416; /* 테두리 스타일 및 색상 설정 */
    border-radius: 20px; /* 테두리 모서리를 둥글게 만듭니다 */
    padding: 80px 60px 60px; /* 내부 여백 설정 - 여기서 첫 번째 값은 상단 패딩입니다 */
}
</style>

<script>
	$(function() {

		$("[name=join]").click(function() {

			if ($(".form-input.cert-input").val().length === 0) {

				$(".cert-input").addClass("fail2")

			}
		});

		var status = {
			id : false,
			name : false,
			pw : false,
			pwCheck : false,
			contact : false,
			email : false,
			birth : false,
			emailOk : false,
			ok : function() {
				return this.id && this.name && this.pw && this.pwCheck
						&& this.contact && this.email && this.birth
						&& this.emailOk;
			}
		};
		$("[name=customerPw]")
				.blur(
						function() {
							var regex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$])[A-Za-z0-9!@#$]{8,20}$/;
							var inputPw = $(this).val();

							var isValid = regex.test(inputPw);
							$(this).removeClass("success fail");
							$(this).addClass(isValid ? "success" : "fail");
							status.pw = isValid;
						});

		//페이지 이탈 방지
		//- window에 beforeunload 이벤트 설정
		$(window).on("beforeunload", function() {
			return false;
		});

		//-form 전송할 때는 beforeunload 이벤트를 제거
		$(".join-form").submit(function(e) {
			$(".form-input").blur();

			if (!status.ok()) {
				e.preventDefault();
			} else {
				$(window).off("beforeunload");
			}
		});

	});
</script>


<form action="exit" method="post" autocomplete="off">
<br><br><br>
	<div class="container justify-content-center"
		style="margin-bottom: 20%;">

		<div class="row text-center mt-5">
    <h1>
        <i class="fa-solid fa-circle-user" style="color: #ffb416;"></i>
        <span style="font-weight: bold;">Delete Membership</span>
    </h1>
</div>
<br>
<div class="row line"></div>
<br>
<br>
<br>
<div class="row mt-4">
    <label style="font-weight: bold;">
        <i class="fa-solid fa-asterisk red"></i> 탈퇴 버튼 선택 시 <br>
        계정은 삭제되며 복구되지 않습니다.
    </label>
</div>
<br>
<br>
<br>
<c:if test="${param.error != null}">
    <div class="row text-center important">
        <h3 style="font-weight: bold;">비밀번호가 일치하지 않습니다</h3>
    </div>
</c:if>



		<div class="row mt-4">

			<input class="form-control" type="password" name="customerPw"
				id="changePw-check" placeholder="비밀번호 확인">
			<div class="success-feedback">비밀번호가 일치합니다</div>
			<div class="fail-feedback">비밀번호가 일치하지 않습니다</div>

		</div>
		<br>
		<div class="row line"></div>
		<div class="row">
			<br>
			<button type="submit" class="btn btn-warning text-black">탈퇴하기</button>
		</div>
		<div class="row line"></div>

		<div class="row mt-4">
			<a class="btn btn-outline-warning" href="mypage">취소</a>
		</div>
	</div>

</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>




