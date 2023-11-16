$(document).ready(function () {
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
                   	window.alert("인증 성공!");
                } else {
                    $(".cert-input").removeClass("success fail fail2")
                        .addClass("fail");
                    status.emailOk = false;
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
