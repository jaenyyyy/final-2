var status = {
    checkPw: false,

    ok: function () {
        return this.checkPw;
    }
};

$("#changePw-check").blur(function () {
    var changePw = $("[name=changePw]").val();
    var checkPw = $(this).val();

    var isValid = changePw === checkPw;

    $(this).removeClass("success fail fail2");

    if (changePw.length === 0) {
        $(this).addClass("fail2");
        status.checkPw = false;
    } else if (isValid) {
        $(this).addClass("success");
        status.checkPw = true;
    } else {
        $(this).addClass("fail");
        status.checkPw = false;
    }
});

$(window).on("beforeunload", function () {
    return false;
});

$(".password-form").submit(function (e) {
    $(".form-input").blur();

    if (!status.ok()) {
        e.preventDefault();
    } else {
        $(window).off("beforeunload");
    }
});
