<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script>
window.contextPath = "${pageContext.request.contextPath}";
	$(function() {
		$(".hash-insert-form").submit(function(e) {
			e.preventDefault();
			var commentData = $(this).serialize();

			$.ajax({
				url : window.contextPath+"/rest/admin/hashtag/insert",
				method : "post",
				data : commentData,
				success : function(response) {
					$("[name=hashComment]").val("");
					location.reload();
				}
			});
		});

		// 해시태그 삭제 모달 띄우기
		$(".delete-btn").click(function() {
			var hashNo = $(this).data("hashno"); 
			$("#cancelModal").modal('show');

			$("#deleteConfirm").click(function() {
				sendCancelRequest(hashNo);
			});
		});

		// 차단 해제 요청 보내기
		function sendCancelRequest(hashNo) {
			// 삭제 요청 보내기
			$.ajax({
				url : window.contextPath+"/rest/admin/hashtag/delete",
				method : "post",
				data : {
					hashNo : hashNo
				},
				success : function(response) {
					location.reload(); // 삭제 후 페이지 새로고침
				}
			});
		}
	});
</script>



<div class="container" style="margin-top: 2%;">
	<div class="row justify-content-center">

		<!-- 이름 -->
		<div class="row">
			<div class="col-md-6 mt-4 mb-4 text-end">
				<h1>
					<i class="fa-solid fa-hashtag" style="color: #ffb416;"></i> 해시태그 관리
				</h1>
			</div>
		</div>

		<!-- 신규 버튼 -->
		<form class="hash-insert-form">
			<div class="row justify-content-center">
				<div class="col-md-6">
					<div class="input-group mb-3">
						<input type="text" class="form-control" name="hashComment"
							placeholder="(#)제외 키워드만 입력해주세요" aria-label="Recipient's username"
							aria-describedby="button-addon2">
						<button class="btn btn-warning" id="button-addon2">신규등록</button>
					</div>
				</div>
			</div>
		</form>



		<!-- 전체 해시태그 목록 -->
		<div class="row border border-warning p-4"
			style="height: 400px; overflow-y: scroll; max-width: 600px;">
			<div class="row justify-content-center" id="hashtagContainer">
				<!-- 해시태그 목록이 여기에 추가됩니다 -->
				<c:forEach items="${hashtagList}" var="hashtag">
					<div class="col-6 mb-3">
						<button class="btn btn-secondary delete-btn"
							data-hashno="${hashtag.hashNo}">
							<h5># ${hashtag.hashComment}</h5>
						</button>
					</div>
				</c:forEach>
			</div>
		</div>

	</div>
</div>



<!-- 해시태그 삭제 모달-->
<div class="modal fade" id="cancelModal" tabindex="-1"
	data-bs-backdrop="static">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<h5>해당 해시태그를 삭제하시겠습니까?</h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">취소</button>
				<button type="button" class="btn btn-warning" id="deleteConfirm">삭제</button>
			</div>
		</div>
	</div>
</div>




<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>