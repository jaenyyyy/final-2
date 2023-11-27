<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
.container {
	max-width: 700px; /* 변경 가능한 폭 설정 */
	margin: 0 auto; /* 가운데 정렬 */
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

		//이메일 인증번호 보내기

		$(".btn-send").find(".fa-spinner").hide();

		//인증번호 보내기 버튼 누르면
		//서버로 비동기 통신을 보내 메일 발송 요쳥
		$(".btn-send").click(function() {
			var email = $("[name=customerEmail]").val();
			if (email.length == 0)
				return;
			$(".btn-send").prop("disabled", true);
			$(".btn-send").find(".fa-spinner").show();
			$(".btn-send").find("span").text("이메일로 보내는 중 입니다");

			$.ajax({
				url : "http://localhost:8080/rest/cert/send",
				method : "post",
				data : {
					certEmail : email
				},
				success : function() {
					console.log(response);
					$(".btn-send").prop("disabled", false);
					$(".btn-send").find(".fa-spinner").hide();
					$(".btn-send").find("span").text("인증번호 보내기");

					window.alert("인증번호가 전송되었습니다. ");

				},
			});

		});

		//확인 버튼을 누르면 이메일과 인증번호를 서버로 전달하여 검사
		$(".btn-cert").click(
				function() {
					//var email = $("[name=memberEmail]").val();
					var email = window.email;
					var number = $(".cert-input").val();
					if (email.length == 0 || number.length == 0)
						return;

					$.ajax({
						url : "http://localhost:8080/rest/cert/check",
						method : "post",
						data : {
							certEmail : email,
							certNumber : number
						},
						success : function(response) {
							console.log(response);
							if (response.result) {//인증 성공
								$(".cert-input").removeClass("success fail")
										.addClass("success");
								$(".btn-cert").prop("disabled", true);
								//상태객체에 상태 저장하는 코드
							} else {
								$(".cert-input").removeClass("success fail")
										.addClass("fail");
								//상태객체에 상태 저장하는 코드
							}
						},
					});
				});

		$("[name=customerId]").blur(function(e) {
			var regex = /^[a-z][a-z0-9-_]{4,14}$/;
			var inputId = $(e.target).val();

			var isValid = regex.test(inputId);
			$(e.target).removeClass("success fail fail2");
			if (isValid) {//형식이 유효하다면
				$.ajax({
					url : "http://localhost:8080/rest/customer/idCheck",
					method : "post",
					// data : {customerId : e.target.value},
					data : {
						customerId : $(e.target).val()
					},
					success : function(response) {
						if (response == "Y") {//사용가능
							$(e.target).addClass("success");
							status.id = true;
						} else {//사용불가(중복)
							$(e.target).addClass("fail2");
							status.id = false;
						}
					},
					error : function() {
						alert("서버와의 통신이 원활하지 않습니다");
					},
				});
			} else {
				$(e.target).addClass("fail");
				status.id = false;
			}

		});

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

		$("[name=customerName]").blur(function() {
			var regex = /^[가-힣]{2,7}$/;
			var inputName = $(this).val();
			var isValid = regex.test(inputName);
			$(this).removeClass("success fail");
			$(this).addClass(isValid ? "success" : "fail");
			status.name = isValid;

		});

		$("#password-check").blur(function() {
			var inputPw = $("[name=customerPw]").val();
			var checkPw = $(this).val();

			var isValid = inputPw == checkPw;

			$(this).removeClass("success fail fail2");
			if (inputPw.length == 0) {
				$(this).addClass("fail2");
				status.pwCheck = false;

			} else if (isValid) {
				$(this).addClass("success");
				status.pwCheck = true;
			} else {
				$(this).addClass("fail");
				status.pwCheck = false;
			}
		});

		$("[name=customerContact]").blur(
				function() {
					var regex = /^010[1-9][0-9]{7}$/;
					var inputContact = $(this).val();

					var isValid = $(this).val().length == 0
							|| regex.test(inputContact);
					$(this).removeClass("success fail");
					$(this).addClass(isValid ? "success" : "fail");
					status.contact = isValid;
				});

		$("[name=customerEmail]").blur(function(e) {
			var regex = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
			var inputEmail = $(e.target).val();

			var isValid = regex.test(inputEmail);
			$(e.target).removeClass("success fail fail2");
			if (isValid) {
				$.ajax({
					url : "http://localhost:8080/rest/customer/emailCheck",
					method : "post",
					data : {
						customerEmail : $(e.target).val()
					},
					success : function(response) {
						if (response == "Y") { //Y면 사용가능
							$(e.target).addClass("success");
							status.email = true;
						} else {//사용불가(중복)
							$(e.target).addClass("fail2");
							status.email = false;
						}
					},
					error : function() {
						alert("서버와의 통신이 원활하지 않습니다");
					},

				});
			} else {
				$(e.target).addClass("fail");
				status.email = false;
			}

		});
		$("[name=customerBirth]")
				.blur(
						function() {
							var regex = /^(19[0-9]{2}|20[0-9]{2})-(((0[13578]|1[02])-(0[1-9]|1[0-9]|2[0-9]|3[01]))|((0[469]|11)-(0[1-9]|1[0-9]|2[0-9]|30))|((02)-(0[1-9]|1[0-9]|2[0-9])))$/;
							var inputBirth = $(this).val();

							var isValid = $(this).val().length == 0
									|| regex.test(inputBirth);
							$(this).removeClass("success fail");
							$(this).addClass(isValid ? "success" : "fail");
							status.birth = isValid;
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

	$(function() {
		// 이메일 수정 버튼 클릭 시 해당 폼 표시
		$(".edit-btn").click(function() {
			var target = $(this).data("target");
			$("#" + target + "-form").toggle();
		});
	});
</script>



<style>
/* 추가한 스타일 */
.fail-feedback, .fail2-feedback, .success-feedback {
	display: none;
}

.line {
	border-top: 1px solid #ffb416;
}

/* 추가한 스타일 */
/* 컨테이너에 테두리 스타일 추가 */
.container {
    border: 3px solid #ffb416; /* 테두리 스타일 및 색상 설정 */
    border-radius: 20px; /* 테두리 모서리를 둥글게 만듭니다 */
    padding: 80px 60px 60px; /* 내부 여백 설정 - 여기서 첫 번째 값은 상단 패딩입니다 */
}

</style>




<form action="change" method="post" autocomplete="off">
<br><br><br>
	<div class="container justify-content-center"
		style="margin-bottom: 20%;">
		<div class="row" style="margin-top: 20%;">

			<!-- 제목 -->
			<div class="row mb-4 text-center">
				<h1 class="bold">
					<i class="fa-solid fa-user" style="color: #ffb416;"></i> <span
						style="font-weight: bold;">Change Info</span>
				</h1>
			</div>
			<div class="text-center">
				<br> <br> <span style="font-weight: bold;"> 회원님의
					개인정보를 안전하게 보호하고, <br> 개인정보 도용으로 인한 피해를 예방하기 위해 비밀번호를 변경해주세요
				</span> <br>
				<br>
				<div class="row line"></div>

			</div>

			<div class="row mt-4">
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon1">기존 이메일</span>
					</div>
					<input type="text" class="form-control"
						value="${customerDto.customerEmail}" aria-label="Username"
						aria-describedby="basic-addon1" disabled>
				</div>
			</div>

			<div class="row mt-4">
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon2">변경 할 이메일</span>
					</div>
					<input type="text" class="form-control" name="customerEmail"
						placeholder="test@kh.com" aria-label="Recipient's username"
						aria-describedby="basic-addon2">
				</div>
				<div class="fail-feedback">이메일 형식이 잘못되었습니다</div>
				<div class="fail2-feedback">중복 된 아이디 존재</div>
			</div>

			<div class="row mt-4">
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon3">기존 연락처</span>
					</div>
					<input type="text" class="form-control"
						value="${customerDto.customerContact}"
						aria-label="Recipient's username" aria-describedby="basic-addon3"
						disabled>
				</div>
			</div>

			<div class="row mt-4">
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon4">연락처</span>
					</div>
					<input type="tel" class="form-control" name="customerContact"
						value="${customerDto.customerContact}" placeholder="( - ) 제외"
						aria-label="Recipient's username" aria-describedby="basic-addon4">
				</div>
				<div class="fail-feedback">전화번호 형식이 올바르지 않습니다</div>
			</div>

			<div class="row mt-4">
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon5">기존 생년월일</span>

					</div>
					<input type="text" class="form-control"
						value="${customerDto.customerBirth}"
						aria-label="Recipient's username" aria-describedby="basic-addon5"
						disabled>
				</div>
			</div>

			<div class="row mt-4">
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text" id="basic-addon6">생년월일</span>
					</div>
					<input type="date" class="form-control" name="customerBirth"
						value="${customerDto.customerBirth}"
						aria-label="Recipient's username" aria-describedby="basic-addon6">
				</div>
				<div class="fail-feedback">잘못된 날짜를 선택하셨습니다</div>
			</div>
			<div class="row mt-4">
				<label class="col-form-label mt-4" for="inputDefault">
					<h4>* 비밀번호 확인 *</h4>
				</label> <input type="password" name="customerPw" class="form-control"
					placeholder="비밀번호를 입력해주세요">
			</div>

			<!-- 버튼 -->
			<div class="row mv-30 mt-4">
				<button type="submit" class="btn btn-warning text-black">
					개인정보 변경</button>
			</div>
			<div class="row mt-4">
				<a class="btn btn-outline-warning" href="mypage">취소</a>
			</div>
			<c:if test="${param.error != null}">
				<div class="row important">
					<span class="text-danger">입력하신 비밀번호가 일치하지 않습니다</span>
				</div>
			</c:if>


		</div>
	</div>

</form>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>