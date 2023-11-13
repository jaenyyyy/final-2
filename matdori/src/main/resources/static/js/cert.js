$(function () {

    $("[name=join]").click(function () {

        if ($(".form-input.cert-input").val().length === 0) {

            $(".cert-input").addClass("fail2")

        }
    });

    // 이메일 인증번호 보내기
    $(".btn-send").find(".fa-spinner").hide();

    // 인증번호 보내기 버튼 클릭 시
    $(".btn-send").click(function () {
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

                // 인증 성공 시 step1을 숨기고 step2를 표시
                $("#step1").hide();
                $("#step2").show();
            },
            error: function () {
                alert("서버와의 통신이 원활하지 않습니다");
            },
        });
    });

    // 확인 버튼 클릭 시 이메일과 인증번호를 서버로 전달하여 검사
    $(".btn-cert").click(function () {
        var email = $("[name=customerEmail]").val();
        var number = $(".cert-input").val();

        $.ajax({
            url: "http://localhost:8080/rest/cert/check",
            method: "post",
            data: {
                certEmail: email,
                certNumber: number
            },
            success: function (response) {
                //console.log(response);
                if (response.result) {//인증 성공
                    $(".cert-input").removeClass("success fail")
                        .addClass("success");
                    $(".btn-cert").prop("disabled", true);
                    //상태객체에 상태 저장하는 코드

                    // 인증 성공 시 step1을 숨기고 step2를 표시
                    $("#step1").hide();
                    $("#step2").show();
                }
                else {
                    $(".cert-input").removeClass("success fail")
                        .addClass("fail");
                    //상태객체에 상태 저장하는 코드
                }
            },
        });
    });
});
