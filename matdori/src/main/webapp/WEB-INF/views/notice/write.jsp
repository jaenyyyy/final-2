<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
 
 
<form action="write" method="post" autocomplete="off">
	<div class="container">
	    <div class="row justify-content-center" style="margin-top:2%;">
	    
	        <div class="col-md-8">
	        
                <div class="mt-4">
                    <h1 class="text-start">
                        <i class="fa-solid fa-pen-to-square" style="color: #ffb416;"></i>
                        공지사항 작성
                    </h1>
                </div>
                
                <div class="mt-4">
                    <input type="text" name="noticeTitle" class="form-control" placeholder="제목을 입력해주세요.">
                </div>
                
                <div class="mt-4">
                    <textarea name="noticeContent" rows="10" class="form-control" placeholder="내용을 입력해주세요."></textarea>
                </div>
                
                <div class="text-end mt-4">
                	<a class="btn btn-secondary btn-list" href="${pageContext.request.contextPath}/notice/list">목록</a>
                    <button class="btn btn-warning">작성</button>
                </div>
                
	        </div>
	        
	    </div>
	</div>
</form>


<script type="text/javascript">
window.contextPath = "${pageContext.request.contextPath}";
	function confirmList() {
	    if (confirm("작성을 중단하고 목록으로 이동하시겠습니까?")) {
	        window.location.href = window.contextPath+"/notice/list";
	    }
	}
	
	
	const deleteButton = document.querySelector('.btn-list');
	deleteButton.addEventListener('click', function(e) {
	    e.preventDefault();
	    confirmList();
	});
	

</script>
 <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

