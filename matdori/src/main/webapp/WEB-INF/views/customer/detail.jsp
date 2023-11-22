<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script>
//체크박스가 변경될 때 실행되는 함수
function handleLevelChange() {
    const levels = [];
    const checkboxes = document.querySelectorAll('.form-check-input:checked');

    checkboxes.forEach(checkbox => {
        levels.push(checkbox.value);
    });

    // levels 배열에는 선택된 등급이 들어 있습니다. 이 값을 서버로 보내면 됩니다.
    // 예를 들어, AJAX 요청을 이용하여 서버에 전송할 수 있습니다.
    // 여기서는 간단히 콘솔에 출력하는 것으로 대체하겠습니다.
    console.log('선택된 등급:', levels);

    // 실제로 서버로 전송하는 로직을 구현해야 합니다.
}

// 체크박스의 변경을 감지하는 이벤트 리스너 등록
const checkboxes = document.querySelectorAll('.form-check-input');
checkboxes.forEach(checkbox => {
    checkbox.addEventListener('change', handleLevelChange);
});

</script>

<div class="row" style="margin-top: 2%">
    <div class="col">
        <!-- 이름 -->
        <div class="row justify-content-center">
            <div class="col-md-6 mt-4">
                <h1 class="text-center ">
                    <i class="fa-solid fa-circle-check me-2" style="color: #ffb416;"></i>
                    회원 관리
                </h1>
            </div>
        </div>
    </div>

    <!-- 매장정보 -->
    <div class="row justify-content-center">
        <input type="hidden" name="customerId" id="customerId" value="${customerAdminListDto.customerId}">
        <div class="card border-warning mb-3 " style="max-width: 50rem;">
            <div class="card-header mt-3 d-flex justify-content-between align-items-center">
                <div class="d-flex align-items-center">
                    <h4 style="font-weight: bold;" class="me-2">
                        <c:choose>
                            <c:when test="${customerAdminListDto.customerStatus == 'N'}">
                                [ ${customerAdminListDto.customerName} ]
                            </c:when>
                            <c:otherwise>
                                <span style="color: red;">
                                    [ <i class="fa-solid fa-ban" style="color: #ff0000;"></i> ${customerAdminListDto.customerName} ]
                                </span>
                            </c:otherwise>
                        </c:choose>
                    </h4>
                </div>
                <c:choose>
                    <c:when test="${customerAdminListDto.customerStatus == 'N'}">
                        <button type="button" class="open-modal-block btn btn-secondary">
                            <i class="fa-solid fa-lock me-2" style="color: #ffffff;"></i>차단하기
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="open-modal-cancel btn btn-secondary">
                            <i class="fa-solid fa-unlock me-2" style="color: #ffffff;"></i>차단해제
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="card-body">
            <label class="form-label mt-4" for="readOnlyInput">회원 아이디</label>
            <input class="form-control" id="readOnlyInput1" type="text" value="${customerAdminListDto.customerId}" disabled>

            <label class="form-label mt-4" for="readOnlyInput">회원 연락처</label>
            <input class="form-control" id="readOnlyInput2" type="text" value="${customerAdminListDto.customerContact}" disabled>

            <label class="form-label mt-4" for="readOnlyInput">차단일</label>
            <input class="form-control mb-4" id="readOnlyInput3" type="text" value="${customerAdminListDto.customerBlockTime}" disabled>
        </div>
    </div>
</div>

<!-- 차단 된 회원일 시 차단 사유 보이는 칸 -->
<c:if test="${customerAdminListDto.customerStatus == 'Y'}">
    <div class="row justify-content-center">
        <div class="card border-warning mb-3 " style="max-width: 50rem;">
            <div class="card-header mt-3 d-flex justify-content-between align-items-center">
                <div>
                    <h5 style="font-weight: bold;">차단 내역</h5>
                </div>
            </div>
            <div class="card-body">
                <div>
                    <label class="form-label mt-4" for="readOnlyInput">차단 일자</label>
                    <input class="form-control" id="readOnlyInput4" type="text" value="${customerAdminListDto.customerBlockTime}" disabled>
                </div>
                <div>
                    <label class="form-label mt-4" for="readOnlyInput">차단 사유</label>
                    <input class="form-control" id="readOnlyInput5" type="text" value="${customerAdminListDto.customerBlockComment}" disabled>
                </div>
            </div>
        </div>
    </div>
