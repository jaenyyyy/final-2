<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script>
	$(document).ready(function() {
		$('#button-addon2').click(function() {
			sendHashTag();
		});

		function sendHashTag() {
			var hashComment = $('#hashComment').val();
			var hashNo = $('#hashNo').val(); // hashNo 값을 얻어옴

			// AJAX를 사용하여 서버에 해시태그 추가 요청 보내기
			$.ajax({
				url : '/rest/admin/hashtag/insert',
				method : 'POST',
				contentType : 'application/json',
				data : JSON.stringify({
					"hashComment" : hashComment,
					"hashNo" : hashNo
				}),
				success : function(response) {
					console.log('해시태그 추가 성공:', response);
					location.reload();
				},
				error : function(xhr, status, error) {
					console.error('해시태그 추가 오류:', error);
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
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="hashComment"
						placeholder="(#)제외 키워드만 입력해주세요" aria-label="Recipient's username"
						aria-describedby="button-addon2"> 
					<button class="btn btn-warning" type="button" id="button-addon2">신규등록</button>

				</div>
			</div>
		</div>



		<!-- 전체 해시태그 목록 -->
		<div class="row border border-warning p-4"
			style="height: 400px; overflow-y: scroll; max-width: 600px;">
			<div class="row justify-content-center">
				<c:forEach items="${hashtagList}" var="hashtag">
					<div class="col-6 mb-3">
						<button class="btn btn-secondary">
							<h5># ${hashtag.hashComment}</h5>
						</button>
					</div>
				</c:forEach>
			</div>
		</div>

	</div>
</div>




<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>