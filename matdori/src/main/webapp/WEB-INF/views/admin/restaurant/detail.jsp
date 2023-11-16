<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
function toggleLabel() {
    var switchCheckbox = document.getElementById("flexSwitchCheckDefault");
    var switchLabel = document.getElementById("switchLabel");

    if (switchCheckbox.checked) {
        switchLabel.innerText = "차단된 회원";
        if (confirm("정말 차단하시겠습니까?")) {
            var blockComment = prompt("차단 사유를 입력해주세요.");
            if (blockComment !== null) {
                // AJAX를 사용하여 서버로 데이터 전송
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "/admin/restaurant/block", true); //여기가 잘못됐나?
                xhr.setRequestHeader("Content-Type", "application/json");  //이게 뭔지 모르겠음 이거 뭐지 

                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            // 서버에서 차단 처리 성공한 경우
                            switchCheckbox.checked = true; // 체크된 상태로 변경
                            switchLabel.innerText = "차단된 회원"; // 라벨 변경
                        } else {
                            // 차단 처리 취소한 경우
                            switchCheckbox.checked = false; // 체크 안된 상태로 변경
                            switchLabel.innerText = "차단하기"; // 라벨 변경
                        }
                    }
                };

                // restaurantBlockDto의 resBlockComment 값을 전송하기 위해 객체 생성
                var data = {
                    resBlockComment: blockComment // 객체에 resBlockComment 추가  //이거도 dto 를 어디꺼 쓰는지 어케 알아?
                };

                xhr.send(JSON.stringify(data)); // JSON 문자열로 변환하여 전송
            } else {
                switchCheckbox.checked = false; // 체크 안된 상태로 변경
                switchLabel.innerText = "차단하기"; // 라벨 변경
            }
        } else {
            switchCheckbox.checked = false; // 체크 안된 상태로 변경
            switchLabel.innerText = "차단하기"; // 라벨 변경
        }
    } else {
        switchLabel.innerText = "차단하기";
    }
}

</script>


<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="row" style="margin-top:2%">
	<div class="col">
	
		<!-- 이름 -->
		<div class="row justify-content-center">
			<div class="col-md-6 mt-4">
		        <h1 class="text-center"> 
		            <i class="fa-solid fa-circle-check" style="color: #ffb416;"></i>매장 관리
		        </h1>
		    </div>
		</div>
		
		<!-- 매장정보 -->
		<div class="row justify-content-center">
			<div class="card border-warning mb-3 " style="max-width: 50rem;">
				<div class="card-header mt-3 d-flex justify-content-between align-items-center">
				    <div>
				        <h4 style="font-weight: bold;">[${restaurantDto.resName}]
				        <c:choose>
						    <c:when test="${restaurantAdminListDto.resJudgeStatus eq '심사중'}">
						        <span class="badge rounded-pill bg-secondary">${restaurantAdminListDto.resJudgeStatus}</span>
						    </c:when>
						    <c:when test="${restaurantAdminListDto.resJudgeStatus eq '심사승인'}">
						        <span class="badge rounded-pill bg-warning">${restaurantAdminListDto.resJudgeStatus}</span>
						    </c:when>
						    <c:when test="${restaurantAdminListDto.resJudgeStatus eq '심사거절'}">
						        <span class="badge rounded-pill bg-primary">${restaurantAdminListDto.resJudgeStatus}</span>
						    </c:when>
						</c:choose>
				        </h4>
				    </div>
				    <div class="form-check form-switch">
				        <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" onclick="toggleLabel()">
				        <label class="form-check-label" id="switchLabel" for="flexSwitchCheckDefault">차단하기</label>
				    </div>
				</div>
				<div class="card-body">
					<label class="form-label mt-4" for="readOnlyInput">사업자 아이디</label>
    				<input class="form-control" id="readOnlyInput" type="text" value="${restaurantDto.busId}" disabled>
    				
    				<label class="form-label mt-4" for="readOnlyInput">사업자 번호</label>
    				<input class="form-control" id="readOnlyInput" type="text" value="${restaurantDto.resRegNo}" disabled>
    				
    				<label class="form-label mt-4" for="readOnlyInput">매장 연락처</label>
    				<input class="form-control" id="readOnlyInput" type="text" value="${restaurantDto.resTel}" disabled>
    				
    				<label class="form-label mt-4" for="readOnlyInput">매장 주소</label>
    				<input class="form-control" id="readOnlyInput" type="text" 
    						value="[ ${restaurantDto.resPost} ] ${restaurantDto.resAddr1} ${restaurantDto.resAddr2}" disabled>
    						
					<label class="form-label mt-4" for="readOnlyInput">신청일</label>
    				<input class="form-control mb-4" id="readOnlyInput" type="text" value="${restaurantDto.resRegDate}" disabled>
				</div>
			</div>
		</div>
		
		<!-- 심사 -->
		

		
	</div>
</div>





<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>