</c:if>

<!-- 매장 차단 모달-->
<div class="modal fade" id="blockModal" tabindex="-1" data-bs-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">회원 차단 사유</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <input type="text" class="form-control" id="customerBlockComment">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-warning" onclick="sendBlockRequest()">차단</button>
            </div>
        </div>
    </div>
</div>

<!-- 차단 해제 모달-->
<div class="modal fade" id="cancelModal" tabindex="-1" data-bs-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h5>해당 회원을 차단해제 하시겠습니까?</h5>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-warning" onClick="sendCancelRequest()">해제</button>
            </div>
        </div>
    </div>
</div>

<script>
	//매장 차단
	$(".open-modal-block").click(function() {
		$("#blockModal").modal('show');
	});

	// 차단 요청 보내기
	function sendBlockRequest() {
		var customerId = document.getElementById("customerId").value; // resNo 값을 가져옴
		var customerBlockComment = $('#customerBlockComment').val(); // 모달 내의 입력값

		var data = {
			customerId : customerId,
			customerBlockComment : customerBlockComment
		};

		$.ajax({
			url : '/rest/customer/block', // 요청을 보낼 URL
			method : 'POST',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(data), // 데이터 전송
			success : function(response) {
				console.log('차단 요청이 성공했습니다.');
				$('#blockModal').modal('hide'); // 성공 시 모달 닫기
				location.reload();
			},
			error : function(xhr, status, error) {
				console.error('차단 요청에 실패했습니다.');
	<div class="col">
		<!-- 이름 -->
		<div class="row justify-content-center">
			<div class="col-md-6 mt-4">
				<h1 class="text-center ">
					<i class="fa-solid fa-circle-check me-2" style="color: #ffb416;"></i>
					회원 관리
				</h1>
			</div>
		</div>
	</div>
</div>
<!-- 매장정보 -->
<div class="row justify-content-center">
	<input type="hidden" name="customerId" id="customerId"
		value="${customerAdminListDto.customerId}">
	<div class="card border-warning mb-3 " style="max-width: 50rem;">
		<div
			class="card-header mt-3 d-flex justify-content-between align-items-center">
			<div class="d-flex align-items-center">
				<h4 style="font-weight: bold;" class="me-2">
					<c:choose>
						<c:when test="${customerAdminListDto.customerStatus == 'N'}">
                                [ ${customerAdminListDto.customerName} ]
                            </c:when>
						<c:otherwise>
							<span style="color: red;"> [ <i class="fa-solid fa-ban"
								style="color: #ff0000;"></i>
								${customerAdminListDto.customerName} ]
							</span>
						</c:otherwise>
					</c:choose>
				</h4>
			</div>
			<c:choose>
				<c:when test="${customerAdminListDto.customerStatus == 'N'}">
					<button type="button" class="open-modal-block btn btn-secondary">
						<i class="fa-solid fa-lock me-2" style="color: #ffffff;"></i>차단하기
					</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="open-modal-cancel btn btn-secondary">
						<i class="fa-solid fa-unlock me-2" style="color: #ffffff;"></i>차단해제
					</button>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

<div class="row justify-content-center">
		<div class="card border-warning mb-3 " style="max-width: 50rem;">
			<div
				class="card-header mt-3 d-flex justify-content-between align-items-center">
				<div>
		<label class="form-label mt-4" for="readOnlyInput">아이디</label> <input
			class="form-control" id="readOnlyInput1" type="text"
			value="${customerAdminListDto.customerId}" disabled> <label
			class="form-label mt-4" for="readOnlyInput">생년월일</label> <input
			class="form-control" id="readOnlyInput2" type="text"
			value="${customerAdminListDto.customerBirth}" disabled> <label
			class="form-label mt-4" for="readOnlyInput">연락처</label> <input
			class="form-control" id="readOnlyInput3" type="text"
			value="${customerAdminListDto.customerContact}" disabled> <label
			class="form-label mt-4" for="readOnlyInput">이메일</label> <input
			class="form-control" id="readOnlyInput4" type="text"
			value="${customerAdminListDto.customerEmail}" disabled> <label
			class="form-label mt-4" for="readOnlyInput">성별</label> <input
			class="form-control" id="readOnlyInput5" type="text"
			value="${customerAdminListDto.customerGender}" disabled>
			
			
			 <label
			class="form-label mt-4">등급</label>
			
			<br>
			<input
			class="form-control" id="readOnlyInput6" type="text"
			value="${customerAdminListDto.customerLevel}" disabled>
			<br> 

		<div class="form-check">
			<input class="form-check-input" type="radio" name="customerLevel"
				value="나무수저" id="customerLevel1"> <label
				class="form-check-label" for="customerLevel1">나무수저</label>
		</div>


		<div class="form-check">
			<input class="form-check-input" type="radio" name="customerLevel"
				value="은수저" id="customerLevel2"> <label
				class="form-check-label" for="customerLevel2">은수저</label>
		</div>


		<div class="form-check">
			<input class="form-check-input" type="radio" name="customerLevel"
				value="금수저" id="customerLevel3"> <label
				class="form-check-label" for="customerLevel3">금수저</label>
		</div>


		<div class="form-check">
			<input class="form-check-input" type="radio" name="customerLevel"
				value="다이아수저" id="customerLevel4"> <label
				class="form-check-label" for="customerLevel4">다이아수저</label>
		</div>



		<div class="form-check">
			<input class="form-check-input" type="radio" name="customerLevel"
				value="맛도리수저" id="customerLevel5"> <label
				class="form-check-label" for="customerLevel5">맛도리수저</label>
		</div>

		<br>

		<button type="button" class="btn btn-warning"
					onClick="updateCustomerLevelBtn()">변경</button>
					
			<br>
			 <label
			class="form-label mt-4" for="readOnlyInput8">회원 포인트 ${customerAdminListDto.customerPoint} pt</label> 
				<input class="form-control" id="readOnlyInput8" type="number" placeholder="새로운 포인트 입력">
				<button type="button" class="btn btn-secondary" onClick="updateUpdatePointBtn()">포인트 변경</button>
			
			<br>
			
			<label
			class="form-label mt-4" for="readOnlyInput">차단일</label> <input
			class="form-control mb-4" id="readOnlyInput3" type="text"
			value="${customerAdminListDto.customerBlockTime}" disabled>
	</div>
</div>
</div>

</div>


<!-- 차단 된 회원일 시 차단 사유 보이는 칸 -->
<c:if test="${customerAdminListDto.customerStatus == 'Y'}">
	<div class="row justify-content-center">
		<div class="card border-warning mb-3 " style="max-width: 50rem;">
			<div
				class="card-header mt-3 d-flex justify-content-between align-items-center">
				<div>
					<h5 style="font-weight: bold;">차단 내역</h5>
				</div>
			</div>
			<div class="card-body">
				<div>
					<label class="form-label mt-4" for="readOnlyInput">차단 일자</label> <input
						class="form-control" id="readOnlyInput4" type="text"
						value="${customerAdminListDto.customerBlockTime}" disabled>
				</div>
				<div>
					<label class="form-label mt-4" for="readOnlyInput">차단 사유</label> <input
						class="form-control" id="readOnlyInput5" type="text"
						value="${customerAdminListDto.customerBlockComment}" disabled>
				</div>
			</div>
		</div>
	</div>
</c:if>

<!-- 매장 차단 모달-->
<div class="modal fade" id="blockModal" tabindex="-1"
	data-bs-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="exampleModalLabel">회원 차단 사유</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<input type="text" class="form-control" id="customerBlockComment">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">취소</button>
				<button type="button" class="btn btn-warning"
					onclick="sendBlockRequest()">차단</button>
			</div>
		</div>
	</div>
</div>

<!-- 차단 해제 모달-->
<div class="modal fade" id="cancelModal" tabindex="-1"
	data-bs-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<h5>해당 회원을 차단해제 하시겠습니까?</h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">취소</button>
				<button type="button" class="btn btn-warning"
					onClick="sendCancelRequest()">해제</button>
			</div>
		</div>
	</div>
</div>

<script>
//등급 변경 라디오 버튼
function updateCustomerLevelBtn() {
    var customerId = document.getElementById("customerId").value;
    var customerLevel = document.querySelector('input[name="customerLevel"]:checked');

    if (customerLevel !== null) {
        console.log(customerId);
        console.log(customerLevel.value);

        var data = {
            customerId: customerId,
            customerLevel: customerLevel.value
        };

        $.ajax({
            type: "POST",
            url: '/rest/customer/levelEdit',
            data: data,
            success: function(response) {
                console.log("등급 변경이 완료되었습니다.");

                // 변경된 등급 값을 가져와서 input에 반영
                var updatedLevel = document.getElementById('readOnlyInput6');
                updatedLevel.value = customerLevel.value;

                // 변경 버튼의 텍스트를 '변경 완료'로 수정
                var updateBtn = document.querySelector('.btn-warning');
                updateBtn.textContent = '변경 완료';
            },
            error: function(xhr, status, error) {
                console.error("등급 변경에 실패했습니다.");
            }
        });
    } else {
        console.log("등급을 선택하세요.");
    }
};

function updateUpdatePointBtn() {
    var customerId = document.getElementById("customerId").value;
    var customerPointInput = document.getElementById("readOnlyInput8");
    var customerPoint = customerPointInput.value;

    if (customerPoint !== null) {
        console.log(customerId);
        console.log(customerPoint);

        var data = {
            customerId: customerId,
            customerPoint: customerPoint
        };

        $.ajax({
            type: "POST",
            url: '/rest/customer/pointEdit',
            data: data,
            success: function(response) {
                console.log("포인트 변경이 완료되었습니다.");

                // 변경된 포인트 값을 가져와서 input에 반영
                var updatedPoint = document.getElementById('readOnlyInput8');
                updatedPoint.value = customerPoint;

                // 포인트 변경 버튼 텍스트를 '포인트 수정 완료'로 변경
                var updateBtn = document.querySelector('.btn-secondary');
                updateBtn.textContent = "수정 완료";
              
            },
            error: function(xhr, status, error) {
                console.error("포인트 변경에 실패했습니다.");
            }
        });
    } else {
        console.log("포인트를 변경하세요.");
    }
}




//포인트 수정 가능하도록 필드 활성화
function enablePointEdit() {
    var pointField = document.getElementById('readOnlyInput7');
    pointField.removeAttribute('disabled');
}

// 포인트 변경 저장
function savePoint() {
    var customerId = document.getElementById("customerId").value;
    var customerPoint = document.getElementById('customerPoint').value; // 수정된 포인트값

    var data = {
        customerId: customerId,
        customerPoint: customerPoint
    };

    // AJAX를 사용하여 수정된 포인트 서버에 전송
    $.ajax({
        type: "POST",
        url: '/rest/customer/pointEdit',
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(response) {
            console.log("포인트 변경이 완료되었습니다.");
        },
        error: function(xhr, status, error) {
            console.error("포인트 변경에 실패했습니다.");
        }
    });
}



// 매장 차단 모달 열기
$(".open-modal-block").click(function() {
    $("#blockModal").modal('show');
});

// 차단 요청 보내기
function sendBlockRequest() {
    var customerId = document.getElementById("customerId").value;
    var customerBlockComment = $('#customerBlockComment').val();

    var data = {
        customerId: customerId,
        customerBlockComment: customerBlockComment
    };

    $.ajax({
        url: '/rest/customer/block',
        method: 'POST',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data),
        success: function(response) {
            console.log('차단 요청이 성공했습니다.');
            $('#blockModal').modal('hide');
            location.reload();
        },
        error: function(xhr, status, error) {
            console.error('차단 요청에 실패했습니다.');
        }
    });
}

// 차단 해제 모달 열기
$(".open-modal-cancel").click(function() {
    $("#cancelModal").modal('show');
});

// 차단 해제 요청 보내기
function sendCancelRequest() {
    var customerId = document.getElementById("customerId").value;

    var data = {
        customerId: customerId
    };

    $.ajax({
        url: '/rest/customer/cancel',
        method: 'POST',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data),
        success: function(response) {
            console.log('차단 해제 요청 성공');
            $('#cancelModal').modal('hide');
            location.reload();
        },
        error: function(xhr, status, error) {
            console.error('차단 해제 요청 실패');
        }
    });
}

		</script>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>