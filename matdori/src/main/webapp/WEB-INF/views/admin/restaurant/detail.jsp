<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>



<div class="row" style="margin-top: 2%">
	<div class="col">

		<!-- 이름 -->
		<div class="row justify-content-center">
			<div class="col-md-6 mt-4">
				<h1 class="text-center ">
					<i class="fa-solid fa-circle-check me-2" style="color: #ffb416;"></i>매장
					관리
				</h1>
			</div>
		</div>

		<!-- 매장정보 -->
		<div class="row justify-content-center">
			<input type="hidden" name="resNo" id="resNo"
				value="${restaurantDto.resNo}">
			<div class="card border-warning mb-3 " style="max-width: 50rem;">
				<div
					class="card-header mt-3 d-flex justify-content-between align-items-center">
					<div class="d-flex align-items-center">
						<h4 style="font-weight: bold;" class="me-2">
							<c:choose>
								<c:when test="${restaurantAdminListDto.resBlock == 'N'}">
					                [ ${restaurantAdminListDto.resName} ]
					            </c:when>
								<c:otherwise>
									<span style="color: red;">
										
										[ <i class="fa-solid fa-ban" style="color: #ff0000;"></i> ${restaurantAdminListDto.resName} ]
									</span>
								</c:otherwise>
							</c:choose>
						</h4>

						<h4>
							<c:choose>
								<c:when test="${restaurantAdminListDto.resJudgeStatus eq '심사대기'}">
									<span class="badge rounded-pill bg-secondary">${restaurantAdminListDto.resJudgeStatus}</span>
								</c:when>
								<c:when
									test="${restaurantAdminListDto.resJudgeStatus eq '심사승인'}">
									<span class="badge rounded-pill bg-warning">${restaurantAdminListDto.resJudgeStatus}</span>
								</c:when>
								<c:when
									test="${restaurantAdminListDto.resJudgeStatus eq '심사거절'}">
									<span class="badge rounded-pill bg-primary">${restaurantAdminListDto.resJudgeStatus}</span>
								</c:when>
							</c:choose>
						</h4>
					</div>
					<div class="d-flex justify-content-between">
						<div>
							<button type="button"
								class="open-modal-judge btn btn-secondary me-2">
								<i class="fa-solid fa-file-lines me-2" style="color: #ffffff;"></i>심사하기
							</button>
						</div>
						<c:choose>
							<c:when test="${restaurantAdminListDto.resBlock == 'N'}">
								<button type="button" class="open-modal-block btn btn-secondary">
									<i class="fa-solid fa-lock me-2" style="color: #ffffff;"></i>차단하기
								</button>
							</c:when>

							<c:otherwise>
								<button type="button"
									class="open-modal-cancel btn btn-secondary">
									<i class="fa-solid fa-unlock me-2" style="color: #ffffff;"></i>차단해제
								</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="card-body">
					<label class="form-label mt-4" for="readOnlyInput">사업자 아이디</label>
					<input class="form-control" id="readOnlyInput" type="text"value="${restaurantDto.busId}" disabled> 
					
					<label class="form-label mt-4" for="readOnlyInput">사업자 번호</label> 
					<input class="form-control" id="readOnlyInput" type="text" value="${restaurantDto.resRegNo}" disabled> <label
						class="form-label mt-4" for="readOnlyInput">매장 연락처</label> 
						<input class="form-control" id="readOnlyInput" type="text" value="${restaurantDto.resTel}" disabled> 
						<label class="form-label mt-4" for="readOnlyInput">매장 주소</label> 
						<input class="form-control" id="readOnlyInput" type="text"
							value="[ ${restaurantDto.resPost} ] ${restaurantDto.resAddr1} ${restaurantDto.resAddr2}" disabled> 
						<label class="form-label mt-4" for="readOnlyInput">신청일</label> 
						<input class="form-control mb-4" id="readOnlyInput" type="text" value="${restaurantDto.resRegDate}"
						disabled>
				</div>
			</div>
		</div>



		<!-- 심사 내역 -->
		<c:if test="${restaurantAdminListDto.resJudgeStatus eq '심사승인'
					|| restaurantAdminListDto.resJudgeStatus eq '심사거절'}">
			<div class="row justify-content-center">
				<div class="card border-warning mb-3 " style="max-width: 50rem;">
					<div
						class="card-header mt-3 d-flex justify-content-between align-items-center">
						<div class="d-flex align-items-center">
							<h5 style="font-weight: bold;" class="me-2">심사 내역</h5>
						</div>

					</div>
					<div class="card-body">
						<div>
							<label class="form-label mt-4" for="readOnlyInput">심사 상태</label>
							<input class="form-control" id="readOnlyInput" type="text"
								value="${restaurantAdminListDto.resJudgeStatus}" disabled>
						</div>

						<div>
							<label class="form-label mt-4" for="readOnlyInput">심사 일자</label>
							<input class="form-control" id="readOnlyInput" type="text"
								value="${restaurantAdminListDto.resJudgeDate}" disabled>
						</div>

						<div>
							<label class="form-label mt-4" for="readOnlyInput">심사 내용</label>
							<input class="form-control" id="readOnlyInput" type="text"
								value="${restaurantAdminListDto.resJudgeComment}" disabled>
						</div>


					</div>
				</div>
			</div>
		</c:if>






		<!-- 차단 된 회원일 시 차단 사유 보이는 칸 -->
		<c:if test="${restaurantAdminListDto.resBlock == 'Y'}">
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
							<label class="form-label mt-4" for="readOnlyInput">차단 일자</label>
							<input class="form-control" id="readOnlyInput" type="text"
								value="${restaurantAdminListDto.resBlockTime}" disabled>
						</div>

						<div>
							<label class="form-label mt-4" for="readOnlyInput">차단 사유</label>
							<input class="form-control" id="readOnlyInput" type="text"
								value="${restaurantAdminListDto.resBlockComment}" disabled>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		

		
		<!-- 목록 버튼 -->
		<div class="row justify-content-center">
			<div class="col-md-6 mt-4 text-center">
				<a class="btn btn-secondary" href="list">목록으로</a>
			</div>
		</div>





		<!-- 매장 차단 모달-->
		<div class="modal fade" id="blockModal" tabindex="-1"
			data-bs-backdrop="static">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5">매장 차단 사유</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<input type="text" class="form-control" id="resBlockComment">
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
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<h5>해당 매장을 차단해제 하시겠습니까?</h5>
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


		<!-- 매장 심사 모달-->
		<div class="modal fade" id="judgeModal" tabindex="-1" data-bs-backdrop="static">
			<div>
			    <input type="hidden" id="resJudgeNo" value="${restaurantJudgeDto.resJudgeNo}">
			</div>
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5">매장 심사</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="text-center">
							<label> 
							    <input type="radio" class="form-radio" name="resJudgeStatus" value="심사승인">심사승인
							</label> 
							<label> 
							    <input type="radio" class="form-radio ms-4" name="resJudgeStatus" value="심사거절">심사거절
							</label>
						</div>
						<div>
							심사 내용<input type="text" class="form-control"
								name="resJudgeComment">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">취소</button>
						<button type="button" class="btn btn-warning"
							onclick="sendJudgeRequest()">완료</button>
					</div>
				</div>
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
		var resNo = document.getElementById("resNo").value; // resNo 값을 가져옴
		var resBlockComment = $('#resBlockComment').val(); // 모달 내의 입력값

		var data = {
			resNo : resNo,
			resBlockComment : resBlockComment
		};

		$.ajax({
			url : '/rest/admin/restaurant/block', // 요청을 보낼 URL
			method : 'POST',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(data), // 데이터 전송
			success : function(response) {
				console.log(response);
				$('#blockModal').modal('hide'); // 성공 시 모달 닫기
				location.reload();
			},
			error : function(xhr, status, error) {
				console.error('차단 요청에 실패했습니다.');
			}
		});
	}

	//차단 해제
	$(".open-modal-cancel").click(function() {
		$("#cancelModal").modal('show');
	});
	// 차단 해제 요청 보내기
	function sendCancelRequest() {
		var resNo = document.getElementById("resNo").value; // resNo 값을 가져옴

		var data = {
			resNo : resNo
		};

		$.ajax({
			url : '/rest/admin/restaurant/cancel', // 차단 해제 요청을 보낼 URL
			method : 'POST',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(data), // 데이터 전송
			success : function(response) {
				console.log('차단 해제 요청 성공');
				$('#cancelModal').modal('hide'); // 성공 시 모달 닫기
				location.reload();
			},
			error : function(xhr, status, error) {
				console.error('차단 해제 요청 실패');
			}
		});
	}

	//심사승인
	$(".open-modal-judge").click(function() {
		$("#judgeModal").modal('show');
	});
	// 심사 요청 보내기
	function sendJudgeRequest() {
		var resJudgeNo = document.getElementById("resJudgeNo").value; // resJudgeNo 값을 가져옴
		var resJudgeStatus = $("input[name='resJudgeStatus']:checked").val();
		var resJudgeComment = $('input[name="resJudgeComment"]').val();
		
		var data = {
			resJudgeNo : resJudgeNo,
			resJudgeStatus : resJudgeStatus,
			resJudgeComment : resJudgeComment
		};
		
		 console.log(data);

		$.ajax({
			url : '/rest/admin/restaurant/judge', // 심사 
			method : 'POST',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(data), // 데이터 전송
			success : function(response) {
				console.log('심사 요청 성공');
				$('#judgeModal').modal('hide'); // 성공 시 모달 닫기
				location.reload();
			},
			error : function(xhr, status, error) {
				console.error('심사 요청 실패');
			}
		});
	}
</script>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>