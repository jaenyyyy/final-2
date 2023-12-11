<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>



<script>
window.contextPath = "${pageContext.request.contextPath}";
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
				url : window.contextPath+"//www.sysout.co.kr/rest/cert/send",
				method : "post",
				data : {
					certEmail : email
				},
				success : function() {
					$(".btn-send").prop("disabled", false);
					$(".btn-send").find(".fa-spinner").hide();
					$(".btn-send").find("span").text("인증번호 보내기");

					window.alert("이메일로 보내드렸어요");

				},
			});

		});

		//확인버튼 누르면 이메일과 인증번호를 서버로 전달해서 검사
		$(".btn-cert").click(
				function() {
					var email = $("[name=customerEmail]").val();
					var number = $(".cert-input").val();

					//				if(email.length==0 || number.length ==0 ) return;

					$
							.ajax({
								url : window.contextPath+"//www.sysout.co.kr/rest/cert/check",
								method : "post",
								data : {
									certEmail : email,
									certNumber : number,

								},
								success : function(response) {
									if (number.length == 0) {
										$(".cert-input").removeClass(
												"success fail fail2").addClass(
												"fail2");
										status.emailOk = false;
									} else if (response.result) {
										$(".cert-input").removeClass(
												"success fail fail2").addClass(
												"success");
										status.emailOk = true;
										$(".btn-cert").prop("disabled", true);
									} else {
										$(".cert-input").removeClass(
												"success fail fail2").addClass(
												"fail");
										status.emailOk = false;
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
					url : window.contextPath+"//www.sysout.co.kr/rest/customer/idCheck",
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

		$("[name=customerPw]").blur(function() {
            var regex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$])[A-Za-z0-9!@#$]{8,20}$/;
            var inputPw = $(this).val();

            var isValid = regex.test(inputPw);
            $(this).removeClass("success fail");
            $(this).addClass(isValid ? "success" : "fail");
            status.pw = isValid;
        });

        $(".btn-cert").click(function() {
            var email = $("[name=customerEmail]").val();
            var number = $(".cert-input").val();

            $.ajax({
                url: window.contextPath+"//www.sysout.co.kr/rest/cert/check",
                method: "post",
                data: {
                    certEmail: email,
                    certNumber: number,
                },
                success: function(response) {
                    if (number.length == 0) {
                        $(".cert-input").removeClass("success fail fail2").addClass("fail2");
                        status.emailOk = false;
                    } else if (response.result) {
                        $(".cert-input").removeClass("success fail fail2").addClass("success");
                        status.emailOk = true;
                        $(".btn-cert").prop("disabled", true);
                        // 여기서 인증번호가 일치하므로 'success' 작업을 수행합니다.
                        $(".cert-wrapper").append('<a href="changePw" class="btn btn-change-password">비밀번호 변경하기</a>');
                    } else {
                        $(".cert-input").removeClass("success fail fail2").addClass("fail");
                        status.emailOk = false;
                    }
                },
            });
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
					url : window.contextPath+"//www.sysout.co.kr/rest/customer/emailCheck",
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
	    $(window).on("beforeunload", function(){
	        return false;
	    });

	    //-form 전송할 때는 beforeunload 이벤트를 제거
	    $(".join-form").submit(function(e){
	        $(".form-input").blur();
	        
	     
	        
	        
	        if(!status.ok()){
	            e.preventDefault();
	        }
	        else{
	            $(window).off("beforeunload");
	        }
	    });

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

.bold-text {
	font-weight: bold;
}
</style>




<form action="findPw" method="post" autocomplete="off">

	<br>
	<br>
	<br>
	<div class="container justify-content-center"
		style="margin-bottom: 20%;">
		<div class="row" style="margin-top: 10%;">
			<h1>
				<i class="fa-solid fa-circle-user" style="color: #ffb416;"></i> <span
					style="font-weight: bold;">Find Pw</span>
			</h1>
		</div>


		<span class="bold-text">계정의 이메일 주소를 입력해주세요 <br> 비밀번호 재설정을
			위해 이메일의 인증번호를 확인해주세요 <br> 인증번호는 계정의 이메일 주소로 발송됩니다 <br>
		</span> <br>
		<br>
		<div class="row line"></div>


		<div class="row mt-4">
			이메일 <br> <input class="form-control" type="text"
				name="customerEmail" placeholder="test@kh.com">
			<div class="fail-feedback">이메일 형식이 잘못되었습니다</div>
			<div class="fail2-feedback">이미 이 이메일로 아이디를 만드셨습니다</div>
			<div class="success-feedback">올바른 이메일 형식입니다</div>

		</div>


		<div class="row mv-30 mt-4">

			<button type="button" class="btn btn-send btn-warning "
				onclick="sendCertNumber()">
				<i class="fa-solid fa-spinner fa-spin"></i> <span>인증번호 보내기</span>
			</button>
		</div>

		<br> <br> <br>

		<!-- 기존 코드에 버튼을 추가합니다 -->




		<div class="row mt-4">
			<input type="text" class="form-control cert-input"
				placeholder="인증번호 입력"> <br>
			<span style="font-weight: bold;">인증번호를 입력해주세요 </span>
			<div class="cert-wrapper right row mv-30 mt-4">

				<button type="button" class="btn btn-cert btn-warning"
					onclick="checkCertNumber()">확인</button>

				<div class="fail2-feedback">인증번호를 입력해주세요</div>
				<div class="fail-feedback">인증번호 잘못 입력하셨습니다</div>
				<div class="success-feedback">인증 성공!</div>
				<div class="row flex-container"></div>
				<c:if test="${param.error != null}">
					<div class="row important">
						<span class="text-danger">입력하신 인증번호가 일치하지 않습니다</span>
					</div>
				</c:if>

			</div>
		</div>
	</div>

</form>





<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>