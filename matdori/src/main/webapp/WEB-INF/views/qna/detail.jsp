<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<div class="row" style="margin-top:2%">
	<div class="col">
		
		<!-- 이름 -->
		<div class="row justify-content-center">
			<div class="col-md-6 mt-4">
		        <h1 class="text-center"> 
		             <i class="fa-regular fa-circle-question" style="color: #ffb416;"></i>
		            <span
					style="font-weight: bold;"> Q & A</span><br><br>
		        </h1>
		    </div>
		</div>
		
		
		<!-- 글 내용 -->
		<div class="row justify-content-center">
			<div class="card border-warning mb-3 " style="max-width: 50rem;">
				<div class="card-header mt-3 d-flex justify-content-between align-items-center">
				    <div>
				        <h4 style="font-weight: bold;"> [${qnaDto.qnaCategory}] ${qnaDto.qnaTitle} </h4>
				        
				    </div>
				</div>
				<div class="card-body">
					${qnaDto.qnaAnswer}
				</div>
			</div>
		</div>
		
			<%-- 글쓰기는 로그인 상태인 경우에만 출력 --%>
	<c:if test="${sessionScope.level == '관리자'}">
	

		
		<!-- 버튼 -->
		<div class="col text-center">
	   		<a class="btn btn-warning btn-edit" href="edit?qnaNo=${qnaDto.qnaNo}">
	   		수정
	   		</a>
	   		<a class="btn btn-warning btn-delete" href="delete?qnaNo=${qnaDto.qnaNo}">
	   		삭제
	   		</a>
	   <a class="btn btn-secondary" href="list">
	   		목록
	   		</a>
	   </div>
		
		</c:if>
		
		<c:if test="${sessionScope.level ne '관리자'}">
    <div class="col text-center">
        <a class="btn btn-secondary" href="list">
            목록
        </a>
    </div>
</c:if>
	
	</div>
</div>


    
<script type="text/javascript">
	function confirmDelete() {
	    if (confirm("정말 삭제하시겠습니까?")) {
	        window.location.href = "delete?qnaNo=${qnaDto.qnaNo}";
	    }
	}
	
	function confirmEdit() {
	    if (confirm("이 글을 수정하시겠습니까?")) {
	        window.location.href = "edit?qnaNo=${qnaDto.qnaNo}";
	    }
	}
	
	// 삭제 버튼 클릭 시 확인창 띄우기
	const deleteButton = document.querySelector('.btn-delete');
	deleteButton.addEventListener('click', function(e) {
	    e.preventDefault();
	    confirmDelete();
	});
	
	// 수정 버튼 클릭 시 확인창 띄우기
	const editButton = document.querySelector('.btn-edit');
	editButton.addEventListener('click', function(e) {
	    e.preventDefault();
	    confirmEdit();
	});
</script>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>