<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script src="/js/cert.js"></script>


<script>

    $(document).ready(function () {
        var status = {
            email: false,
            numberCheck: false,
            ok: function () {
                return this.email && this.numberCheck;
            },
        };

        $("[name=customerEmail]").blur(function () {
            var regex = /^[a-zA-Z0-9+\-_.]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9-.]+$/;
            var isValid = regex.test($(this).val());
            $(this).removeClass("success fail");
            $(this).addClass(isValid ? "success" : "fail");
            status.email = isValid;
        });

        $("#number-check").blur(function () {
            var originNumber = $("[name=customerEmail]").val();
            var checkNumber = $(this).val();

            $(this).removeClass("success fail fail2");
            if (originNumber.length == 0) {
                $(this).addClass("fail2");
                status.numberCheck = false;
            } else if (originNumber == checkNumber) {
                $(this).addClass("success");
                status.numberCheck = true;
            } else {
                $(this).addClass("fail");
                status.numberCheck = false;
            }
        });

        $(".findPw-form").submit(function (e) {
            if (status.ok() == false) {
                e.preventDefault();
            }
        });
        
        // 이메일 인증번호 보내기
        $(".btn-send").find(".fa-spinner").hide();

        // 인증번호 보내기 버튼 누르면
        // 서버로 비동기 통신을 보내 메일 발송 요청
        $(".btn-send").click(function () {
            sendCertNumber();
        });

        // 확인버튼 누르면 이메일과 인증번호를 서버로 전달해서 검사
        $(".btn-cert").click(function () {
            checkCertNumber();
        });

        // 추가적으로, 확인 버튼을 누를 때 페이지 이동을 위한 함수
        function checkCertNumber() {
            var email = $("[name=customerEmail]").val();
            var number = $(".cert-input").val();

            $.ajax({
                url: "http://localhost:8080/rest/cert/check",
                method: "post",
                data: {
                    certEmail: email,
                    certNumber: number,
                },
                success: function (response) {
                    if (number.length == 0) {
                        $(".cert-input").removeClass("success fail fail2")
                            .addClass("fail2");
                        status.emailOk = false;
                    } else if (response.result) {
                        $(".cert-input").removeClass("success fail fail2")
                            .addClass("success");
                        status.emailOk = true;
                        $(".btn-cert").prop("disabled", true);
                        // 인증 성공 시 페이지 이동 코드
                        $(".success-feedback").html("성공! <a href='changePw'>변경하러 가기</a>");
                        window.alert("인증 성공!");
                    } else {
                        $(".cert-input").removeClass("success fail fail2")
                            .addClass("fail");
                        status.emailOk = false;
                        $(".success-feedback").html(""); // 실패 시 이전에 성공 메시지를 제거
                        window.alert("인증 실패 다시 입력해주세요!");
                    }
                },
            });
        }

        // 추가적으로, 이메일 인증번호 보내기 함수
        function sendCertNumber() {
            var email = $("[name=customerEmail]").val();
            if (email.length == 0) return;
            $(".btn-send").prop("disabled", true);
            $(".btn-send").find(".fa-spinner").show();
            $(".btn-send").find("span").text("이메일로 보내는 중 입니다");

            $.ajax({
                url: "http://localhost:8080/rest/cert/send",
                method: "post",
                data: { certEmail: email },
                success: function () {
                    $(".btn-send").prop("disabled", false);
                    $(".btn-send").find(".fa-spinner").hide();
                    $(".btn-send").find("span").text("인증번호 보내기");

                    window.alert("이메일로 보내드렸어요");
                },
            });
        }
    });
</script>



<form class="findPw-form" action="findPw" method="post" autocomplete="off">
	<div class="container form-border mt-30">
		<div class="center w-400">
		
        <h1>비밀번호 찾기</h1>
    </div>

        <div class="row left">
            <label>이메일</label>
            <input type="text" name="customerEmail" placeholder="test@kh.com" class="form-input underline-input w-100 customer-input">
            <div class="fail-feedback">이메일 형식이 잘못되었습니다</div>
            <div class="fail2-feedback">이미 이 이메일로 아이디를 만드셨습니다</div>
        </div>

        <div class="row flex-container">
            <div class="w-50">
                <button type="button" class="btn btn-send btn-positive" onclick="sendCertNumber()">
                    <i class="fa-solid fa-spinner fa-spin"></i>
                    <span>인증번호 보내기</span>
                </button>
            </div>

            <div class="cert-wrapper right">
			        <input type="text" id="number-check" class="form-input underline-input customer-input cert-input w-50">
			        <button type="button" class="btn btn-cert btn-positive" onclick="checkCertNumber()">확인</button>
			        
			        <div class="fail2-feedback">인증번호를 입력해주세요</div>
			        <div class="fail-feedback">인증번호 잘못입력하셨습니다</div>
			        <div class="success-feedback">성공!</div>
	    		</div>
                
               
        </div>
    </div>
    </form>
    
    
    
    

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>