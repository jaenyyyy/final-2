<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script>
$(function(){

    var status={
        
        changePw:false,
        checkPw:false,
 
        ok:function(){
            return this.changePw && this.checkPw;
  
        }
    };

    $("[name=changePw]").blur(function(){
        var regex=/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$])[A-Za-z0-9!@#$]{8,20}$/;
        var originPw=$("[name=originPw]").val();
        var changePw=$(this).val();
        
        var isValid=regex.test(changePw);
        $(this).removeClass("success fail fail2");
 
       
        if(!isValid){
            $(this).addClass("fail");
            status.changePw=false;
        }
        else if(originPw==changePw){
            $(this).addClass("fail2");
            status.changePw=false;
        }
        else {
            $(this).addClass("success");
            status.changePw=true;
        }

    });

    $("#changePw-check").blur(function(){
        var changePw=$("[name=changePw]").val();
        var checkPw=$(this).val();

        var isValid=changePw==checkPw;

        $(this).removeClass("success fail fail2");
        
        if(changePw.length==0){
            $(this).addClass("fail2");
            status.checkPw=false;
        }
        else if(isValid){
            $(this).addClass("success");
            status.checkPw=true;
        }
        else{
            $(this).addClass("fail");
            status.checkPw=false;
        }
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

</script>

<style>
.container {
	max-width: 500px; /* 변경 가능한 폭 설정 */
	margin: 0 auto; /* 가운데 정렬 */
}

.tag-none {
	text-decoration: none
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

<form action="changePw" method="post" autocomplete="off">
<br><br><br>

	<div class="container justify-content-center"
		style="margin-bottom: 20%;">


		
    <div class="row text-center mt-5">
        <h1>
            <i class="fa-solid fa-circle-user" style="color: #ffb416;"></i>
            <span style="font-weight: bold;">Change Pw</span>
        </h1>
    </div>
    <br>

    <div class="text-center">
        <span style="font-weight: bold;">
            안전한 비밀번호로 내 정보를 보호하세요 <br>
            다른 아이디/사이트에서 사용한 적 없는 비밀번호<br>
            이전에 사용한 적 없는 비밀번호가 안전합니다.
        </span>
        <br><br>
        <div class="row line"></div>
    </div>


		<div class="row mt-4">
			<input class="form-control" type="password" name="originPw"
				placeholder="현재 비밀번호">
		</div>

		<div class="row mt-4">
			<input class="form-control" type="password" name="changePw"
				placeholder="새 비밀번호">
			<div class="success-feedback">올바른 비밀번호 형식입니다</div>
			<div class="fail-feedback">잘못된 비밀번호 형식입니다</div>
			<div class="fail2-feedback">현재 비밀번호와 일치합니다</div>
		</div>

		<div class="row mt-4">
			<input class="form-control" type="password" id="changePw-check"
				placeholder="새 비밀번호 확인">
			<div class="success-feedback">비밀번호가 일치합니다</div>
			<div class="fail-feedback">바꿀 비밀번호와 일치하지 않습니다</div>
			<div class="fail2-feedback">바꿀 비밀번호를 먼저 작성하세요</div>
		</div>

		<br>
		<div class="row line"></div>
		<br>


		<div class="row mv-30 mt-4">
			<button type="submit" class="btn btn-warning text-black">
				비밀번호 변경</button>
		</div>
		<div class="row mt-4">	
			<a class="btn btn-outline-warning" href="mypage">취소</a>
		</div>
	</div>

<c:if test="${param.error !=null }">
	<div class="row important">
		<h3>기존 비밀번호가 일치하지 않습니다</h3>
	</div>
</c:if>


</form>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>