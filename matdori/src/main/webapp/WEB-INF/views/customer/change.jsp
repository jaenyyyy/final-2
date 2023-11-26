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
        $(".btn-cert").click(function(){
            //var email = $("[name=memberEmail]").val();
            var email = window.email;
            var number = $(".cert-input").val();
            if(email.length == 0 || number.length == 0) return;

            $.ajax({
                url:"http://localhost:8080/rest/cert/check",
                method:"post",
                data: {
                    certEmail : email,
                    certNumber : number
                },
                success:function(response){
                    console.log(response);
                    if(response.result) {//인증 성공
                        $(".cert-input").removeClass("success fail")
                                                .addClass("success");
                        $(".btn-cert").prop("disabled", true);
                        //상태객체에 상태 저장하는 코드
                    }
                    else {
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
</style>



<form action="change" method="post" autocomplete="off">
	<div class="container justify-content-center"
		style="margin-bottom: 6%;">
		<div class="row" style="margin-top: 20%;">

			<!-- 제목 -->
			<div class="row mb-4">
				<h1 class="bold">
					<i class="fa-solid fa-user" style="color: #ffb416;"></i> 개인정보 변경
				</h1>
			</div>
 
			

	기존 이메일 ${customerDto.customerEmail}


			<div class="row mt-4"></div>
			변경 할 이메일 <input class="form-control" type="text" name="customerEmail"
				placeholder="test@kh.com">
			<div class="fail-feedback">이메일 형식이 잘못되었습니다</div>
			<div class="fail2-feedback">중복 된 아이디 존재 </div>

		</div>




			<div class="row mt-4">
				<label class="col-form-label mt-4" for="inputDefault">연락처</label> <input
					type="tel" name="customerContact" class="form-control"
					value="${customerDto.customerContact}" placeholder="( - ) 제외"
					id="inputDefault">
				<div class="fail-feedback">전화번호 형식이 올바르지 않습니다</div>
			</div>

			<div class="form-group">
				<label class="col-form-label mt-4" for="inputDefault">생년월일</label> <input
					type="date" name="customerBirth" class="form-control"
					value="${customerDto.customerBirth}">
				<div class="fail-feedback">잘못된 날짜를 선택하셨습니다</div>
			</div>

			<div class="form-group mb-4">
				<label class="col-form-label mt-4" for="inputDefault">비밀번호
					확인</label> <input type="password" name="customerPw" class="form-control"
					placeholder="비밀번호를 입력해주세요">
			</div>


			<!-- 버튼 -->
			<div class="row ms-1 mt-2">
				<button type="submit" class="btn btn-warning">변경하기</button>
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