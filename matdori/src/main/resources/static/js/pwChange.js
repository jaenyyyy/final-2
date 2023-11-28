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

