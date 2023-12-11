<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
 
 
<form action="edit" method="post">
	<input type="hidden" name="qnaNo" value="${qnaDto.qnaNo}">
	<div class="container">
		<div class="row justify-content-center" style="margin-top:2%;">
			
			<div class="col-md-8">
			
				<div class="mt-4">
                    <h1 class="text-start">
                        <i class="fa-solid fa-pen-to-square" style="color: #ffb416;"></i>
                        Q & A 수정
                    </h1>
                </div>
                
                <div class="mt-4">
					<c:choose>
						<c:when test="${qnaDto.qnaCategory == '기타'}">
							<select name="qnaCategory" class="form-select">
								<option value="이용안내" >이용안내</option>
							    <option value="결제안내">결제안내</option>
							    <option value="취소안내">취소안내</option>
							    <option value="예약안내">예약안내</option>
							    <option value="기타" selected>기타</option>
							</select>
						</c:when>
						<c:when test="${qnaDto.qnaCategory == '결제안내'}">
							<select name="qnaCategory" class="form-select">
								<option value="이용안내">이용안내</option>
							    <option value="결제안내" selected>결제안내</option>
							    <option value="취소안내">취소안내</option>
							    <option value="예약안내">예약안내</option>
							    <option value="기타">기타</option>
							</select>
						</c:when>
						<c:when test="${qnaDto.qnaCategory == '취소안내'}">
							<select name="qnaCategory" class="form-select">
								<option value="이용안내">이용안내</option>
							    <option value="결제안내">결제안내</option>
							    <option value="취소안내" selected>취소안내</option>
							    <option value="예약안내">예약안내</option>
							    <option value="기타">기타</option>
							</select>
						</c:when>
						<c:when test="${qnaDto.qnaCategory == '예약안내'}">
							<select name="qnaCategory" class="form-select">
								<option value="이용안내">이용안내</option>
							    <option value="결제안내">결제안내</option>
							    <option value="취소안내">취소안내</option>
							    <option value="예약안내" selected>예약안내</option>
							    <option value="기타">기타</option>
							</select>
						</c:when>
						<c:otherwise>
							<select name="qnaCategory" class="form-select">
								<option value="이용안내">이용안내</option>
							    <option value="결제안내">결제안내</option>
							    <option value="취소안내">취소안내</option>
							    <option value="예약안내">예약안내</option>
							    <option value="기타">기타</option>
							</select>
						</c:otherwise>
					</c:choose>
				</div>
				
				
				<div class="mt-4">
                	<input class="form-control" type="text" name="qnaTitle" value="${qnaDto.qnaTitle}">
                </div>
              
              	<div class="mt-4">
       				<textarea class="form-control" rows="10" name="qnaAnswer">${qnaDto.qnaAnswer}</textarea>
              	</div>

				<div class="text-end mt-4">
                	<a class="btn btn-secondary btn-list" href="${pageContext.request.contextPath}/qna/list">목록</a>
                    <button class="btn btn-warning">수정</button>
                </div>
		
 			</div>
 		</div>
 	</div>
 </form>
 
  <script type="text/javascript">
  window.contextPath = "${pageContext.request.contextPath}";
	function confirmList() {
	    if (confirm("수정을 중단하고 목록으로 이동하시겠습니까?")) {
	        window.location.href = window.contextPath+"/qna/list";
	    }
	}
	
	
	const deleteButton = document.querySelector('.btn-list');
	deleteButton.addEventListener('click', function(e) {
	    e.preventDefault();
	    confirmList();
	});
	

</script>
 
 
 
 
 
 <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>